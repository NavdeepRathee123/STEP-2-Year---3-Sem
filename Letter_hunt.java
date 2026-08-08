import java.util.*;
class Letter_hunt
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a String");
        String a=sc.nextLine();
        char hash[]=new char[256];
        for(int i=0;i<a.length();i++)
        {
            hash[a.charAt(i)]+=1;
        }
        for(int i=1;i<256;i++)
        {
            if(hash[i]==1)
            {
                System.out.println((char)i+" is unique");
            }
        }
    }
}