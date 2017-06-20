package model;

import java.util.LinkedList;

/**
 * Created by anonymous on 05.06.2017.
 */
public class DataBase {
    private boolean isOperator(char symbol) {
        return symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' || symbol == '^';
    }

    private String optimizeInputString(String inputString) {
        inputString = inputString.replace(" ", "").replace("(-", "(0-");
        if (inputString.charAt(0) == '-') {
            inputString = "0" + inputString;
        }
        while (inputString.indexOf("sqrt") > -1) {
            int temp = 1;
            for (int i = inputString.indexOf("sqrt") + 5; i < inputString.length(); i++) {
                if (inputString.charAt(i) == '(') temp++;
                if (inputString.charAt(i) == ')') temp--;
                if (temp == 0) {
                    inputString = inputString.substring(0, i + 1) + "^0.5" + inputString.substring(i + 1);
                    break;
                }
            }
            inputString = inputString.substring(0, inputString.indexOf("sqrt")) + inputString.substring(inputString.indexOf("sqrt") + 4);
        }
        return inputString;
    }

    int priority(char operator) {
        if (operator == Operators.DEGREE)
            return 2;
        else if (operator == Operators.MULTIPLY || operator == Operators.DIVIDE || operator == Operators.MOD) {
            return 1;
        } else if (operator == Operators.PLUS || operator == Operators.MINUS) {
            return 0;
        } else {
            return -1;
        }
    }

    private void hangUp(LinkedList<Character> someOperators, LinkedList<Node> someNode) {
        Node node = new Node(someOperators.removeLast());
        Node someRight = someNode.removeLast();
        Node someLeft = someNode.removeLast();
        node.setRight(someRight);
        node.setLeft(someLeft);
        double dRight = Double.parseDouble(someRight.getValue());
        double dLeft = Double.parseDouble(someLeft.getValue());
        char operator = node.getOperator();
        switch (operator) {
            case Operators.PLUS:
                node.setValue(String.valueOf(dRight + dLeft));
                break;
            case Operators.MINUS:
                node.setValue(String.valueOf(dRight - dLeft));
                break;
            case Operators.MULTIPLY:
                node.setValue(String.valueOf(dRight * dLeft));
                break;
            case Operators.DIVIDE:
                node.setValue(String.valueOf(dRight / dLeft));
                break;
            case Operators.MOD:
                node.setValue(String.valueOf(dRight % dLeft));
                break;
            case Operators.DEGREE:
                node.setValue(String.valueOf(Math.pow(dRight, dLeft)));
                break;
            default:
                System.out.println("Oops");
        }
        someNode.add(node);
    }

    public Node buildNodes(String inputString) {
        inputString = optimizeInputString(inputString);
        LinkedList<Node> someNode = new LinkedList<>();
        LinkedList<Character> someOperators = new LinkedList<>();
        Node node;
        for (int i = 0; i < inputString.length(); i++) {
            char symbol = inputString.charAt(i);
            if (symbol == '(') {
                someOperators.add('(');
            } else if (symbol == ')') {
                while (someOperators.getLast() != '(') {
                    hangUp(someOperators, someNode);
                }
                someOperators.removeLast();
            } else if (isOperator(symbol)) {
                while (!someOperators.isEmpty() &&
                        priority(someOperators.getLast()) >= priority(symbol)) {
                    hangUp(someOperators, someNode);
                }
                someOperators.add(symbol);
            } else {
                String operand = "";
                while (i < inputString.length() &&
                        (Character.isDigit(inputString.charAt(i)) || inputString.charAt(i) == '.')) {
                    operand += inputString.charAt(i++);
                }
                --i;
                node = new Node(operand);
                someNode.add(node);
            }
        }
        while (!someOperators.isEmpty()) {
            hangUp(someOperators, someNode);
        }
        return someNode.get(0);
    }
}
