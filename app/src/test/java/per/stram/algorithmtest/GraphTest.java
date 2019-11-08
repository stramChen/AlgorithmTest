package per.stram.algorithmtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GraphTest {
    private static ALGraph alGraph;
    private static MGraph mGraph;

    /**
     *①②③④⑤⑥⑦⑧⑨⑩⑪⑫⑬
     ***************************************
     *******************①******************
     ******************╱│ ╲*****************
     **************6╱***│ **╲5**************
     *************╱****1│ ****╲*************
     ***********╱*******│ ******╲***********
     *********╱*********│ *********╲********
     *******②**********│ ***********④******
     *********╲***5╲****│ *****5╱***╱*******
     *********3╲********③*********╱2*******
     ***********╲****6╱****╲4*****╱*********
     ************╲**╱********╲**╱***********
     *************⑤-----------⑥************
     ********************6******************
     ***************************************
     ***************************************
     */

    static {
        alGraph = initALGraph();
        mGraph = initMGraph();
    }

    /**
     * 模拟采用邻接矩阵的方式存储
     *
     * @return
     */
    public static MGraph initMGraph() {
        MGraph mGraph = new MGraph();
        mGraph.verNum = 6;
        mGraph.arcNum = 10;
        mGraph.ver = new String[mGraph.verNum];
        mGraph.edge = new int[mGraph.verNum][mGraph.verNum];

        mGraph.ver[0] = "Node1";
        mGraph.ver[1] = "Node2";
        mGraph.ver[2] = "Node3";
        mGraph.ver[3] = "Node4";
        mGraph.ver[4] = "Node5";
        mGraph.ver[5] = "Node6";

        mGraph.edge[0][1] = 6;
        mGraph.edge[0][2] = 1;
        mGraph.edge[0][3] = 5;

        mGraph.edge[1][0] = 6;
        mGraph.edge[1][2] = 5;
        mGraph.edge[1][4] = 3;

        mGraph.edge[2][0] = 1;
        mGraph.edge[2][1] = 5;
        mGraph.edge[2][3] = 5;
        mGraph.edge[2][4] = 6;
        mGraph.edge[2][5] = 4;

        mGraph.edge[3][0] = 5;
        mGraph.edge[3][2] = 5;
        mGraph.edge[3][5] = 2;

        mGraph.edge[4][1] = 3;
        mGraph.edge[4][2] = 6;
        mGraph.edge[4][5] = 6;

        mGraph.edge[5][2] = 4;
        mGraph.edge[5][3] = 2;
        mGraph.edge[5][4] = 6;

        for (int i = 0; i < mGraph.verNum; i++) {
            for (int j = 0; j < mGraph.verNum; j++) {
                if (mGraph.edge[i][j] == 0) {
                    //假设65535为无穷大
                    mGraph.edge[i][j] = 65535;
                }

            }
        }

        return mGraph;
    }

    /**
     * 模拟采用邻接表方式存储
     *
     * @return
     */
    public static ALGraph initALGraph() {
        ALGraph alGraph = new ALGraph();
        alGraph.verNum = 6;
        alGraph.arcNum = 10;
        List<VNode> vertexs = new ArrayList<>();

        //构造第一个顶点集
        VNode vNode1 = new VNode();
        vNode1.data = "Node1";
        ArcNode arcNode11 = new ArcNode();
        arcNode11.adjVex = 2;
        arcNode11.weight = 6;
        ArcNode arcNode12 = new ArcNode();
        arcNode12.adjVex = 3;
        arcNode12.weight = 1;
        arcNode11.next = arcNode12;
        ArcNode arcNode13 = new ArcNode();
        arcNode13.adjVex = 4;
        arcNode13.weight = 5;
        arcNode12.next = arcNode13;
        vNode1.first = arcNode11;
        vertexs.add(vNode1);

        //构造第一个顶点集
        VNode vNode2 = new VNode();
        vNode2.data = "Node2";
        ArcNode arcNode21 = new ArcNode();
        arcNode21.adjVex = 1;
        arcNode21.weight = 6;
        ArcNode arcNode22 = new ArcNode();
        arcNode22.adjVex = 3;
        arcNode22.weight = 5;
        arcNode21.next = arcNode22;
        ArcNode arcNode23 = new ArcNode();
        arcNode23.adjVex = 5;
        arcNode23.weight = 3;
        arcNode22.next = arcNode23;
        vNode2.first = arcNode21;
        vertexs.add(vNode2);

        //构造第三个顶点集
        VNode vNode3 = new VNode();
        vNode3.data = "Node3";
        ArcNode arcNode31 = new ArcNode();
        arcNode31.adjVex = 1;
        arcNode31.weight = 1;
        ArcNode arcNode32 = new ArcNode();
        arcNode32.adjVex = 2;
        arcNode32.weight = 5;
        arcNode31.next = arcNode32;
        ArcNode arcNode33 = new ArcNode();
        arcNode33.adjVex = 4;
        arcNode33.weight = 5;
        arcNode32.next = arcNode33;
        ArcNode arcNode34 = new ArcNode();
        arcNode34.adjVex = 5;
        arcNode34.weight = 6;
        arcNode33.next = arcNode34;
        ArcNode arcNode35 = new ArcNode();
        arcNode35.adjVex = 6;
        arcNode35.weight = 4;
        arcNode34.next = arcNode35;
        vNode3.first = arcNode31;
        vertexs.add(vNode3);

        //构造第四个顶点集
        VNode vNode4 = new VNode();
        vNode4.data = "Node4";
        ArcNode arcNode41 = new ArcNode();
        arcNode41.adjVex = 1;
        arcNode41.weight = 5;
        ArcNode arcNode42 = new ArcNode();
        arcNode42.adjVex = 3;
        arcNode42.weight = 5;
        arcNode41.next = arcNode42;
        ArcNode arcNode43 = new ArcNode();
        arcNode43.adjVex = 6;
        arcNode43.weight = 2;
        arcNode42.next = arcNode43;
        vNode4.first = arcNode41;
        vertexs.add(vNode4);

        //构造第五个顶点集
        VNode vNode5 = new VNode();
        vNode5.data = "Node5";
        ArcNode arcNode51 = new ArcNode();
        arcNode51.adjVex = 2;
        arcNode51.weight = 3;
        ArcNode arcNode52 = new ArcNode();
        arcNode52.adjVex = 3;
        arcNode52.weight = 6;
        arcNode51.next = arcNode52;
        ArcNode arcNode53 = new ArcNode();
        arcNode53.adjVex = 6;
        arcNode53.weight = 6;
        arcNode52.next = arcNode53;
        vNode5.first = arcNode51;
        vertexs.add(vNode5);

        //构造第六个顶点集
        VNode vNode6 = new VNode();
        vNode6.data = "Node6";
        ArcNode arcNode61 = new ArcNode();
        arcNode61.adjVex = 5;
        arcNode61.weight = 6;
        ArcNode arcNode62 = new ArcNode();
        arcNode62.adjVex = 3;
        arcNode62.weight = 4;
        arcNode61.next = arcNode62;
        ArcNode arcNode63 = new ArcNode();
        arcNode63.adjVex = 4;
        arcNode63.weight = 2;
        arcNode62.next = arcNode63;
        vNode6.first = arcNode61;
        vertexs.add(vNode6);

        alGraph.vertexs = vertexs;

        return alGraph;
    }

    @Test
    public void main() {
//        BFSTraverse(alGraph);

        //from any node create mini spanning tree
//        prim(mGraph,3);

//        kruskal(edges,mGraph);

        dijkstraTest(mGraph, 1, 6);

        floydTest(mGraph, 1, 6);

    }

    /**
     * 广度优先搜索
     */
    private boolean[] visited;

    public void BFSTraverse(ALGraph alGraph) {
        visited = new boolean[alGraph.verNum];
        int i;
        //开始的时候为设置每个节点都没被访问过
        for (i = 0; i < alGraph.verNum; i++) {
            visited[i] = false;
        }

        //遍历每一个节点，防止有其它连通子图没被访问到
        for (i = 0; i < alGraph.verNum; i++) {
            BFS(alGraph, i);
        }
    }

    /**
     * 广度优先搜索核心部分
     *
     * @param alGraph
     * @param i
     */
    public void BFS(ALGraph alGraph, int i) {
        Queue<Integer> vexQueue = new PriorityQueue<>();

        if (visited[i]) return;

        //打印节点表的信息，并进入队列
        visited[i] = true;
        System.out.print(alGraph.vertexs.get(i).data + "-->");
        vexQueue.add(i);

        //节点出队列,并访问此节点的边表
        while (!vexQueue.isEmpty()) {
            int vex = vexQueue.poll();
            VNode vNode = alGraph.vertexs.get(vex);
            //首先获得边表的头节点
            ArcNode arcNode = vNode.first;
            //遍历边表
            while (null != arcNode) {
                vex = arcNode.adjVex;
                //如果节点没被访问过，则节点入队，并设置已访问。
                if (!visited[vex - 1]) {
                    vexQueue.add(vex - 1);
                    visited[vex - 1] = true;
                    System.out.print(alGraph.vertexs.get(vex - 1).data + "-->");
                }
                //继续访问下一个节点
                arcNode = arcNode.next;
            }

        }

    }

    /**
     * Prim algorithm,to creat minimum spanning tree
     */
    public void prim(MGraph mGraph, int vex) {
        vex = vex - 1;
        //when the lowCost[i] = 0,it means vertex has been visited
        int[] lowCost = new int[mGraph.verNum];
        //record the previous node
        int[] adjVex = new int[mGraph.verNum];
        boolean[] visited = new boolean[mGraph.verNum];

        for (int i = 0; i < lowCost.length; i++) {
            lowCost[i] = mGraph.edge[vex][i];
            adjVex[i] = vex;
        }

        lowCost[vex] = 0;

        //traverse n-1 times,because the first vertex has been add to spanning tree
        for (int i = 1; i < mGraph.verNum; i++) {
            int k = 0;
            int min = 65535;
            //find the minimum weight
            for (int j = 0; j < mGraph.verNum; j++) {
                if (lowCost[j] != 0 && lowCost[j] < min) {
                    min = lowCost[j];
                    k = j;
                }
            }
            System.out.print(adjVex[k] + 1 + "--" + (k + 1) + ":weight:" + mGraph.edge[adjVex[k]][k] + "\n");
            lowCost[k] = 0;
            //update lowCost array
            for (int j = 0; j < mGraph.verNum; j++) {
                if (lowCost[j] != 0 && mGraph.edge[k][j] < lowCost[j]) {
                    lowCost[j] = mGraph.edge[k][j];
                    adjVex[j] = k;
                }
            }

        }

    }

    /**
     * ************************Kruskal****************************
     */
    public static List<Edge> edges = new ArrayList<>();

    static {
        Edge edge1 = new Edge(0, 1, 6);
        Edge edge2 = new Edge(0, 2, 1);
        Edge edge3 = new Edge(0, 3, 5);
        Edge edge4 = new Edge(2, 1, 5);
        Edge edge5 = new Edge(2, 3, 5);
        Edge edge6 = new Edge(2, 4, 6);
        Edge edge7 = new Edge(2, 5, 4);
        Edge edge8 = new Edge(1, 4, 3);
        Edge edge9 = new Edge(4, 5, 6);
        Edge edge10 = new Edge(5, 3, 2);
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);
        edges.add(edge8);
        edges.add(edge9);
        edges.add(edge10);
    }

    //find  parent
    public int find(int[] parents, int vex) {
        while (parents[vex] != -1) {
            vex = parents[vex];
        }
        return vex;
    }

    // using kruskal create spanning tree
    public void kruskal(List<Edge> edges, MGraph mGraph) {
        int parent[] = new int[mGraph.verNum];

        //init parent array,every node will be a single tree
        for (int i = 0; i < mGraph.verNum; i++) {
            parent[i] = -1;
        }

        //sort
        kruskalSort(edges);

        for (int i = 0; i < edges.size(); i++) {
            int parentA = find(parent, edges.get(i).a);
            int parentB = find(parent, edges.get(i).b);

            //if parentA == parentB,that will be form a ring
            if (parentA != parentB) {
                parent[parentA] = parentB;
                System.out.print((edges.get(i).b + 1) + "-->" + (edges.get(i).a + 1) + "\n");
            }
        }

    }

    public void kruskalSort(List<Edge> edges) {
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
    }

    /**
     * ***********************Kruskal*****************************
     */


    //*****************************dijkstra**************************************/
    public void dijkstraTest(MGraph mGraph, int from, int to) {
        from = from - 1;
        to = to - 1;
        int dis[] = new int[mGraph.verNum];
        int path[] = new int[mGraph.verNum];
        dijkstra(mGraph, from, dis, path);

        System.out.print("distance:" + dis[to] + "\n");
        while (path[to] != -1) {
            int preNode = path[to];
            System.out.print((preNode + 1) + "-->" + (to + 1) + "\n");
            to = preNode;
        }

    }

    /**
     * **
     * dijkstra
     *
     * @param mGraph the Matrix to traverse
     * @param ver    it will be start from ver
     */
    public void dijkstra(MGraph mGraph, int ver, int dis[], int path[]) {
        int nodeNum = mGraph.verNum;
        boolean visited[] = new boolean[nodeNum];

        for (int i = 0; i < nodeNum; i++) {
            //mGraph.edge[ver][i] is the weight of ver-->i
            dis[i] = mGraph.edge[ver][i];
            if (mGraph.edge[ver][i] < 65535) {
                path[i] = ver;
            } else {
                path[i] = -1;
            }
        }

        visited[ver] = true;
        path[ver] = -1;

        int k = ver;

        for (int i = 1; i < nodeNum; i++) {
            //find the minimum weight of edge
            int mini = 65535;
            for (int j = 0; j < nodeNum; j++) {
                if (!visited[j] && dis[j] < mini) {
                    mini = dis[j];
                    k = j;
                }
            }
            visited[k] = true;

            //update distance array
            for (int j = 0; j < nodeNum; j++) {
                //if Node-->k + k-->j < Node-->j  update the minumum distance
                if (!visited[j] && dis[k] + mGraph.edge[k][j] < dis[j]) {
                    dis[j] = dis[k] + mGraph.edge[k][j];
                    path[j] = k;
                }
            }
        }
    }
    //*****************************dijkstra**************************************/

    //*****************************floyd**************************************/

    public void floydTest(MGraph mGraph, int startNode, int endNode) {
        startNode = startNode - 1;
        endNode = endNode - 1;
        int[][] path = new int[mGraph.verNum][mGraph.verNum];
        int A[][] = new int[mGraph.verNum][mGraph.verNum];
        floyd(mGraph, path, A);
        System.out.print("distance:" + A[startNode][endNode]+"\n");

        while (path[startNode][endNode] != -1){
            int nextNode = path[startNode][endNode];
            System.out.print((startNode+1) + "-->" + (nextNode+1)+"\n");
            startNode = nextNode;
        }
        System.out.print((startNode+1) + "-->" + (endNode+1));


    }

    /**
     * floyd:find all node minimum path
     *
     * @param mGraph
     * @param path   the path Matrix,whick can find the minimum path from any node
     */
    public void floyd(MGraph mGraph, int[][] path, int A[][]) {
        int nodeSize = mGraph.verNum;
        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                A[i][j] = mGraph.edge[i][j];
                path[i][j] = -1;
            }
        }
        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                for (int k = 0; k < nodeSize; k++) {
                    if (A[j][k] > A[j][i] + A[i][k]) {
                        A[j][k] = A[j][i] + A[i][k];
                        path[j][k] = i;
                    }
                }
            }
        }

    }

}

class Edge {
    //起点和终点。
    public int a, b;
    //边的权值
    public int weight;

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

}