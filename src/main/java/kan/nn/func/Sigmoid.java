package kan.nn.func;

/**
 * Sigmoid activation function.
 */
public class Sigmoid implements IActivationFunction {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public double derivative( final double x ) {
        double v = this.value( x );
        return v * (1 - v);
    }

    @Override
    public double value( final double x ) {
        return 1d / (1 + Math.exp( -x ));
    }

}
