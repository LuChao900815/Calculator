/**
 * 一元负号节点
 * @author LC
 *
 */
public class UMinusNode extends UnaryNode {

	public  UMinusNode(Node child){
		super(child);
	}
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		return -child.doCalc();
	}
}
