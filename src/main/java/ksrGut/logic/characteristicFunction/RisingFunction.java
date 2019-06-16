package ksrGut.logic.characteristicFunction;

public class RisingFunction extends CharacteristicFunction {
    private double left;
    private double right;

    public RisingFunction(String columnName, double left, double right) {
        super(columnName);
        this.left = left;
        this.right = right;
    }

    @Override
    public Double applyRaw(double x) {
        if (x < left) return 0.0;
        else if (x >= right) return 1.0;
        else return 1 - (right - x) / (right - left);
    }
}
