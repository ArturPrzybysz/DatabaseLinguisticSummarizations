package ksrGut.logic.summaries.quantifier;

import ksrGut.logic.characteristicFunction.CharacteristicFunction;

public class Quantifier {
    public String getName() {
        return name;
    }

    public final String name;
    public final QuantifierRelativity relativity;
    public final CharacteristicFunction function;

    public Quantifier(String name, QuantifierRelativity relativity, CharacteristicFunction function) {
        this.name = name;
        this.relativity = relativity;
        this.function = function;
    }

//    public double getDegreeOfFuzziness() {
//        return function.getBase(relativity);
//    }

}
