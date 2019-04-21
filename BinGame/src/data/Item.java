package data;

public class Item implements Comparable<Item>{
    
    /**
     * the size of an item is an integer between 0 and 100
     */
    protected int size;

    public Item(int size) {
	super();
	this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean hasCorrectSize() {
	return (this.size <=100);
    }

    @Override
    public int compareTo(Item o) {
	// TODO Auto-generated method stub
	return -(this.getSize()-o.getSize());
    }

    
    

}
