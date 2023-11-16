package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 날짜_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("33");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 날짜_예외_테스트3() {
        assertSimpleTest(() -> {
            runException("-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("수량에 형식이 맞지 않을경우")
    void 주문_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라- a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("수량을 1이하 20초과로 입력했을 경우")
    void 주문_예외_테스트3() {
        assertSimpleTest(() -> {
            runException("3", "제로콜 라-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "제로콜 라-21");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("수량을 이 총 20이 넘었을 경우")
    void 주문_예외_테스트_수량초과() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-15,티본스테이크-7");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("특수문자를 입력했을 경우")
    void 주문_예외_테스트4() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라#3-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴 입력")
    void 주문_예외_테스트5() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라3--1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴에 없는 메뉴 입력시 예외")
    void 메뉴_입력5() {
        assertSimpleTest(() -> {
            runException("3", "김다빠진제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_음료만입력() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-15,레드와인-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 주문_예외_테스트_중복() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-15,티본스테이크-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_형식() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-15,,제로콜라-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_형식2() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-15,");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_형식3() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_형식4() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-2-티본스테이크-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
