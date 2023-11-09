package christmas.validate;

public class Validator {
    public static void validateDate(String date) {
        if(!date.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        int day = Integer.parseInt(date);
        if(day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
