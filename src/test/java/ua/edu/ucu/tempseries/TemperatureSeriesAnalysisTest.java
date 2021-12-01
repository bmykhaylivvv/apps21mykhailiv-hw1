package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }


    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.7416573867739413;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindClosestToZero() {
        double[] temperatureSeries = {-0.2, 0.2, -0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindClosestToValue() {
        double[] temperatureSeries = {-0.2, 0.2, -0.2, 0.1, -0.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;

        double actualResult = seriesAnalysis.findTempClosestToValue(1);

        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-0.2, 0.2, -0.2, 0.1, -0.1, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = new double[] {-0.2, -0.2, -0.1};

        double[] actualResult = seriesAnalysis.findTempsLessThen(0.1);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-0.2, 0.2, -0.2, 0.1, -0.1, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = new double[] {0.2, 3.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(0.1);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {0.1, -0.1, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 6.2;

        double actualResult = seriesAnalysis.addTemps(0.2, 3.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {0.1, -0.1, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics seriesStatistics = seriesAnalysis.summaryStatistics();

        assertEquals(seriesStatistics.getAvgTemp(), 1, 0.00001);
        assertEquals(seriesStatistics.getDevTemp(), 1.4165686240583852, 0.00001);
        assertEquals(seriesStatistics.getMaxTemp(), 3, 0.00001);
        assertEquals(seriesStatistics.getMinTemp(), -0.1, 0.00001);
    }

}
