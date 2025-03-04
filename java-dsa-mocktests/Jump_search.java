import java.util.Collections;
public class Jump_search{
    public static int Jump(int[] arr, int target){
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;
        while(arr[Math.min(step,n)-1]<target){
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if(prev>=n){
                return -1;
            }
        }
        for(int i =prev;i<Math.min(step,n);i++){
            if(arr[i] == target){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;

        int result = Jump(arr, target);

        if (result == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}