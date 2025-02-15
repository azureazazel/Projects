public class LCS{
    public static int subseq(String A, String B){
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i<=m;i++){
            for(int j = 0;j<=n;j++){
                if(i==0|| j==0){
                    dp[i][j]=0;
                }
                else if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args){
        String A = "bghhjuy";
        String B = "bjkhtu";
        int result = subseq(A, B);
        System.out.println("The longest common subsequence in these both strings"+"\nA:"+ A+"\nB:"+ B+"\nare "+result);
    }
}