package kan.nn.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Neural network.
 */
public class FeedForwardNetwork implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long        serialVersionUID = 1L;

    /**
     * Layers list.
     */
    private final List<LayerWeights> layers           = new ArrayList<>();

    /**
     * Adds layer to this network.
     *
     * @param layer
     *            layer to add
     * @return this
     */
    public FeedForwardNetwork addLayer( final Layer layer ) {
        Layer previousLayer = null;

        if ( this.layers.size() > 0 ) {
            previousLayer = this.layers.get( this.layers.size() - 1 ).getLayer();
        }

        this.layers.add( new LayerWeights( layer, previousLayer ) );
        return this;
    }

    /**
     * Return list of layers.
     *
     * @return list of layers
     */
    public List<LayerWeights> getLayers() {
        return this.layers;
    }

    /**
     * Returns network output for provided input.
     *
     * @param input
     *            input
     * @return output
     */
    public double[] getOutput( final double[] input ) {
        int layersNumber = this.layers.size();
        this.layers.get( 0 ).getLayer().setAll( input );

        for ( int i = 1; i < layersNumber; i++ ) {
            Layer current = this.layers.get( i ).getLayer();
            Layer previous = this.layers.get( i - 1 ).getLayer();
            int currentNeuronCount = current.getNeuronCount();
            int previousNeuronCount = previous.getNeuronCount();
            Matrix weights = this.layers.get( i ).getWeights();

            for ( int c = 0; c < currentNeuronCount; c++ ) {
                double sum = 0;

                for ( int p = 0; p < previousNeuronCount; p++ ) {
                    sum += previous.getValue( p ) * weights.get( p, c );
                }

                current.setValue( c,
                                  current.getActivationFunction().value( sum ) );
            }
        }

        return this.layers.get( this.layers.size() - 1 ).getLayer().getValues();
    }

    /**
     * Sets weights for provided layer.
     *
     * @param layer
     *            layer
     * @param weights
     *            weights
     */
    public void setWeightsForLayer( final Layer layer, final Matrix weights ) {
        LayerWeights lw = this.getLayerWeights( layer );
        lw.setWeights( weights );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for ( LayerWeights lw : this.layers ) {
            sb.append( lw.getWeights() );
            sb.append( "-------\n" );
        }

        return sb.toString();
    }

    /**
     * Updates weights for specified layer using deltas provided.
     *
     * @param layer
     *            layer
     * @param update
     *            weights deltas
     */
    public void updateWeightsForLayer( final Layer layer, final Matrix update ) {
        int rowNum = update.getRowNum();
        int colNum = update.getColNum();
        Matrix weights = this.getLayerWeights( layer ).getWeights();

        for ( int row = 0; row < rowNum; row++ ) {
            for ( int col = 0; col < colNum; col++ ) {
                weights.set( row, col,
                             weights.get( row, col ) + update.get( row, col ) );
            }
        }
    }

    /**
     * Returns {@link LayerWeights} for provided layer.
     *
     * @param layer
     *            layer
     * @return {@link LayerWeights} instance
     */
    private LayerWeights getLayerWeights( final Layer layer ) {
        for ( LayerWeights lw : this.layers ) {
            if ( lw.getLayer().equals( layer ) ) {
                return lw;
            }
        }
        return null;
    }
}
