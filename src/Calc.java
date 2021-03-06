import exceptions.CheckNumberException;
import exceptions.NullDivException;
import exceptions.WrongOperationException;

import java.util.HashMap;
import java.util.Scanner;

public class Calc {

    private Calc(){

        while (true){
            System.out.println("ВВЕДИТЕ ОПРЕАЦИЮ ЧЕРЕЗ ПРОБЕЛ (ЧИСЛА ОТ 0 ДО 10 ВКЛЮЧИТЕЛЬНО):");
            Scanner scan = new Scanner(System.in);

            String[] str = scan.nextLine().split(" ");
            if(str[0].equals("exit")){
                System.exit(0);
            }

            int first = 0;
            int second = 0;
            String op = str[1];

            for(Roma r : Roma.values()){
                if(r.getKey().equals(str[0])){
                    first = num(r.getKey());
                }
                if(r.getKey().equals(str[2])){
                    second = num(r.getKey());
                }
            }

            if(first != 0 && second != 0){
                try {
                    String result = Convert.romanNum(operation(first, second, op));
                    System.out.println(result);
                } catch (WrongOperationException e) {
                    System.err.println(e.getMessage());
                }
            }

            if(first == 0 && second == 0){
                first = Integer.parseInt(str[0]);
                second = Integer.parseInt(str[2]);
                try {
                    checkNumber(first, second);

                } catch (CheckNumberException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                try {
                    int result = operation(first, second, op);
                    System.out.println(result);

                } catch (WrongOperationException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private int operation(int first, int second, String op) throws WrongOperationException {
        switch (op){
            case("+"): return Action.sum(first,second);
            case("-"): return Action.diff(first,second);
            case("*"): return Action.multi(first,second);
            case("/"):
                try {
                   return Action.div(first,second);
                } catch (NullDivException e) {
                    System.err.println(e.getMessage());
                }
            default: throw new WrongOperationException("НЕВЕРНАЯ ОПЕРАЦИЯ !");
        }
    }

    private void checkNumber(int first, int second) throws CheckNumberException {
        if((first < 0 || first > 10) || (second < 0 || second > 10)){
            throw new CheckNumberException("ВВЕДЕННОЕ ЧИСЛО НЕ ДОЛЖНО БЫТЬ БОЛЬШЕ 10 И МЕНЬШЕ ЧЕМ 0");
        }
    }

    private int num(String n) {
        HashMap<Character, Integer> romeNumbers = new HashMap<>();


        romeNumbers.put('I', 1);
        romeNumbers.put('V', 5);
        romeNumbers.put('X', 10);
        romeNumbers.put('L', 50);
        romeNumbers.put('C', 100);
        romeNumbers.put('D', 500);
        romeNumbers.put('M', 1000);

        int tmp = 0;
        int res = 0;

        for (char c : n.toCharArray()) {

            int v = romeNumbers.get(c);
            if (v < tmp) {
                res += tmp;
                tmp = v;
            } else if (v > tmp) {
                if (tmp == 0)
                    tmp = v;
                else {
                    res += v - tmp;
                    tmp = 0;
                }
            } else if (v == tmp) {
                res += v + tmp;
                tmp = 0;
            }
        }
        return res + tmp;
    }

    public static void main(String[] args) {

            new Calc();

    }
}
