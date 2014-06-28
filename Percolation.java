
public class Percolation {
    // create N-by-N grid, with all sites blocked
    private boolean[] sites;
    private WeightedQuickUnionUF graph;
    private int size;

    public Percolation(int N)
    {
        if (N <= 0)  throw new java.lang.IllegalArgumentException();
        size = N;
        sites = new boolean[N * N + 2];
        for (int i = 0; i < N * N + 2; i++)
            { sites[i] = false; }
        graph = new WeightedQuickUnionUF(N * N + 2);
    }
    // open site (row i, column j) if it is not already
    public void open(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > size || j > size)
            { throw new java.lang.IndexOutOfBoundsException(); }
        // mark site as open
        int siteindex = (i-1) * size + j;
        sites[siteindex] = true;
        // connect it to its 4 neighouring sites (or top/bottom)
        if (i == 1) graph.union(siteindex, 0);
        if (i == size) graph.union(siteindex, size * size + 1);
        if (i > 1 && isOpen(i-1, j))    graph.union(siteindex, siteindex - size);
        if (i < size && isOpen(i+1, j)) graph.union(siteindex, siteindex + size);
        if (j > 1 && isOpen(i, j-1))    graph.union(siteindex, siteindex - 1);
        if (j < size && isOpen(i, j+1)) graph.union(siteindex, siteindex + 1);
    }
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > size || j > size)
            { throw new java.lang.IndexOutOfBoundsException(); }
        return sites[(i-1) * size + j];
    }
    // is site (row i, column j) full (connected to top)?
    public boolean isFull(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > size || j > size)
            { throw new java.lang.IndexOutOfBoundsException(); }
        return graph.connected(0, (i-1) * size + j);
    }
    // does the system percolate?
    public boolean percolates()
    {
        return graph.connected(0, size * size + 1);
    }
    // test client
    public static void main(String[] args)
    {
        Percolation myobj = new Percolation(10);
    }
}