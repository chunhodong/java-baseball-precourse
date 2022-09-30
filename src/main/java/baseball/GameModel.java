package baseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static baseball.ExceptionMessage.INVALID_GAMEBALL_SIZE;


public class GameModel {
    private List<Integer> computerNumbers;
    private static final int GAMEBALL_SIZE = 3;


    public void initGame(){
        computerNumbers = new ArrayList<>();
    }


    public String calculateScore(String userNumber) {

        List<Integer> userNumbers = Converter.stringToIntegerArray(userNumber);
        validateUserNumbers(userNumbers);

        return null;

    }

    private void validateUserNumbers(List<Integer> userNumbers){
        if(userNumbers.size() != GAMEBALL_SIZE)throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
        if(new HashSet(userNumbers).size() != GAMEBALL_SIZE) throw new IllegalArgumentException(INVALID_GAMEBALL_SIZE);
    }




}
