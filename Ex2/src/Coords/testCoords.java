package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class testCoords {
				
		@Test
		void testvector3D()   {
			MyCoords ms= new MyCoords ();
			Point3D c= new Point3D (32.103315, 35.209039, 670);
			Point3D d= new Point3D (32.106352, 35.205225, 650);
			Point3D expected= new Point3D (337.69899206128815,-359.24920693881893,-20);
			Point3D ans=ms.vector3D(c,d);
			String ansPrint=ans.toString();
			String expectedPrint=expected.toString();
			assertEquals(expectedPrint,ansPrint);
		}

	@Test
		void testisValid() { 
			MyCoords ms= new MyCoords ();
			Point3D p= new Point3D (32.103315, 35.209039, 670);
			boolean u=ms.isValid_GPS_Point(p);
			assertTrue(u);
	}
	
		@Test
		void testadd() { //throws Exception  {
			MyCoords ms= new MyCoords ();
			Point3D polry= new Point3D (32.103315, 35.209039, 670);
			Point3D meter= new Point3D (337.6989921, -359.2492069, -20);
			Point3D sum=new Point3D (ms.add (polry, meter));
			Point3D expected= new Point3D (32.100277999999655,35.21227501911918,650.0);
			String sumPrint=sum.toString();
			String expectedPrint=expected.toString();
	
			assertEquals(expectedPrint,sumPrint);
		}

		@Test
		void testdistance3d()  throws Exception  {
			
			Point3D c= new Point3D (32.103315, 35.209039, 670);
			Point3D d= new Point3D (32.106352, 35.205225, 650);
			//Point3D e= new Point3D ();
			double q=493.0523318324134;
			MyCoords ms= new MyCoords ();
			double pp=ms.distance3d(c,d);
			assertEquals(q,pp);
	
		}
	}