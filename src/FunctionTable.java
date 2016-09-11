/**
 * FunctionTable类用于储存所支持的所有函数
 * @author LC
 *
 */
public class FunctionTable {
	private static final int NUMS_OF_FUNCTIONS = 13;
	
	SymbolTable symbolTable;
	
	/**
	 * 将函数名添加到符号表中，函数名放在符号表中前面
	 * @param str
	 */
	private void addValue(String str){
		symbolTable.add(str);
		System.out.println(str);
	}
	
	public FunctionTable(SymbolTable tbl){
		this.symbolTable = tbl;
		
		System.out.println("function supported list as follow: ");
		addValue("log");
		addValue("log10");
		addValue("exp");
		addValue("sqrt");
		addValue("sin");
		addValue("cos");
		addValue("tan");
		addValue("sinh");
		addValue("cosh");
		addValue("tanh");
		addValue("asin");
		addValue("acos");
		addValue("atan");
	}
	
	/**
	 * 判断符号表中id所代表的符号是否为所支持的函数
	 * @param id
	 * @return 
	 */
	public boolean isFunction(int id){
		return id < NUMS_OF_FUNCTIONS;
	}
}
