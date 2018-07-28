import java.util.*;
import java.io.*;
import java.math.*;

public class matrix {

	public static String before = new String();
	
	public static void main(String[] args) {
		
		//Scanner sc = new Scanner(System.in);
		
		//System.out.println("Please enter string:");
		//String s = sc.nextLine();
		
		try {
			
			incode2();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		/*
		System.out.println("Please enter string:");
		s = sc.nextLine();
		decode(s);*/
		
	}
	
	public static String alph2num(char[] chararr)
	{
		for(int i = 0 ; i < chararr.length ; i++)
		{
			int id = chararr[i];
			id=(id+253)%95+32;
			chararr[i] = (char)id;
		}
		String outs = String.copyValueOf(chararr);
		
		return outs;
	}
	
	public static void incode() throws IOException
	{
		FileInputStream fin;
		try {
			
			fin = new FileInputStream("doc.txt");
			byte buf[] = new byte[5];//建立一個大小8Byte的緩衝區
			int bufSize;//用來計算讀入的資料大小
			do
			{
				bufSize = fin.read(buf);//讀取資料到buf內
				byte[] key = buf;
				int[] num = new int[5];
				
				for(int i : key)
					num [i]= key[i]&0xff;
				//String s = new String(buf, 0, bufSize);
				
				System.out.println(num);
				
				
			}while (fin.available() > 0);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static  int[] byte2int(byte b[])
	{
		int t1=((b[0]&0xff)+253)%95+32;
		int t2=((b[1]&0xff)+253)%95+32;
		int t3=((b[2]&0xff)+253)%95+32;
		int t4=((b[3]&0xff)+253)%95+32;
		//System.out.println(t1+" "+t2+" "+t3+" "+t4);
		int[] arr = {t1, t2, t3, t4}; 
		
		return arr ;
	}
	
	public static void incode2() throws IOException
	{
		
		FileReader f = new FileReader(new File("doc.txt"));
		BufferedReader br = new BufferedReader(f);
		String s = "";		
		
		while(br.ready())
		{
			s = s + " " +br.readLine();			
		}
		
		br.close();
		f.close();
		
		char[] arr = s.toCharArray();
		int size=(arr.length);
		
		int array2d[][] = new int[5][93];
		while(s.length()%25!=0)
		{
			s += " ";
		}

		
		LinkedList<matrixClass> l = new LinkedList<matrixClass>();
		for(int i=0; i<s.length()/25;i++)
		{
			matrixClass mc = new matrixClass();
		   String subs = s.substring(i*25, i*25+25);
		   mc.setm(subs);
		   l.add(mc);
		}
		
		for(int i=0; i<l.size();i++)
		{
			l.get(i).trans(l.get(i).getm());
			l.get(i).print();
			System.out.println();
		}
		
		
		
	}
	
	public static void decode(String s)
	{
		
	}

}
