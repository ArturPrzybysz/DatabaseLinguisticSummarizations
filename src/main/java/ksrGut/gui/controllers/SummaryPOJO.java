package ksrGut.gui.controllers;

import ksrGut.logic.qualityMeasures.*;
import ksrGut.logic.summaries.Summary;

public class SummaryPOJO {
    SummaryPOJO(Summary summary, boolean[] tValues) {
        this.summary = summary;

        if (tValues[0]) t1 = Double.toString(T1.getValue(summary));  else t1 = " ";
        if (tValues[1]) t2 = Double.toString(T2.getValue(summary)); else t2 = " ";
        if (tValues[2]) t3 = Double.toString(T3.getValue(summary)); else t3 = " ";
        if (tValues[3]) t4 = Double.toString(T4.getValue(summary)); else t4 = " ";
        if (tValues[4]) t5 = Double.toString(T5.getValue(summary)); else t5 = " ";
        if (tValues[5]) t6 = Double.toString(T6.getValue(summary)); else t6 = " ";
        if (tValues[6]) t7 = Double.toString(T7.getValue(summary)); else t7 = " ";
        if (tValues[7]) t8 = Double.toString(T8.getValue(summary)); else t8 = " ";
        if (tValues[8]) t9 = Double.toString(T9.getValue(summary)); else t9 = " ";
        if (tValues[9]) t10 = Double.toString(T10.getValue(summary)); else t10 = " ";
        if (tValues[10]) t11 = Double.toString(T11.getValue(summary)); else t11 = " ";
    }

    public Summary summary;
    public String t1;
    public String t2;
    public String t3;
    public String t4;
    public String t5;
    public String t6;
    public String t7;
    public String t8;
    public String t9;
    public String t10;
    public String t11;
}
