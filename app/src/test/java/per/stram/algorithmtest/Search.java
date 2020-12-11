package per.stram.algorithmtest;

import org.junit.Test;

/**
 * des:
 *
 * @author: chensichuang@jd.com
 * @date: 2019/11/6 5:27 PM
 */
public class Search {
    public static TNode rootNode;

    @Test
    public void main() {
        TNode tNode = insertBST(null, 14);
//        insertBST(tNode, 4);
//        insertBST(tNode, 9);
//        insertBST(tNode, 11);
//        insertBST(tNode, 10);
//        insertBST(tNode, 21);
//        insertBST(tNode, 2);
//        insertBST(tNode, 3);
//        insertBST(tNode, 98);
//        insertBST(tNode, 6);
//        insertBST(tNode, 5);

        deleteBST(tNode, 14);
//        TNode tNode1 = searchBSTByRecursion(tNode, 14);
//        System.out.print(null == tNode1 ? "未找到\n" : tNode1.value + "\n");
//        TNode tNode2 = searchBSTByRecursion(tNode, 4);
//        System.out.print(null == tNode2 ? "未找到\n" : tNode2.value + "\n");
//        TNode tNode3 = searchBSTByRecursion(tNode, 9);
//        System.out.print(null == tNode3 ? "未找到\n" : tNode3.value + "\n");
//        TNode tNode4 = searchBSTByRecursion(tNode, 11);
//        System.out.print(null == tNode4 ? "未找到\n" : tNode4.value + "\n");
//        TNode tNode5 = searchBSTByRecursion(tNode, 2);
//        System.out.print(null == tNode5 ? "未找到\n" : tNode5.value + "\n");
//        TNode tNode6 = searchBSTByRecursion(tNode, 21);
//        System.out.print(null == tNode6 ? "未找到\n" : tNode6.value + "\n");
//        TNode tNode7 = searchBSTByRecursion(tNode, 98);
//        System.out.print(null == tNode7 ? "未找到\n" : tNode7.value + "\n");
//        TNode tNode8 = searchBSTByRecursion(tNode, 3);
//        System.out.print(null == tNode8 ? "未找到\n" : tNode8.value + "\n");
//        TNode tNode9 = searchBSTByRecursion(tNode, 6);
//        System.out.print(null == tNode9 ? "未找到\n" : tNode9.value + "\n");
//        TNode tNode10 = searchBSTByRecursion(tNode, 5);
//        System.out.print(null == tNode10 ? "未找到\n" : tNode10.value + "\n");
//        TNode tNode11 = searchBSTByRecursion(tNode, 10);
//        System.out.print(null == tNode11 ? "未找到\n" : tNode11.value + "\n");

    }

    public TNode insertBST(TNode root, int value) {
        if (root == null) {
            root = new TNode(value, null, null);
        }
        if (value < root.value) {
            if (root.leftChild == null) {
                root.leftChild = new TNode(value, null, null);
            } else {
                insertBST(root.leftChild, value);
            }
        }
        if (value > root.value) {
            if (root.rightChild == null) {
                root.rightChild = new TNode(value, null, null);
            } else {
                insertBST(root.rightChild, value);
            }
        }
        return root;
    }

    //递归实现二叉排序树查找
    public TNode searchBSTByRecursion(TNode root, int value) {

        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        }
        if (value < root.value) {
            return searchBSTByRecursion(root.leftChild, value);
        }
        if (value > root.value) {
            return searchBSTByRecursion(root.rightChild, value);
        }

        return null;

    }

    //非递归实现二叉排序树查找
