package baseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static baseball.ExceptionMessage.INVALID_GAMEBALL_SIZE;


public class GameModel {
    private List<Integer> computerNumbers;
    private static final int GAMEBALL_SIZE = 3;
    private static final int STRIKE_COUNT = 3;


    public void initGame(){
        computerNumbers = new ArrayList<>();
    }


    public GameRecord calculateScore(String userNumber) {

        List<Integer> userNumbers = Converter.stringToIntegerArray(userNumber);
        validateUserNumbers(userNumbers);

        int strikeCount = 0;
        int ballCount = 0;
        return new GameRecord(strikeCount,ballCount);

    }

    private void validateUserNumbers(List<Integer> userNumbers){
        if(userNumbers.size() != GAMEBALL_SIZE)throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
        if(new HashSet(userNumbers).size() != GAMEBALL_SIZE) throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
    }



    public boolean isEnd(GameRecord gameRecord) {
        return gameRecord.getStrikeCount() == STRIKE_COUNT;
    }
}
