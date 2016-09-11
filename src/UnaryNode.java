/**
 * 一元节点
 * 
 */
public abstract class UnaryNode extends  Node {
	protected Node child;
	public UnaryNode(Node child){
		this.child = child;
	}
}
