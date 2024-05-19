package islands.backend;



import java.util.ArrayList;

import java.util.List;

class UnionFind {
    private int[] parent, rank;

    public boolean[] has_top, has_bottom, has_left, has_right;
    private int total_size;

    public UnionFind(int size) {



        total_size = size;


        int n = size * size;

        parent = new int[n];
        rank = new int[n];
        has_top = new boolean[n];
        has_bottom = new boolean[n];
        has_left = new boolean[n];
        has_right = new boolean[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    /***
     * joins two islands together
     * @param idx1
     * @param idx2
     *
     */
    public boolean union(int[] idx1, int[] idx2) {
        int row1 = idx1[0];
        int col1 = idx1[1];

        int row2 = idx2[0];
        int col2 = idx2[1];


        int rootP = find(new int[] {row1, col1});
        int rootQ = find(new int[] {row2, col2});

        has_top[rootP] = has_top[rootQ] = has_top[rootP] || has_top[rootQ];
        has_bottom[rootP] = has_bottom[rootQ] = has_bottom[rootP] || has_bottom[rootQ];
        has_left[rootP] = has_left[rootQ] = has_left[rootP] || has_left[rootQ];
        has_right[rootP] = has_right[rootQ] = has_right[rootP] || has_right[rootQ];


        if (rootP == rootQ){
            return false;}

        if (rank[rootQ] > rank[rootP]) {
            parent[rootP] = rootQ;
        }
        else {
            parent[rootQ] = rootP;
            if (rank[rootP] == rank[rootQ]) {
                rank[rootP]++;
            }
        }

        return true;
    }


    /***
     * finds the parent of an island
     * @param idx
     * @return
     */
    public int find(int[] idx) {

        int row = idx[0];
        int col = idx[1];

        int p = (row * total_size) + col;

        if (row == 0)
            has_top[p] = true;

        if (col == 0)
            has_left[p] = true;

        if (row == total_size - 1)
            has_bottom[p] = true;

        if (col == total_size - 1)
            has_right[p] = true;


        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }


    /***
     * returns true if white has formed an island from top to bottom
     * @param idx
     * @return
     */
    public boolean hasTopToBottomPath(int[] idx){

        int row = idx[0];
        int col = idx[1];

        int root = find(new int[] {row, col});

        return has_top[root] & has_bottom[root];

    }

    /***
     * returns true if black has formed an island from left to right
     * @param idx
     * @return
     */
    public boolean hasLeftToRightPath(int[] idx){

        int row = idx[0];
        int col = idx[1];

        int root = find(new int[] {row, col});

        return has_left[root] & has_right[root];
    }




}








