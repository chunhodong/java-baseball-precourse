package baseball;

import baseball.exception.ExceptionMessage;
import baseball.model.GameModel;
import baseball.model.GameRecord;
import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

public class GameModelTest {

    @Nested
    @DisplayName("점수계산을 위한 calculateScore메소드는")
    class CalculateScore{

        @Nested
        @DisplayName("입력값 검증에서")
        class ValidationInput{
            @Test
            @DisplayName("NULL값이면 예외발생")
            void throws_exception_when_params_null() {

                //given
                GameModel gameModel = new GameModel();

                //when,then
                assertThatThrownBy(() -> gameModel.calculateScore(null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ExceptionMessage.EMPTY_STRING);


            }

            @Test
            @DisplayName("빈값이면 예외발생")
            void throws_exception_when_params_empty() {
                //given
                GameModel gameModel = new GameModel();

                //when,then
                assertThatThrownBy(() -> gameModel.calculateScore(""))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ExceptionMessage.EMPTY_STRING);
            }

            @Test
            @DisplayName("1에서 9사이 숫자가 아니면 예외발생")
            void throws_exception_when_params_outofrange() {
                //given
                GameModel gameModel = new GameModel();

                //when,then
                assertThatThrownBy(() -> gameModel.calculateScore("032"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ExceptionMessage.NOT_ALLOW_CHARACTER);

            }

            @Test
            @DisplayName("3자리 수 아니면 예외발생")
            void throws_exception_when_params_numbersize_not_3() {
                //given
                GameModel gameModel = new GameModel();

                //when,then
                assertThatThrownBy(() -> gameModel.calculateScore("12234"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ExceptionMessage.INVALID_GAMEBALL_SIZE);

            }

            @Test
            @DisplayName("중복숫자포함이면 예외발생")
            void throws_exception_when_params_duplicate_number() {
                //given
                GameModel gameModel = new GameModel();

                //when,then
                assertThatThrownBy(() -> gameModel.calculateScore("115"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ExceptionMessage.INVALID_GAMEBALL_SIZE);

            }

        }

        @Nested
        @DisplayName("리턴값 확인에서")
        class CheckReturn{
            @Test
            @DisplayName("3자리 수 입력하면 점수객체리턴")
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
            @DisplayName("같은 수가 같은 자리에 있으면 스트라이크")
            void returns_stirke_only_when_params_include_strike() {
                //given
                MockedStatic<Randoms> mock = mockStatic(Randoms.class);
                mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
                GameModel gameModel = new GameModel();
                gameModel.initGame();

                //when
                GameRecord gameRecord = gameModel.calculateScore("568");

                //then
                assertThat(gameRecord.toString()).isEqualTo("3스트라이크");
                mock.close();
            }

            @Test
            @DisplayName("같은 수가 다른 자리에 있으면 볼")
            void returns_ball_only_when_params_include_ball() {
                //given
                MockedStatic<Randoms> mock = mockStatic(Randoms.class);
                mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
                GameModel gameModel = new GameModel();
                gameModel.initGame();

                //when
                GameRecord gameRecord = gameModel.calculateScore("843");

                //then
                assertThat(gameRecord.toString()).isEqualTo("1볼");
                mock.close();
            }

            @Test
            @DisplayName("같은자리 같은수, 다른 자리 같은 수가 모두포함이면 스트라이크,볼")
            void returns_strike_ball_when_params_include_strike_and_ball() {
                //given
                MockedStatic<Randoms> mock = mockStatic(Randoms.class);
                mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
                GameModel gameModel = new GameModel();
                gameModel.initGame();

                //when
                GameRecord gameRecord = gameModel.calculateScore("586");

                //then
                assertThat(gameRecord.toString()).isEqualTo("2볼 1스트라이크");
                mock.close();

            }

            @Test
            @DisplayName("같은 수가 없으면 낫싱")
            void returns_nothing_when_params_not_include_any() {
                //given
                MockedStatic<Randoms> mock = mockStatic(Randoms.class);
                mock.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .thenReturn(5, Arrays.stream(new Integer[]{6,8}).toArray());
                GameModel gameModel = new GameModel();
                gameModel.initGame();

                //when
                GameRecord gameRecord = gameModel.calculateScore("173");

                //then
                assertThat(gameRecord.toString()).isEqualTo("낫싱");
                mock.close();
            }
        }

    }


    @Nested
    @DisplayName("종료확인을 위한 isEnd메소드는")
    class IsEnd{

        @Test
        @DisplayName("3스트라이크면 true리턴")
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
        @DisplayName("3스트라이크 아니면 false리턴")
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







}
