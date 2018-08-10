package CodeGenerator;
import java.io.*;
import java.util.LinkedList;

import SymbolTable.Tokens;
import SyntaxParser.RecursiveDescentParsing;
public class CodeGenerator {

	public static final int MAXIDLEN = 100;
	public static String[] charArr = new String[MAXIDLEN];
	static int max_temp = 0;	//紀錄宣告Temp的數量
	static String tempname = new String();
	public File file;
    public FileWriter fw;
	public BufferedWriter bw;
	
	public static LinkedList<Tokens> aLinkedList = new LinkedList<Tokens>();
    public static LinkedList<Tokens> symbolTable = new LinkedList<Tokens>();
    
	public CodeGenerator(LinkedList<Tokens> t, File f) {
		this.aLinkedList = t;
		this.file = f;
		try {
			fw = new FileWriter(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
	}

	public void start()
	{
	}
	
	public void assign(expr_rec target, expr_rec source)
	{
		generate("STORE", source.getName(), target.getName(), "");
	}
	
	public void read_id(expr_rec in_var)
	{
		generate("READ", in_var.getName(),"INTEGER", "");
	}

	public void write_expr(expr_rec out_expr)
	{
		generate("WRITE", out_expr.getName(),"INTEGER", "");
	}

	public expr_rec gen_infix(expr_rec e1, op_rec op, expr_rec e2)
	{
		expr_rec e_rec = new expr_rec();
		
		e_rec.setKind("TEMPEXPR");
		e_rec.setName(get_temp());
		generate(extract2(op.get()), e1.getName(), e2.getName(), e_rec.getName());
		return e_rec;
	}

	public static op_rec process_op(Tokens tok)
	{
		op_rec o = new op_rec();
		if (tok.getTokenString().equals("+"))
			o.operator = "PLUS";
		else
			o.operator = "MINUS";
		return o;
	}
	
	public expr_rec process_id(Tokens tok)
	{
		expr_rec t = new expr_rec();
		check_id(tok.getTokenString());
		t.setKind("IDEXPR");
		t.setName(tok.getTokenString());
		return t;
	}
	
	public String get_temp()
	{
		max_temp++;
		tempname = "TEMP&"+max_temp;
		check_id(tempname);
		return tempname;
	}
	
	public void check_id(String s)
	{
		if (!lookup(s)) 
		{
			symbolTable.add(new Tokens(s, "IDENTITY"));
			generate("DECLEAR", s, "INTEGER", " ");
		}
	}
	
	public boolean lookup(String s)
	{
		boolean control = false;
		for(int i = 0 ; i < symbolTable.size() ; i++)
		{
			if(symbolTable.get(i).getTokenString().equals(s))
				control = true;
		}
		return control;
	}

	public String extract2(String ops) {
		if (ops.equals("PLUS"))
			return "ADD";
		else
			return "SUB";
	}
	
	public void finish()
	{
		generate("HALT", "", "", "");
	}
		
	private void generate(String string, String name, String string2, String string3) {
		try {
			bw.write(string+" "+name+" "+string2+" "+string3+"\r\n");	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFile(){
		try {
			bw.close();
			fw.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Tokens> getSymbolTable(){
		return symbolTable;
	}
}
