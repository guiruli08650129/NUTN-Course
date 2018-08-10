package MainProc;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import SyntaxParser.RecursiveDescentParsing;
import CodeGenerator.CodeGenerator;
import CodeScanner.CodeScanner;
import LexicalParser.LexicalParser;
import SymbolTable.Tokens;

public class main {
	public static void main(String[] args){
		String fileName = "code.txt";
		CodeScanner codeScanner = new CodeScanner(fileName, StandardCharsets.UTF_8);
		LexicalParser lexicalParser = new LexicalParser(codeScanner);
		lexicalParser.RunParser();
		LinkedList<Tokens> aLinkedList = lexicalParser.getTokenTable();
		System.out.println("----------詞法分析-----------");
		for(int i=0;i<aLinkedList.size();i++){
			System.out.println(i+" "+aLinkedList.get(i).getTokenType()+" "+aLinkedList.get(i).getTokenString());
		}
		System.out.println("----------------------------");
		File file = new File("target.txt");
		System.out.println();
		System.out.println("----------語法分析-----------");
		RecursiveDescentParsing RDP = new RecursiveDescentParsing(aLinkedList, file);
		RDP.system_goal();
		RDP.outputSymbolTable();
		RDP.closeFile();
		System.out.println("----------------------------");
	}
}
