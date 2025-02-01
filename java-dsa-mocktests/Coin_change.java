import java.util.Arrays;
public class Coin_change{
    public static int coin(int[] coins,int amount){
        Arrays.sort(coins);
        int count = 0;
        int n = coins.length;
        for(int i = n-1;i>=0 && amount>0;i--){
            while(amount>=coins[i]){
            amount -= coins[i];
            count++;
            }
        }
        return amount==0?count:-1;
    }
    public static void main(String[] args){
        int[] coins = {1,5,10,25};
        int amount = 89;
        int result = coin(coins, amount);
        if (result!=-1) {
            System.out.println("The mininmum number of coins to fill the purse for that amount is "+ result);
        }
        else{
            System.out.println("There is not a combination of coins for that amount");
        }
    }
}