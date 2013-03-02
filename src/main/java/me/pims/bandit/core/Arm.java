package me.pims.bandit.core;

public class Arm implements Comparable<Arm> {

    public final Long id;
    public final String label;
    public final String value;

    public Arm(Long id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + id.hashCode();
        hash = hash * 31 + label.hashCode();
        hash = hash * 31 + value.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Arm)) {
            return false;
        }
        Arm otherArm = (Arm) other;

        return id.equals(otherArm.id)
                && label.equals(otherArm.label)
                && value.equals(otherArm.value);
    }

    @Override
    public int compareTo(Arm arm) {
        return this.id.compareTo(arm.id);
    }

    @Override
    public String toString() {
        return "{" + id + label + value + "}";
    }
}
