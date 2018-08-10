package CodeScanner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CodeScanner {
	private String _in_file;
	private String _in_str;
	private int _now_idx;
	private Charset _encoding;
	private String _tk_buffer;
	
	public CodeScanner(String in,Charset encoding){
		this._in_file = in;
		this._encoding = encoding;
		this._loadFileIntoCharArray(_in_file,this._encoding);
		this._now_idx = -1;
		this._tk_buffer = "";
	}
	
	private void _loadFileIntoCharArray(String in,Charset encoding){
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(in));
			this._in_str = new String(encoded, encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNextCharInRange(){
		return ((this._now_idx+1) < this._in_str.length())?true:false;
	}
	
	public boolean isLastCharInRange(){
		return ((this._now_idx-1) > 0)?true:false;
	}
	
	public char getNowChar(){
		return this._in_str.charAt(this._now_idx);
	}
	
	public char getNextChar(){
		this._now_idx++;
		return this._in_str.charAt(this._now_idx);
	}
	
	public char getLastChar(){
		this._now_idx--;
		return this._in_str.charAt(this._now_idx);
	}
	
	public void skipNowChar(){
		this._now_idx++;
	}
	
	public void pushBackChar(){
		this._now_idx--;
	}
	
	public void addIntoBufferdToken(char c){
		this._tk_buffer += c;
	}
	
	public void clearBufferdToken(){
		this._tk_buffer = "";
	}
	
	public String getBufferdToken(){
		return this._tk_buffer;
	}
}
