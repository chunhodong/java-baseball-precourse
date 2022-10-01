package baseball.utils;

import java.util.ArrayList;
import java.util.List;

import static baseball.exception.ExceptionMessage.EMPTY_STRING;

public class Converter {

    public static List<Integer> stringToIntegerArray(String input){
        validateInput(input);
        List<Integer> numbers = new ArrayList<>();
        for(int i=0; i<input.length(); i++)
            numbers.add(input.charAt(i) - '0');
        return numbers;
    }

    private static void validateInput(String input){
        if(input == null || input.isEmpty())throw new IllegalArgumentException(EMPTY_STRING);

    }

}
