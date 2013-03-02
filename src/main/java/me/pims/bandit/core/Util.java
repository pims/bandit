package me.pims.bandit.core;

import com.google.common.annotations.VisibleForTesting;

import java.util.Random;

public class Util {

    public static double computeTemperature(int[] counts) {
        checkNotNull(counts);

        double t = 1.0;
        for (int count : counts) {
            t += count;
        }
        return 1.0 /  Math.log(t + 0.0000001);
    }

    public static int getIndexForMaxValue(double[] values) {
        checkNotNull(values);
        int maxIndex = 0;
        double maxValue = 0.0;

        for(int i = 0; i < values.length; i++) {
            if(values[i] > maxValue) {
                maxIndex = i;
                maxValue = values[i];
            }
        }

        return maxIndex;
    }

    public static double[] computeProbabilities(double[] values, double temperature) {
        checkNotNull(values);

        double z = 0.0;
        final double[] probabilities = new double[values.length];

        for(int i = 0; i < values.length; i++) {
            z += Math.exp(values[i] / temperature);
        }

        for(int j = 0; j < values.length; j++) {
            probabilities[j] =  Math.exp(values[j] / temperature) /z;
        }
        return probabilities;
    }

    public static int categoricalDraw(double [] probabilities) {
        return categoricalDraw(probabilities, Math.random());
    }


    public static int categoricalDraw(double[] probabilities, double randomNumber) {
        checkNotNull(probabilities);

        double cumProb = 0.0;

        for(int i = 0; i < probabilities.length; i++) {
            double prob = probabilities[i];
            cumProb += prob;
            if (cumProb > randomNumber) {
                return i;
            }
        }

        return probabilities.length - 1;
    }

    private static void checkNotNull(double[] values) {
        if(values == null || values.length == 0) {
            throw new IllegalArgumentException("values can not be null or empty");
        }
    }

    private static void checkNotNull(int[] values) {
        if(values == null || values.length == 0) {
            throw new IllegalArgumentException("values can not be null or empty");
        }
    }
}
