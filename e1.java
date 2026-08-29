import java.util.*;
class e2
{
    int x=10;
    static void show()
    {
        e2 obj1=new e2();
        System.out.println(obj1.x);
    }
}
class e1
{
    public static void main(String[] args)
    {
        e2 obj=new e2();
        e2.show();
    }
}