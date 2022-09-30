package baseball;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        new GameController(new GameModel(),new GameView()).onStart();
    }
}
