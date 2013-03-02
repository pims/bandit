bandit
======

Simple collection of Multi Armed Bandit strategies

Example Use:

```java
List<Arm> options = new ArrayList<Arm>();
Arm first = new Arm(new Long(1), "Option 1", "image1.jpg");
Arm second = new Arm(new Long(2), "Option 2", "image2.jpg");

options.add(first);
options.add(second);

BaseStrategy strategy = new EpsilonGreedy(options, 0.1);
Arm selected = strategy.selectArm();

double reward = (Math.random() >= 0.5) ? 1.0 : 0.0;
strategy.update(selected, reward);
```