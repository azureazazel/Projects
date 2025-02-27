public class Quicksort{
    public static void quicksort(int[] arr,int low,int high){
        if(low<high){
            int pivotindex = partition(arr,low,high);
            quicksort(arr,pivotindex+1,high);
            quicksort(arr,low,pivotindex-1);
        }
    }
    private static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static int partition(int[] arr,int low, int high){
        int pivot = arr[high];
        int i = low -1;
        for(int j = low;j<high;j++){
            if(arr[j]<pivot){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }
    public static void main(String[] args){
        int[] arr = {10, 7, 8, 9, 1, 5};
        quicksort(arr, 0, arr.length - 1);
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    } 
}