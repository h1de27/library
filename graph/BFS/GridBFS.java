package graph.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二次元格子上の幅優先探索を O(mn) (m: row, n: col)で行う。
 *
 * @author https://github.com/h1de27
 */
public class GridBFS {
  final int[][] grid;

  /**
   * グラフを構築する。
   * @param grid
   */
  public GridBFS(int[][] grid) {
    this.grid = grid;
  }

  /**
   * グリッド上の点 start からすべてのマスへの最短距離を求める。
   * 1 は壁を表し、そこに移動することはできない。
   * 到達できるマスには最短距離が、できないマスには -1 が格納される。
   *
   * @param start
   * @return
   */
  public int[][] shortestPath(int[] start) {
    int[] vx = new int[]{0, 1, 0, -1};
    int[] vy = new int[]{1, 0, -1, 0};

    int[][] minCost = new int[grid.length][grid[0].length];
    for (int[] row : minCost) {
      Arrays.fill(row, -1);
    }
    Queue<int[]> q = new LinkedList<>();
    minCost[start[0]][start[1]] = 0;
    q.add(start);

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = cur[1] + vx[i];
        int ny = cur[0] + vy[i];
        if (nx < 0 || ny < 0 || nx >= grid[0].length || ny >= grid.length) {
          continue;
        }
        if (minCost[ny][nx] != -1) {
          continue;
        }
        if (grid[ny][nx] == 1) {
          continue;
        }
        minCost[ny][nx] = minCost[cur[0]][cur[1]] + 1;
        q.add(new int[]{ny, nx});
      }
    }
    return minCost;
  }

  /***************************** DEBUG *********************************/
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        sb.append(grid[i][j]);
        if (j != grid[0].length - 1) {
          sb.append(" ");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  /******* Usage *******/

  public static void main(String[] args) {
    int[] row1 = new int[]{0,0,1,0,0};
    int[] row2 = new int[]{0,0,0,0,0};
    int[] row3 = new int[]{0,0,0,1,0};
    int[] row4 = new int[]{1,1,0,1,1};
    int[] row5 = new int[]{0,0,0,0,0};

    int[][] grid = new int[][]{row1, row2, row3, row4, row5};
    GridBFS g = new GridBFS(grid);
    System.out.println(g.toString());
    System.out.println();
    int[][] minCost = g.shortestPath(new int[]{0, 4});
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.print(minCost[i][j]);
        if (j != grid[0].length - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}