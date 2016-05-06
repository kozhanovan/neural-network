package kan.nn.func;

import java.io.Serializable;

/**
 * Activation function interface.
 */
public interface IActivationFunction extends Serializable {

    /**
     * Returns function derivative value.
     *
     * @param x
     *            parameter value
     * @return derivative value
     */
    double derivative( double x );

    /**
     * Returns function value.
     *
     * @param x
     *            parameter value
     * @return function value
     */
    double value( double x );
}
