
public class QuickFindUF
{
    // id[i] is the connected group of element i
    private int[] id;

    // constructor: initialise group of size N with no connections
    public QuickFindUF(int N)
    {
        // initialise array member (pointer)
        id = new int[N];
        // give each element its own group (disconnected)
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // test for connectedness: return True if members p & q are connected
    public boolean connected(int p, int q)
    { return id[p] == id[q]; }

    // connect two members by creating the union of their groups
    public void union(int p, int q)
    {
        int p_group = id[p];
        int q_group = id[q];
        // change all p_group members to q_group
        for (int i = 0; i < id.length; i++)
            if (id[i] == p_group) id[i] = q_group;
    }
}
