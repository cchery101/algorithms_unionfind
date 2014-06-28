
public class Percolation {
    // create N-by-N grid, with all sites blocked
    private boolean[] sites;
    private WeightedQuickUnionUF graph;
    private WeightedQuickUnionUF graphfull;
    private int size;

    public Percolation(int N)
    {
        if (N <= 0)  throw new java.lang.IllegalArgumentException();
        size = N;
        sites = new boolean[N * N + 2];
        for (int i = 0; i < N * N + 2; i++)
            { sites[i] = false; }
        graph = new WeightedQuickUnionUF(N * N + 2);
        graphfull = new WeightedQuickUnionUF(N * N + 2);
    }
    // index conversion and checking
    private int gridtounion(int i, int j) {
        return (i - 1) * size + j;
    }
    private void checkindices(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size)
            { throw new java.lang.IndexOutOfBoundsException(); }
    }
    // open site (row i, column j) if it is not already
    public void open(int i, int j)
    {
        checkindices(i, j);
        // mark site as open
        int siteindex = gridtounion(i, j);
        sites[siteindex] = true;
        // connect it to its 4 neighouring sites (or top/bottom)
        if (i == 1) graph.union(siteindex, 0);
        if (i == size) graph.union(siteindex, size * size + 1);
        if (i > 1 && isOpen(i-1, j))    graph.union(siteindex, siteindex - size);
        if (i < size && isOpen(i+1, j)) graph.union(siteindex, siteindex + size);
        if (j > 1 && isOpen(i, j-1))    graph.union(siteindex, siteindex - 1);
        if (j < size && isOpen(i, j+1)) graph.union(siteindex, siteindex + 1);
        // keep track of connections to top only (for fullness)
        if (i == 1) graphfull.union(siteindex, 0);
        if (i > 1 && isOpen(i-1, j))    graphfull.union(siteindex, siteindex - size);
        if (i < size && isOpen(i+1, j)) graphfull.union(siteindex, siteindex + size);
        if (j > 1 && isOpen(i, j-1))    graphfull.union(siteindex, siteindex - 1);
        if (j < size && isOpen(i, j+1)) graphfull.union(siteindex, siteindex + 1);
    }
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {
        checkindices(i, j);
        return sites[gridtounion(i, j)];
    }
    // is site (row i, column j) full (connected to top)?
    public boolean isFull(int i, int j)
    {
        checkindices(i, j);
        return graphfull.connected(0, gridtounion(i, j));
    }
    // does the system percolate?
    public boolean percolates()
    {
        return graph.connected(0, size * size + 1);
    }
}