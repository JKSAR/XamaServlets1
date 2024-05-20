package json.xama1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import json.xama1.model.*;
import json.xama1.service.XamaService;

/**
 * Servlet implementation class XamaServlet
 */
// @WebServlet(urlPatterns = {"/xama"}, name = "XamaServlet" , description = "XamaServlet returns JSON")
public class XamaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XamaService xService = new XamaService( );
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XamaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		//List<Xamada> xamada = new ArrayList<>();
		Xamada xamadaRes = null;
		
		Xamada xamada = xService.getXamada();
		
		Gson gson = new Gson();
		String xamaJSON = gson.toJson(xamada);
		
		//---------------------------------------------------------
		try {
			xamadaRes = new Gson().fromJson(xamaJSON, Xamada.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------------------------------------------------------
		
		PrintWriter pr = response.getWriter();
		response.setContentType("application/JSON");
		response.setCharacterEncoding("UTF-8");
		pr.write(xamaJSON);
		pr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
