package File_format;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GISLayer;
import File_format.Csv2Layer;
import Geom.Point3D;


public class Csv2kml  {

	/**kmlMaker is a method that produce a KML file from CSV data.
	 * This function has two versions, one is turning a single CSV file into a KML file
	 * and the other one turning a GIS project(several CSV files) into a KML file.
	 * 
	 * The next method receiving a string form the user , which is the file name,
	 * and send is to layersCreator to make a GIS layer.
	 * After we got a layer we write a kml file using google earth file format. 
	 * Every location is being set by "placemark" and has two kinds of fields;
	 * Coordinates , determine the place's location, and Description , other data 
	 * that will be displayed. 
	 * The method will go through all of the layers element and set the on the KML file.
	 * @param path The CSV file as a string.
	 * @throws IOException 
	 */
	public void kmlMaker (String path )  throws IOException{
		Csv2Layer cl= new Csv2Layer();
		GISLayer layer= cl.layersCreator(path);

	    PrintWriter writer = new PrintWriter(new File("GISfile.kml"));
	    writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
	    writer.println("<Document>");
	    writer.println("<Folder>");
	    writer.println("<name/>");
	    for (GIS_element element : layer) {
	        writer.println("<Placemark>");
	        writer.println("<name/>");
	        writer.println("<description><![CDATA[Timestamp: <b>" + element.getData().getUTC() + "</b><br/>Date: <b></b>]]></description>");
	        writer.println("<Point>");
	        writer.println("<coordinates>" + ((Point3D) element.getGeom()).y() + "," + ((Point3D) element.getGeom()).x() + "," + ((Point3D) element.getGeom()).z() + "</coordinates>");
	        writer.println("</Point>");
	        writer.println("</Placemark>");
	    }
	    writer.println("</Folder>");
	    writer.println("</Document>");
	    writer.println("</kml>");
	    writer.close();
	} 
	/**kmlMaker is a method that produce a KML file from CSV data.
	 * This function has two versions, one is turning a single CSV file into a KML file
	 * and the other one turning a GIS project(several CSV files) into a KML file.
	 * 
	 * The next method receiving a GIS project for the user and write a new kml file.
	 * Firstly , we will use Google Earth kml pattern opening. Then , the function will set every element 
	 * of every layer in the KML file. Using its coordinates to determine the location and 
	 * description to display the placemark's data.
	 * @param project GIS project that was build on multiScv.
	 * @throws IOException
	 */
	public void kmlMaker(GIS_project project) throws IOException {
		

		PrintWriter writer = new PrintWriter(new File("GISfile.kml"));
	    writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
	    writer.println("<Document>");
	    writer.println("<Folder>");
	    writer.println("<name/>"); 
	    
	    for(GIS_layer layers: project) {
	    for (GIS_element element : layers) {
	        writer.println("<Placemark>");
	        writer.println("<name/>");
	        writer.println("<description><![CDATA[Timestamp: <b>" + element.getData().getUTC() + "</b><br/>Date: <b></b>]]></description>");
	        writer.println("<Point>");
	        writer.println("<coordinates>" + ((Point3D) element.getGeom()).y() + "," + ((Point3D) element.getGeom()).x() + "," + ((Point3D) element.getGeom()).z() + "</coordinates>");
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

	
	
	

