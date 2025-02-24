import java.util.Arrays;
public class BubbleSort{
    public static void Sort(int[] arr){
        int n = arr.length;
        for(int i = 0;i<n-1;i++){
            for(int j = 0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args){
        int[] arr={209,5,64,23};
         System.out.println("Original Array: " + Arrays.toString(arr));
         Sort(arr);
         System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}