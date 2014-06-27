 
public class QuickFindUF_Test
{
    public static void main(String[] argv)
    {
        System.out.println("Hello World!");
        QuickFindUF myobj = new QuickFindUF(10);
        System.out.println(myobj.connected(1,2));
        System.out.println(myobj.connected(1,3));
        myobj.union(1,2);
        System.out.println(myobj.connected(1,2));
        System.out.println(myobj.connected(1,3));
        myobj.union(2,3);
        System.out.println(myobj.connected(1,3));
    }
}