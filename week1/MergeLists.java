//program to merge linked list
class Node
{
	int data;
	Node next;
	Node(int d) {data = d;
				next = null;}
}
	
class MergingLists
{
Node head;

public void addingLast(Node node)
{
	if (head == null)
	{
		head = node;
	}
	else
	{
		Node temp = head;
		while (temp.next != null)
			temp = temp.next;
		temp.next = node;
	}
}

void displayList()
{
	Node temp = head;
	while (temp != null)
	{
		System.out.print(temp.data + " ");
		temp = temp.next;
	}
	System.out.println();
}



public static void main(String args[])
{
	
	MergingLists list1 = new MergingLists();
	MergingLists list2 = new MergingLists();
	
	
	list1.addingLast(new Node(100));
	list1.addingLast(new Node(400));
	list1.addingLast(new Node(-1000));
	list1.addingLast(new Node(-500));
	

	list2.addingLast(new Node(-300));
	list2.addingLast(new Node(2000));
	list2.addingLast(new Node(-500));
	
	
	list1.head = new Gfg().MergeList(list1.head, list2.head);
	list1.displayList();	
	
}
}

class Gfg
{
Node MergeList(Node headA, Node headB)
{
	
	Node dummy = new Node(0);
	
	int sum = 0;
	Node tail = dummy;
	while(true)
	{
		
		if(headA == null)
		{
			tail.next = headB;
			break;
		}
		if(headB == null)
		{
			tail.next = headA;
			break;
		}
		
		sum = sum + headA.data;
		if(sum > 0){
		    tail.next = headA;
		    headA = headA.next;
		}
		else if(sum < 0){
		    sum = headA.data+headB.data;
		       tail.next = headB;
		        headB = headB.next; 
		    }
		  
        else{
            tail.next = headA;
            headA = headA.next;
        }
		tail = tail.next;
	}
	return dummy.next;
}
}

