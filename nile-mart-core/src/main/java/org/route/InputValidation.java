package org.route;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputValidation {

    public static final Scanner SCANNER =new Scanner(System.in);

    public static String setValidString(String message){
        String name="";
        while (name.isBlank()){
            System.out.print(message);
            name= SCANNER.nextLine();
        }
        return name;
    }

    public static int setInt(String message){
        System.out.print(message);
        int num;
        boolean flag;
        while (!(flag=SCANNER.hasNextInt())||(num=SCANNER.nextInt())<=0){
            System.out.print("enter a valid integer number :  ");
            if(!flag)
                SCANNER.next();
        }
        SCANNER.nextLine();
        return num;
    }


}
