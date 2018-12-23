package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import Geom.Point3D;

public class Path2KML {
	private Map map= new Map();
	/**This function is getting a game and produce a KML file.
	 * It's being done by using Google Earth KML file intro, and
	 * for each path point we added a new placemark.
	 * 
	 * @param game
	 * @throws FileNotFoundException
	 */
	public void saveAsKML(Boardgame game) throws FileNotFoundException {
		
		for(Packman packman : game.getPackmen()) 
			 packman.setPoint(map.XYtoLatLon(packman.getPoint() , 644,644));
		 for(Fruit fruit : game.getFruits()) 
			 fruit.setPoint(map.XYtoLatLon(fruit.getPoint(), 644, 644));
		 PrintWriter writer = new PrintWriter(new File("Game.kml"));
		    writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		    writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		    writer.println("<Document>");
		    writer.println("<Folder>");
		    writer.println("<name/>");
		    for(Packman packman : game.getPackmen()) {
		    	
				Iterator<Point3D> p=packman.getPath().iterator();
				while(p.hasNext()) { 
					Point3D current=p.next();
					writer.println("<Placemark>");
			        writer.println("<name/>");
			        writer.println("<description><![CDATA[ID: <b>" + packman.getId() + "</b><br/>: <b></b>]]></description>");
			        writer.println("<Point>");
			        writer.println("<coordinates>" + current.y() + "," + current.x() + "," + current.z() + "</coordinates>");
			        writer.println("</Point>");
			        writer.println("</Placemark>");
				}
		    }
		    writer.println("</Folder>");
		    writer.println("</Document>");
		    writer.println("</kml>");
		    writer.close();
		    
		    
	}

}
