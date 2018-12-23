package Game;

import Geom.Point3D;

public class Fruit {
	
	private Point3D point;
	private int id;
	private boolean eaten;
	public Fruit(Point3D _point, int _id ) {
		point=_point;
		id=_id;
		eaten=false;
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
	
	public void eaten() {
		this.eaten=true;
	}

	public boolean isEaten() {
		return eaten;
	}
	
}
