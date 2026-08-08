import java.util.*;
class File_Validator
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter a Filename");
        String n=sc.nextLine();
        String sub_string = n.substring(n.lastIndexOf(".")+1);
        if(sub_string.equalsIgnoreCase("pdf")||sub_string.equalsIgnoreCase("docx")||sub_string.equalsIgnoreCase("zip"))
        {
            System.out.println("Accepted");
        }
        else
        {
            System.out.println("Rejected - Invalid");
        }
    }
}