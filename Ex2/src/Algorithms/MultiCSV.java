package Algorithms;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import File_format.Csv2Layer;
import File_format.Csv2kml;
import GIS.GISProject;
import GIS.GIS_layer;
import GIS.GIS_project;

public class MultiCSV {
	
        /**This method getting the folder location and send it to displayDirectoryContents.
         * The thing that returns from that function is an array list of strings which contains
         * CSV file names. For every file name that the list has we create a GIS layer.
         * Then we gather the layers together into one GIS project (set of layers).
         * The method returns the project and also send it to kmlMaker to create a new KML file.
         * @param path Folder location as a string.
         * @return GIS project - a collection of GIS layers made from several CSV files.
         * @throws IOException
         */
		public static GIS_project projectCreator(String path) throws IOException {
			File currentDir = new File(path); 
			ArrayList<String> list=displayDirectoryContents(currentDir);
			GIS_project project=new GISProject();
			for(String dir : list) {
				Csv2Layer cl= new Csv2Layer();
				GIS_layer layer=cl.layersCreator(dir);
				project.add(layer);
			} 
			Csv2kml ck=new Csv2kml();
			ck.kmlMaker(project);
		
			return project;
		}

		/**This function go through all of the folder files and adding the files
		 * path location in the list. The recursion will stop when it reach to a
		 * file.
		 * @param dir  folder That contains CSV files.
		 * @return List of all of the CSV file names.
		 */
		public static ArrayList<String> displayDirectoryContents(File dir) {
			 ArrayList<String> alist=new ArrayList<String>(); 
		     	try {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
                        alist.add(file.getCanonicalPath());
						displayDirectoryContents(file);
					}
					else 
						alist.add(file.getCanonicalPath());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return alist;
	}
	}
