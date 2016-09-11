/**
 * 二元节点
 * @author LC
 *
 */
public abstract class BinaryNode  extends  Node {
	protected Node left;
	protected Node right;
	public BinaryNode(Node left,Node right){
		this.left = left;
		this.right = right;
	}
}
