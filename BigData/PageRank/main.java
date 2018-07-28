import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class main {

	public static ArrayList<Integer> fromNode = new ArrayList<Integer>();
	public static ArrayList<Integer> toNode = new ArrayList<Integer>();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long start=0, read=0, end=0;
		
		try {
			
			start = System.currentTimeMillis();

			File file = new File("web-Google.txt");
			File outfile = new File("output.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile)));
			double frequency = 0;
			

			while(reader.ready())
			{
				String s = reader.readLine();
				String[] temp = s.split("\t");
				
				fromNode.add(1+Integer.parseInt(temp[0].trim()));
				toNode.add(1+Integer.parseInt(temp[1].trim()));				
			}
			
			read = System.currentTimeMillis();
			
			for(int k = 0 ; k < 5105039 ;  )
			{
				
				frequency = (Collections.frequency(fromNode, fromNode.get(k)));
				for(int w = 0 ; w < frequency ; w++)
				{
					writer.write(fromNode.get(k)+" " + toNode.get(k)+" "+(1/frequency)+"\n");
					System.out.println(k);
					k++;
				}
				writer.flush();
			}
			
			fromNode.clear();
			toNode.clear();
			reader.close();
			writer.close();
			//bw.close();
			//fromNode.clear();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		end = System.currentTimeMillis();
		System.out.println("read = "+(read-start)/1000 + "秒");
		System.out.println("write = "+(end-read)/1000 + "秒");
		
	}

}
