package LexicalParser;

import java.util.LinkedList;

import CodeScanner.CodeScanner;
import SymbolTable.Tokens;

public class LexicalParser {
	private LinkedList<Tokens> _tk_tb;
	private String _nextToken;
	private CodeScanner _codeScanner;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public LexicalParser(CodeScanner codeScanner){
		this._tk_tb = new LinkedList<Tokens>();
		this._nextToken = "";
		this._codeScanner = codeScanner;
	}
	
	public void RunParser(){
		char _now_char = ' ';
		while(this._codeScanner.isNextCharInRange()){
			_now_char = this._codeScanner.getNextChar();
			if(this._isSpace(_now_char) || (_now_char == '\n') || (_now_char == '\r')){
				this._codeScanner.clearBufferdToken();
			}else if(this._isAlpha(_now_char)){
				this._codeScanner.addIntoBufferdToken(_now_char);
				for(_now_char = this._codeScanner.getNextChar();(this._isAlnum(_now_char)) || (_now_char == '_');_now_char = this._codeScanner.getNextChar()){
					this._codeScanner.addIntoBufferdToken(_now_char);
				}
				this._nextToken = this._codeScanner.getBufferdToken();
				if(this._checkReserved(this._nextToken)){
					if(this._nextToken.equals("begin")){
						this._addIntoTokenTable("BEGIN", this._nextToken);
					}else if(this._nextToken.equals("end")){
						this._addIntoTokenTable("END", this._nextToken);
					}else if(this._nextToken.equals("read")){
						this._addIntoTokenTable("READ", this._nextToken);
					}else{
						this._addIntoTokenTable("WRITE", this._nextToken);
					}
				}else{
					this._addIntoTokenTable("IDENTITY", this._nextToken);
				}
				this._codeScanner.pushBackChar();
				this._codeScanner.clearBufferdToken();
			}else if(this._isDigit(_now_char)){
				this._codeScanner.addIntoBufferdToken(_now_char);
				boolean isFloat = false;
				for(_now_char = this._codeScanner.getNextChar();this._isDigit(_now_char)|| this._isPoint(_now_char);_now_char = this._codeScanner.getNextChar()){
					if(!isFloat && this._isPoint(_now_char)){
						isFloat = true;
					}else if(isFloat && this._isPoint(_now_char)){
						System.out.println("LEXICAL ERROR: [UNKNOWN TOKEN] IN: "+_now_char);
						System.exit(0);
					}else{
						
					}
					this._codeScanner.addIntoBufferdToken(_now_char);
				}
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("INTLITERAL", this._nextToken);
				this._codeScanner.pushBackChar();
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == '('){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("LPAREN", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == ')'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("RPAREN", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == ';'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("SEMICOLON", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == ','){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("COMMA", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == '+'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("PLUOP", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == '-'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				_now_char = this._codeScanner.getNextChar();
				if(_now_char == '-'){
					do {
						_now_char = this._codeScanner.getNextChar();
					} while (_now_char != '\n');
					this._codeScanner.clearBufferdToken();
				}else{
					this._nextToken = this._codeScanner.getBufferdToken();
					this._addIntoTokenTable("MINUSOP", this._nextToken);
					this._codeScanner.pushBackChar();
					this._codeScanner.clearBufferdToken();
				}
			}else if(_now_char == '*'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("MULTIOP", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == '/'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("DIVIDEOP", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else if(_now_char == ':'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				_now_char = this._codeScanner.getNextChar();
				if(_now_char == '='){
					this._codeScanner.addIntoBufferdToken(_now_char);
					this._nextToken = this._codeScanner.getBufferdToken();
					this._addIntoTokenTable("ASSIGNOP", this._nextToken);
					this._codeScanner.clearBufferdToken();
				}else{
					this._codeScanner.pushBackChar();
					System.out.println("LEXICAL ERROR: [UNKNOWN TOKEN] IN: "+_now_char);
					System.exit(0);
				}
			}else if(_now_char == '$'){
				this._codeScanner.addIntoBufferdToken(_now_char);
				this._nextToken = this._codeScanner.getBufferdToken();
				this._addIntoTokenTable("SCANEOF", this._nextToken);
				this._codeScanner.clearBufferdToken();
			}else{
				System.out.println("LEXICAL ERROR: [UNKNOWN TOKEN] IN: "+_now_char);
				System.exit(0);
			}
		}
	}
	
	private boolean _isSpace(char c){
		return (Character.isWhitespace(c))?true:false;
	}
	
	private boolean _isDigit(char c){
		return Character.isDigit(c);
	}
	
	private boolean _isAlpha(char c){
		return Character.isLetter(c);
	}
	
	private boolean _isAlnum(char c){
		return (Character.isLetter(c) || Character.isDigit(c));
	}
	
	private boolean _checkReserved(String token){
		return (token.equals("begin") || token.equals("end") || token.equals("read") || token.equals("write"))?true:false;
	}
	
	private boolean _isPoint(char c){
		return (c == '.')?true:false;
	}
	private void _addIntoTokenTable(String tk_type,String tk_str){
		Tokens temp = new Tokens(tk_str, tk_type);
		this._tk_tb.addLast(temp);
	}
	
	public LinkedList<Tokens> getTokenTable(){
		return this._tk_tb;
	}
}
