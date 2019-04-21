package data;
	// Implementierung des ENUMERATION-Interfaces:
public class DoubleLinkedListEnumeration
{
	private Node node;
	private Object o;
	
	public DoubleLinkedListEnumeration(DoubleLinkedList dll)
	{
		this.node = dll.getHead();
	}
	
	public boolean hasMoreElements()
	{
		return (this.node != null);
	}
	
	public Object nextElement()
	{
		o = node.getContent();
		node = node.getNextNode();
		return o;
	}
}