package Game;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution {

	private ArrayList<Path> paths= new ArrayList<Path>();
	
	
	public void addPath(Path path) {
		paths.add(path);
	}


	public int size() {
		return paths.size();
	}


	public boolean isEmpty() {
		return paths.isEmpty();
	}


	public String toString() {
		return paths.toString();
	}


	public boolean add(Path e) {
		return paths.add(e);
	}


	public Iterator<Path> iterator() {
		return paths.iterator();
	}
	
}
