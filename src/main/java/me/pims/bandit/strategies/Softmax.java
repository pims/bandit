package me.pims.bandit.strategies;

import me.pims.bandit.core.Arm;
import me.pims.bandit.core.Util;
import java.util.Collection;

public class Softmax extends BaseStrategy {
    private final double temperature;

    public Softmax(Collection<Arm> options) {
        super(options);
        this.temperature = 0.1;
    }

    public Softmax(Collection<Arm> options, double temperature) {
        super(options);
        this.temperature = temperature;
    }

    public Arm selectArm() {
        final double[] probabilities = Util.computeProbabilities(values, temperature);
        final int selectedArmIndex = Util.categoricalDraw(probabilities);
        return options.get(selectedArmIndex);
    }
}