package data;
public class Node
{
	private Object content;
	private Node prev,next;
	
	public Node(Object o)
	{
		this.content = o;
		this.unlink();
	}
	
	public Node(Object o, Node prev, Node next)
	{
		this.content = o;
		this.link(prev,next);
	}
	
	public void link(Node prev, Node next)
	{
		this.prev = prev;
		this.next = next;
	}
	
	public void unlink()
	{
		this.prev = null;
		this.next = null;
	}
	
	public void setContent(Object o)
	{
		this.content = o;
	}

	public Object getContent()
	{
		return (this.content);
	}
	
	public void setNextNode(Node n)
	{
		this.next = n;
	}

	public Node getNextNode()
	{
		return (this.next);
	}

	public void setPrevNode(Node n)
	{
		this.prev = n;
	}

	public Node getPrevNode()
	{
		return (this.prev);
	}
	
	public boolean equals(Node n)
	{
	    return this.getContent().equals(n.getContent());
	}
}