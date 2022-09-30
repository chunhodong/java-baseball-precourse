package baseball.controller;

import baseball.model.GameModel;
import baseball.model.GameRecord;
import baseball.view.GameView;

import static baseball.exception.ExceptionMessage.INVALID_PROGRESS;

public class GameController {

    private static final String START = "1";
    private static final String END = "2";
    private GameModel model;
    private GameView view;
    private boolean isRunning;


    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public void onStart() {

        onReady();
        onRunning();

    }

    private void onReady(){
        isRunning = true;
        model.initGame();
    }

    private void onClose(){
        isRunning = false;
        view.printExit();
    }


    private void onRunning() {

        while (isRunning) {

            //사용자입력
            String userNumber = view.printInput();


            //점수계산
            GameRecord record = model.calculateScore(userNumber);

            //기록출력
            view.printRecord(record);

            //종료조건확인
            boolean isEnd = model.isEnd(record);

            //종료확인출력
            responseView(isEnd);


        }

    }


    private void responseView(boolean isEnd) {
        if (isEnd) {
            String resume = view.printResume();
            setProgress(resume);
        }

    }

    private void setProgress(String resume) {
        validateProgressInput(resume);

        if(resume.equals(START)){
            onReady();
        }
        if(resume.equals(END)){
            onClose();
        }
    }

    private void validateProgressInput(String resume) {
        if (resume == null) throw new IllegalArgumentException(INVALID_PROGRESS);
        if (!resume.equals(START) && !resume.equals(END)) throw new IllegalArgumentException(INVALID_PROGRESS);

    }

}
