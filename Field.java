import java.util.*;
class Field
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter student registration data");
        String n=sc.nextLine();
        String a[]=n.split(",");
        if(a.length!=3)
        {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Name : "+a[0]);
        System.out.println("Roll no. : "+a[1]);
        System.out.println("Dept : "+a[2]);
    }
}