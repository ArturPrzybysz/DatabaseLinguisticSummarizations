package ksrGut.logic.characteristicFunction;

import ksrGut.data.Tuple;

import java.util.Objects;
import java.util.function.Function;

public abstract class CharacteristicFunction implements Function<Tuple, Double> {

    String columnName;

    CharacteristicFunction(String columnName) {
        this.columnName = columnName;
    }

    public abstract Double applyRaw(double x);

    abstract double getAreaRaw();

    abstract double getBaseRaw();

    public double getArea() {
        return getAreaRaw();
    }

    @Override
    public Double apply(Tuple t) {
        if (Objects.isNull(columnName)) throw new IllegalArgumentException();
        double x = t.getValue(this.columnName);
        return applyRaw(x);
    }

    public double getBase() {
        return getBaseRaw();
    }

    public String getColumnName() {
        return columnName;
    }

}

