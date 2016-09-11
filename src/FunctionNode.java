/**
 * FunctionNode类支持函数计算
 * @author LC
 *
 */
public class FunctionNode extends UnaryNode {
	private int id;
	
	public FunctionNode(Node node,int id){
		super(node);
		this.id = id;
	}
	
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		
		switch(id){
		case 0:
			return Math.log(child.doCalc());
		case 1:
			return Math.log10(child.doCalc());
		case 2:
			return Math.exp(child.doCalc());
		case 3:
			return Math.sqrt(child.doCalc());	
		case 4:
			return Math.sin(child.doCalc());
		case 5:
			return Math.cos(child.doCalc());
		case 6:
			return Math.tan(child.doCalc());
		case 7:
			return Math.sinh(child.doCalc());
		case 8:
			return Math.cosh(child.doCalc());
		case 9:
			return Math.tanh(child.doCalc());
		case 10:
			return Math.asin(child.doCalc());
		case 11:
			return Math.acos(child.doCalc());
		case 12:
			return Math.atan(child.doCalc());
		}
		return 0;
	}
}
