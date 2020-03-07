import java.util.*;
class Dpbasics{
    int fibonaci(int n){
    	/* Worst case time complexity is 2^n and space complexity is O(n)*/
    	if(n==0 || n==1)
    		return n;
    	int lastTerm=fibonaci(n-1);
    	int secondLastTerm=fibonaci(n-2);
    	return lastTerm + secondLastTerm;
    }
    int fibonaciMem(int n,int dp[]){
    	/* Time complexity is removed using memoizaton and removing overall
           subproblems -time complexity is O(n) and space is O(n)

    	*/
    	if(n==0 || n==1)
    		return n;
    	if(dp[n]!=-1)
    		return dp[n];
    	int lastTerm=fibonaci(n-1);
    	int secondLastTerm=fibonaci(n-2);
    	dp[n]=lastTerm + secondLastTerm;
    	return lastTerm + secondLastTerm;
    }
    int fibonaciPureDp(int n){
    	/*Bottom up approach with O(n) compleity for both time andspace
         Space complexity can further be reduced as we do not require the whole array
         we can maintain three varaiable as first,second and third
    	*/

    	int dp[]=new int[n+1];
    	dp[0]=0;
    	dp[1]=1;
        for(int i=2;i<=n;i++)
        	dp[i]=dp[i-1]+dp[i-2];
    	return dp[n];
    }
    int reduceToOne(int n){
    	/* Purely recursive code worst case time complexity is 3^n and space complexity is O(n)*/
    	if(n==1)
    		return 0;
    	int oneCount=Integer.MAX_VALUE;
    	int twoCount=Integer.MAX_VALUE;
    	int threeCount=Integer.MAX_VALUE;
    	oneCount=reduceToOne(n-1);
    	if(n%2==0)
    		twoCount=reduceToOne(n/2);
    	if(n%3==0)
    		threeCount=reduceToOne(n/3);
    	return 1+Math.min(oneCount,Math.min(twoCount,threeCount));
    }
    int reduceToOneMemo(int n,int dp[]){
    	/* Memoised code with time complexity and space complexity
    	 as O(n)*/
    	if(n==1)
    		return 0;
    	if(dp[n]!=-1)
    		return dp[n];
    	int oneCount=Integer.MAX_VALUE;
    	int twoCount=Integer.MAX_VALUE;
    	int threeCount=Integer.MAX_VALUE;
    	oneCount=reduceToOneMemo(n-1,dp);
    	if(n%2==0)
    		twoCount=reduceToOneMemo(n/2,dp);
    	if(n%3==0)
    		threeCount=reduceToOneMemo(n/3,dp);
    	dp[n]=1+Math.min(oneCount,Math.min(twoCount,threeCount));
    	return dp[n];

    }
    int reduceToOnePureDp(int n){
    	/*Pure Dp base approach of complexity O(n) for both time ans Space*/
    	int dp[]=new int[n+1];
    	dp[0]=0;
    	dp[1]=0;
    	dp[2]=1;
    	dp[3]=1;
    	int oneCount=Integer.MAX_VALUE;
    	int twoCount=Integer.MAX_VALUE;
    	int threeCount=Integer.MAX_VALUE;
        for(int i=4;i<=n;i++)
    	{
         oneCount=dp[i-1];
         if(i%3==0)
         threeCount=dp[i-3];
         if(i%2==0)
         twoCount=dp[i-2];

         dp[i]=1+Math.min(oneCount,Math.min(twoCount,threeCount));
    	}
    	return dp[n];
    }
    public int countBoardPaths(int curr,int n){
    	if(curr==n)
    		return 1;
    	if(curr>n)
    		return 0;
    	int count=0;
    	for(int i=1;i<=6;i++)
    		count+=countBoardPaths(curr+i,n);
    	return count;
    }
    int countBoardPathsMemo(int curr,int n,int dp[]){
    	if(curr==n)
    		return 1;
    	if(curr>n)
    		return 0;
    	if(dp[curr]!=-1)
    		return dp[curr];
    	int count=0;
    	for(int i=1;i<=6;i++)
    		count+=countBoardPathsMemo(curr+i,n,dp);
    	dp[curr]=count;
    	 return count;

    }
    int countBoardPathsPureDP(int n){
    	int dp[]=new int[n+1];
    	dp[n]=1;
        for(int i=n-1;i>=0;i--){
        	for(int dice=1;dice<=6;dice++){
        		if(dice+i>n)
        			break;
        		else
        			dp[i]+=dp[dice+i];
        	}
        }
    	return dp[0];

    }
    int getPerfectSum(int n){
    	if(n==1)
    		return 1;
    	if(n==0)
    		return 0;

    	int minValue=Integer.MAX_VALUE;
    	for(int i=1;i*i<=n;i++){
    		minValue=Math.min(minValue,getPerfectSum(n-(i*i)));
    	}
    	return minValue+1;
    }
    int getPerfectSumMemo(int n,int dp[]){
    	if(n==1)
    		return 0;
    	if(n<=0)
    		return 0;
    	if(dp[n]!=-1)
    		return dp[n];

         int minValue=Integer.MAX_VALUE;
         for(int i=1;i*i<=n;i++){
    		minValue=Math.min(minValue,getPerfectSumMemo(n-(i*i),dp));
    	}
    	dp[n]=minValue+1;
    	return dp[n];
    }
    int numSquares(int n) {
        int dp[]=new int[n+1];
        dp[0]=0;
        dp[1]=1;

        for(int i=2;i<=n;i++){
            int min=Integer.MAX_VALUE;
            for(int k=1;k*k<=i;k++){
                min=Math.min(min,dp[i-k*k]);

            }
            dp[i]=min+1;


        }

        return dp[n];

    }
    int getMaxMoney(int arr[],int i,int j){
         if(i>=j)
         	return 0;

    	int include=arr[i]+getMaxMoney(arr,i+2,j);
    	int exclude=getMaxMoney(arr,i+1,j);
    	return Math.max(include,exclude);
    }
     int getMaxMoneyMemo(int arr[],int i,int j,int []dp){
         if(i>=j)
         	return 0;
          if(dp[i]!=-1)
          return dp[i];
    	int include=arr[i]+getMaxMoneyMemo(arr,i+2,j,dp);
    	int exclude=getMaxMoneyMemo(arr,i+1,j,dp);
    	dp[i]=Math.max(include,exclude);
    	return Math.max(include,exclude);
    }
    int getMaxMoneyPureDp(int arr[],int n){
    	int dp[]=new int[n+2];
        for(int i=n-1;i>=0;i--){
         int include=arr[i]+dp[i+2];
         int exclude=dp[i+1];
         dp[i]=Math.max(include,exclude);

        }
        return dp[0];
    }
	public static void main(String [] args){
		/* Fibonaci using pure recursion*/

		Dpbasics d=new Dpbasics();
		// System.out.println(d.fibonaci(5));
		// int dp[]=new int[6];
		// Arrays.fill(dp,-1);
		// System.out.println(d.fibonaciMem(5,dp));
		// System.out.println(d.fibonaciPureDp(5));

		/* Given a number n find the minimum number of steps required to reduce it to one using following operations
		1.reduce number by one
		2.divide by 3 but only when n is divisible by 3
		3. divide by 2 but only when n is divisivle by 2*/
		// int n=12;
		// int dp[]=new int[n+1];//the size of array is n+1
		// Arrays.fill(dp,-1);
		// System.out.println(d.reduceToOne(n));
		// System.out.println(d.reduceToOneMemo(n,dp));
		// System.out.println(d.reduceToOne(n));

		/*

         Given a dice and a borad of lenght n we have to find the total number of ways to cover the whole board
         by throwing the dice

		*/
   //       int n=10;
   //       int dp[]=new int[n+1];//the size of array is n+1
		 // Arrays.fill(dp,-1);
   //       System.out.println(d.countBoardPaths(0,n));
   //       System.out.println(d.countBoardPathsMemo(0,n,dp));
   //       System.out.println(d.countBoardPathsPureDP(10));

         /* Given a number n find the find the number of perfect square that sums upto n*/
         /*int n=12;
         int dp[]=new int[n+1];//the size of array is n+1
		 Arrays.fill(dp,-1);
         System.out.println(d.getPerfectSumMemo(n,dp));
         System.out.println(d.numSquares(n));*/


         /*
           You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
            LEETCODE-198
         */
            int arr[]={1,2,3,1};
            int n=arr.length;
           int dp[]=new int[n+1];//the size of array is n+1
		    Arrays.fill(dp,-1);
            System.out.println(d.getMaxMoney(arr,0,n));
            System.out.println(d.getMaxMoneyMemo(arr,0,n,dp));
            System.out.println(d.getMaxMoneyPureDp(arr,n));







	}
}
