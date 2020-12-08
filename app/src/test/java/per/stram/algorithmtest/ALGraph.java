package per.stram.algorithmtest;

import java.util.ArrayList;
import java.util.List;

/**
 * des:图的邻接表存储形式
 *
 * @author: chensichuang@jd.com
 * @date: 2019/10/29 5:36 PM
 */
public class ALGraph {

    public List<VNode> vertexs = new ArrayList<>();

    public int verNum,arcNum;


}

//边表
class ArcNode{
    int weight;
    int adjVex;
    ArcNode next;
}

//顶点表
class VNode{
    String data;
    ArcNode first;
}
