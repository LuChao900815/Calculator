/**
 * Assign抽象类主要用来支持变量赋值所需的两个方法
 * @author LC
 *
 */
public abstract class Assign {
	/**
	 * 赋值方法，除了变量节点其他不应该调用此方法
	 * @param val 
	 * @throws Exception 
	 */
	public void  assign(double val) throws Exception{
		//System.out.println("You should not call this method.");
		throw new Exception("You should not call this method.");
	}
	/**
	 * 判断节点是否为左值
	 * @return
	 */
	public boolean isLValue(){
		return  false;
	}
}
