package me.pims.bandit.core;

import junit.framework.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void testComputeTemperature() {
        final double sumCounts = 3.0;
        final double res = 1.0 /  Math.log(sumCounts + 1 + 0.0000001);
        final int[] counts = new int[]{1,1,1};

        assertThat(Util.computeTemperature(counts), is(res));
        assertThat(Util.computeTemperature(null), is(res));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetIndexForMaxValue() {
        double[] values = new double[] {1.0,2.0,3.0};
        assertThat(Util.getIndexForMaxValue(values), is(2));

        double[] emptyValues = new double[] {0,0};
        assertThat(Util.getIndexForMaxValue(emptyValues), is(0));

        Assert.assertEquals(0, Util.getIndexForMaxValue(null));
        Assert.assertEquals(0, Util.getIndexForMaxValue(new double[]{}));
    }

    @Test
    public void testCategoricalDraw() {
        double[] probabilities = new double[]{0.1,0.2,0.3,0.4};
        assertThat(Util.categoricalDraw(probabilities, 0.0), is(0));
        assertThat(Util.categoricalDraw(probabilities, 0.1), is(1));
        assertThat(Util.categoricalDraw(probabilities, 0.7), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComputeProbabilities() {
        final double[] values = new double[]{0.3,0.3,0.4};
        final double temperature = 0.1;
        final double[] probabilities = new double[]{0.2119415576170854, 0.2119415576170854, 0.5761168847658292};
        assertThat(Util.computeProbabilities(values, temperature), is(probabilities));
        assertThat(Util.computeProbabilities(null, temperature), is(probabilities));
    }
}
