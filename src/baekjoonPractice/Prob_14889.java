package baekjoonPractice;

import java.util.Scanner;

public class Prob_14889 {
	int test=1;
	static int result=Integer.MAX_VALUE;
	public static void min(int i){

		if(i<0)
			i*=-1;
		if(result>i)
			result=i;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int cnt=0;
		int n = sc.nextInt();
		int half=n/2;
		int S[][]= new int[n][n];
		
		int select[]=new int[half];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				S[i][j]=sc.nextInt();
			}
		}
		
		for(int i =0;i<half;i++)
		{
			select[i]=i;
		}
		while(select[0]!=half+1)
		{
			boolean chk[]=new boolean[n];
			
			int sum1 = 0;
			int sum2 = 0;
			for(int i=0;i<half;i++)
			{
				for(int j=0;j<half;j++)
				{
					sum1+=S[select[i]][select[j]];
				}
				chk[select[i]]=true;
			}
			
			for(int i=0;i<n;i++)
				if(!chk[i]){
					for(int j=0;j<n;j++)
					{
						if(!chk[j])
						{
							sum2+=S[i][j];
						}
					}
				}
			
			
			select[half-1]++;
			for(int i=1;i<half;i++){
				if(select[half-i]==n-i+1){
					select[half-i-1]++;
				}
			}
			if(select[0]==half+1)
				break;
			for(int i=0;i<half;i++)
			{
				
				if(select[i]==half+i+1)
					select[i]=select[i-1]+1;
			}
			
			min(sum1-sum2);
		}
		
		System.out.println(result);
		
		
		
	}

}
