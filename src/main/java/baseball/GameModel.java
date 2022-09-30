package baseball;

import camp.nextstep.edu.missionutils.Randoms;

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

        int strikeCount = countStrike(computerNumbers,userNumbers);
        int ballCount = countBall(computerNumbers,userNumbers);
        return new GameRecord(strikeCount,ballCount);

    }

    private int countStrike(List<Integer> computerNumbers,List<Integer> userNumbers){

        int strikeCount = 0;
        for(int i = 0; i < computerNumbers.size(); i++){
            strikeCount += checkStrike(computerNumbers.get(i),userNumbers.get(i));
        }
        return strikeCount;
    }

    private int checkStrike(int computerNumber,int userNumber){
        return computerNumber == userNumber ? 1 : 0;
    }

    private int countBall(List<Integer> computerNumbers,List<Integer> userNumbers){
        int ballNumber = 0;
        for(int i = 0; i < computerNumbers.size(); i++){
            ballNumber += checkBall(computerNumbers.get(i),i,userNumbers);
        }
        return ballNumber;
    }

    private int checkBall(int computerNumber,int index,List<Integer> userNumbers){

        int position = userNumbers.indexOf(computerNumber);
        return position != -1 && position != index ? 1 : 0;
    }

    private void validateUserNumbers(List<Integer> userNumbers){
        if(userNumbers.size() != GAMEBALL_SIZE)throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
        if(new HashSet(userNumbers).size() != GAMEBALL_SIZE) throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
    }



    public boolean isEnd(GameRecord gameRecord) {
        return gameRecord.getStrikeCount() == STRIKE_COUNT;
    }
}
