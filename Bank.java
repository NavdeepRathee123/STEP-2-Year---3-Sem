import java.util.*;
class Bank
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a String");
        String n=sc.nextLine();
        StringBuilder sb=new StringBuilder(n.trim());
        for(int i=0;i<3;i++)
        {
            char temp=Character.toUpperCase(sb.charAt(i));
            sb.setCharAt(i,temp);
        }
        if(sb.length()!=14)
        {
            System.out.println("Invalid : Wrong Length");
            return; 
        }
        for(int i=0;i<3;i++)
        {
            if(!Character.isLetter(sb.charAt(i)))
            {
                System.out.println("Invalid : Bank Code Should be Letters");
                return;
            }
        }
        for(int i=3;i<14;i++)
        {
            if(!Character.isDigit(sb.charAt(i)))
            {
                System.out.println("Invalid : There Should be 11 Digits in the bank Code");
                return;
            }
        }
        System.out.println("["+sb.substring(0,3)+"]");
        System.out.println("Date : "+sb.substring(3,5)+"/"+sb.substring(5,7)+"/"+sb.substring(7,9));
        System.out.println("SEQ : "+sb.substring(9));
    }
}