import java.util.*;
class Counter
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a String");
        String n=sc.nextLine();
        int vowel=0,consonant=0;
        for(int i=0;i<n.length();i++)
        {
            if(n.charAt(i)=='a'||n.charAt(i)=='A'||n.charAt(i)=='e'||n.charAt(i)=='E'||n.charAt(i)=='i'||n.charAt(i)=='I'||n.charAt(i)=='o'||n.charAt(i)=='O'||n.charAt(i)=='u'||n.charAt(i)=='U')
            {
                vowel++;
            }
            else if(n.charAt(i)!=' ');
            {
                consonant++;
            }
        }
        System.out.println("No. of Vowels : "+vowel);
        System.out.println("No. of constant : "+consonant);
    }
}