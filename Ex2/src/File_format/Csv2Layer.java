package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.GISElement;
import GIS.GISLayer;
import GIS.GISMetaData;
import GIS.Meta_data;
import Geom.Point3D;

public class Csv2Layer {
	/**This class reads data form CSV file and turn it into GIS layer.
	 * This method going through all the files lines , and turn every line into an array
	 * of strings. Those strings contain data that builds an element. Cells 0-5,9,10 are
	 * for the element meta data , and the rest for the location coordinates.
	 * Every element is being added to the GIS layer.
	 * @param path String that represent a file name.
	 * @return GIS Layer - > represent CSV file.
	 * @throws IOException
	 */
	public GISLayer layersCreator (String path) throws  IOException{
		
	    String csvFile = path;
	    String line = "";
	    String cvsSplitBy = ",";
	    int count=1;
	    GISLayer layer=new GISLayer();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	  
	        while ((line = br.readLine()) != null) {
	            String[] geoInfo = line.split(cvsSplitBy);
	            if( count > 3) {
	            	
	            Meta_data data=new GISMetaData(geoInfo[0] ,geoInfo[1] ,geoInfo[2] ,geoInfo[3] ,geoInfo[4] ,geoInfo[5] ,geoInfo[9] ,geoInfo[10]);
	            Point3D point=new Point3D(Double.parseDouble(geoInfo[6]),Double.parseDouble(geoInfo[7]),Double.parseDouble(geoInfo[8]));
	            GISElement element=new GISElement(data,point);
	            layer.add(element);
	            }
	        	else
	        		count++;
	        }
	    } catch (IOException e) 
	    {
	    	
	        e.printStackTrace();
	    }
       return layer;
	}
}