import java.util.Arrays;

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

        QuickFindUF testobj = new QuickFindUF(10);
        testobj.union(5,0);
        testobj.union(1,9);
        testobj.union(5,3);
        testobj.union(8,3);
        testobj.union(5,2);
        testobj.union(7,5);
        System.out.println(Arrays.toString(testobj.id));
    }
}