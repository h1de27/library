package graph.others;

import java.util.ArrayList;

public class Bipartite {
  private final int v;
  private final ArrayList<Integer>[] adj;
  private int[] color;

  /**
   * 二部グラフか否かを確認する対象となるグラフを構築。
   *
   * @param v   頂点の数
   * @param adj 隣接リスト
   */
  public Bipartite(int v, ArrayList<Integer>[] adj) {
    this.v = v;
    this.adj = adj;
  }

  /**
   * 二部グラフである場合には true, そうでない場合には false
   * を返す。
   * @return
   */
  public boolean isBipartite() {
    this.color = new int[this.v];
    for (int i = 0; i < this.v; i++) {
      if (color[i] == 0) {
        if (!dfs(i, 1)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 一つの頂点に対し、隣接する頂点の色を調べる。
   * 隣接する頂点が同じ色であれば false を返し、色が塗られていない場合には、
   * 頂点と異なる色で塗り、引き続き深さ優先探索を行う。
   *
   * @param v 現在の頂点
   * @param c 色
   * @return
   */
  public boolean dfs(int v, int c) {
    color[v] = c;
    for (int i = 0; i < adj[v].size(); i++) {
      if (color[adj[v].get(i)] == c)
        return false;
      if (color[adj[v].get(i)] == 0 && !dfs(adj[v].get(i), -c)) {
        return false;
      }
    }

    return true;
  }

  /******* Usage *******/

  public static void main(String[] args) {
    int v = 4;
    ArrayList<Integer>[] adj = new ArrayList[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new ArrayList<>();
    }
    adj[0].add(1);
    adj[0].add(3);
    adj[1].add(0);
    adj[1].add(2);
    adj[2].add(1);
    adj[2].add(3);
    adj[3].add(0);
    adj[3].add(2);
    Bipartite b = new Bipartite(v, adj);
    if (b.isBipartite())
      System.out.println("Yes");
    else
      System.out.println("No");
  }
}
