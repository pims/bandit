package me.pims.bandit.strategies;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import me.pims.bandit.core.Arm;
import java.util.Collection;

public abstract class BaseStrategy {

    protected final int[] counts;
    protected final double[] values;
    protected final ImmutableList<Arm> options;
    protected final int numberOfOptions;

    public BaseStrategy(Collection<Arm> options) {

        this.options = ImmutableList.copyOf(ImmutableSortedSet.copyOf(options));
        if(this.options.isEmpty()) {
            throw new IllegalArgumentException("Non empty list of options required");
        }

        this.numberOfOptions = this.options.size();
        this.counts = new int[this.options.size()];
        this.values = new double[this.options.size()];
    }

    public abstract Arm selectArm();

    public void update(Arm chosenArm, double reward) {
        final int index = options.indexOf(chosenArm);
        update(index, reward);
    }

    synchronized private void update(int index, double reward) {
        counts[index] = counts[index] + 1;
        final double n =  counts[index];

        if(n == 1) {
            values[index] = reward;
            return;
        }

        double newValue = (n - 1.0) / n * values[index] + (1.0 / n) * reward;
        values[index] = newValue;
    }
}
