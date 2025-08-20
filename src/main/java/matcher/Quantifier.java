package matcher;

public class Quantifier {

    int minTimes;
    int maxTimes;

    public int getMinTimes(){
        return minTimes;
    }

    public int getMaxTimes(){
        return maxTimes;
    }

    public Quantifier(int minTimes, int maxTimes) {
        this.minTimes = minTimes;
        this.maxTimes = maxTimes;
    }
}
