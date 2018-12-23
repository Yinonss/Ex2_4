package Game; 

import Geom.Point3D;

public class Map {

	/**This method takes a point on the screen and convert it to lat/lon 
	 * coordinates point taking account of the screen height and width.
	 * @param point pixel point
	 * @param height of the screen pixels
	 * @param width of the screen pixels
	 * @return lat/lon point
	 */
	public Point3D XYtoLatLon(Point3D point, int height,int width ) {
		
		return new Point3D((32.10577-(point.y()/height)*0.003871),(35.202469+(point.x()/width)*0.009119));
	}
	/**This method takes a lat/lon point and convert it to a pixel
	 * coordinates point taking account of the screen height and width.
	 * @param point lat/lon point
	 * @param height of the screen pixels
	 * @param width of the screen pixels
	 * @return X/Y point
	 */
	public Point3D LatLontoXY(Point3D point, int height,int width ) {
		
		return new Point3D((point.y()-35.202469)/0.009119*width,((point.x()-32.10577)/0.003871)*-height);
	}
	
}
