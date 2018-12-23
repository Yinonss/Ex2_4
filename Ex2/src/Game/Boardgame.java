package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Geom.Point3D;

public class Boardgame {

	private Set<Fruit> Fruits=new HashSet<Fruit>();
	private Set<Packman> Packmen=new HashSet<Packman>();
	private Solution solution=new Solution();
	/**This method builds the game using data stored in a scv file.
	 * The method gets path from the user and reads the data from it.
	 * If the first string is P -> the method will create a packman and add
	 * it to the packmen list.
	 * If the first string i F -> the method will create a fruit and add
	 * it to the fruits list.
	 * 
	 * @param path CSV file directory as string.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readCsv (String path) throws FileNotFoundException, IOException {
		File file=new File(path);
		 String line = "";
		 String cvsSplitBy = ",";
		 boolean firstLine=true;
		 try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    
		        while ((line = br.readLine()) != null) {
		        	if(firstLine)
				    	firstLine=false;
				    else {
		            String[] data = line.split(cvsSplitBy);
		            if("P".equals(data[0]))
		            {
		            	Point3D point=new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]));
		            	Packman packman=new Packman(point , Integer.parseInt(data[1]));
		            	Packmen.add(packman);
		            }
		            else if("F".equals(data[0])) {
		            	Point3D point=new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]));
		            	Fruit fruit=new Fruit(point , Integer.parseInt(data[1]));
		            	Fruits.add(fruit);
		            }
				    }
		            }
	}
	
}
	/**This method save the game data to a CSV file according to the game's format
	 * The data that will be saved is the packmen and fruits current location,
	 * the packman path, IDs and how many fruits did the packman ate.
	 */
	public void saveGame() {
		String fileName = "Game.csv";
		PrintWriter pw = null;
		Map map=new Map();
		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		for(Packman packman : Packmen) 
			 packman.setPoint(map.XYtoLatLon(packman.getPoint() , 644,644));
		 for(Fruit fruit : Fruits) 
			 fruit.setPoint(map.XYtoLatLon(fruit.getPoint(), 644, 644));

		 for(Packman packman : Packmen) 
			 packman.setPoint(map.XYtoLatLon(packman.getPoint() , 644,644));
		 for(Fruit fruit : Fruits) 
			 fruit.setPoint(map.XYtoLatLon(fruit.getPoint(), 644, 644));
		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("Eats");
		sb.append(',');
		sb.append("Path");
		sb.append("\n");
		for( Packman packman : Packmen) {
			
			sb.append("P");
			sb.append(",");
			sb.append(packman.getId());
			sb.append(",");
			sb.append(packman.getPoint().x());
			sb.append(",");
			sb.append(packman.getPoint().y());
			sb.append(",");
			sb.append(packman.getPoint().z());
			sb.append(',');
			sb.append(packman.getEats());
			
			Iterator<Point3D> p=packman.getPath().iterator();
			while(p.hasNext()) { 
				Point3D current=p.next();
				sb.append(',');
				sb.append("->");
				sb.append(',');
				sb.append(current.x());
				sb.append(',');
				sb.append(current.y());
				
		}
			
			sb.append('\n');
		}
	
		for( Fruit fruit : Fruits) {
			sb.append("F");
			sb.append(",");
			sb.append(fruit.getId());
			sb.append(",");
			sb.append(fruit.getPoint().x());
			sb.append(",");
			sb.append(fruit.getPoint().y());
			sb.append(",");
			sb.append(fruit.getPoint().z());
			sb.append('\n');
		}
		
		pw.write(sb.toString());
		pw.close();
		
	}
	public Set<Fruit> getFruits() {
		return Fruits;
	}
	public void setFruits(Set<Fruit> fruits) {
		Fruits = fruits;
	}
	public Set<Packman> getPackmen() {
		return Packmen;
	}
	public void setPackmen(Set<Packman> packmen) {
		Packmen = packmen;
	}
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
}
