package graph;

import java.util.ArrayList;

public class Graph {
  private final int n;
  private int m = 0;
  private int[] color;
  private final ArrayList<Edge>[] adj;

  public Graph(final int n) {
    this.n = n;
    this.adj = new ArrayList[this.n];
    for (int i = 0; i < this.n; i++) {
      this.adj[i] = new ArrayList<>();
    }
  }

  public void addEdge(final int from, final int to, final long cost) {
    final Edge e = new Edge(from, to , cost);
    this.addEdge(e);
    this.addEdge(e.reverse());
  }

  public void addEdge(final int from, final int to) {
    this.addEdge(from, to, 1);
  }

  public void addEdge(final Edge e) {
    this.adj[e.from].add(e);
    this.m++;
  }

  public Edge getEdge(final int from, final int index) {
    return this.adj[from].get(index);
  }

  public final ArrayList<Edge> getEdges(final int i) {
    return this.adj[i];
  }

  public final int getV() {
    return this.n;
  }

  public final int getE() {
    return this.m;
  }

  public boolean isBipartite() {
    this.color = new int[this.n];
    for (int i = 0; i < this.n; i++) {
      if (color[i] == 0) {
        if (!dfs(i, 1)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean dfs(int v, int c) {
    color[v] = c;
    for (int i = 0; i < adj[v].size(); i++) {
      if (color[adj[v].get(i).to] == c) return false;
      if (color[adj[v].get(i).to] == 0 && !dfs(adj[v].get(i).to, -c)) {
        return false;
      }
    }

    return true;
  }
}

class Edge {
  public final int from, to;
  public final long cost;

  public Edge(final int from , final int to, final long cost) {
    this.from = from; this.to = to; this.cost = cost;
  }

  public Edge(final int from, final int to) {
    this(from, to, 1);
  }

  public final Edge reverse() {
    return new Edge(to, from, cost);
  }
}