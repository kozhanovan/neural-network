package kan.nn.training;

import java.util.List;

import kan.nn.data.FeedForwardNetwork;
import kan.nn.data.LayerWeights;
import kan.nn.data.Matrix;
import kan.nn.data.TrainingSample;
import kan.nn.func.IActivationFunction;

/**
 * Basic backpropagation training.
 */
public class BasicTraining {

    /**
     * Network to train.
     */
    private final FeedForwardNetwork network;

    /**
     * Learning rate.
     */
    private final double             learningRate;

    /**
     * Momentum.
     */
    private final double             momentum;

    /**
     * Constructor.
     *
     * @param network
     *            network to train
     * @param learningRate
     *            learning rate
     * @param momentum
     *            momentum
     */
    public BasicTraining( final FeedForwardNetwork network,
            final double learningRate, final double momentum ) {
        this.network = network;
        this.learningRate = learningRate;
        this.momentum = momentum;
    }

    /**
     * Executes one training iteration using provided sample.
     *
     * @param sample
     *            sample to use
     */
    public void iteration( final TrainingSample sample ) {
        this.network.getOutput( sample.getInput() );
        List<LayerWeights> layers = this.network.getLayers();
        int numOfLayers = layers.size();
        double[] prevNodeDeltas = null;
        Matrix[] weightUpdates = new Matrix[numOfLayers];
        Matrix[] weightUpdatesPrevious = null;
        LayerWeights next = null;

        for ( int l = numOfLayers - 1; l > 0; l-- ) {
            LayerWeights current = layers.get( l );
            LayerWeights previous = layers.get( l - 1 );
            IActivationFunction function = current.getLayer().getActivationFunction();

            // Define node deltas for current layer
            double[] nodeDeltas = new double[current.getLayer().getNeuronCount()];

            if ( null == next ) {
                for ( int nd = 0; nd < nodeDeltas.length; nd++ ) {
                    double actual = current.getLayer().getValue( nd );

                    int previousNeuronCount = previous.getLayer().getNeuronCount();
                    Matrix weights = current.getWeights();
                    double sum = 0;
                    for ( int p = 0; p < previousNeuronCount; p++ ) {
                        sum += previous.getLayer().getValue( p )
                               * weights.get( p, nd );
                    }

                    nodeDeltas[nd] = -(actual - sample.getOutput()[nd])
                                     * function.derivative( sum );
                }
            } else {
                Matrix weights = next.getWeights();
                int colNum = weights.getColNum();

                for ( int nd = 0; nd < nodeDeltas.length; nd++ ) {
                    double actual = 0;
                    int previousNeuronCount = previous.getLayer().getNeuronCount();
                    Matrix actualWeights = current.getWeights();

                    for ( int p = 0; p < previousNeuronCount; p++ ) {
                        actual += previous.getLayer().getValue( p )
                                  * actualWeights.get( p, nd );
                    }

                    double sum = 0d;

                    for ( int c = 0; c < colNum; c++ ) {
                        sum += weights.get( nd, c ) * prevNodeDeltas[c];
                    }

                    nodeDeltas[nd] = function.derivative( actual ) * sum;
                }
            }

            next = current;

            // Define weights update for current layer
            Matrix weights = current.getWeights();
            int rowNum = weights.getRowNum();
            int colNum = weights.getColNum();
            Matrix deltaWeights = new Matrix( rowNum, colNum );

            for ( int r = 0; r < rowNum; r++ ) {
                for ( int c = 0; c < colNum; c++ ) {
                    double d = nodeDeltas[c] * previous.getLayer().getValue( r )
                               * this.learningRate;

                    if ( weightUpdatesPrevious != null ) {
                        d += this.momentum
                             * weightUpdatesPrevious[l].get( r, c );
                    }

                    deltaWeights.set( r, c, d );
                }
            }

            this.network.updateWeightsForLayer( current.getLayer(),
                                                deltaWeights );
            weightUpdates[l] = deltaWeights;

            // Store current node deltas for next step
            prevNodeDeltas = nodeDeltas;
        }

        weightUpdatesPrevious = weightUpdates;

        // for ( int l = 1; l < numOfLayers; l++ ) {
        // LayerWeights current = layers.get( l );
        // this.network.updateWeightsForLayer( current.getLayer(),
        // weightUpdates[l] );
        // }
    }
}
