package me.pims.bandit.strategies;

import me.pims.bandit.core.Arm;
import me.pims.bandit.core.Util;
import java.util.Collection;
import java.util.Random;

public class EpsilonGreedy extends BaseStrategy {

    private final double epsilon;

    public EpsilonGreedy(Collection<Arm> options) {
        super(options);
        this.epsilon = 0.1;
    }

    public EpsilonGreedy(Collection<Arm> options, double epsilon) {
        super(options);
        this.epsilon = epsilon;
    }

    public Arm selectArm() {
        Random r = new Random();
        if (r.nextDouble() > this.epsilon) {
            int index = Util.getIndexForMaxValue(values);
            return options.get(index);
        }

        return options.get(r.nextInt(numberOfOptions));
    }
}
