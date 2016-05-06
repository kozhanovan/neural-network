package kan.nn;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kan.nn.data.FeedForwardNetwork;
import kan.nn.data.Layer;
import kan.nn.data.TrainingSample;
import kan.nn.func.Sigmoid;
import kan.nn.training.BasicTraining;

public class TestNetwork {

    private static final int ITERATIONS = 100000;

    public static void main( final String[] args ) throws Exception {
        FeedForwardNetwork myNetwork = new FeedForwardNetwork();
        myNetwork.addLayer( new Layer( "Input", 2, null ) );
        myNetwork.addLayer( new Layer( "Hidden 1", 8, new Sigmoid() ) );
        // myNetwork.addLayer( new Layer( "Hidden 2", 3, new Sigmoid() ) );
        myNetwork.addLayer( new Layer( "Output", 2, new Sigmoid() ) );

        List<TrainingSample> samples = Arrays.asList( new TrainingSample(
                                                                          new double[] {
                                                                              0d,
                                                                              0d},
                                                                          new double[] {
                                                                              1d,
                                                                              0d} ),
                                                      new TrainingSample(
                                                                          new double[] {
                                                                              0d,
                                                                              1d},
                                                                          new double[] {
                                                                              0d,
                                                                              1d} ),
                                                      new TrainingSample(
                                                                          new double[] {
                                                                              1d,
                                                                              0d},
                                                                          new double[] {
                                                                              0d,
                                                                              1d} ),
                                                      new TrainingSample(
                                                                          new double[] {
                                                                              1d,
                                                                              1d},
                                                                          new double[] {
                                                                              1d,
                                                                              0d} ) );

        BasicTraining training = new BasicTraining( myNetwork, 0.1, 0.9 );
        Random random = new Random( 1 );

        for ( int i = 0; i < TestNetwork.ITERATIONS; i++ ) {
            TrainingSample sample = samples.get( random.nextInt( samples.size() ) );
            training.iteration( sample );
        }

        System.out.println( Arrays.toString( myNetwork.getOutput( new double[] {
            0d, 0d} ) ) );
        System.out.println( Arrays.toString( myNetwork.getOutput( new double[] {
            0d, 1d} ) ) );
        System.out.println( Arrays.toString( myNetwork.getOutput( new double[] {
            1d, 0d} ) ) );
        System.out.println( Arrays.toString( myNetwork.getOutput( new double[] {
            1d, 1d} ) ) );
    }
}
