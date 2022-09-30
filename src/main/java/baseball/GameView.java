package baseball;

import camp.nextstep.edu.missionutils.Console;

public class GameView {

    public String printInput() {
        System.out.println("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public void printRecord(String record){
        System.out.println(record);
    }

    public String printResume(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요");
        return Console.readLine();
    }

}
