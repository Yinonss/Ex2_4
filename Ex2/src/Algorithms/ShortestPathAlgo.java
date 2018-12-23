package Algorithms;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.Timer;

import Coords.MyCoords;
import Game.Boardgame;
import Game.Fruit;
import Game.Map;
import Game.Packman;
import Game.Path;
import Game.Solution;
import Geom.Point3D;


public class ShortestPathAlgo implements ActionListener {
	
	private MyCoords mc=new MyCoords();
	private Map map=new Map();
    private Thread thread;
	
	/**This is the game main algorithm.
	 * This function is going through all of the game data and search the shortest
	 * distance between packman and fruit. When it finds them , the algorithm calls
	 * packmanMovmeant to operate the eating process.
	 * For each packman eating the algorithm adds a point to the packman path and 
	 * finally adds the paths into one solution list.
	 * The algorithm will keep running untill there is no fruits left.
	 * @param game Lists of packmen and fruits which created by CSV file of by the user.
	 * @param solution List of paths which happened in the game
	 */
	public void pathCalculator(Boardgame game ) {
		boolean end=false;
		    
		    for(Packman packman : game.getPackmen()) 
				 packman.setPoint(map.LatLontoXY(packman.getPoint() , 644,644));
			 for(Fruit fruit : game.getFruits()) 
				 fruit.setPoint(map.LatLontoXY(fruit.getPoint(), 644, 644));
			while (!end) {
			double min=Double.MAX_VALUE;
			Packman closestPackman=null;
			Fruit closestFruit=null;
			
			for(Packman packman : game.getPackmen()) {
				for(Fruit fruit : game.getFruits()) {
					if(fruit.isEaten())
						continue;
					Point3D p1=map.XYtoLatLon(packman.getPoint(), 644,644);
					Point3D p2=map.XYtoLatLon(fruit.getPoint(), 644, 644);
					double distance=p1.distance3D(p2);
					if(distance<min) {
						closestPackman = packman;
						closestFruit = fruit;
						min=distance;
				       }
			        }			
		          }
			packmanMovmeant(closestPackman,closestFruit);		
 			closestPackman.add2Path(map.XYtoLatLon(closestPackman.getPoint(),644, 644));
			closestPackman.eat();
			closestFruit.eaten();
			int countFood=0;
			for(Fruit fruit : game.getFruits()) {
				if(!fruit.isEaten())
					countFood++;
			}
			if(countFood==0) 	
				end=true;
			}
			for(Packman packman : game.getPackmen())
				game.getSolution().addPath(packman.getPath());		
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	/**This function is moving the packman location towards the closing fruit.
	 * The packman is taking couple steps to get to the fruit and it will not
	 * stop moving till it be approximately on the same spot(deviation of 2 pixels).
	 * The function compute the angle between the fruit and the packman and moving
	 * it on the vector with proportional steps.
	 * To make it visable the packman will move every 0.25 seconds.
	 * @param packman The current packman
	 * @param fruit The closst fruit
	 */
	public void packmanMovmeant(Packman packman , Fruit fruit) {
		
		Point3D tempPackman=map.XYtoLatLon(packman.getPoint(), 644, 644);
		Point3D tempFruit=map.XYtoLatLon(fruit.getPoint(), 644, 644);
		double mainAngle = (tempPackman.angleXY(tempFruit));
		double distance = tempPackman.distance2D(tempFruit);
		double stepx=Math.cos(mainAngle)*distance/10;
		double stepy=Math.sin(mainAngle)*distance/10;
		
		while((packman.getPoint().ix()<=fruit.getPoint().ix()-2)||(packman.getPoint().ix()>=fruit.getPoint().ix()+2)&&(packman.getPoint().iy()<=fruit.getPoint().iy()-2)||(packman.getPoint().iy()>=fruit.getPoint().iy()+2)) {
			
			tempPackman=new Point3D(tempPackman.x()+stepx,tempPackman.y()+stepy,0);
			packman.setPoint(map.LatLontoXY(tempPackman, 644, 644));
			
			try {
				Thread.sleep(250);}
			catch(Exception e) {}
			}	
		packman.setPoint(fruit.getPoint());
	}
		
	}

