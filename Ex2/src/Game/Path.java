package Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Coords.MyCoords;

public class Path<Point3D> {
	
	private ArrayList<Point3D> path=new  ArrayList<Point3D>();

	public void addPathPoint(Point3D pathPoint) {
		path.add(pathPoint);
	
	}
	
	public int size() {
		return path.size();
	}

	/**This method computes the path's length.
	 * 
	 * @return the summed distance
	 */
	public double getDistance() {
		double distance=0;
		Iterator<Point3D> currentPath=path.iterator();
		MyCoords mc=new MyCoords();
		while(currentPath.hasNext()) {
			distance=distance+mc.distance3d((Geom.Point3D)currentPath.next(), (Geom.Point3D)currentPath.next());
		}
		return distance;
	}
	public Iterator<Point3D> iterator() {
		return path.iterator();
	}
	public ArrayList<Point3D> getPath() {
		return (ArrayList<Point3D>) path;
	}

	
	}

