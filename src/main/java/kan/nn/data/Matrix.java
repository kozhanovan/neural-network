package kan.nn.data;

import java.io.Serializable;

/**
 * Represents matrix.
 */
public class Matrix implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Matrix values.
     */
    private final double[][]  data;

    /**
     * Number of rows.
     */
    private final int         rowNum;

    /**
     * Number of columns.
     */
    private final int         colNum;

    /**
     * Constructor.
     *
     * @param rowNum
     *            number of rows
     * @param colNum
     *            number of columns
     */
    public Matrix( final int rowNum, final int colNum ) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.data = new double[rowNum][colNum];
    }

    /**
     * Returns specific value from matrix. Does not handle any out of bounds
     * exceptions.
     *
     * @param x
     *            row number
     * @param y
     *            column number
     * @return data read
     */
    public double get( final int x, final int y ) {
        return this.data[x][y];
    }

    /**
     * Returns number of columns.
     *
     * @return number of columns
     */
    public int getColNum() {
        return this.colNum;
    }

    /**
     * Returns number of rows.
     *
     * @return number of rows
     */
    public int getRowNum() {
        return this.rowNum;
    }

    /**
     * Sets specific value.
     *
     * @param x
     *            row number
     * @param y
     *            column number
     * @param val
     *            value to set
     */
    public void set( final int x, final int y, final double val ) {
        this.data[x][y] = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for ( int x = 0; x < this.data.length; x++ ) {
            for ( int y = 0; y < this.data[x].length; y++ ) {
                sb.append( String.format( "%10.6f ", this.data[x][y] ) );
            }
            sb.append( "\n" );
        }

        return sb.toString();
    }
}
