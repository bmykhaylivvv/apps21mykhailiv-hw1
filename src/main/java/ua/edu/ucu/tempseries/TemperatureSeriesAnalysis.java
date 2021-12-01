package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private final int minimalBound = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < minimalBound) {
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
    }

    public double average() {
        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        double sum = 0;

        for (double temp : temperatureSeries) {
            sum += temp;
        }

        return sum/temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        double sum = 0, deviation = 0;
        int temperatureSeriesLength = temperatureSeries.length;

        for (double temp : temperatureSeries) {
            sum += temp;
        }

        double mean = average();

        for (double temp : temperatureSeries) {
            deviation += (temp - mean) * (temp - mean);
        }

        return Math.sqrt(deviation / temperatureSeriesLength);

    }

    public double min() {
        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        return findTempClosestToValue(Integer.MIN_VALUE);
    }

    public double max() {

        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        return findTempClosestToValue(Integer.MAX_VALUE);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double diff = Double.POSITIVE_INFINITY;
        double closestTemp = 0;

        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < diff) {
                diff = Math.abs(temp - tempValue);
                closestTemp = temp;
            } else if (Math.abs(temp - tempValue) == diff && temp > 0) {
                diff = Math.abs(temp - tempValue);
                closestTemp = temp;
            }
        }

        return closestTemp;
    }

    public double[] findTempsLessThen(double tempValue) {
        int lessTempsLength = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                lessTempsLength++;
            }
        }

        double[] lessTemps = new double[lessTempsLength];

        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                lessTemps[index] = temp;
                index++;
            }
        }

        return lessTemps;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int greaterTempsLength = 0;
        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                greaterTempsLength++;
            }
        }

        double[] greaterTemps = new double[greaterTempsLength];

        int index = 0;
        for (double temp : temperatureSeries) {
            if (temp > tempValue) {
                greaterTemps[index] = temp;
                index++;
            }
        }

        return greaterTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length < 1) {
            throw new IllegalArgumentException();
        }

        TempSummaryStatistics tempSummaryStatistics =
                new TempSummaryStatistics(average(),
                        deviation(), min(), max());

        return tempSummaryStatistics;
    }

    public double addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < minimalBound) {
                throw new InputMismatchException();
            }
        }

        double sum = 0;
        double[] newTemperatureSeries = new double[temperatureSeries.length];

        while (temperatureSeries.length + temps.length
                > newTemperatureSeries.length) {
            newTemperatureSeries =
                    new double[newTemperatureSeries.length * 2];
        }

        int index = 0;
        for (double temp : temperatureSeries) {
            newTemperatureSeries[index] = temp;
            sum += temp;
            index++;
        }

        for (double temp : temps) {
            newTemperatureSeries[index] = temp;
            sum += temp;
            index++;
        }

        temperatureSeries = newTemperatureSeries;
        return sum;
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }
}
