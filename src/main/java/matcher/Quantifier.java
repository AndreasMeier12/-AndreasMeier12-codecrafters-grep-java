package matcher;

public class Quantifier {

    int minTimes;
    int maxTimes;

    int getMinTimes(){
        return minTimes;
    }

    int getMaxTimes(){
        return maxTimes;
    }

    public Quantifier(int minTimes, int maxTimes) {
        this.minTimes = minTimes;
        this.maxTimes = maxTimes;
    }
}
