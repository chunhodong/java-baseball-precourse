package baseball;

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


    }

    private void onRunning() {

        while (onProgress()) {

        }

    }

    private boolean onProgress() {
        return isProgress;
    }

}
