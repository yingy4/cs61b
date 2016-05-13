package list;

public class LockDList extends DList {

	
	  /**
	   *  LockDList() constructor for an empty LockDList.
	   */
	public LockDList() {
		super();
	}
	
	
	public void lockNode(DListNode node){
		((LockDListNode)node).isLocked=true;
	}
	
	
	  /**
	   *  newNode() calls the LockDListNode constructor.
	   *  @param item the item to store in the node.
	   *  @param prev the node previous to this node.
	   *  @param next the node following this node.
	   */
	  protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
	    return new LockDListNode(item, prev, next);
	  }
	
	
	  /**
	   *  remove() removes "node" from this LockDList.  If "node" is null, do nothing.
	   *  if "node" is locked, do nothing.
	   *  Performance:  runs in O(1) time.
	   */
	  public void remove(DListNode node) {
		  if (((LockDListNode)node).isLocked){
			  return;
		  } else {
			  super.remove(node);
		  }
	  }
	

}
