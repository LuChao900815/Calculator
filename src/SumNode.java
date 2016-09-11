/**
 * ProductNode类用于(T' 	-> [+ -]F)文法
 * @author LC
 * 
 */
import java.util.Iterator;

public class SumNode extends MultipleNode {

	public SumNode(Node node){
		super(node);
	}
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		Iterator<Node> itNode = childs.iterator();
		Iterator<Boolean> itPos = positives.iterator();
		double sum = 0.0;
		while(itNode.hasNext()){
			if(itPos.next()){
				sum += itNode.next().doCalc();
			}
			else{
				sum -= itNode.next().doCalc();
			}
		}
		return sum;
	}
}
