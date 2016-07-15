
public class PriorityQueue {
	
	Node[] pq;
	int back = 0;
	int front = 0;
	int size = 0;
	
	public PriorityQueue() {
		back = 0; //where to insert next
		front = 0; //where to remove next
		size = 0; //how many elements
	}
	
	public void createPQ(int dimension) {
		
		this.pq = new Node[(dimension*dimension)];
	}
	
	public Node[] getPQ() {
		return pq;
	}
	
	public void add(Node n) {
		pq[back] = n;
		back++;
		size++;
		//System.out.println("Added node. Size = "+size+" front = "+front+" back = "+back);
	}
	
	public Node remove() {
		Node ICHOOSEYOU = pq[front];
		front++;
		size--;
		if(size == 0) {
			front = 0;
			back = 0;
		}
		//System.out.println("Removed node. Size = "+size+" front = "+front+" back = "+back);
		return ICHOOSEYOU;
	}
	
	public void sort() {
		Node swap;
		for(int i=front; i<(back-1); i++) {
			for(int j=front+1; j<(back); j++) {
				if(pq[j-1].cost > pq[j].cost) {
					swap = pq[j-1];
					pq[j-1] = pq[j];
					pq[j] = swap;
				}
			}
		}
		/*System.out.print("Sorted PQ as follows: ");
		for(int i=front; i<back; i++) {
			pq[i].printInfo();
		}
		System.out.println();*/
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void print() {
		for(int i=front; i<back; i++) {
			pq[i].printInfo();
		}
	}
}
