import javax.swing.text.IconView;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;
public class Main {
     static String arabicToRoman(int number) {
        if( number <= 0) {
            throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
        }
        StringBuilder builder = new StringBuilder();
        Roman[] values = Roman.values();
        for (int i = values.length - 1; i >= 0; i--) {
            while (number >= values[i].value) {
                builder.append(values[i]);
                number -= values[i].value;
            }
        }
        return builder.toString();
    }
    static int romanToArabic(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)) {
                case 'I':
                    sum++;
                    break;
                case 'V':
                    if (sum == 0 || number.charAt(i-1) != 'I') {
                        sum += 5;
                    } else {
                        sum += 3;
                    }
                    break;
                case 'X':
                    if (sum == 0 || number.charAt(i-1) != 'I') {
                        sum += 10;
                    } else {
                        sum += 8;
                    }
                    break;
                case 'L':
                    if (sum == 0 || number.charAt(i-1) != 'X') {
                        sum += 50;
                    } else {
                        sum += 30;
                    }
                    break;
                case 'C':
                    if (sum == 0 || number.charAt(i-1) != 'X') {
                        sum += 100;
                    } else {
                        sum += 80;
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Невозможно конвертировать");
            }
        }
        return sum;
    }

    public static String calc(String input){
        String multiply="*";
        String divide="/";
        String plus="+";
        String minus="-";
        String[] inputs=input.split(" ");
        if (inputs.length!=3) throw new IllegalArgumentException("Неверный формат ввода");
        String sign = inputs[1];
        if ((!sign.equals(multiply)&&!sign.equals(divide)&&!sign.equals(minus)&&!sign.equals(plus))) throw new IllegalArgumentException("Неверная операция");
        int c=0;
        if (inputs[0].matches("[0-9]+")&&inputs[2].matches("[IXCVL]+")) throw new IllegalArgumentException("Неверный формат ввода");
        if (inputs[0].matches("[IXCVL]+")&&inputs[2].matches("[0-9]+")) throw new IllegalArgumentException("Неверный формат ввода");
        if (inputs[0].matches("[^IXCVL0-9]+")||inputs[2].matches("[^IXCVL0-9]+")) throw new IllegalArgumentException("Неверный формат ввода");
        if (inputs[0].contains(".")||inputs[2].contains(".")) throw new IllegalArgumentException("Неверный формат ввода");
        if (inputs[0].contains(",")||inputs[2].contains(",")) throw new IllegalArgumentException("Неверный формат ввода");
        if (inputs[0].matches("[IXCVL]+")&&inputs[2].matches("[IXCVL]+")) {
            int a= romanToArabic(inputs[0]);
            int b = romanToArabic(inputs[2]);
            if (a>10||b>10||a<0||b<0) throw new IllegalArgumentException("Введенные числа находится вне диапазона от 1 до 10 ");
            if(sign.equals(plus)) c=a+b;
            else if (sign.equals(minus)) c=a-b;
            else if (sign.equals(divide)) c=a/b;
            else if (sign.equals(multiply)) c=a*b;
            return arabicToRoman(c);
        }
        else if(inputs[0].matches("[0-9]+")&&inputs[2].matches("[0-9]+")){
            int a = Integer.valueOf(inputs[0]);
            int b = Integer.valueOf(inputs[2]);
            if (a>10||b>10||a<=0||b<=0) throw new RuntimeException("Введенные числа находятся вне диапазона от 1 до 10 ");
            if(sign.equals(plus)) c=a+b;
            else if (sign.equals(minus)) c=a-b;
            else if (sign.equals(divide)) c=a/b;
            else if (sign.equals(multiply)) c=a*b;
            return Integer.toString(c);
        }
        return null;
    }

public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    System.out.println("Input: ");
    String input = in.nextLine().toUpperCase();
    System.out.println("Output: ");
    System.out.println(calc(input));

    }
}
