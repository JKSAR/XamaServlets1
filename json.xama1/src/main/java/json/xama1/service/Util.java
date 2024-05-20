package json.xama1.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

public class Util
{

	public static void main(String args[])
	{
		//long milliseconds = 1000000;
		//int sec = miliSecondsToInt(milliseconds);
		//String tst;
		
		java.sql.Timestamp sqlTimestmp = null;
		//sqlTimestmp = Util.getCurrentTimeStamp("10");
		
		//getLocalExec();
		//System.out.println(getLocalExec());
	}
	
	public static boolean getLocalExec()
	{
		Enumeration e = null;
		String l_str = null;
		
		Map<String, String> env = System.getenv();
	    if (env.containsValue("LAPTOP-3JDNFP3S"))
	        //System.out.println(env.get("COMPUTERNAME"));
	    	return true;
	    else
	    	//System.out.println("Unknown Computer");
	    	return false;
		
//		try {
//			e = NetworkInterface.getNetworkInterfaces();
//		} catch (SocketException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		while(e.hasMoreElements())
//		{
//			NetworkInterface n = (NetworkInterface) e.nextElement();
//			Enumeration ee = n.getInetAddresses();
//			while (ee.hasMoreElements())
//			{
//				InetAddress i = (InetAddress) ee.nextElement();
//				//System.out.println(i.getHostAddress());
//				l_str = i.getHostAddress();
//				if(l_str.equals("127.0.0.1"))
//				{
//					return true;
//				}
//			}
//		}				
	}

	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException 
	{
	   java.sql.Connection mySQLCnx = null;	 
	   try{
		   mySQLCnx = MySQLCnx.getConexaoMySQL();
	   } catch (ClassNotFoundException e) {
			//e.printStackTrace();
		    throw new ClassNotFoundException();
		} catch (SQLException e) {
		    //e.printStackTrace();
			throw new SQLException();
	   }
	   return mySQLCnx;
	}

	public static java.sql.Timestamp getCurrentTimeStamp()
	{
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	public static java.sql.Timestamp subCurrentTimeStamp(java.sql.Timestamp sqlTimestmp, String milisec_str)
	{
		java.util.Date today = new java.util.Date();
		
		int milisec_int = Integer.valueOf(milisec_str);
		long sqlTmstp = sqlTimestmp.getTime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(sqlTmstp);
        
        //System.out.println("Current Date " + dateFormat.format(date));

        Calendar c = Calendar.getInstance();
        c.setTime(date);	
        
        milisec_int = milisec_int * -1;
        c.add(Calendar.SECOND, milisec_int);
             
       	return new java.sql.Timestamp(c.getTimeInMillis());
	}	

	public static int miliSecondsToInt(long miliseconds)
	{
		// long minutes = (milliseconds / 1000) / 60;
		// long minutes = TimeUnit.MILLISECONDS.toMinutes(miliseconds);

		// long seconds = (milliseconds / 1000);
		return (int) TimeUnit.MILLISECONDS.toSeconds(miliseconds);

		// System.out.format("%d Milliseconds = %d minutes\n", milliseconds, minutes );
		// System.out.println("Or");
		// System.out.format("%d Milliseconds = %d seconds", milliseconds, seconds );

	}

	static void printMessage(HttpServletResponse _response, String message)
	{
		PrintWriter out = null;

		try
		{
			out = _response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		out.print(message);
	}

	static java.sql.Date getAtuDate()
	{
		Calendar cal = Calendar.getInstance();
		Date date = new java.util.Date();

		date.setTime(cal.getTimeInMillis());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	static java.sql.Timestamp getAtuTime()
	{
		Calendar cal = Calendar.getInstance();
		Date date = new java.util.Date();

		date.setTime(cal.getTimeInMillis());
		java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
		return sqlTime;
	}

	static String formataMsgErro(String msg1)
	{
		String erro = formataMsgErro(msg1, "");
		return erro;
	}

	static String formataMsgErro(String msg1, String msg2)
	{
		String erro = formataMsgErro(msg1, msg2, "");
		return erro;
	}

	static String formataMsgErro(String msg1, String msg2, String msg3)
	{
		String erro = formataMsgErro(msg1, msg2, msg3, "");
		return erro;
	}

	static String formataMsgErro(String msg1, String msg2, String msg3, String msg4)
	{
		String erro = formataMsgErro(msg1, msg2, msg3, msg4, "");
		return erro;
	}

	static String formataMsgErro(String msg1, String msg2, String msg3, String msg4, String msg5)
	{
		String erro = msg1;
		if (msg2 != "")
		{
			erro += " ";
			erro += msg2;
		}

		if (msg3 != "")
		{
			erro += " ";
			erro += msg3;
		}

		if (msg4 != "")
		{
			erro += " ";
			erro += msg4;
		}

		if (msg5 != "")
		{
			erro += " ";
			erro += msg5;
		}
		return erro;
	}

}