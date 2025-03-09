package br.com.mark.transacaoapi.core;

public class Statistics {

    private Long count;

    private double sum;

    private double avg;

    private double min;

    private double max;

    public Statistics(Long count, double sum, double avg, double min, double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public Statistics() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "count=" + count +
                ", sum=" + sum +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

}