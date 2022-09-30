package baseball;


public class GameRecord{

    private final int strikeCount;
    private final int ballCount;

    public GameRecord(int strikeCount,int ballCount){
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public int getStrikeCount(){
        return strikeCount;
    }


    
}