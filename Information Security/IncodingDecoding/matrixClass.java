import java.awt.*;

public class matrixClass {
	
	int[][] m = new int[5][5];
	
	public matrixClass()
	{
		for(int i = 0 ; i < 5 ; i++)
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				this.m[i][j] = 0;
			}
		}
	}
	
	public void setm(String s)
	{
		char[] arr = s.toCharArray();
		
		for(int i = 0 ; i < 5 ; i++)
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				this.m[i][j] = ((arr[i*5+j])+254)%95+32;
			}
		}
	}
	
	public int[][] getm()
	{
		return this.m;
	}
	
	public void trans(int[][] matrix)
	{
		for(int i = 0; i < 5; i++) 
		{
			  for(int j = i+1; j < 5; j++) 
			  {
			    int temp = matrix[i][j];
			    matrix[i][j] = matrix[j][i];
			    matrix[j][i] = temp;
			  }
		}
	}
	
	public void print()
	{
		for(int i = 0 ; i < 5 ; i++)
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				System.out.print(this.m[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
