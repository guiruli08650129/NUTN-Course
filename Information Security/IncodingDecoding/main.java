import java.util.*;

public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter string:");
		String s = sc.nextLine();
		incode(s);
		
		System.out.println("Please enter string:");
		s = sc.nextLine();
		decode(s);
		
	}
	
	public static void incode(String s)
	{		
		char[] chararr = s.toCharArray();
		for(int i = 0 ; i < chararr.length ; i++)
		{
			int id = chararr[i];
			id=(id+254)%95+32;//ascii+1
			chararr[i] = (char)id;
		}
		String outs = String.copyValueOf(chararr);
		System.out.println("\nIncode finish!!");
		System.out.println("Output:"+ outs + "\n");
		
	}
	
	public static void decode(String s)
	{
		char[] chararr = s.toCharArray();
		for(int i = 0 ; i < chararr.length ; i++)
		{
			int id = chararr[i];
			id=(id+252)%95+32;
			chararr[i] = (char)id;
		}
		String outs = String.copyValueOf(chararr);
		System.out.println("\nDecode finish!!");
		System.out.println("Output:"+ outs+ "\n");
	}
	

}
