package ksrGut.logic.characteristicFunction;

public class FallingFunction extends CharacteristicFunction {
    private double left;
    private double right;

    public FallingFunction(String columnName, double left, double right) {
        super(columnName);
        this.left = left;
        this.right = right;
    }

    public FallingFunction(double left, double right) {
        super(null);
        this.left = left;
        this.right = right;
    }

    @Override
    public Double applyRaw(double x) {
        if (x < left) return 1.0;
        else if (x >= right) return 0.0;
        else return (right - x) / (right - left);
    }

    @Override
    public double getAreaRaw() {
        return Math.max(0, left) + (right - left) / 2;
    }

    @Override
    double getBaseRaw() {
        return right;
    }
}
