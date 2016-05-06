package kan.nn.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import kan.nn.func.IActivationFunction;

/**
 * Neural network layer.
 */
public class Layer implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long         serialVersionUID = 1L;

    /**
     * Layer activation function.
     */
    private final IActivationFunction activationFunction;

    /**
     * Neuron values.
     */
    private final double[]            values;

    /**
     * Layer name.
     */
    private final String              name;

    /**
     * Constructor.
     *
     * @param name
     *            layer name (optional)
     * @param numOfNeurons
     *            number of neurons in a layer.
     * @param activationFunction
     *            activation function
     */
    // TODO add bias nodes
    public Layer( final String name, final int numOfNeurons,
            final IActivationFunction activationFunction ) {
        this.name = Objects.toString( name );
        this.values = new double[numOfNeurons];
        this.activationFunction = activationFunction;
    }

    /**
     * Return activation function for current layer.
     *
     * @return activation function for current layer
     */
    public IActivationFunction getActivationFunction() {
        return this.activationFunction;
    }

    /**
     * Returns count of neurons in this layer.
     *
     * @return count of neurons in this layer
     */
    public int getNeuronCount() {
        return this.values.length;
    }

    /**
     * Returns specified node value.
     *
     * @param index
     *            node index, 0-based
     * @return node value
     */
    public double getValue( final int index ) {
        if ( index >= this.values.length ) {
            throw new IndexOutOfBoundsException(
                                                 String.format( "Layer %s has only %d neurons, attempted to get neuron %d",
                                                                this.name,
                                                                this.values.length,
                                                                index ) );
        }

        return this.values[index];
    }

    /**
     * Return all nodes values.
     *
     * @return all nodes values
     */
    public double[] getValues() {
        return Arrays.copyOf( this.values, this.values.length );
    }

    /**
     * Sets all neuron values to provided input.
     *
     * @param input
     *            values to set
     */
    public void setAll( final double[] input ) {
        if ( this.values.length != input.length ) {
            throw new IndexOutOfBoundsException(
                                                 String.format( "Array sizes do not match! Expected %d, Actual: %d",
                                                                this.values.length,
                                                                input.length ) );
        }

        for ( int i = 0; i < this.values.length; i++ ) {
            this.values[i] = input[i];
        }
    }

    /**
     * Sets specified neuron value to provided one.
     *
     * @param index
     *            neuron index, 0-based
     * @param value
     *            value to set
     */
    public void setValue( final int index, final double value ) {
        if ( index >= this.values.length ) {
            throw new IndexOutOfBoundsException(
                                                 String.format( "Layer %s has only %d neurons, attempted to set neuron %d",
                                                                this.name,
                                                                this.values.length,
                                                                index ) );
        }

        this.values[index] = value;
    }

    @Override
    public String toString() {
        return String.format( "%s %s", this.name, Arrays.toString( this.values ) );
    }
}
