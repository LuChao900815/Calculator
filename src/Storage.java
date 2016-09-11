/**
 * Storage类表示变量表，储存变量值
 * @author LC
 */
import java.util.ArrayList;

public class Storage {
	private final int NUM_OF_FUNCTIONS = 13;
	private ArrayList<Double> cells;
	private ArrayList<Boolean> inits;
	
	public Storage(SymbolTable tbl){
		cells = new ArrayList<Double>();
		inits = new ArrayList<Boolean>();
		addConstants(tbl);
	}
	
	/**
	 * 清除
	 */
	public void clear(){
		cells.clear();
		inits.clear();
	}
	/**
	 * 将e,pi添加到符号表和变量表中
	 * @param tbl
	 */
	public void  addConstants(SymbolTable tbl){
		System.out.println("variable list: ");
		int id = tbl.add("e");
		setValue(id,Math.exp(1.0));
		System.out.println("e = " + Math.exp(1.0));
		id = tbl.add("pi");
		addValue(id,Math.PI);
		System.out.println("pi = " + Math.PI);
	}
	/**
	 * 将Str，value添加到符号表和变量表中
	 * @param tbl 符号表
	 * @param str 字符串
	 * @param value 值
	 */
	public void  addConstants(SymbolTable tbl,String str,double value){
		int id = tbl.add(str);
		setValue(id,value);
	}
	/**
	 * 将value添加到表中，同时初始化
	 * @param id    not used
	 * @param value 
	 */
	public void addValue(int id,double value){
		cells.add(value);
		inits.add(true);
	}
	/**
	 * 修改或添加符号表中为id的符号的值
	 * @param id
	 * @param value
	 */
	public void setValue(int id,double value){
		id -= NUM_OF_FUNCTIONS;						//去除函数的个数
		if(id < cells.size()){
			cells.set(id,value);
		}else{
			addValue(id,value);
		}
	}
	/**
	 * 获取id所对应变量的值
	 * @param id
	 * @return
	 */
	public double getValue(int id){					
		id -= NUM_OF_FUNCTIONS;						//去除函数的个数
		if(id < cells.size()){
			return cells.get(id);
		}
		return Double.MAX_VALUE;
	}
	
	//判断id所对应的变量是否初始化
	public boolean isInit(int id){
		id -= NUM_OF_FUNCTIONS;
		if(id >= inits.size()){
			return false;
		}
		return inits.get(id);
	}
	
	@Override
	public String toString(){
		return cells.toString() + "\n" + inits.toString();
	}
}
