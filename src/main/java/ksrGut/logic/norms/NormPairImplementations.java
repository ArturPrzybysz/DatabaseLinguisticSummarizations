package ksrGut.logic.norms;

public class NormPairImplementations {
    private static class MinNorm extends Norm {

        @Override
        public Double apply(double v1, double v2) {
            return Math.min(v1, v2);
        }
    }

    private static class MaxNorm extends Norm {

        @Override
        public Double apply(double v1, double v2) {
            return Math.max(v1, v2);
        }
    }

    public static NormPair minMaxNormPair() {
        return new NormPair(new MinNorm(), new MaxNorm());
    }
}
