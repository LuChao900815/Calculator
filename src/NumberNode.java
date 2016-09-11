/**
 * 数值节点
 * @author LC
 *
 */
public class NumberNode extends Node {

	private double number;
	public NumberNode(double number){
		this.number = number;
	}
	@Override
	public double doCalc() {
		// TODO Auto-generated method stub
		return number;
	}
}
