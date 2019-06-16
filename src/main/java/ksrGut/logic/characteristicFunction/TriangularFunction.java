package ksrGut.logic.characteristicFunction;

public class TriangularFunction extends CharacteristicFunction {
    public TriangularFunction(String columnName, double left, double center, double right) {
        super(columnName);
        this.left = left;
        this.right = right;
        this.center = center;
    }


    public TriangularFunction(double left, double center, double right) {
        super(null);
        this.left = left;
        this.right = right;
        this.center = center;
    }

    private double left;
    private double right;
    private double center;

    @Override
    public Double applyRaw(double x) {
        if (x <= left)
            return 0.0;
        else if (left < x && x <= center)
            return (x - left) / (center - left);
        else if (center < x && x <= right)
            return (right - x) / (right - center);
        else
            return 0.0;
    }

    @Override
    public double getAreaRaw() {
        return (right - left) / 2;
    }

    @Override
    double getBaseRaw() {
        return right - left;
    }
}
