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
    elements = new int[maxSize];
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

  public void percolateDown(int i){
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

  public void insert(int v){
    if(isFull()){
      return;
    }
    int i;
    for(i = ++size; elements[i/2] > v; i *= 2){
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
    MinPQ pq = new MinPQ(new int[]{150, 80, 40, 30, 10, 70, 110, 100, 20, 90, 60, 50, 120, 140, 130});
    while(pq.getSize() > 0){
      System.out.println(pq.deleteMin());
    }
  }

}
