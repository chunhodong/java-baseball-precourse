package baseball;

import static baseball.ExceptionMessage.INVALID_PROGRESS;

public class GameController {

    private static final String START = "1";
    private static final String END = "2";
    private GameModel model;
    private GameView view;
    private boolean isProgress;


    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public void onStart() {

        switchProgress(START);
        onRunning();

    }

    private void onRunning() {

        while (onProgress()) {

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

    private boolean onProgress() {
        return isProgress;
    }

    private void responseView(boolean isEnd) {
        if (isEnd) {
            String resume = view.printResume();
            switchProgress(resume);
        }

    }

    private void switchProgress(String resume) {
        validateProgressInput(resume);

        if(resume.equals(START)){
            isProgress = true;
        }
        if(resume.equals(END)){
            isProgress = false;
        }
    }

    private void validateProgressInput(String resume) {
        if (resume == null) throw new IllegalArgumentException(INVALID_PROGRESS);
        if (!resume.equals(START) && !resume.equals(END)) throw new IllegalArgumentException(INVALID_PROGRESS);

    }

}
