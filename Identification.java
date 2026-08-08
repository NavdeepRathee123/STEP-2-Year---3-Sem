import java.util.*;
class Identification
{
    public String check(String n)
    {
        if(n.startsWith("VIP"))
        {
            return "VIP";
        }
        else
        {
            return "Regular";
        }
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter your ID");
        String Id=sc.nextLine();
        Identification obj=new Identification();
        String result=obj.check(Id);
        if(result.equals("VIP"))
        {
            System.out.println("VIP Costumer");
        }
        else
        {
            System.out.println("Regular Costumer");
        }
    }
}