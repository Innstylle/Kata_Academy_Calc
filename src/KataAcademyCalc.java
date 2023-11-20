import java.util.Scanner;

public class KataAcademyCalc {
    public static void main(String[] args) throws ScException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws ScException {
        int number1 = 0;
        int number2 = 0;
        String rezult;
        boolean itsRoman = false;
        String[] operand = input.split(" ");

        if (operand.length == 3) {
        } else if (operand.length < 3) {
            throw new ScException("Cтрока не является математической операцией");
        } else if (operand.length > 3) {
            throw new ScException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)\";");
        }

        String operator = operand[1];
        if (!Roman.itsRoman(operand[0]) && !Roman.itsRoman(operand[2])) {
            number1 = Integer.parseInt(operand[0]);
            number2 = Integer.parseInt(operand[2]);
        } else if (Roman.itsRoman(operand[0]) && Roman.itsRoman(operand[2])) {
            number1 = Roman.toArabic(operand[0]);
            number2 = Roman.toArabic(operand[2]);
            itsRoman = true;
        } else if (!Roman.itsRoman(operand[0]) && Roman.itsRoman(operand[2])) {
            throw new ScException("Используются одновременно разные системы счисления");
        } else if (Roman.itsRoman(operand[0]) && !Roman.itsRoman(operand[2])) {
            throw new ScException("Используются одновременно разные системы счисления");
        }
        if (number1 > 10) {
            throw new ScException("Строка должна принимать на вход числа менее 10!");
        } else if (10 < number2) {
            throw new ScException("Строка должна принимать на вход числа менее 10!");
        }

        int arabic = calculation(number1, number2, operator);

        if (!itsRoman) {
            rezult = String.valueOf(arabic);
            input = rezult;
        } else if (itsRoman) {
            try {
                rezult = Roman.getArrayToRoman(arabic);
                if (0 >= arabic) {
                    throw new ScException("В римской системе нет числа 0!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {

                throw new ScException("В римской системе нет отрицательных чисел");
            }
            input = rezult;
        }

        return input;
    }

    public static int calculation(int a, int b, String op) {
        if (op.equals("+")) return a + b;
        if (op.equals("-")) return a - b;
        if (op.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    public static String[] arrayToRoman = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
            "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII",
            "XLVIII", "XLIX", "L"};

    public static int toArabic(String roman) {
        for (int i = 0; i < arrayToRoman.length; i++) {
            if (roman.equals(arrayToRoman[i])) {
                return i;
            }
        }

        return -1;
    }

    public static boolean itsRoman(String s) {
        for (int i = 0; i < arrayToRoman.length; i++) {
            if (s.equals(arrayToRoman[i])) {
                return true;
            }

        }
        return false;
    }

    public static String getArrayToRoman(int arabic) {
        return arrayToRoman[arabic];
    }
}