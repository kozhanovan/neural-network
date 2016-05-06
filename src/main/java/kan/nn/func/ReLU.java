package kan.nn.func;

/**
 * Rectified linear unit.
 */
public class ReLU implements IActivationFunction {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public double derivative( final double x ) {
        if ( x > 0d ) {
            return 1d;
        }

        return 0d;
    }

    @Override
    public double value( final double x ) {
        return Math.max( 0d, x );
    }

}
