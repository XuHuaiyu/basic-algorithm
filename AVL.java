import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class AVL{
  private Node root = null;

  class Node{
    int N;
    Node left, right;
    int h;
    int key, value;
    public Node(int key, int value, int N, int h){
      this.key = key;
      this.value = value;
      this.N = N;
      this.h = h;
      this.left = null;
      this.right = null;
    }
  }

  /*
  插入节点
  */
  public void insert(int key, int value){
    root = insert(root, key, value);
  }
  private Node insert(Node x, int key, int value){
    if(x == null) return new Node(key, value, 1, 0);
    if(key == x.key) {x.value = value; return x;}
    else if(key > x.value){
      x.right = insert(x.right, key, value);
      x.h = max(height(x.left), height(x.right))+1;
      if(height(x.right) - height(x.left) == 2){
        if(key > x.right.key) x = singleRotateRight(x);
        else x = doubleRotateRight(x);
      }
    }else{
      x.left = insert(x.left, key, value);
      x.h = max(height(x.left), height(x.right))+1;
      if(height(x.left) - height(x.right) == 2){
        if(key < x.left.key) x = singleRotateLeft(x);
        else x = doubleRotateLeft(x);
      }
    }
    x.h = max(height(x.left), height(x.right))+1;
    x.N = size(x.left) + size(x.right) + 1;

    return x;
  }

  /*
  右旋
  */
  private Node singleRotateLeft(Node x){
    Node lchild = x.left;
    x.left = lchild.right;
    lchild.right = x;

    x.N = size(x.left) + size(x.right) + 1;
    x.h = max(height(x.left), height(x.right)) + 1;
    return lchild;
  }
  /*
  左旋
  */
  private Node singleRotateRight(Node x){
    Node rchild = x.right;
    x.right = rchild.left;
    rchild.left = x;

    x.N = size(x.left) + size(x.right) + 1;
    x.h = max(height(x.left), height(x.right)) + 1;

    return rchild;
  }
  /*
  左-右旋
  */
  private Node doubleRotateLeft(Node x){
    x.left = singleRotateRight(x.left);//注意:右子树中的单旋是左旋
    return singleRotateLeft(x);
  }
  /*
  右-左旋
  */
  private Node doubleRotateRight(Node x){
    x.right = singleRotateLeft(x.right);
    return singleRotateRight(x);
  }

  /*
  树高度
  */
  public int height(){
    return height(root);
  }
  private int height(Node x){//空节点的高度约定为-1！！！
    return x == null ? -1:x.h;
  }

  /*
  树中节点个数
  */
  public int size(){
    return size(root);
  }
  private int size(Node x){
    return x == null ? 0:x.N;
  }

  private int max(int x, int y){
    return x>y?x:y;
  }


  /*
  层次遍历，利用队列
  */
  private void printLayer(){
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);
    while(!queue.isEmpty()){
      Node x = queue.poll();
      if(x.right != null) queue.add(x.right);
      if(x.left != null) queue.add(x.left);
      System.out.println(x.key);
    }
  }

  /*
  递归先序遍历
  */
  public void preOrderRecursion(){
    preOrderRecursion(root);
  }
  private void preOrderRecursion(Node x){
    if(x == null) return;
    System.out.print(x.key+" ");
    preOrderRecursion(x.left);
    preOrderRecursion(x.right);
  }

  /*非递归先序遍历*/
  public void preOrder(){
    preOrder(root);
  }
  private void preOrder(Node x){
    Stack<Node> stack = new Stack<Node>();
    stack.push(x);
    while(!stack.isEmpty()){
      x = stack.pop();
      if(x.right != null) stack.push(x.right);//注意是右子树先压入栈！
      if(x.left != null) stack.push(x.left);

      System.out.print(x.key+" ");
    }
  }
  /*
  递归中序遍历
  */
  public void inOrderRecursion(){
    inOrderRecursion(root);
  }
  private void inOrderRecursion(Node x){
    if(x == null) return;
    inOrderRecursion(x.left);
    System.out.print(x.key + " ");
    inOrderRecursion(x.right);
  }

  /*非中序先序遍历*/
  public void inOrder(){
    inOrder(root);
  }
  private void inOrder(Node x){
    Stack<Node> stack = new Stack<Node>();
    while(x != null || !stack.isEmpty()){
      if(x != null){
        stack.push(x);
        x = x.left;
      }else{
        x = stack.pop();
        System.out.print(x.key + " ");
        x = x.right;
      }
    }
  }
  /*
  递归后序遍历
  */
  public void postOrderRecursion(){
    postOrderRecursion(root);
  }
  private void postOrderRecursion (Node x){
    if(x == null) return;
    postOrderRecursion(x.left);
    postOrderRecursion(x.right);
    System.out.print(x.key + " ");
  }
  /*非递归后序遍历*/
  public void postOrder(){
    postOrder(root);
  }
  private void postOrder(Node x){
    Stack<Node> stack = new Stack<Node>();
    while(x != null){
      stack.push(x.left);
      x = x.left;
    }
    while(!stack.isEmpty()){
      x = stack.pop();

      if(x.right != null){
        x = x.right;
        stack.push(x);
        while(x.left != null){

        }
      }
      stack.push(x);
      x = stack.pop();
      System.out.print(x.key + " ");
    }

  }


  /*
  按照树结构打印
  */
  public void printAsBT(){
    printAsBT(root, 0);
  }
  private void printAsBT(Node x, int layer){
    if(x == null) return;
    printAsBT(x.right, layer+1);
    for(int i = 0; i < layer; i++){
      System.out.print("  ");
    }
    System.out.println(x.key);
    printAsBT(x.left, layer+1);
  }



  public static void main(String args[]){
    int[] A = {6,9,2,7,5,8,3,10,0,1};
    AVL avl = new AVL();
    for(int i : A){
      avl.insert(i, i);
    }
    avl.inOrder();
    System.out.println();
    avl.inOrderRecursion();
  }
}
