/**
 * 变量节点类
 * @author LC
 *
 */
public class VariableNode extends  Node {
	private int id;
	private Storage storage;
	public VariableNode(int id, Storage storage) {
		this.id = id;
		this.storage = storage;
	}
	/**
	 * 重写assign
	 */
	public void assign(double val){
		storage.setValue(id, val);
	}
	/**
	 * 变量节点应该为左值
	 */
	public boolean isLValue(){
		return true;
	}
	
	@Override
	public double doCalc() throws Exception {
		// TODO Auto-generated method stub
		double x = 0.0;
		if(storage.isInit(id))
		{
			x = storage.getValue(id);
			return x;
		}
		else{
			//System.out.println("it is not initialized value.");
			throw new Exception("it is not initialized value.");
		}
	}
}
