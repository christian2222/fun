package data;
/*
 * Created on 01.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author chrissy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Vokabel implements Comparable,Cloneable
{
    private String wort;
    private String vokabel;
    private int wdh;
    
	public Vokabel()
	{
	    this.wdh = 1;
	    this.wort = "";
	    this.vokabel = "";
	}
	
	public Vokabel(String wort, String vokabel, int wdh)
	{
	    this.wort = wort;
	    this.vokabel = vokabel;
	    this.wdh = wdh;
	}
	public Vokabel(String wort, String vokabel)
	{
	    this.wort = wort;
	    this.vokabel = vokabel;
	    this.wdh = 1;
	}
	
	public void swip()
	{
	    String tmp = this.wort;
	    this.wort = this.vokabel;
	    this.vokabel = tmp;
	}
	
	public Object clone()
	{
	    return new Vokabel(this.wort,this.vokabel);
	}
	
	public void turnAround()
	{
		String tmp = this.wort;
		this.wort = this.vokabel;
		this.vokabel = tmp;
	}
	
	public void setWort(String wort)
	{
	    this.wort = wort;
	}
	
	public String getWort()
	{
	    return this.wort;
	}
	
	public void setVokabel(String vokabel)
	{
	    this.vokabel = vokabel;
	}

	public String getVokabel()
	{
	    return this.vokabel;
	}
	
	public int getWdh()
	{
	    return this.wdh;
	}

	public void setWdh(int wdh)
	{
	    this.wdh = wdh;
	}
	
	public void decWdh()
	{
	    if(this.wdh > 0)
	        this.wdh--;
	}
	
	public boolean isToWdh()
	{
	    return (this.wdh > 0);
	}

	public boolean equals(Vokabel vok)
	{
	    boolean isWort = vok.getWort().equals(this.getWort());
	    boolean isVokabel = vok.getVokabel().equals(this.getVokabel());
	    return ((isWort)&&(isVokabel));
	    
	}
	
	public String toString()
	{
	    return (this.wort+" |~| "+this.vokabel);
	}
	
	public int compareTo(Vokabel vok)
	{
	    return this.getWort().compareTo(vok.getWort());
	}
	
	public int compareTo(Object o)
	{
	    if( o instanceof Vokabel)
	    {
	        return this.compareTo((Vokabel)o);
	    }
	    return 1;
	}

}
