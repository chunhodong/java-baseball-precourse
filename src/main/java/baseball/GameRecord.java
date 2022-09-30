package baseball;


public class GameRecord{

    private final int strikeCount;
    private final int ballCount;

    public GameRecord(int strikeCount,int ballCount){
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    @Override
    public String toString() {
        if(strikeCount > 0 && ballCount > 0){
            return String.format("%d볼 %d스트라이크",strikeCount,ballCount);
        }
        if(strikeCount > 0){
            return String.format("%d스트라이크",strikeCount);
        }
        if(ballCount > 0){
            return String.format("%d볼",ballCount);
        }
        return String.format("낫싱");
    }
}