import exceptions.BigNum;

public class Convert {

    public static String romanNum(int number) {
        int[] roman_value_list = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i++) {
            while (number >= roman_value_list[i]){
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }

//    public static String con(int num){
//        switch (num){
//            case(0): return "0";
//            case(1): return "I";
//            case(2): return "II";
//            case(3): return "III";
//            case(4): return "IV";
//            case(5): return "V";
//            case(6): return "VI";
//            case(7): return "VII";
//            case(8): return "VIII";
//            case(9): return "IX";
//            case(10): return "X";
//            default: return "";
//        }
//
//    }
}
