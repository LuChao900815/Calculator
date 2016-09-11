/**
 * 节点抽象类
 * @author LC
 *
 */

interface AbstractNode {
	double  doCalc() throws Exception;
}

public abstract class Node extends Assign implements AbstractNode {
	
}