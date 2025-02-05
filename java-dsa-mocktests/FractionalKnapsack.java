import java.util.Arrays;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {

    public static double fractionalKnapsack(int W, Item[] arr, int n) {
        // Sort items based on value-to-weight ratio
        Arrays.sort(arr, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));
        double totalValue = 0;

        for (int i = 0; i < n; i++) {
            // Add entire item if it doesn't exceed capacity
            if (W - arr[i].weight >= 0) {
                W -= arr[i].weight;
                totalValue += arr[i].value;
            } else {  // Else add a fraction of it
                double fraction = (double) W / arr[i].weight;
                totalValue += arr[i].value * fraction;
                W = (int) (W - (arr[i].weight * fraction));
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int W = 50;   // Knapsack capacity
        Item[] arr = {new Item(10, 60), new Item(20, 100), new Item(30, 120)};
        int n = arr.length;

        System.out.println("Maximum value in knapsack = " + fractionalKnapsack(W, arr, n));
    }
}