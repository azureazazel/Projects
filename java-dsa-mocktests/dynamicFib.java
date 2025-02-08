public class dynamicFib{
    public static long[] memo;

    public static long fib(int n){
        if(memo[n]!=-1){
            return memo[n];
        }
        if(n<=1){
            memo[n] = n;
        }
        else{
            memo[n] = fib(n-1)+fib(n-2);
        }
        return memo[n];
    }
    public static void main(String[] args){
        int n = 100;
        memo = new long[n+1];
        for(int i = 0;i<=n;i++){
            memo[i]= -1;
        }
        System.out.println("The fibonacci number at position "+ n + "is:"+ fib(n));
    }
}