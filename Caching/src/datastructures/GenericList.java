package datastructures;

import java.util.ArrayList;

public class GenericList<T extends Page> {

	
	protected boolean genericListHasCurrent(ArrayList<T> list, T page) {
		boolean contains = false;
		for(int i = 0; i < list.size(); i++) {
			// equals needs <T extends Page> to use Page.equals otherwise it would use Object.equals
			if(list.get(i).equals(page)) {
				contains = true;
			}
		}
		return contains;
	}
	
	protected ArrayList<T> getDisitincElements(ArrayList<T> genList) {
		ArrayList<T> list = new ArrayList<T>();
		for(int i = 0; i < genList.size(); i++) {
			T addCurrent = genList.get(i);
			if(!genericListHasCurrent(list, addCurrent)) {
				list.add(addCurrent);
			}
		}
		return list;
	}
}
