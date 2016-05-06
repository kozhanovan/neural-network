package kan.nn.data;

import java.util.Random;

/**
 * Sets matrix values to random ones.
 */
public class Randomizer {

    /**
     * Fills provided matrix with random values.
     *
     * @param m
     *            matrix
     */
    public static void randomize( final Matrix m ) {
        int rowNum = m.getRowNum();
        int colNum = m.getColNum();
        Random random = new Random( 1 );

        for ( int r = 0; r < rowNum; r++ ) {
            for ( int c = 0; c < colNum; c++ ) {
                m.set( r, c, random.nextDouble() );
            }
        }
    }
}
