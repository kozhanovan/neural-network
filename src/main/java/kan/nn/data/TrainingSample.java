package kan.nn.data;

import java.util.Arrays;

/**
 * Contains training sample.
 */
public class TrainingSample {

    /**
     * Input data.
     */
    private final double[] input;

    /**
     * Expected output.
     */
    private final double[] output;

    /**
     * Constructor.
     *
     * @param input
     *            input
     * @param output
     *            expected output
     */
    public TrainingSample( final double[] input, final double[] output ) {
        this.input = input;
        this.output = output;
    }

    /**
     * Returns input.
     *
     * @return input
     */
    public double[] getInput() {
        return this.input;
    }

    /**
     * Returns output.
     *
     * @return output
     */
    public double[] getOutput() {
        return this.output;
    }

    @Override
    public String toString() {
        return String.format( "Input: %s%nOutput: %s",
                              Arrays.toString( this.input ),
                              Arrays.toString( this.output ) );
    }
}
