public class BinarySearch{
  public int search(int[] array, int key){

  }
  private int search(int[] array, int key, int lo, int hi){

    while(lo <= hi){
      int mid = lo + (hi-lo)/2;
      if(array[mid] > key) hi = mid-1;
      else if(array[mid] < key) lo = mid+1;
      else return mid;
    }

  }
}
