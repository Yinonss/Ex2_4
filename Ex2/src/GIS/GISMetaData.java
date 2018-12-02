package GIS;

import java.sql.Date;
import java.text.SimpleDateFormat;

import Geom.Point3D;


public class GISMetaData implements Meta_data {
	private String MAC;
	private String SSID;
	private String AuthMode;
	private String FirstSeen;
	private int Channel;
	private int RSSI;
	private int AccuracyMeters;
	private String Type;

	public GISMetaData(String _MAC,String _SSID,String _AuthMode,String _FirstSeen,String _Channel,String _RSSI,String _AccuracyMeters,String _Type) {
		MAC=_MAC;
		SSID=_SSID;
		AuthMode=_AuthMode;
		FirstSeen=_FirstSeen;
		Channel=Integer.parseInt(_Channel);
		RSSI=Integer.parseInt(_RSSI);
		AccuracyMeters=Integer.parseInt(_AccuracyMeters);
		Type=_Type;
	}
	
	
    /**This function calculate the utc by using first seen string.
     * the method return the utc in milliseconds.
     */
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
	
		long time;
		try {
			FirstSeen.replace('/', '-');
			SimpleDateFormat utc = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date d=(Date) utc.parse(FirstSeen);
			time = d.getTime();
		}catch (Exception e) {
		    time = System.currentTimeMillis();	
		}
		return time;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}	
}
