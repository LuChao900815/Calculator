/**
 * 主类用于测试
 * @author LC
 */

import java.util.Scanner;

public class Calcutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = null;
		STATUS status = STATUS.STATUS_OK;
		
		try{
			input = new Scanner(System.in);
			String line = null;
			MyScanner scanner = null;
			MyParser myParser = null;
			
			SymbolTable symbolTable = new SymbolTable();
			FunctionTable funTable = new FunctionTable(symbolTable);
			Storage storage = new Storage(symbolTable);
			
			do{
				try{
						System.out.print(">");
					line = input.nextLine();
					line.toLowerCase();
					if(line.equals("end")){
						break;
					}
					scanner= new MyScanner(line);
					myParser = new MyParser(scanner,storage,symbolTable,funTable);
					myParser.parse();
					if((status = myParser.getStatus()) != 
							STATUS.STATUS_ERROR){
						System.out.println(myParser.calculate());
					}else{
						System.out.println("Syntax Error");
					}
				}catch(Exception e){
					System.out.println(e);
				}	
			}while(status != STATUS.STATUS_QUIT);
		}finally{
			input.close();
		}
	}
}
