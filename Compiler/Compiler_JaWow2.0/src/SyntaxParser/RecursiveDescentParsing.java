package SyntaxParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import SymbolTable.Tokens;
import CodeGenerator.CodeGenerator;
import CodeGenerator.expr_rec;
import CodeGenerator.op_rec;

public class RecursiveDescentParsing {
	
	public static final String BEGIN = "BEGIN";
	public static final String END = "END";
	public static final String SCANEOF = "SCANEOF";
	public static final String ASSIGNOP = "ASSIGNOP";
	public static final String PLUSOP = "PLUSOP";
	public static final String LPAREN = "LPAREN";
	public static final String RPAREN = "RPAREN";
	public static final String SEMICOLON = "SEMICOLON";
	public static final String COMMA = "COMMA";
	public static final String IDENTITY = "IDENTITY";
	public static final String READ = "READ";
	public static final String WRITE = "WRITE";
	public static final String INTLITERAL = "INTLITERAL";
	
	public static LinkedList<Tokens> aLinkedList = new LinkedList<Tokens>();
	public static Tokens tok;
	public static int index = 0;
	public static CodeGenerator cg;

	

	public RecursiveDescentParsing(LinkedList<Tokens> t, File f) {
		this.aLinkedList = t;
		this.cg = new  CodeGenerator(aLinkedList, f);
	}

	public static void match(String LABEL) {
		
		if (!LABEL.equals(tok.getTokenType())) {
			syntax_error(tok);
		} else
			System.out.println(tok.getTokenString() + " is OK");
	}

	public void system_goal() {
		/* <system goal> ::= <program> SCANEOF */
		program();
		tok = next_token();
		match(SCANEOF);
		cg.finish();
	}

	public void program() {
		/* <program> ::= BEGIN <statement list> END */
		tok = next_token();
		match(BEGIN);
		statement_list();
		match(END);
	}

	public static void statement_list() {
		/* <statement list> ::= <statement> { <statement> } */
		statement();
		while (true) {
			String temp = lookAhead(0);
			if (temp.equals(WRITE)){
				statement();
			}
			else if (temp.equalsIgnoreCase(IDENTITY)){
				statement();
			}
			else if (temp.equals(READ)){
				statement();
			}
			else{
				tok = next_token();
				return;
			}
		}
	}

	// next_token(): a function that returns the next token. It does not call
	// scanner(void).

	public static Tokens next_token() {
		tok = aLinkedList.get(index++);
		return tok;
	}

	public static void statement() {
		tok = next_token();
		expr_rec target = new expr_rec(); 
		expr_rec source = new expr_rec();
		switch (tok.getTokenType()) {
		case IDENTITY: // <statement> ::= ID := <expression> ;
		{	match(IDENTITY);
		    target = cg.process_id(tok);
			tok = next_token();
			match(ASSIGNOP);
			source = expression2(source);
			cg.assign(target, source);
			tok = next_token();
			match(SEMICOLON);
			break;
		}
		case READ: // <statement> ::= READ ( <id list> ) ;
			match(READ);
			match(LPAREN);
			id_list();
			match(RPAREN);
			match(SEMICOLON);
			break;
		case WRITE: // <statement> ::= WRITE ( <expr list> ) ;
			match(WRITE);
			match(LPAREN);
			expr_list();
			match(RPAREN);
			match(SEMICOLON);
			break;/**/
		default:
			syntax_error(tok);
			break;
		}
	}

	public static void id_list() {
		/* <id list> ::= ID { , ID } */
		match(IDENTITY);
		while (next_token().getTokenType() == COMMA) {
			match(COMMA);
			match(IDENTITY);
		}
	}


	
	public static expr_rec expression2(expr_rec result) {
		expr_rec left_operand = new expr_rec();
		expr_rec right_operand = new expr_rec();
		op_rec op = new op_rec();

		left_operand = primary(left_operand);
		if(lookAhead(0).equals(SEMICOLON)){
			return left_operand;
		}
		tok = next_token();
		while (tok.getTokenString().equals("+") || tok.getTokenString().equals("-")){
			op = add_op(op);
			right_operand = primary(right_operand);
			left_operand = cg.gen_infix(left_operand, op, right_operand);
		}
		return left_operand;
	}
	
	public static expr_rec primary(expr_rec result) {
		Tokens temp = next_token();
		switch (temp.getTokenType()){
		case LPAREN:
			//<primary> ::= (<expression>)
			match(LPAREN);
			result = expression2(result);
			temp = next_token();
			match(RPAREN);
			return result;
		case IDENTITY:
			//<primary> ::= ID
			match(IDENTITY);
			return cg.process_id(temp);
		case INTLITERAL:
			//<primary> ::= INTLETERAL
			match(INTLITERAL);
			result.setKind("LITERALEXPR");
			result.setName(temp.getTokenString());
			return result;
		default:
			syntax_error(tok);
			return result;
		}
	}
	
	public static op_rec add_op(op_rec ops) {
		//<add op> ::= PLUSOP | MINUSOP
		if (tok.getTokenString().equals("+") || tok.getTokenString().equals("-")) {
			match(tok.getTokenType());
			return cg.process_op(tok);
		}
		else{
			syntax_error(tok);
			return null;
		}
	}

	public static void expr_list() {
		/* <expr list> ::= <expression> { <expression> } */
		expr_rec target = new expr_rec();
		target = expression2(target);
		while (next_token().getTokenString() == COMMA) {
			match(COMMA);
			target = expression2(target);
		}
	}

	public static void add_op() {
		/* <add op> ::= PLUSOP I MINUSOP */
		index--;
		tok = next_token();
		
		if (tok.getTokenType().equals("PLUOP") || tok.getTokenType().equals("MINUSOP") || tok.getTokenType().equals("DIVIDEOP") || tok.getTokenType().equals("MULTIOP"))
			match(tok.getTokenType());
		else
			syntax_error(tok);
	}



	private static void syntax_error(Tokens tok) {
		
		System.out.println("Error Tokens: " + tok.getTokenString() );
		System.out.println("Error Tokens' Label: " + tok.getTokenType());
		System.exit(0);
	}
	
	public void closeFile(){
		cg.closeFile();
	}
	
	private static String lookAhead(int n){
		return aLinkedList.get(index+n).getTokenType();
	}
	
	public static void outputSymbolTable(){
		LinkedList<Tokens> ou = cg.getSymbolTable();
		File file;
	    FileWriter fw;
		BufferedWriter bw;
		file = new File("symbol.txt");
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(int i=0;i<ou.size();i++){
				bw.write(ou.get(i).getTokenString()+"\r\n");
			}
			bw.close();
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
