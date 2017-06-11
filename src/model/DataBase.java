package model;

import java.util.LinkedList;

/**
 * Created by anonymous on 05.06.2017.
 */
public class DataBase {
    private boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' || symbol == '^';
    }

    int priority(char operator) {
        if (operator == '^')
            return 2;
        else if (operator == '*' || operator == '/' || operator == '%') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    void letGo(LinkedList<Double> st, char operator) {
        double someOne = st.removeLast();
        double someTwo = st.removeLast();
        switch (operator) {
            case Operators.PLUS:
                st.add(someTwo + someOne);
                break;
            case Operators.MINUS:
                st.add(someTwo - someOne);
                break;
            case Operators.MULTIPLY:
                st.add(someTwo * someOne);
                break;
            case Operators.DIVIDE:
                st.add(someTwo / someOne);
                break;
            case Operators.MOD:
                st.add(someTwo%someOne);
                break;
            case Operators.DEGREE:
                st.add(Math.pow(someTwo, someOne));
                break;
            default:
                System.out.println("Oops");
        }
    }

    public double eval(String inputString) {
        inputString = inputString.replace(" ", "").replace("(-", "(0-");
        if (inputString.charAt(0) == '-') {
            inputString = "0" + inputString;
        }
        while (inputString.indexOf("sqrt")>-1){
            int temp = 1;
            for (int i = inputString.indexOf("sqrt")+5; i<inputString.length();i++){
                if (inputString.charAt(i) == '(') temp++;
                if (inputString.charAt(i) == ')') temp--;
                if (temp==0) {
                    inputString = inputString.substring(0, i+1)+"^0.5"+inputString.substring(i+1);
                    break;
                }
            }
            inputString = inputString.substring(0, inputString.indexOf("sqrt"))+ inputString.substring(inputString.indexOf("sqrt")+4);
        }

        LinkedList<Double> someNumbers = new LinkedList<>();
        LinkedList<Character> someOperators = new LinkedList<>();
        for (int i = 0; i < inputString.length(); i++) {
            char symbol = inputString.charAt(i);
            if (symbol == '(') {
                someOperators.add('(');
            } else if (symbol == ')') {
                while (someOperators.getLast() != '(') {
                    letGo(someNumbers, someOperators.removeLast());
                }
                someOperators.removeLast();
            } else if (isOperator(symbol)) {
                while (!someOperators.isEmpty() &&
                        priority(someOperators.getLast()) >= priority(symbol)) {
                    letGo(someNumbers, someOperators.removeLast());

                }
                someOperators.add(symbol);
            } else {
                String operand = "";
                while (i < inputString.length() &&
                        (Character.isDigit(inputString.charAt(i)) || inputString.charAt(i) == '.')) {
                    operand += inputString.charAt(i++);
                }
                --i;
                someNumbers.add(Double.parseDouble(operand));
            }
        }
        while (!someOperators.isEmpty()) {
            letGo(someNumbers, someOperators.removeLast());

        }
        return someNumbers.get(0);

    }
}
