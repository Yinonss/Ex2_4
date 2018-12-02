package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GISLayer implements GIS_layer {
 
	private Set<GIS_element> lines= new HashSet<>();
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.lines.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.lines.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return this.lines.contains(o);
	}

	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return this.lines.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.lines.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return this.lines.toArray(a);
	}

	@Override
	public boolean add(GIS_element e) {
		// TODO Auto-generated method stub
		return this.lines.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return this.lines.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.lines.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		// TODO Auto-generated method stub
		return this.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.lines.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.lines.removeAll(c);
	}

	@Override
	public void clear() {
		this.lines.clear();
		// TODO Auto-generated method stub
		
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return this.get_Meta_data();
	}

}
