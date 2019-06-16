package ksrGut.logic.characteristicFunction;

import ksrGut.data.Tuple;
import ksrGut.logic.summaries.quantifier.QuantifierRelativity;

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

    public double getArea(QuantifierRelativity relativity) {
        if (relativity.equals(QuantifierRelativity.RELATIVE)) {
            return getAreaRaw();
        } else return getAreaRaw() / ColumnToWidthMap.get(columnName);
    }

    @Override
    public Double apply(Tuple t) {
        if (Objects.isNull(columnName)) throw new IllegalArgumentException();
        double x = t.getValue(this.columnName);
        return applyRaw(x);
    }

    public double getBase(QuantifierRelativity relativity) {
        if (relativity.equals(QuantifierRelativity.RELATIVE)) {
            return getBaseRaw();
        } else return getBaseRaw() / ColumnToWidthMap.get(columnName);
    }
}

