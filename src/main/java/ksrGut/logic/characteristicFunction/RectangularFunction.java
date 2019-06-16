package ksrGut.logic.characteristicFunction;

import ksrGut.data.Tuple;

import java.util.Objects;

public class RectangularFunction extends CharacteristicFunction {

    RectangularFunction(String columnName, double begin, double end) {
        super(columnName);
        this.begin = begin;
        this.end = end;
    }

    RectangularFunction(double begin, double end) {
        super(null);
        this.begin = begin;
        this.end = end;
    }

    private double begin;
    private double end;

    @Override
    public Double applyRaw(double x) {
        if (begin <= x && x <= end) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
