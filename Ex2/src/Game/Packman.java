package Game;

import java.util.ArrayList;

import Geom.Point3D;

public class Packman {
	
	private Point3D point;
	private int id;
	private int eats=0;
	private Path path;
	
	public Packman(Point3D _point, int _id ) {
		point=_point;
		id=_id;
		path=new Path();
		Map map=new Map();
		path.addPathPoint(_point);
	}

	public Point3D getPoint() {
		return point;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**This function adds 1 to the eats count.
	 * 
	 */
	public void eat() {
		eats++;
	}
   
	public int getEats() {
		return eats;
	}
	/**this method adds point to the packman path
	 * 
	 * @param pathPoint path point
	 */
	public void add2Path(Point3D pathPoint) {
		path.addPathPoint(pathPoint);
	}

	public Path getPath() {
		return this.path;
	}
	

}
