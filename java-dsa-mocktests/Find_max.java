public class Find_max{
    public static int maxvalue(int[] arr, int left, int right){
        if(right-left<=1){
            return arr[left]>arr[right]?arr[left]:arr[right];
        }
        else{
            int mid = left +(right-left)/2;
            int leftmax = maxvalue(arr,left,mid-1);
            int rightmax = maxvalue(arr,mid+1,right);
            return leftmax>rightmax?leftmax:rightmax;
        }
    }
}