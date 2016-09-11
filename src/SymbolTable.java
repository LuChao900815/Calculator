/**
 * 符号表
 * @author LC		
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SymbolTable {
	private Map<String,Integer> dictionary;
	private int id;
	
	public SymbolTable(){
		dictionary = new HashMap<String,Integer>();
		id = 0;
	}
	/**
	 * 清除
	 */
	public void clear(){
		dictionary.clear();
		id = 0;
	}
	/**
	 * 将str添加到符号表中，返回其id
	 * @param str
	 * @return
	 */
	public int add(String str){
		dictionary.put(str, id);
		return id++;
	}
	/**
	 * 查找str所对应的id值
	 * @param str
	 * @return
	 */
	public int find(String str){
		Integer id = dictionary.get(str);
		if(id != null){
			return  id.intValue();
		}
		return  -1;
	}
	/**
	 * 查找id所对应的变量名
	 * @param id
	 * @return
	 */
	public String getSymbolName(int id){
		Set<Map.Entry<String,Integer>> set = dictionary.entrySet();
		
		Iterator<Map.Entry<String,Integer>> it = set.iterator();
		while(it.hasNext()){
			Map.Entry<String,Integer> me = it.next();
			if(me.getValue() == id){
				return me.getKey();
			}
		}
		return "ERROR";
	}
	
	public String toString(){
		return dictionary.toString();
	}
}
