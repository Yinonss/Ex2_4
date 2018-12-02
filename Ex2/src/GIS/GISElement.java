package GIS;

import Coords.MyCoords;
import Geom.Geom_element;

import Geom.Point3D;

public class GISElement implements GIS_element{
				
		private Meta_data data;
		private Geom_element point;
		
		public GISElement(Meta_data data ,Geom_element point ) {
			this.data=data;
			this.point=point;
		}
		
		@Override
		public Geom_element getGeom() {
			// TODO Auto-generated method stub
			return this.point;
		}
		@Override
		public Meta_data getData() {
			// TODO Auto-generated method stub
			return this.data;
		}
		/**This function receive a vector and adds it to the point.
		 * 
		 */
		@Override
		public void translate(Point3D vec) {
			// TODO Auto-generated method stub 
			MyCoords mc= new MyCoords();
			Point3D p=new Point3D(vec);
			Point3D newPoint=mc.add(p, vec);
			this.point=newPoint;
		}

	}



