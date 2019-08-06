package sort;

import java.util.Comparator;

import utils.ToDoItem;

public class ToDoItemSorter implements Comparator<ToDoItem>{

	@Override
	public int compare(ToDoItem o1, ToDoItem o2) {
		if(o1.getExpiry().compareTo(o2.getExpiry()) > 0) {
			return 1;
		}else if(o1.getExpiry().compareTo(o2.getExpiry()) < 0) {
			return -1;
		}else {
			if(o1.getName().compareTo(o2.getName()) > 0) {
				return 1;
			}else if(o1.getName().compareTo(o2.getName()) < 0) {
				return -1;
			}else {
				if(o1.getId() > o2.getId()) {
					return 1;
				}else {
					return -1;
				}
			}
		}
	}

}
