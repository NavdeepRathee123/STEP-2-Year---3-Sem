import java.util.*;
class Palindrome
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the String to be checked");
        String a=sc.nextLine();
        boolean check=true;
        for(int i=0;i<a.length()-1;i++)
        {
            if(a.charAt(i)!=a.charAt(a.length()-i-1))
            {
                check=false;
                break;
            }
        }
        if(check==true)
        {
            System.out.println(a+" is a palindrome String");
        }
        else
        {
            System.out.println(a+" is not a palindrome String");
        }
    }
}