package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.Timer;

import Algorithms.ShortestPathAlgo;
import Game.Boardgame;
import Game.Fruit;
import Game.Map;
import Game.Packman;
import Game.Path2KML;
import Game.Solution;
import Geom.Point3D;
import javafx.scene.effect.Light.Point;
import javafx.scene.shape.Path;


public class MyFrame extends JFrame implements MouseListener, MouseMotionListener , ActionListener
{

	public BufferedImage myImage;
	private Boardgame game;
	private boolean createPackman=false;
	private boolean createFruit=false;
	private boolean startGame=false;
	private boolean done=false;
	private boolean started=false;
	private Thread[] thread;
	private Map map;
	private int i=0;
	private int j=0;
	
	public MyFrame() {
		this.game=new Boardgame();
		initGUI();
		this.addMouseListener(this); 
		map=new Map();
		
	}
	/**This method builds the game GUI.
	 * It creates a menu and menu items, and for each item it runs
	 * the needed process.
	 * 
	 * Item 1(Save game) : Calls saveGame method of boardgame calls. It takes the game data 
	 * and create a csv file.
	 * Item 2(Load game): Creating a new game by using data which was red from csv file.
	 * (being set by using readCsv method).
	 * Item 3(Add packman) : Adding a new packman by clicking somewhere on the screen.
	 * Item 4(Add fruit) : Adding a new fruit by clicking somewhere on the screen.
	 * Item 5(Start) : Runs the game's algorithm (must be press weather you read data
	 * form csv file or place the packmen and fruits yourself).
	 * 
	 * In addition , this method also update the picture file (which be printed on
	 * the screen later on).
	 */
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File"); 
		Menu build = new Menu("Build game"); 
		MenuItem item1 = new MenuItem("Save game as CSV");
		MenuItem item2 = new MenuItem("Load game");
		MenuItem item3 = new MenuItem("Add packman");
		MenuItem item4 = new MenuItem("Add fruit");
		MenuItem item5 = new MenuItem("Start");
		MenuItem item6 = new MenuItem("Save game as KML");
		menuBar.add(file);
		menuBar.add(build);
		file.add(item2);
		file.add(item1);
		file.add(item6);
		build.add(item3);
		build.add(item4);
		build.add(item5);
		this.setMenuBar(menuBar);
		System.out.println("GUI printed!");
		try {
			 myImage = ImageIO.read(new File("image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		item1.addActionListener(new ActionListener() {
						@Override
			public void actionPerformed(ActionEvent e) {
				game.saveGame();
			}
		}
		);
		item2.addActionListener(new ActionListener() {
						@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				try {
					game.readCsv("C:\\Users\\yinon\\git\\Ex2_4\\Ex2\\src\\game_1543684662657.csv");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameSetter();
			}
		}
		);
		item3.addActionListener(new ActionListener() {
						@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				createPackman=true;
				}
		});
		
		item4.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			createFruit=true;
			}
		});
		
		item5.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			
			startGame=true;}
		});
		
		item6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			Path2KML save=new Path2KML();
			try {
				save.saveAsKML(game);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
	}

	int x = -1;
	int y = -1;


	/**This method  go through the game packmen and fruits, and 
	 * convert the from GIS coordinates to pixel.
	 */
	private void gameSetter() {
		
		Map map=new Map();
		 for(Packman packman : game.getPackmen()) 
			 packman.setPoint(map.LatLontoXY(packman.getPoint() , 644,644));
		 for(Fruit fruit : game.getFruits()) 
			 fruit.setPoint(map.LatLontoXY(fruit.getPoint(), 644, 644));
	}
  /**This method  go through the game packmen and fruits, and
   *  convert the from pixel to GIS coordinates.	
   */
  private void backwardGameSetter() {
	  Map map=new Map();
		 for(Packman packman : game.getPackmen())  
			 packman.setPoint(map.XYtoLatLon(packman.getPoint() , 644,644));
		 for(Fruit fruit : game.getFruits()) 
			 fruit.setPoint(map.XYtoLatLon(fruit.getPoint(), 644, 644));
  }
	
  /**This method is responsible on all of the graphics.
   * The main operation of this method is running the algorithms while printing
   * the fruits and packmen.
   * 
   * This function firstly prints the game, and whenever the user click on
   * 
   * 
   */
	public void paint(Graphics g)
	{

	drawFruit(g);
	drawPackman(g);
	g.drawImage(myImage, 0, 0, this);
	if((startGame)&&(started==false)){
		   ShortestPathAlgo algo=new ShortestPathAlgo();
			//Solution solution=new Solution();
			thread=new Thread[2];
			thread[0]=new Thread(new Runnable() {
				public void run() {
					backwardGameSetter();
					algo.pathCalculator(game);
					gameSetter();
					done=true;}
			});
			thread[1]=new Thread(new Runnable() {
				public void run() {
					while(!done) {
					 drawFruit(g);
					 drawPackman(g);
					repaint();
					}
				}
			});		
			thread[0].start();
			thread[1].start();
			started=true;
	}

	
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}g.setColor(Color.BLACK);
	   
		 drawFruit(g);
		 drawPackman(g);
	
	}
	
    /**This method operates whenever the user clicked he mouse.
     * If the mouse clicked ; x and y will get the pixel value.
     * 
     * If the user clicked on the menu add packman , the method will create a
     * packman with the click's x & y value.
     * 
     *  If the user clicked on the menu add fruit , the method will create a
     * fruit with the click's x & y value.
     * 
     * For each time it will be repaint.
     */
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		if(createPackman) {
			
			Packman packman = new Packman(new Point3D(arg.getX(),arg.getY(),0),i++);
			game.getPackmen().add(packman);
			System.out.println("Packman added");
			createPackman=false;
		}
		if(createFruit) {
			Fruit fruit = new Fruit(new Point3D(arg.getX(),arg.getY(),0) ,j++);
			game.getFruits().add(fruit);
			System.out.println("Fruit added");
			createFruit=false;			
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setGame(Boardgame game) {
		this.game = game;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	/** This method go through all of the game fruits and
	 * print them as red oval.
	 * @param g Graphics 
	 */
	private void drawFruit(Graphics g) {
		for(Fruit fruit : game.getFruits()) {
	    	Point3D fruitPoint=fruit.getPoint();
	    	g.setColor(Color.RED);
	    	g.fillOval((int)fruitPoint.x(), (int)fruitPoint.y(), 10, 10);	 	    	 
	    }	
	}
	/**This method go through all of the game packmen and
	 * print them as a yellow oval.
	 * @param g Graphics
	 */
	private void drawPackman(Graphics g) {
		     for(Packman packman : game.getPackmen()) {
			   Point3D packmanPoint=packman.getPoint();
			   g.setColor(Color.YELLOW);
		       g.fillOval((int)packmanPoint.x(), (int)packmanPoint.y(), 20, 20);
		   }
	}
	

}
