package json.xama1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import json.xama1.service.MySQLCmd;
import json.xama1.service.MySQLCnx;

/**
 * Servlet implementation class BalladdServlet
 */
//@WebServlet(description = "/ServletInput", urlPatterns = { "/ServletInput" })
public class ServletInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private static MySQLCnx mySqlCnx;
	
	//private static Controle cNtrl;
	//private static Connection mySqlCnx;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInput() throws ServletException {  
        super();
        
        String dummy = new String();
        
        //TODO: v2 descomentar???
        //mySqlCnx = new MySQLCnx( );
        //cNtrl = new Controle( ); 
        //----------------------------
        
//      try {
//			mySqlCnx = MySQLCnx.getConexaoMySQL();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}               
    }
    	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		//MySQLCmd mySQLCmd = new MySQLCmd( ); 			
		//String dummy = new String();
		
		int l_mesa = 0;
		int l_mesaAux = 0;
		
		if( request.getParameter("mesa") == null )
		{
			return;
		}
	    else
	    {
	    	l_mesa = Integer.valueOf(request.getParameter("mesa"));
		}
		
		//int l_idCliente = Integer.valueOf(request.getParameter("idCliente"));  
		//int l_mesa = Integer.valueOf(request.getParameter("mesa"));
		
		//response.getWriter().append("Served at: 9").append(request.getContextPath());
		
		//boolean tst = MySQLCmd.insFluxo(l_idCliente, l_mesa, 1, response);
		
		//System.out.println(tst);		
		//response.getWriter().append(String.valueOf(tst));
		//String xamaJSON = "Julio Cesar"; 
		
		ClassSingleton classSingleton = ClassSingleton.getInstance();
		
		classSingleton.setMesaNum(l_mesa);
		
		l_mesaAux = classSingleton.getMesaNum();
		
		PrintWriter pr = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//pr.write(xamaJSON);
		pr.write(String.valueOf(l_mesaAux));
		pr.close();
						
		//ThreadSaveData t1 = new ThreadSaveData(request, response); //, mySqlCnx.getMySqlCnx());  
        //t1.start();  				
		//Controle.coreLoopMySwim(request, response); 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}