package kan.nn.func;

/**
 * Step activation function.
 */
public class Step implements IActivationFunction {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Value above which the function will return 1. Equals 0.5 by default.
     */
    private double            step;

    /**
     * Default constructor.
     */
    public Step() {
        this( 0.5 );
    }

    /**
     * Constructor.
     *
     * @param step
     *            step to use
     */
    public Step( final double step ) {
        this.step = step;
    }

    @Override
    public double derivative( final double x ) {
        return 1d;
    }

    /**
     * Returns step.
     *
     * @return step
     */
    public double getStep() {
        return this.step;
    }

    /**
     * Sets step to use.
     *
     * @param step
     *            step to use
     */
    public void setStep( final double step ) {
        this.step = step;
    }

    @Override
    public double value( final double x ) {
        if ( x >= this.step ) {
            return 1d;
        }
        return 0d;
    }

}
