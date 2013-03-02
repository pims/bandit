package me.pims.bandit.strategies;

import me.pims.bandit.core.Arm;
import me.pims.bandit.core.Util;
import java.util.Collection;

public class AnnealingSoftmax extends BaseStrategy {

    public AnnealingSoftmax (Collection<Arm> options) {
        super(options);
    }

    public Arm selectArm() {
        final double temperature = Util.computeTemperature(counts);
        final double[] probabilities = Util.computeProbabilities(values, temperature);
        final int selectedArmIndex = Util.categoricalDraw(probabilities);
        return options.get(selectedArmIndex);
    }
}
