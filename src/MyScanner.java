/**
 * EToken枚举用来表示字符类型
 * @author LC
 *
 */
enum EToken{
	ETOKEN_BEGIN,
	ETOKEN_PLUS,   			// '+'
	ETOKEN_MINUS,			// '-'
	ETOKEN_MULTIPLY,		// '*'
	ETOKEN_DIVIDE,			// '/'
	ETOKEN_LPARENTHESIS,	// '('
	ETOKEN_RPARENTHESIS,	// ')'
	ETOKEN_NUMBER,			// number 数值
	ETOKEN_ASSIGN,			// '='
	ETOKEN_IDTIFIER,        // 字符串
	ETOKEN_ERROR,			// 错误
	ETOKEN_END				// 结束标志
}

public class MyScanner {
	private String buf;
	private int    pos;
	private EToken token;
	private double number;
	private StringBuffer id = new StringBuffer();
	
	public MyScanner(String buf)
	{
		this.buf = buf;
		pos = 0;
		setToken(EToken.ETOKEN_BEGIN);
		setNumber(0.0);
	}
	/**
	 * 获取字符类型
	 * @return EToken
	 */
	public EToken getToken() {
		return token;
	}
	/**
	 * 设置字符类型
	 * @param token
	 */
	private void setToken(EToken token) {
		this.token = token;
	}
	/**
	 * 获取数值
	 * @return double
	 */
	public double getNumber() {
		return number;
	}
	/**
	 * 设置数值
	 * @param number
	 */
	private void setNumber(double number) {
		this.number = number;
	}
	
	/**
	 * 跳过空白字符
	 */
	private void skipWhite()
	{
		while(!isEnd() && Character.isSpaceChar(buf.charAt(pos))){
			pos++;
		}	
	}
	/**
	 * 增加pos值
	 */
	private void increasePos(){
		++pos;
	}
	/**
	 * 判断是否到了字符串的末尾
	 * @return
	 */
	public boolean isEnd(){
		return pos == buf.length();
	}
	/**
	 * 扫描缓存区，返回下一个字符的状态
	 */
	public void doAccpet(){
		
		skipWhite();
		
		if(isEnd()){
			setToken(EToken.ETOKEN_END);
			return;
		}
		char c = buf.charAt(pos);
		
		switch(c){
		case '-':
			setToken(EToken.ETOKEN_MINUS);
			increasePos();
			break;
		case '+':
			setToken(EToken.ETOKEN_PLUS);
			increasePos();
			break;
		case '*':
			setToken(EToken.ETOKEN_MULTIPLY);
			increasePos();
			break;
		case '/':
			setToken(EToken.ETOKEN_DIVIDE);
			increasePos();
			break;	
		case '(':
			setToken(EToken.ETOKEN_LPARENTHESIS);
			increasePos();
			break;
		case ')':
			setToken(EToken.ETOKEN_RPARENTHESIS);
			increasePos();
			break;
		case '=':
			setToken(EToken.ETOKEN_ASSIGN);
			increasePos();
			break;
		case '0':case '1':case '2':case '3':case '4':
		case '5':case '6':case '7':case '8':case '9':
		case '.':
			//从字符缓冲区提取数值
			setToken(EToken.ETOKEN_NUMBER);
			int prevPos = pos;
			while(!isEnd() && (Character.isDigit(buf.charAt(pos)) 
					|| buf.charAt(pos) == '.')){
				increasePos();
			}
			number = Double.parseDouble(buf.substring(prevPos,pos));
			break;
		default:
			//从字符缓冲区提取符号,以字符或下划线开头
			if(Character.isAlphabetic(c) || c == '_'){
				//清除上次的id值
				id.delete(0, id.length());
				setToken(EToken.ETOKEN_IDTIFIER);
				do
				{
					id.append(c);
					increasePos();
					if(isEnd())
						break;
					c = buf.charAt(pos);
				}while((Character.isAlphabetic(c) || Character.isDigit(c)
						|| c == '_'));
			}else{
				setToken(EToken.ETOKEN_ERROR);
			}
			break;
		}
	}
	
	/**
	 * 获取符号
	 */
	public String getSymbol(){
		return id.toString();
	}
	/**
	 * 获取EToken状态的描述信息
	 * @return
	 */
	public String getTokenString(){
		return  getToken().name();
	}
	
	/**
	 * 根据token值获取对应描述信息
	 */
	public String toString(){
		switch(token){
		case ETOKEN_BEGIN:
			return "--------------begin---------------";
		case ETOKEN_END:
			return "--------------end---------------";
		case ETOKEN_ERROR:
			return "error";
		case ETOKEN_NUMBER:
			return number + "\t\t\t" + getTokenString();
		default:
			return id + "\t\t\t" + getTokenString();
		}
	}
}
