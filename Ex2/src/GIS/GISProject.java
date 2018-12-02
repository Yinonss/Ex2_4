package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GISProject implements GIS_project {
	
	private Set<GIS_layer> layers=new HashSet<GIS_layer>();

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.layers.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.layers.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return this.layers.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return this.layers.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.layers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return this.layers.toArray(a);
	}

	@Override
	public boolean add(GIS_layer e) {
		// TODO Auto-generated method stub
		return this.layers.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return this.layers.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.layers.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		// TODO Auto-generated method stub
		return this.layers.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.layers.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.layers.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.layers.clear();
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return this.get_Meta_data();
	}

}
