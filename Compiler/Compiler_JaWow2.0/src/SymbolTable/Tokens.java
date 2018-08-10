package SymbolTable;

public class Tokens {
	private String _tk_str;
	private String _tk_type;
	
	public Tokens(String token_string,String token_type) {
		this._tk_str = token_string;
		this._tk_type = token_type;
	}
	
	public void setTokenString(String token_string){
		this._tk_str = token_string;
	}
	
	public void setTokenType(String token_type){
		this._tk_type = token_type;
	}
	
	public String getTokenString(){
		return this._tk_str;
	}
	
	public String getTokenType(){
		return this._tk_type;
	}
}
