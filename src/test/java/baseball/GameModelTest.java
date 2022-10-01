package baseball;

import baseball.exception.ExceptionMessage;
import baseball.model.GameModel;
import baseball.model.GameRecord;
import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class GameModelTest {


    @Test
    @DisplayName("점수계산에서 NULL값입력하면 예외발생")
    void throws_exception_when_params_null() {

        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.EMPTY_STRING);


    }

    @Test
    @DisplayName("점수계산에서 빈값입력하면 예외발생")
    void throws_exception_when_params_empty() {
        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.EMPTY_STRING);


    }


    @Test
    @DisplayName("점수계산에서 문자가하나이상포함이면 예외발생")
    void throws_exception_when_params_include_character() {
        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore("12a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.NOT_ALLOW_CHARACTER);

    }

    @Test
    @DisplayName("점수계산에서 숫자가 1에서 9사이가 아니면 예외발생")
    void throws_exception_when_params_outofrange() {
        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore("115"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_GAMEBALL_SIZE);

    }

    @Test
    @DisplayName("점수계산에서 3자리 수 아니면 예외발생")
    void throws_exception_when_params_numbersize_not_3() {
        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore("12234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_GAMEBALL_SIZE);

    }

    @Test
    @DisplayName("점수계산에서 중복숫자포함이면 예외발생")
    void throws_exception_when_params_duplicate_number() {
        //given
        GameModel gameModel = new GameModel();

        //when,then
        assertThatThrownBy(() -> gameModel.calculateScore("115"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_GAMEBALL_SIZE);

    }


    @Test
    @DisplayName("점수계산에서 3자리 수 입력하면 점수객체리턴")
    void returns_record_when_params_numbersize_3() {
        //given
        GameModel gameModel = new GameModel();
        gameModel.initGame();

        //when
        GameRecord gameRecord = gameModel.calculateScore("453");

        //then
        assertThat(gameRecord).isNotNull();

    }

    @Test
    @DisplayName("점수계산에서 스트라이크만있으면 리턴값에 스트라이크만포함")
    void returns_stirke_only_when_params_include_strike() {
        //given
        MockedStatic<Randoms> mock = mockStatic(Randoms.class);
        mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
        GameModel gameModel = new GameModel();
        gameModel.initGame();

        //when
        GameRecord gameRecord = gameModel.calculateScore("461");

        //then
        assertThat(gameRecord.toString()).isEqualTo("1스트라이크");

    }



    @Test
    @DisplayName("점수가 3스트라이크면 true리턴")
    void returns_true_when_score_is_3strike() {
        //given
        MockedStatic<Randoms> mock = mockStatic(Randoms.class);
        mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
        GameModel gameModel = new GameModel();
        gameModel.initGame();



        //when
        GameRecord gameRecord = gameModel.calculateScore("568");


        assertThat(gameModel.isEnd(gameRecord)).isTrue();
        mock.close();


    }

    @Test
    @DisplayName("점수가 3스트라이크 아니면 false리턴")
    void returns_false_when_score_isnot_3strike() {
        //given
        MockedStatic<Randoms> mock = mockStatic(Randoms.class);
        mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
        GameModel gameModel = new GameModel();
        gameModel.initGame();



        //when
        GameRecord gameRecord = gameModel.calculateScore("178");


        assertThat(gameModel.isEnd(gameRecord)).isFalse();
        mock.close();



    }






}
