package christmas;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest extends NsTest{

    @Test
    @DisplayName("메뉴 입력")
    void 메뉴_입력() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴 입력")
    void 메뉴_입력2() {
        assertSimpleTest(() -> {
            runException("3", "제로콜 라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴 입력")
    void 메뉴_입력3() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라#3-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴 입력")
    void 메뉴_입력4() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라3--1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    @DisplayName("메뉴 입력")
    void 메뉴_입력5() {
        assertSimpleTest(() -> {
            runException("3", "김다빠진제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}