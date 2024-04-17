package org.example;
import java.util.Scanner;
public class HexaToDeci {
    public static void main(String[] args) {

        String hexa;
        int deci=0,i,j=0,len=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter any hexadecimal number: ");
        hexa=sc.nextLine();
        len= hexa.length(); //for calculating  0 place


        for(i=len-1;i>=0;i--){
            if(hexa.charAt(i)>='0' && hexa.charAt(i)<='9'){
                deci+=((hexa.charAt(i)-48) * Math.pow(16,j));
                j++;
            }
            else if(hexa.charAt(i)>='A' && hexa.charAt(i)<='F'){
                deci+=((hexa.charAt(i)-55) * Math.pow(16,j));
                j++;
            }
        }
        System.out.println("Decimal value of "+hexa+" = "+deci);
    }
}
