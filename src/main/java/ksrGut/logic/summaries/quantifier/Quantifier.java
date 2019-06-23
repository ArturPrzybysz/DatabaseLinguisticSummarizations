package ksrGut.logic.summaries.quantifier;

import ksrGut.logic.characteristicFunction.CharacteristicFunction;
import ksrGut.logic.characteristicFunction.ColumnToWidthMap;

public class Quantifier {
    public String getName() {
        return name;
    }

    public final String name;
    public final QuantifierRelativity relativity;
    public final CharacteristicFunction function;
    private double maxValue;


    public Quantifier(String name, QuantifierRelativity relativity, CharacteristicFunction function) {
        this.name = name;
        this.relativity = relativity;
        this.function = function;
        if (relativity.equals(QuantifierRelativity.RELATIVE)) {
            this.maxValue = 1;
        } else {
            this.maxValue = ColumnToWidthMap.get(function.getColumnName());
        }
    }

    public double getDegreeOfFuzzinessForFunction() {
        return this.function.getBase() / this.maxValue;
    }

    public double getDegreeOfFuzziness() {
        return function.getBase();
    }

    public double getCardinalityRatioForFunction() {
        return function.getArea() / this.maxValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
