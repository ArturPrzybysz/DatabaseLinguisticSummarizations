package ksrGut;

import ksrGut.data.ColumnMap;
import ksrGut.logic.CrispSet;
import ksrGut.logic.characteristicFunction.CharacteristicFunction;
import ksrGut.logic.characteristicFunction.FallingFunction;
import ksrGut.logic.characteristicFunction.TriangularFunction;
import ksrGut.logic.norms.NormPairImplementations;
import ksrGut.logic.qualityMeasures.*;
import ksrGut.logic.summaries.LinguisticVariable;
import ksrGut.logic.summaries.SummaryWithQualifier;
import ksrGut.logic.summaries.quantifier.QuantifierImplementations;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            Table t = Table.read().file(ProjectPaths.dataPath.toString());
            CrispSet universe = new CrispSet(t);

            Map<String, CharacteristicFunction> m = new HashMap<>();
            m.put("strong", new TriangularFunction(ColumnMap.alcohol, 20, 40, 100000));
            m.put("medium", new TriangularFunction(ColumnMap.alcohol, 10, 25, 35));
            m.put("non", new FallingFunction(ColumnMap.alcohol, 0, 1));

            LinguisticVariable alcoholic = new LinguisticVariable("alcohol", m, universe);

            Map<String, CharacteristicFunction> m2 = new HashMap<>();
            m2.put("not", new FallingFunction(ColumnMap.fat, 0, 3));
            m2.put("medium", new TriangularFunction(ColumnMap.fat, 3, 10, 23));
            m2.put("very", new TriangularFunction(ColumnMap.fat, 20, 50, 70));
            m2.put("a lot", new TriangularFunction(ColumnMap.fat, 50, 70, 100));

            LinguisticVariable fatty = new LinguisticVariable("fat", m2, universe);

            List<LinguisticVariable> summarizers = new ArrayList<>();
            List<LinguisticVariable> qualifiers = new ArrayList<>();
            qualifiers.add(alcoholic);
            summarizers.add(fatty);

            List<String> sNames = new ArrayList<>();
            List<String> qNames = new ArrayList<>();
            sNames.add("not");
            qNames.add("strong");

            SummaryWithQualifier summary = new SummaryWithQualifier("PRODUCTS", summarizers, sNames, QuantifierImplementations.MOST(),
                    new ArrayList<>(), NormPairImplementations.minMaxNormPair(),
                    qualifiers, qNames, new ArrayList<>()
            );

            System.out.println(summary);
            System.out.println(T1.getValue(summary));
            System.out.println(T2.getValue(summary));
            System.out.println(T3.getValue(summary));
            System.out.println(T4.getValue(summary));
            System.out.println(T5.getValue(summary));
            System.out.println(T6.getValue(summary));
            System.out.println(T7.getValue(summary));
            System.out.println(T8.getValue(summary));
            System.out.println(T9.getValue(summary));
            System.out.println(T10.getValue(summary));
            System.out.println(T11.getValue(summary));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
