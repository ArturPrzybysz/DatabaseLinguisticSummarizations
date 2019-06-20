package ksrGut.gui.controllers;

import ksrGut.logic.qualityMeasures.*;
import ksrGut.logic.summaries.Summary;

public class SummaryPOJO {
    boolean[] tValues;

    SummaryPOJO(Summary summary, boolean[] tValues) {
        this.summary = summary.toString();
        this.tValues = tValues;
        if (tValues[0]) t1 = T1.getValue(summary);
        else t1 = 0.0;
        if (tValues[1]) t2 = (T2.getValue(summary));
        else t2 = 0.0;
        if (tValues[2]) t3 = (T3.getValue(summary));
        else t3 = 0.0;
        if (tValues[3]) t4 = (T4.getValue(summary));
        else t4 = 0.0;
        if (tValues[4]) t5 = (T5.getValue(summary));
        else t5 = 0.0;
        if (tValues[5]) t6 = (T6.getValue(summary));
        else t6 = 0.0;
        if (tValues[6]) t7 = (T7.getValue(summary));
        else t7 = 0.0;
        if (tValues[7]) t8 = (T8.getValue(summary));
        else t8 = 0.0;
        if (tValues[8]) t9 = (T9.getValue(summary));
        else t9 = 0.0;
        if (tValues[9]) t10 = (T10.getValue(summary));
        else t10 = 0.0;
        if (tValues[10]) t11 = (T11.getValue(summary));
        else t11 = 0.0;
    }

    public String summary;
    private double t1;
    private double t2;
    private double t3;
    private double t4;
    private double t5;
    private double t6;
    private double t7;
    private double t8;
    private double t9;
    private double t10;
    private double t11;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(summary);

        if (tValues[0]) sb.append(String.format("T1=%.3f, ", t1));
        if (tValues[1]) sb.append(String.format("T2=%.3f, ", t2));
        if (tValues[2]) sb.append(String.format("T3=%.3f, ", t3));
        if (tValues[3]) sb.append(String.format("T4=%.3f, ", t4));
        if (tValues[4]) sb.append(String.format("T5=%.3f, ", t5));
        if (tValues[5]) sb.append(String.format("T6=%.3f, ", t6));
        if (tValues[6]) sb.append(String.format("T7=%.3f, ", t7));
        if (tValues[7]) sb.append(String.format("T8=%.3f, ", t8));
        if (tValues[8]) sb.append(String.format("T9=%.3f, ", t9));
        if (tValues[9]) sb.append(String.format("T10=%.3f, ", t10));
        if (tValues[10]) sb.append(String.format("T11=%.3f", t11));
        return sb.toString();
    }
}
