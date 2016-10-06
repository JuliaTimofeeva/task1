package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**1234**/

public class Main {


    //выходная строка
    static ArrayList<String> outStr = new ArrayList<String>();
    //список цифр
    static List<String> nums = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".");
    //список операторов и скобок
    static List<String> operands = Arrays.asList("+","-","/","*","(",")");

    public static void main(String[] args) throws Exception {


        try{
            System.out.print("Введите арифметическое выражение:\n");


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inStr = reader.readLine();

            Stack stackVal = new Stack();


            int lengthMathExpression = inStr.length();//длина введенного выражения
            int i = 0;
            Object character;
            String upElement = "";


            while (i < lengthMathExpression)
            {
                if (nums.contains(String.valueOf(inStr.charAt(i)))) {

                    String digitsOrPoint = "";

                    while (i < lengthMathExpression && nums.contains(String.valueOf(inStr.charAt(i)))) { 
                        digitsOrPoint += String.valueOf(inStr.charAt(i++));
                    }

                    outStr.add(digitsOrPoint); 
                    continue; 
                }

                if (String.valueOf(inStr.charAt(i)).equals("(")) {
                    stackVal.push(String.valueOf(inStr.charAt(i)));
                }
                String st = String.valueOf(inStr.charAt(i));
                if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/")) {

                    while ((!stackVal.empty()) && (priority(String.valueOf(inStr.charAt(i))) <= (priority((String) stackVal.peek())))) {
                        upElement = (String) stackVal.pop();
                        outStr.add(upElement);
                    }
                    stackVal.push(String.valueOf(inStr.charAt(i)));
                }

                if (String.valueOf(inStr.charAt(i)).equals(")")) {
                    int stepsToOpenParenthesis = stackVal.search("(");
                    while (stepsToOpenParenthesis > 0) {
                        character = stackVal.pop();
                        outStr.add(character.toString());
                        stepsToOpenParenthesis--;
                    }
                    
                    int size = outStr.size() - 1;
                    outStr.remove(size);
                }

                i++;
            }

            while (!stackVal.empty()) {
                character = stackVal.pop();
                outStr.add(character.toString());
            }



            double res = 0;
            double a = 0;
            double b = 0;
            int sizeOutStr = outStr.size();
            i = 0;
            

            while (i < sizeOutStr) {
                if (operands.contains(outStr.get(i))) 
                {
                    a = Double.parseDouble(stackVal.pop().toString());
                    b = Double.parseDouble(stackVal.pop().toString()); 


                    switch (outStr.get(i).toString())   
                    {                                   
                        case "+":
                            res = b + a;         
                            break;
                        case "-":
                            res = b - a;
                            break;
                        case "*":
                            res = b * a;
                            break;
                        case "/":
                            res = b / a;
                            break;
                    }

                    stackVal.push(res);
                } else { 
                    stackVal.push(Double.parseDouble(outStr.get(i))); 
                }
                i++;
            }
            res = Double.parseDouble(stackVal.pop().toString());
            double newRes = new BigDecimal(res).setScale(4, RoundingMode.UP).doubleValue();
            System.out.println(outStr);
            System.out.println(stackVal);
            System.out.print(newRes);
        }catch (Exception e){

            System.out.print("Выражение невозможно вычислить");
        }



    }

    public static int priority(String op)
    {

        if ((op.equals("*")) || (op.equals("/"))) {
            return 3;
        } else if ((op.equals("+")) || (op.equals("-"))) {
            return 2;
        } else if (op.equals("(")) {
            return 1;
        } else
            return -1;

    }
}

