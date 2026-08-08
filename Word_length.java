import java.util.*;
class Word_length
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a String");
        String n=sc.nextLine();
        String a[]=n.trim().split(" ");
        String min=a[0] , max=a[0];
        for(int i=0;i<a.length;i++)
        {
            if(a[i].length()<min.length())
            {
                min=a[i];
            }
            if(a[i].length()>max.length())
            {
                max=a[i];
            }
        }
        System.out.println("Longest Word : "+max);
        System.out.println("Smallest Word : "+min);
    }
}   