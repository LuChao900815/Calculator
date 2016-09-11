/**
 * 解析类，将表达式解析成一颗表达式树
 * @author LC
 *
 */
enum STATUS{
	STATUS_OK,
	STATUS_ERROR,
	STATUS_QUIT
}

/**
 * MyParser类用于将表达式解析成一颗表达式树
 * @author LC
 *
 */

public class MyParser {
	//扫描类
	private MyScanner myscanner;
	//符号表、函数表、变量表
	private SymbolTable symTbl;
	private FunctionTable funTable;
	private Storage storage;
	//表达式树的根节点
	private Node tree;
	//状态
	STATUS status = STATUS.STATUS_OK;
	/**
	 * 获取解析状态
	 * @return
	 */
	public STATUS getStatus() {
		return status;
	}

	public MyParser(MyScanner scanner,Storage storage,SymbolTable sym,FunctionTable tbl){
		this.myscanner = scanner;
		this.storage = storage;
		this.symTbl = sym;
		this.funTable = tbl;
	}
	
	/**
	 * parse方法负责解析
	 * @throws Exception 
	 */
	public void parse() throws Exception{
		tree = expression();
		if(!myscanner.isEnd()){
			status = STATUS.STATUS_ERROR;
		}
	}
	
	/**
	 * 计算表达式的值
	 */
	public double calculate() throws Exception{
		if(tree == null){
			//status = STATUS.STATUS_ERROR;
			throw new Exception("invalid expression");
		}
		return tree.doCalc();
	}
	
	/**
	 * 文法：
	 * E 	-> TE'
	 * 		 |  T = E
	 * E' 	-> [+-]TE'
	 * 		 | null
	 * @return
	 * @throws Exception 
	 */
	public Node expression() throws Exception{
		//按照文法 (E 	-> TE')解析
		Node node = term();
		EToken token = myscanner.getToken();
		
		//遇到'+','-'按照文法 (E' 	-> [+-]TE')解析
		if(token == EToken.ETOKEN_PLUS || token == EToken.ETOKEN_MINUS){
			MultipleNode mNode = new SumNode(node);
			
			do{
				myscanner.doAccpet();
				node = term();
				mNode.appendChild(node, token == EToken.ETOKEN_PLUS);
				token = myscanner.getToken();
			}while(token == EToken.ETOKEN_PLUS || token == EToken.ETOKEN_MINUS);
			
			node = mNode;
		}else if(token == EToken.ETOKEN_ASSIGN){ // 遇到 = ，按照文法 (E -> T = E)解析
			myscanner.doAccpet();
			Node rightNode = expression();
			if(node.isLValue()){
				node = new AssignNode(node,rightNode);
			}else{
				//status = STATUS.STATUS_ERROR;
				//System.out.println("it is not a left-handle value");
				throw new Exception("it is not a left-handle value");
			}
		}
		return node;
	}
	
	/**
	 * 文法：
	 * T 	-> FT'
	 * T' 	-> [* /]F
	 * 		 | null
	 * @return
	 * @throws Exception 
	 */
	public Node term() throws Exception{
		//按照文法 (T 	-> FT')解析
		Node node = factor();
		EToken token = myscanner.getToken();
		//遇到'*','/',按照文法 (T' 	-> [* /]F)解析
		if(token == EToken.ETOKEN_MULTIPLY || token == EToken.ETOKEN_DIVIDE){
			MultipleNode mNode = new ProductNode(node);
			
			do{
				myscanner.doAccpet();
				node = factor();
				mNode.appendChild(node, token == EToken.ETOKEN_MULTIPLY);
				token = myscanner.getToken();
			}while(token == EToken.ETOKEN_MULTIPLY || token == EToken.ETOKEN_DIVIDE);
			
			node = mNode;
		}
		return node;
	}
	
	/**
	 * 文法：
	 * F 	-> NUM
	 * F    -> -F
	 * F    -> (E)
	 * F    -> ID(E)
	 * F	-> ID
	 * @throws Exception 
	 */
	public Node factor() throws Exception{
		Node node = null;
		EToken token = myscanner.getToken();
		
		if(token == EToken.ETOKEN_BEGIN){
			myscanner.doAccpet();
			token = myscanner.getToken();
		}
		
		if(token == EToken.ETOKEN_NUMBER){ 					//F 	-> NUM
			myscanner.doAccpet();
			node = new NumberNode(myscanner.getNumber());
		}else if(token == EToken.ETOKEN_MINUS){				//F 	-> -F
			myscanner.doAccpet();
			node = new UMinusNode(factor());
		}else if(token == EToken.ETOKEN_LPARENTHESIS){ 		// F    -> (E)
			myscanner.doAccpet();
			node = expression();
			token = myscanner.getToken();
			if(token != EToken.ETOKEN_RPARENTHESIS){ 
				node = null;
				status = STATUS.STATUS_ERROR;
				//System.out.println("invalid expression: miss )");
				throw new Exception("invalid expression: miss )");
			}else{
				myscanner.doAccpet();
			}
		}else if(token == EToken.ETOKEN_IDTIFIER){			
			myscanner.doAccpet();							
			String symbol = myscanner.getSymbol();
			int id = symTbl.find(symbol);
			//函数调用				
			if(myscanner.getToken() == EToken.ETOKEN_LPARENTHESIS){ //F  -> ID(E)
				myscanner.doAccpet();
				node = expression();
				if(myscanner.getToken() == EToken.ETOKEN_RPARENTHESIS){
					myscanner.doAccpet();
					if(id != -1 && funTable.isFunction(id)){ 	
						node = new FunctionNode(node,id);
					}else{
						//System.out.println(symbol + " is not  a function");
						status = STATUS.STATUS_ERROR;
						throw new Exception(symbol + " is not  a function");
					}
				}else{
					//System.out.println("in a function call missing ) ");
					status = STATUS.STATUS_ERROR;
					throw new Exception("in a function call missing )");
				}
			//变量
			}else{
				if(id == -1){								//F  -> ID
					id = symTbl.add(symbol);
				}
				node = new VariableNode(id,storage);
			}
		}
		else
		{
			//System.out.println("invalid expression");
			status = STATUS.STATUS_ERROR;
			throw new Exception("invalid expression");
		}
		return node;
	}
}
