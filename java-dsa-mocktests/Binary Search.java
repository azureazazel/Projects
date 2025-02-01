public class Binary Search{
    public static int binary(int[] arr, int target, int left , int right){
        int mid = left +(right -left)/2;
        if(left>right){
            return -1;
        }
        if (target==arr[mid]) {
            return mid;
        }
        else if (target>arr[mid]) {
            return binary(arr,target,mid+1,right);
        }
        else if (target<arr[mid]) {
            return binary(arr,target,left,mid-1);
        }
    }
}