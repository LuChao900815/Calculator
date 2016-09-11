/**
 * AssignNode类用于变量赋值
 * @author LC
 *
 */
public class AssignNode extends BinaryNode {

	public AssignNode(Node left, Node right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		double x = right.doCalc();
		left.assign(x);
		return x;
	}
}
