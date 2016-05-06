package kan.nn.func;

/**
 * Linear activation function.
 */
public class Linear implements IActivationFunction {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public double derivative( final double x ) {
        return 1d;
    }

    @Override
    public double value( final double x ) {
        return x;
    }

}
