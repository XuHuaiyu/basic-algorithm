/*
1. 将merge()方法放在排序方法的最后，保证在任何时刻，只需要一个临时数组活动
   以避免在任意时刻可能会有log n个临时数组活动的情况
2. 另外，如果merge()方法的每个递归调用均局部动态生成一个数组，那么分配内存的操作占用的时间会增多
*/
public class MergeSort{

  public static final int CUTOFF = 20;
  public void MSort(int[] array){
    int[] tmpArray = new int[array.length];
    MSort(array, tmpArray, 0, tmpArray.length - 1);

  }

  public void MSort(int[] array, int[] tmpArray, int left, int right){
    if(right - left + 1 <= CUTOFF){
      insertionSort(array, left, right);
      return ;
    }
    int mid = left + (right - left)/2;
    MSort(array, tmpArray, left, mid);
    MSort(array, tmpArray, mid+1, right);
    merge(array, tmpArray, left, mid, right);
  }
  private void merge(int[] array, int[] tmpArray, int left, int mid, int right){
    int lpos = left, lend = mid, rpos = mid+1, rend = right;
    for(int i = left; i <= right; i++){
      tmpArray[i] = array[i];
    }
    int i ;
    for(i = lpos; lpos <= left && rpos <= rend;){
      if(tmpArray[lpos] <= tmpArray[rpos]) array[i++] = tmpArray[lpos++];
      else array[i++] = tmpArray[rpos++];
    }
    while(lpos <= lend) array[i++] = tmpArray[lpos++];
    while(rpos <= rend) array[i++] = tmpArray[rend++];
  }

  private void insertionSort(int[] array, int left, int right){
    for(int i = 1; i < right-left+1; i++){
      for(int j = i; j >=1; j --){
        if(array[j] < array[j-1]){
          swap(array, j, j - 1 );
        }else break;
      }
    }
  }

  private void swap(int[] array, int i, int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  public static void main(String[] args) {
    MergeSort sort = new MergeSort();
    int[] array = {9,8,7,6,5,4,3,2,1};
    sort.MSort(array);
    for(int i : array){
      System.out.println(i);
    }

  }
}
