/**
 * ProductNode类用于(T' 	-> [* /]F)文法
 * @author LC
 */
import java.util.Iterator;

public class ProductNode extends MultipleNode {
	public ProductNode(Node node){
		super(node);
	}
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		Iterator<Node> itNode = childs.iterator();
		Iterator<Boolean> itPos = positives.iterator();
		double val = 1.0;
		while(itNode.hasNext()){
			double aVal = itNode.next().doCalc();
			if(itPos.next()){
				val *= aVal;
			}
			else if( aVal != 0.0){
				val /= aVal;
			}else{
				throw new RuntimeException("dividing by zero");
			}
		}
		return val;
	}
}
