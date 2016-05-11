
class DList {
	
	private DListNode head;
	private DListNode tail;
	private int size;

	public DList(){
		head = new DListNode(null,null,null);
		tail = new DListNode(null,null,null);
		size = 0;
	}
	
	public void insertAfter(Object item){
		if (size==0){
			head.next = new DListNode(item,null,null);
			tail.prev = head.next;
			size++;
		} else {
			tail.prev.next = new DListNode(item,tail.prev,null);
			tail.prev = tail.prev.next;
			size++;
		}
	}
	
	public int getSize(){
		return this.size;
	}
	
	public DListNode getHead(){
		return head;
	}
	
	
}
