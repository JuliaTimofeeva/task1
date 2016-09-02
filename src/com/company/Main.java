package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {



    static ArrayList<String> outStr = new ArrayList<String>();
    
    static List<String> nums = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".");
    
    static List<String> operands = Arrays.asList("+","-","/","*","(",")");

    public static void main(String[] args) throws Exception {
        // write your code here

        try{
            System.out.print("Введите арифметическое выражение:\n");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inStr = reader.readLine();

            Stack oper = new Stack();


            int k = inStr.length();
            int i = 0;
            Object s;
            String out = "";

            while (i < k)
            {
                if (nums.contains(String.valueOf(inStr.charAt(i)))) {

                    String num = "";

                    while (i < k && nums.contains(String.valueOf(inStr.charAt(i)))) { 
                        num += String.valueOf(inStr.charAt(i++));
                    }

                    outStr.add(num); 
                    continue; 
                }

                if (String.valueOf(inStr.charAt(i)).equals("(")) {
                    oper.push(String.valueOf(inStr.charAt(i)));
                }
                String st = String.valueOf(inStr.charAt(i));
                if (st.equals("+") || st.equals("-") || st.equals("*") || st.equals("/")) {

                    while ((!oper.empty()) && (priority(String.valueOf(inStr.charAt(i))) <= (priority((String) oper.peek())))) {
                        out = (String) oper.pop();
                        outStr.add(out);
                    }
                    oper.push(String.valueOf(inStr.charAt(i)));
                }

                if (String.valueOf(inStr.charAt(i)).equals(")")) {
                    int l = oper.search("(");
                    while (l > 0) {
                        s = oper.pop();
                        outStr.add(s.toString());
                        l--;
                    }
                    //убираем последний элемент
                    int size = outStr.size() - 1;
                    outStr.remove(size);
                }

                i++;
            }

            while (!oper.empty()) {
                s = oper.pop();
                outStr.add(s.toString());
            }


            double res = 0;
            double g = 0;
            double j = 0;
            int h = outStr.size();
            i = 0;

            while (i < h) {
                if (operands.contains(outStr.get(i))) 
                {
                    g = Double.parseDouble(oper.pop().toString()); 
                    j = Double.parseDouble(oper.pop().toString()); 


                    switch (outStr.get(i).toString())   
                    {                                   
                        case "+":
                            res = j + g;         
                            break;
                        case "-":
                            res = j - g;
                            break;
                        case "*":
                            res = j * g;
                            break;
                        case "/":
                            res = j / g;
                            break;
                    }

                    oper.push(res);
                } else { 
                    oper.push(Double.parseDouble(outStr.get(i))); 
                }
                i++;
            }
            res = Double.parseDouble(oper.pop().toString());
            double newRes = new BigDecimal(res).setScale(4, RoundingMode.UP).doubleValue();
           //System.out.println(outStr);
           //System.out.println(oper);
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

