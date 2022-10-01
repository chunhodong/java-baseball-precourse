package baseball.utils;

import java.util.ArrayList;
import java.util.List;

import static baseball.exception.ExceptionMessage.EMPTY_STRING;
import static baseball.exception.ExceptionMessage.NOT_ALLOW_CHARACTER;

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
        char[] characters = input.toCharArray();
        for(int i = 0; i < characters.length; i++)
            checkNumber(characters[i]);
    }

    private static void checkNumber(char c){
        if( c >= '1' && c <= '9')return;
        throw new IllegalArgumentException(NOT_ALLOW_CHARACTER);
    }
}
