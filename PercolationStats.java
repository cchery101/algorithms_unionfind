
public class PercolationStats {
    private double[] threshold;
    private int numtests;
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T)
    {
        numtests = T;
        int i;
        int j;
        Percolation myobj;
        if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();
        threshold = new double[T];
        for (int test = 0; test < T; test++)
        {
            myobj = new Percolation(N);
            threshold[test] = 0;
            while (!myobj.percolates())
            {
                // open random sites while there is no percolation
                i = StdRandom.uniform(N);
                j = StdRandom.uniform(N);
                if (!myobj.isOpen(i + 1, j + 1)) {
                    myobj.open(i + 1, j + 1);
                    threshold[test] += 1;
                }
            }
            threshold[test] = threshold[test] / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() { return StdStats.mean(threshold); }
    // sample standard deviation of percolation threshold
    public double stddev() { return StdStats.stddev(threshold); }
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() { return mean() - 1.96 * stddev()
        / Math.sqrt(numtests); }
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() { return mean() + 1.96 * stddev()
        / Math.sqrt(numtests); }
   
    // test client
    public static void main(String[] args)
    {
        PercolationStats testobj = new PercolationStats(
            Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(String.format(
            "mean =                    %15.15f",
            testobj.mean()));
        System.out.println(String.format(
            "stddev =                  %15.15f",
            testobj.stddev()));
        System.out.println(String.format(
            "95%% confidence interval = %15.15f, %15.15f",
            testobj.confidenceLo(), testobj.confidenceHi()));
    }
}
