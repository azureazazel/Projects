import java.util.Arrays;

public class SelectionSort{
    public static void selection(int[] arr){
        int n = arr.length;
        int minindex;
        for(int i=0;i<n-1;i++){
            minindex = i;
            for(int j=i+1;j<n;j++){
                if(arr[minindex]>arr[j]){
                    minindex = j;
                }
            }
            int temp = arr[minindex];
            arr[minindex] = arr[i];
            arr[i] = temp;
        }
    }
    public static void main(String[] args){
       int[] arr={209,5,64,23};
        System.out.println("Original Array: " + Arrays.toString(arr));
        selection(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}