/*
优先队列核心操作
1. 插入(核心代码为insert)
2. 删除(核心代码为percolateDown)
*/

public class MinPQ{
  private int[] elements;
  private int maxSize;
  private int size;

  private int MIN_VALUE = 1 << 31;

  public MinPQ(){

  }

  public MinPQ(int maxSize){
    this.maxSize = maxSize;
    elements = new int[maxSize+1];//注意，此处应当是maxSize+1的空间，需留出一个sentinel
    size = 0;
    elements[0] = MIN_VALUE;
  }

  public MinPQ(int[] array){
    maxSize = array.length;
    size = maxSize;
    elements = new int[maxSize+1];
    elements[0] = MIN_VALUE;
    for(int i = 1; i <= size; i++){
      elements[i] = array[i-1];
    }

    for(int i = size/2; i>=1; i--){
      percolateDown(i);
    }
  }

  public void percolateDown(int i){//下滤以下标作为判断条件，是因为子节点有两个，下滤一层后还需判断下子节点之间的大小
    int child;
    for(; i*2 <= size ; i = child){
      child = i * 2;
      //下面这句判断通过child<size避免最后一个父节点只存在左孩子的情况(此时节点总数为偶数)
      if(child < size &&  less(child+1, child))
        child = child+1;
      if(less(child, i)) exch(child, i);
      else break;
    }
  }

  public int deleteMin(){
    if(isEmpty()) return elements[0];
    exch(1, size--);
    percolateDown(1);
    return elements[size+1];
  }

  public void insert(int v){ //插入操作以元素值作为循环判断条件，是因为插入是上浮操作，父节点只有一个
    if(isFull()){
      return;
    }
    int i;
    for(i = ++size; elements[i/2] > v; i /= 2){//此处判断条件应当是>v,而非>elements[i]
      elements[i] = elements[i/2];
    }
    elements[i] = v;
  }

  public boolean less(int i, int j){
    return elements[i] < elements[j];
  }
  private boolean isEmpty(){
    return size == 0;
  }
  private boolean isFull(){
    return size == maxSize;
  }

  private void exch(int i, int j){
    int tmp = elements[i];
    elements[i] = elements[j];
    elements[j] = tmp;
  }

  public int getSize(){
    return size;
  }

  public static void main(String[] args) {
    /*MinPQ pq = new MinPQ(new int[]{150, 80, 40, 30, 10, 70, 110, 100, 20, 90, 60, 50, 120, 140, 130});
    // pq.insert(5);
    while(pq.getSize() > 0){
      System.out.println(pq.deleteMin());
    }*/

    MinPQ pq2 = new MinPQ(5);
    for(int i = 5; i >= 1; i--){
      pq2.insert(i);
    }
    while(pq2.getSize() > 0){
      System.out.println(pq2.deleteMin());
    }

  }

}
