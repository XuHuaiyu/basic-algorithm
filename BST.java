public class BST{
  private Node root;

  private class Node{
    private int key;
    private int val;
    private Node lchild;
    private Node rchild;
    private int N;

    public Node(int key, int val, int N){
      this.key = key;
      this.val = val;
      this.N = N;
    }
  }

  public int size(){
    return size(root);
  }
  private int size(Node x){
    x.N;
  }

  public int get(int key){
    get(root, key);
  }
  private int get(Node root, int key){
    if(root == null) return 0;
    int cmp = key - root.key;
    if(cmp == 0) return root.val;
    else if(cmp > 0) return get(root.rchild, key);
    else return get(root.lchild, key);
  }



  public void put(int key, int val){
    root = put(root, key, val);

  }
  private Node put(Node x, int key, int val){
    if(x == null) return x = new Node(key, val, 1);
    int cmp = key - x.key;
    if(cmp > 0) x.lchild = put(x.lchild, key, val);
    else if(cmp < 0) x.rchild = put(x.rchild, key, val);
    else x.val = val;
    x.N = size(x.lchild) + size(x.rchild) + 1;
  }

  public Node max(){
    return max(root);
  }
  private Node max(Node x){
    if(x.rchild == null) return x;
    return max(x.rchild);
  }

  public Node min(){
    return min(root);
  }
  private Node min(Node x){
    if(x.lchild == null) return x;
    return min(x.lchild);
  }

  public int floor(int key){
    return floor(root, key).key;
  }
  private Node ceiling(Node x, int key){
    if(x == null) return null;
    if(key == x.key) return x;
    else if(key > x.key) return floor(x.rchild, key);
    Node tmp = floor(x.rchild, key);
    return tmp == null ? x : tmp;

  }

  public Key ceiling(int key){
    return ceiling(root, key).key;
  }
  private Node floor(Node x, int key){
    if(x == null) return null;
    if(key == x.key) return x;
    else if(key < x.key) return floor(x.lchild, key);
    Node tmp = floor(x.rchild, key);
    return tmp == null ? x : tmp;
  }

  public int select(int k){
    return select(root, k).key;
  }
  private Node select(Node x, int k){
    int n = size(x.lchild);
    if(k == n){
      return x;
    }
    else if(k < n){
      return select(x.lchild, k);
    }
    else return select(x.rchild, k - n - 1);
  }

  public int rank(int key){
    return rank(root, key);
  }
  private int rank(Node x, int key){
    if(x == null) return 0;
    int cmp = key - x.key;
    if(cmp == 0) return size(x.lchild) + 1;
    else if(cmp > 0) return size(x.lchild) + 1 + rank(x.rchlid, key);
    return size(l.lchlid, key);
  }

  public void deleteMin(){
    root = deleteMin(root);
  }
  private Node deleteMin(Node x){
    if(x == null) return null;
    if(x.lchild==null) return x.rchild;
    x.left = deleteMin(x.lchild);
    x.N = size(x.left) + size
  }

  public void delete(int key){
    root = delete(root, key);
  }
  private Node delete(Node x, int key){
    if(x == null) return null;
    int cmp = key - x.key;
    if(cmp > 0) x.rchild = delete(x.rchild, key);
    else if(cmp < 0) x.lchild = delete(x.lchild, key);
    else{
      if(x.rchild == null) return x.lchild;
      if(x.lchild == null) return x.rchild;
      Node t = min(x.rchild);
      t.lchild = x.lchild;
      t.rchild = deleteMin(x.rchild);
    }
    x.N = size(x.lchild) + size(x.rchild) + 1;
    return x;
  }

}
