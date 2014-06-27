
public class QuickUnionUF
{
    // id[i] is the connected group of element i
    public int[] id;
    public int[] sz;

    // constructor: initialise group of size N with no connections
    public QuickUnionUF(int N)
    {
        // initialise array member (pointer)
        id = new int[N];
        sz = new int[N];
        // give each element its own group (disconnected)
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // return p's root, by moving recursively up through id tree
    private int root(int p)
    {
        while (p != id[p])
        {
            // tree compression - halve the path length
            //id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    // test for connectedness: return true if members p & q have same root
    public boolean connected(int p, int q)
    { return root(p) == root(q); }

    // connect two members by connecting their roots
    public void union(int p, int q)
    {
        int root_p = root(p);
        int root_q = root(q);
        if (root_p == root_q) return;
        // connect the smaller tree under the bigger tree
        if (sz[root_p] < sz[root_q]) {
            id[root_p] = root_q;
            sz[root_q] += sz[root_p];
        }
        else {
            id[root_q] = root_p;
            sz[root_p] += sz[root_q];
        }
    }
}
