package baekjoonPractice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * �迭 clone�� 2�� ���� �ؾ���
 * ��ü instance�� ���� clonable extends �ؾߵǴµ� ���⼭ �������� ��ü �ȿ� ����ִ� �ſ� ���� shallow cloning�� deeply cloning�ϱ� ���� ���� �ٲܰ� ���� ����
 * �ٸ� ������� �迭 ������  dfs �������� 2�� �迭 ���� �ٲ�� ���� Ǯ�� ���ϴ� �� �ذ�
 * dfs �� ��ü�� ����� Ǯ������ �׷����� �迭�� Ǯ����� .... �������� -������ ����
 * ������ �ڵ常 �����... 4���� �̵��ϴ� �ڵ带 ������ �����ϸ� �� �����ϰ� ���� �� ���� �� 
 * 
 */
public class prob_12100 {

	
	static int result=0;
	public static void recur(int num, puzzle p)
	{
		if(num==5)
		{
			for(int i=0;i<p.length;i++)
				for(int j=0;j<p.length;j++)
				{
					if(result<p.get(i, j))
						result=p.get(i, j);
				}
		
		}
		else{
			num++;
			int gg[][] = new int[p.length][p.length];
			copy(p.field,gg);
			
			
			
			recur(num,p.up());
			copy(gg, p.field);
			recur(num,p.down());
			copy(gg, p.field);
			recur(num,p.left());
			copy(gg, p.field);
			recur(num,p.right());
			copy(gg, p.field);
			
		}
	}
	
	public static void copy(int[][] a,int[][] b)
	{
		for(int i=0;i<a.length;i++)
			b[i]=a[i].clone();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		Scanner sc =new Scanner(System.in);
		int n =sc.nextInt();

		puzzle puz = new puzzle(n);
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				puz.setField(i, j, sc.nextInt());
		int gg[][] = new int[n][n];
		copy(puz.field,gg);
		
		recur(0,puz);
		
		System.out.println(result);
		
		
	}

}

class puzzle
{
	
	Queue<Integer> box = new LinkedList<Integer>();
	int[][] field;
	int length;
	
	
	puzzle(int k)
	{
		this.field= new int[k][k];
		this.length = k;
	}
	public int get(int a,int b)
	{
		return field[a][b];
	}
	
	public void setField(int a, int b, int value)
	{
		field[a][b]=value;
	}
	

	public puzzle up(){
		
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(field[j][i]!=0)
				{
					box.add(field[j][i]);
					field[j][i]=0;
				}
			}
			int j=0;
			while(!box.isEmpty())
			{
				field[j][i]=box.poll();
				j++;
			}
			
		}
		
		for(int i=0;i<length;i++)
			for(int j=0;j<length-1;j++)
			{
				if(field[j][i]==field[j+1][i])
				{
					field[j][i]+=field[j][i];
					field[j+1][i]=0;
					for(int k=j+1;k<length-1;k++)
						field[k][i]=field[k+1][i];
					field[length-1][i]=0;
				}
			}
		return this;
	}
	public puzzle down(){
		for(int i=0;i<length;i++)
		{
			for(int j=length-1;j>=0;j--)
			{
				if(field[j][i]!=0)
				{
					box.add(field[j][i]);
					field[j][i]=0;
				}
			}
			int j=length-1;
			while(!box.isEmpty())
			{
				field[j][i]=box.poll();
				j--;
			}
			
		}
		
			for(int j=0;j<length;j++)
				for(int i=length-1;i>0;i--)
				{
				if(field[i][j]==field[i-1][j])
				{
					field[i][j]+=field[i][j];
					field[i-1][j]=0;
					for(int k=i-1;k>0;k--)
						field[k][j]=field[k-1][j];
					field[0][j]=0;
				}
			}
		return this;
	}
	public puzzle right(){
		for(int i=0;i<length;i++)
		{
			for(int j=length-1;j>=0;j--)
			{
				if(field[i][j]!=0)
				{
					box.add(field[i][j]);
					field[i][j]=0;
				}
			}
			int j=length-1;
			while(!box.isEmpty())
			{
				field[i][j]=box.poll();
				j--;
			}
			
		}
		
		
		for(int j=0;j<length;j++)
			for(int i=length-1;i>0;i--)
			{
				if(field[j][i]==field[j][i-1])
				{
					field[j][i]+=field[j][i];
					field[j][i-1]=0;
					for(int k=i-1;k>0;k--)
						field[j][k]=field[j][k-1];
					field[j][0]=0;
				}
			}
		return this;
	}
	public puzzle left(){
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(field[i][j]!=0)
				{
					box.add(field[i][j]);
					field[i][j]=0;
				}
			}
			int j=0;
			while(!box.isEmpty())
			{
				field[i][j]=box.poll();
				j++;
			}
			
		}
		
		
		for(int j=0;j<length;j++)
			for(int i=0;i<length-1;i++)	
			{
				if(field[j][i]==field[j][i+1])
				{
					field[j][i]+=field[j][i];
					field[j][i+1]=0;
					for(int k=i+1;k<length-1;k++)
						field[j][k]=field[j][k+1];
					field[j][length-1]=0;
				}
			}
		return this;
	}
	
	public void print(){
		for(int i=0;i<length;i++){
			for(int j=0;j<length;j++)
				System.out.print(this.field[i][j]);
			System.out.println("");
		}
	}
}
