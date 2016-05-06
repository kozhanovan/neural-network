package kan.nn.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Contains layer and its weights.
 */
public class LayerWeights implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Layer.
     */
    private final Layer       layer;

    /**
     * Weights.
     */
    private Matrix            weights;

    /**
     * Constructor.
     *
     * @param currentLayer
     *            current layer
     * @param previousLayer
     *            previous layer
     */
    public LayerWeights( final Layer currentLayer, final Layer previousLayer ) {
        this.layer = currentLayer;

        if ( null != previousLayer ) {
            this.weights = new Matrix( previousLayer.getNeuronCount(),
                                       currentLayer.getNeuronCount() );
            Randomizer.randomize( this.weights );
        }
    }

    /**
     * Return layer.
     *
     * @return layer
     */
    public Layer getLayer() {
        return this.layer;
    }

    /**
     * Return weights.
     *
     * @return weights
     */
    public Matrix getWeights() {
        return this.weights;
    }

    /**
     * Sets weights.
     *
     * @param weights
     *            weights
     */
    public void setWeights( final Matrix weights ) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return String.format( "%s%n%s", Objects.toString( this.layer ),
                              Objects.toString( this.weights ) );
    }
}
