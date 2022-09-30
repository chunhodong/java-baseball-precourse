package baseball;

import baseball.model.GameModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameModelTest {


    @Test
    @DisplayName("초기화후 요소가3개인 리스트객체가 생성된다")
    void create_computernumbers() throws NoSuchFieldException, IllegalAccessException {

        //given
        GameModel gameModel = new GameModel();

        //when
        gameModel.initGame();
        Field field = gameModel.getClass().getDeclaredField("computerNumbers");
        field.setAccessible(true);
        List<Integer> computerNumbers = (List<Integer>) field.get(gameModel);

        //then
        assertThat(computerNumbers.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("점수계산에서 NULL값입력하면 예외발생")
    void throws_exception_when_params_null() {

    }

    @Test
    @DisplayName("점수계산에서 빈값입력하면 예외발생")
    void throws_exception_when_params_empty() {

    }


    @Test
    @DisplayName("점수계산에서 문자가하나이상포함이면 예외발생")
    void throws_exception_when_params_include_character() {

    }

    @Test
    @DisplayName("점수계산에서 숫자가3자리가아니면 예외발생")
    void throws_exception_when_params_numbersize_not_3() {

    }





}
