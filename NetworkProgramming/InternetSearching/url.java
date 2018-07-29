import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;

public class url {
	
	public String key;
	public int num;
	public String cat;
	
	
	public url(String key, String cat, int num)
	{
			this.key = key;
			this.num = num;
			this.cat = cat;
	}

	public void parser(String str, String cat, int page){	
		
		 try { 
			 
			URL url = new  URL("http://search.books.com.tw/exep/prod_search.php?cat=" + cat + "&page=" + page + "&key=" 
										+URLEncoder.encode(str, "utf-8")); // 網址

			BufferedReader buf = new BufferedReader (new InputStreamReader(url.openStream(), "utf-8"));
			FileOutputStream fr = new FileOutputStream("response.txt", true);
			OutputStreamWriter ow = new OutputStreamWriter(fr, "utf-8");// 原始碼存在 response.txt
			String line;
			
			
			while ((line = buf.readLine()) != null)  
			{ 
				ow.write(line+"\r\n"); 
			}
			
			    fr.close();  
			    buf.close();
			    ow.close();
			    
			    
			}
			catch (Exception e) {};
	
	}
	
	public void analysis() throws IOException
	{
		BufferedReader buf = new BufferedReader (new InputStreamReader(new FileInputStream("response.txt"), "utf-8"));
		BufferedWriter fr = new BufferedWriter (new OutputStreamWriter(new FileOutputStream("data.txt"),"UTF-8"));
		String line;
	    LinkedList<String> ss = new LinkedList<String>();
	    LinkedList<String> title = new LinkedList<String>();
	    LinkedList<String> author = new LinkedList<String>();
	
	    while ((line = buf.readLine()) != null)  
	    { 
	    	if(line.contains("<a target=\"_blank\"") || line.contains("<a rel=\"go_author\"") 
	    			|| line.contains("<a rel=\"mid_publish\"") || line.contains("</strong>"))    	
	    	{
	    		String[] temp = line.replaceAll("[,.\";!:\'\\[\\]()\\?\\&\\$\\{\\}*+\\-\\=]"," ").split("title");
	    		for(int i = 0 ; i < temp.length ; i++)
	    		{
	    			ss.add(temp[i]);
	    		}
	    	}	    		
	    }
	    

	    for(int j = 0 ; j < ss.size() ; j++)
	    {
	    	if(ss.get(j).contains("</a><h3>"))
	    	{
	    		String[] temp = ss.get(j).replace("<p", "").split("\\>");
	    		title.add(".");
	    		title.add(temp[0].trim());
	    	}
	    	else if(ss.get(j).contains("</a>"))
	    	{
	    		String[] temp = ss.get(j).replace("<p", "").split("\\>");
	    		title.add(temp[0].trim());
	    	}
	    	else if(ss.get(j).contains("<b>"))
	    	{
	    		String[] temp;
	    		if(ss.get(j).trim().contains("，"))
	    		{
	    			temp = ss.get(j).split("，");

	    		}
	    		else
	    		{
	    			temp = ss.get(j).split("：");

	    		}
	    			temp[1] = temp[1].replaceAll("<b>", "");
	    			temp[1] = temp[1].replaceAll("</b>", "");
	    			temp[1] = temp[1].replace("</strong>", "");
	    			//System.out.println(temp[1].trim());	    		
	    		
	    			title.add(temp[1].trim());

	    	}
	    		
	    }
	    
	    for(int k = 0 ; k < title.size() ; k++)
	    {
	    	fr.write(title.get(k).trim() + "\r\n");	   

	    }
	    
	    System.out.println("SUCCESS");
	    title.clear();
	    ss.clear();
	    fr.close();  
	    buf.close();
	}
	
}
