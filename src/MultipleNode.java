import java.util.ArrayList;
import java.util.List;

/**
 * 多重节点抽象类
 * @author LC
 *
 */
public abstract class MultipleNode extends Node{
	protected List<Node> childs = new ArrayList<Node>();;
	protected List<Boolean> positives = new ArrayList<Boolean>();
	
	public MultipleNode(Node node){
		appendChild(node,true);
	}
	
	/**
	 * 添加节点
	 * @param node 
	 * @param positive 表示正负性
	 */
	public void appendChild(Node node,Boolean positive){
		childs.add(node);
		positives.add(positive);
	}
}
