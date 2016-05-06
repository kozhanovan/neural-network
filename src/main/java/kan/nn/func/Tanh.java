package kan.nn.func;

/**
 * Hyperbolic tangent.
 */
public class Tanh implements IActivationFunction {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public double derivative( final double x ) {
        double v = this.value( x );
        return 1d - v * v;
    }

    @Override
    public double value( final double x ) {
        return Math.tanh( x );
    }

}
