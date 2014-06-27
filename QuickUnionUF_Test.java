 
import java.util.Arrays;

public class QuickUnionUF_Test
{
    public static void main(String[] argv)
    {
        System.out.println("Hello World!");
        QuickUnionUF myobj = new QuickUnionUF(10);
        System.out.println(myobj.connected(1,1));  // true
        System.out.println(myobj.connected(1,2));  // false
        System.out.println(myobj.connected(1,3));  // false
        myobj.union(1,2);
        System.out.println(myobj.connected(1,2));  // true - direct
        System.out.println(myobj.connected(1,3));  // false
        myobj.union(2,3);
        System.out.println(myobj.connected(1,3));  // true - transitive

        QuickUnionUF testobj = new QuickUnionUF(10);
        testobj.union(0,4);
        testobj.union(0,7);
        testobj.union(5,8);
        testobj.union(2,9);
        testobj.union(7,6);
        testobj.union(3,0);
        testobj.union(8,2);
        testobj.union(3,1);
        testobj.union(7,9);
        System.out.println(Arrays.toString(testobj.id));
        System.out.println(Arrays.toString(testobj.sz));
    }
}