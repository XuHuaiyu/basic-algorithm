/**
*快排注意事项：
*1. 枢纽元选取策略
*2. 分割策略（细节的实现依赖于枢纽元的选取策略）
*3. 小数组
*/

public class QuickSortTest{

  public final static int CUTOFF = 20;
  public void QuickSort(int[] array){
    QuickSort(array, 0, array.length-1);
  }

/*
排序区间为：[from, to]
*/
  private void QuickSort(int[] array, int from, int to){

    if(to - from + 1 <= CUTOFF){
      insertionSort(array, from, to);
      return ;  //DO NOT FORGET return!
    }

    int pivot = getPivot(array, from, to);
    int idx = partition(array, from, to, pivot);


    swap(array, idx, to-1);  //这一句不要丢了！！每次分割完毕，
                             //枢纽元已经确保在正确的位置

    QuickSort(array, from, idx - 1);
    QuickSort(array, idx + 1, to);


    //另外一定需要注意，不是对[from, mid)和[mid,to]进行快排！！！
  }


  /*
  枢纽元的选取使用三值取中的方法
  区间[from, to]
  */

  private int getPivot(int[] array, int from, int to){
    int mid = from + (to-from)/2;
    if(array[from] > array[mid]) swap(array, from, mid);
    if(array[mid] > array[to]) swap(array, mid, to);
    if(array[from] > array[mid]) swap(array, from, mid);

    /*Invariant: A[from] <= A[mid] <= A[to]*/
    swap(array, mid, to-1);  //枢纽元放到to-1，而非to的位置
                             //此时to位置的元素已经大于枢纽元
    return array[to-1];
  }

  /*
  区间[from, to]
  */
  private int partition(int[] array, int from, int to, int pivot){
    int i = from, j = to - 1; //进行分割前 array[from] <= pivot &&
                              //array[to-1] == pivot &&
                              //array[to] >= pivot
                              //i, j并非初始化为from+1 和 to-2， 而是分别向左右超过1位
    for(;;){
      /* 1.双向扫描
         2.对于相等的情况，采用i,j都停止，进行元素交换的策略
         此两步操作都为了避免分割的不平衡导致花费二次时间
      */
      while(array[++i] < pivot) {} //因为i,j在进行初始化时候分别向左右各超了1位
                                   //所以先++
      while(array[--j] > pivot) {}

      if(i < j) swap(array, i, j);
      else break;
    }

    /*
    若上面的i，j初始化及循环换成如下语句，则A[i]==A[j]==pivot时会产生死循环

    i = from + 1; j = right - 2;
    for(;;){
      while(A[i] < pivot) i++;
      while(A[j] > pivot) j++;

      if(i < j) swap(A, i, j);
      else break;
    }
    */
    return i;
  }
  private void swap(int[] array, int i, int j){
    int t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  /*
  区间[from, to]
  */
  private void insertionSort(int[] array, int from, int to){
    for(int i = from + 1; i <= to; i++){
      for(int j = i; j > from; j--){
        if(array[j] < array[j-1]) swap(array, j, j - 1);
        else break;
      }
    }
  }
  public static void main(String[] args) {
    int[] A = new int[]{32,103,24,88,95,70,97,15,102,6,79,46,51,37,93,108,9,58,53,58,79,36,58,91,78,58,61,81};
    // int[] A = new int[]{32,103,24,88,95,70,97,15,102,6};
    new QuickSortTest().QuickSort(A);
    for(int i : A){
      System.out.print(i + " ");
    }
  }
}
