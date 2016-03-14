public class ShellSort{

  public void sort(int[] array){
      sort(array, 0, array.length-1);
  }

  private void sort(int[] array, int lpos, int rpos){
    int N = rpos - lpos + 1;
    int h = 1;
    while(h < N/3) h=3*h+1;
    while(h >= 1){
      for(int i = h; i < N; i ++){
        for(int j = i - h; j >= 0; j-=h){
          if(array[j]>array[j+h]) swap(array, j, j+1);
        }
      }
      h /= 3;
    }
  }

  private void swap(int[] array, int i, int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  public static void main(String[] args) {
    int[] array = {9,8,7,6,5,4,3,2,1};
    ShellSort sort = new ShellSort();
    sort.sort(array);
    for(int i : array){
      System.out.println(i);
    }
  }

}