//    public TNode searchBST(TNode root, int value) {
//        TNode temp = root;
//        while (temp != null) {
//            if (temp.value == value) {
//                return temp;
//            }
//            if (value < temp.getValue()) {
//                temp = temp.leftChild;
//            } else {
//                temp = temp.rightChild;
//            }
//        }
//
//        return null;
//    }

    /**
     * 需要考虑父亲节点，待删节点，以及待删节点的子节点
     * @param root
     * @param value
     * @return
     */
    public TNode deleteBST(TNode root,int value ){

        TNode parentNode = null;

        //first we need to find the value which we want to delete
        while (root != null){
            if(value == root.value){
                break;
            }
            //save the parent root;
            parentNode = root;
            if(value<root.value){
                root = root.leftChild;
            }else{
                root = root.rightChild;
            }
        }

        //we can not find the value which we want to delete
        if(root == null){
            return null;
        }

        //else we find the value
        //1.if the node that we want to delete has no child,delete directly
        if(root.rightChild == null && root.leftChild == null){
            if(parentNode == null) return null;
            //if the the want to delete is the left node of parent.Delete it.
            if(parentNode.leftChild == root){
                parentNode.leftChild = null;
            }else {
                parentNode.rightChild =null;
            }
        }
        //2.if the node that we want to delete has leftchild
        if(root.leftChild != null){
            if(parentNode.leftChild == root){
                parentNode.leftChild = root.leftChild;
            }else {
                parentNode.rightChild = root.leftChild;
            }
        }

        //2.if the node that we want to delete has right
        if(root.rightChild != null){
            if(parentNode.leftChild == root){
                parentNode.leftChild = root.rightChild;
            }else {
                parentNode.rightChild = root.rightChild;
            }
        }

        //3.if the node that we want to delete both  has right and left child
        //we delete the precursor node,and give the the value of precurosr to the node we want
        // to really delete.
        if(root.leftChild!=null && root.rightChild != null){
            //the parent of precursor node
            TNode preNodeParent = null;
            TNode preNode = null;
            preNode = root.leftChild;
            //find the root node's precursor node,leftchild's most right node
            while (preNode.rightChild !=null){
                preNodeParent = preNode;
                preNode  =  preNode.rightChild;
            }
            //it means the root node's first  left child node dose not has right child tree
            if(preNode == root.leftChild){
                root.leftChild =preNode.leftChild;
            }else {
                //it means the root node's first left child  has right child tree,
                //and now we need to make the most right node giving the value to the root,and giving it's leftchild
                //to it's parent's right child.(And of course it do not has right child)
                preNodeParent.rightChild = preNode.leftChild;
            }
            root.value = preNode.value;
        }

        return root;

    }

    //二叉排序树的删除
    public TNode deleteBST2(TNode root, int value) {
        TNode cur = root;    //当前结点
        TNode parent = null; //待删结点的父结点
        TNode delNode = null;    //在后面用来引用待删结点
        TNode temp = null;       //作为一个局域内的根结点

        //查找待删结点p和待删结点的父结点f
        while (cur != null) {
            if (value == cur.value) {
                break;
            }
            parent = cur;
            if (value > cur.value) {
                cur = cur.rightChild;
            } else {
                cur = cur.leftChild;
            }
        }

        //当前结点为null，即没有找到待删结点。  此时cur指向待删结点
        if (cur == null) {
            return null;
        }
        //待删结点只有右子树
        if (cur.leftChild == null) {
            //待删结点的父结点为null，即待删结点为根结点
            if (parent == null) {
                //根结点为待删结点的右子树
                root = cur.rightChild;
            } else if (parent.leftChild == cur) {    //待删结点为父结点的左子树
                //把待删结点的右子树作为待删结点父结点的左子树
                parent.leftChild = cur.rightChild;
            } else {                        //待删结点为父结点的右子树
                parent.rightChild = cur.rightChild;
            }
        } else {//待删结点有左子树，要找左子树的最右下角的结点
            temp = cur;
            delNode = cur.leftChild;    //此时s指向待删结点
            while (delNode.rightChild != null) {//查找待删结点的最右下角结点
                temp = delNode;
                delNode = delNode.rightChild;
            }
            if (temp == cur) {//即，待删结点没有右子树，把左子树向上移动
                temp.leftChild = delNode.leftChild;
            } else {//即，待删结点有右子树
                temp.rightChild = delNode.leftChild;
            }
            cur.value = delNode.value;
        }

        return root;
    }


}

class TNode {
    public int value;//结点值
    public TNode leftChild;//左孩子结点
    public TNode rightChild;//右孩子结点

    public TNode(int value, TNode leftChild, TNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}