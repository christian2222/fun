package data;

import java.util.*;

public class DoubleLinkedList implements Cloneable
{
	private Node head,tail;
	private Node tmp,tmp2;
	private Random r = new Random();

	
	public DoubleLinkedList()
	{
		this.createNew();
	}
	
	public DoubleLinkedList(Object o)
	{
		this.createNew();
		this.addAsHead(o);
	}
	
	private void createNew()
	{
		this.head = null;
		this.tail = null;
	}
	
	public Node getHead()
	{
		return (this.head);
	}
	
	public Node getTail()
	{
	    return (this.tail);
	}
	
	public void add(Object o)
	{
	    this.addAsHead(o);
	}
	
	public void addAsHead(Object o)
	{
		tmp = new Node(o); //unlinked

		if(this.head == null)
		{
			this.head = tmp;
			this.tail = tmp;
			
		}
		else
		{
			this.head.setPrevNode(tmp);
			tmp.setNextNode(this.head);
			this.head = tmp;
		}
		
		tmp = null;
	}
	
	public void addAsTail(Object o)
	{
		tmp = new Node(o); //unliked
		
		if(this.tail == null)
		{
			this.head = tmp;
			this.tail = tmp;
		}
		else
		{
			tmp.setPrevNode(this.tail);
			this.tail.setNextNode(tmp);
			this.tail = tmp;
		}
		
		tmp = null;
	}

	public Node getNodeOfObject(Object o)
	{
		if (this.isEmpty()) return null;
		
		tmp = this.head;
		if (tmp.getContent().equals(o)) return tmp;
		
		while(tmp.getNextNode() != null)
		{
			tmp = tmp.getNextNode();
			if (tmp.getContent().equals(o)) return tmp;
			
		}
		
		return null;
	}
	
	public boolean hasElement(Object o)
	{
		return (this.getNodeOfObject(o) != null);
	}
	
	public void erase(Object o)
	{
		tmp = this.getNodeOfObject(o);
		if (tmp != null)
		{
			if( tmp.equals(this.getHead()) )
			{
			    tmp2 = this.head;
		    	this.head = this.head.getNextNode();
			    tmp2.unlink();
			    if(this.head != null)
			    	this.head.setPrevNode(null);
			}
			else 
			    if( tmp.equals(this.getTail()) )
			    {
		    	    tmp2 = this.tail;
			        this.tail = this.tail.getPrevNode();
			        tmp2.unlink();
			        if(this.tail != null)
			        this.tail.setNextNode(null);
		    	}
				else // => o ist in der Liste zwischen zwei anderen Einträgen enthalten
				{
					tmp.getPrevNode().setNextNode(tmp.getNextNode());
					tmp.getNextNode().setPrevNode(tmp.getPrevNode());
				}
		}
	}
	
	public boolean isEmpty()
	{
		return (this.head == null);
	}
	
	public boolean isNotEmpty()
	{
		return (!this.isEmpty());
	}
	
	public void eraseAll()
	{
		if(this.isNotEmpty())
		{
			tmp = this.head;
			
			while(this.head.getNextNode() != null)
			{
				this.head = this.head.getNextNode();
				this.head.setPrevNode(null);
				tmp.unlink();
				tmp = this.head;
			}
			
			this.head.unlink();
			
			this.createNew();
			
		}
	}
	
	public long countItems()
	{
	    long counter = 0;
	    
	    DoubleLinkedListEnumeration enumeration = new DoubleLinkedListEnumeration(this);
	    Object o = null;
	    while(enumeration.hasMoreElements())
	    {
	        o = enumeration.nextElement();
	        counter++;
	    }
	    
	    return counter;
	}
	
	public Object getRandomObject()
	{
	    if(this.isNotEmpty())
	    {
	    	Node n = this.getHead();
		    long count = this.countItems();
		    long num = r.nextInt() % (count +1);
	    	while(num > 0)
	    	{
	    	    if(n != null)
					n = n.getNextNode();
	    	    else
	    	        n = this.getHead();
	        	num--;
	    	}
	    	
	    	if (n == null)
	    	    n = this.getHead();
	    	
	    	return n.getContent();
	    }
	    else
	    	return null;
	}
	
	public Object clone()
	{
	    DoubleLinkedList dll = new DoubleLinkedList();
	    Object o = null;
	    DoubleLinkedListEnumeration enumeration = new DoubleLinkedListEnumeration(this);
	    while(enumeration.hasMoreElements())
	    {
	        o = enumeration.nextElement();
	        dll.add(o);
	    }
	    
	    return dll;
	}

	
}