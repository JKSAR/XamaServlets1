package json.xama1.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
//import java.text.Format;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class MySQLCnx 
{
	//public static String status = "N�o conectou...";
	
	public static java.sql.Connection mySqlCnx; 	

	public MySQLCnx() 
	{		
      try {
			mySqlCnx = MySQLCnx.getConexaoMySQL();
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}  		
	}
	
	public java.sql.Connection getMySqlCnx()
	{
	   return mySqlCnx;
	}

	public static void main(String args[]) {
		try {
			getConexaoMySQL();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static java.sql.Connection getConexaoMySQL() throws ClassNotFoundException, SQLException 
	{
		Connection connection = null; // atributo do tipo Connection
		
		String serverName = "";
		String mydatabase = ""; 
		String url = "";
		String username = "";
		String password = "";
		
		try {
			// Carregando o JDBC Driver padr�o
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Drive"); //???
			
//		    Map<String, String> env = System.getenv();
//		    if (env.containsKey("LAPTOP-3JDNFP3S"))
//		        System.out.println(env.get("COMPUTERNAME"));
//		    else if (env.containsKey("HOSTNAME"))
//		    	System.out.println(env.get("HOSTNAME"));
//		    else
//		    	System.out.println("Unknown Computer");			
//
//			//TODO: v2 encontrar uma forma de alterar o local da defini��o dos dados de cnx do banco
//			//CONFIGURAÇÃO INTEGRATOR
//			serverName = "localhost:3306"; // caminho do servidor do BD
//			mydatabase = "julio2021_CallMe"; // nome do seu banco de dados
//			url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
//			username = "julio2021_ksar"; // nome de um usu�rio de seu BD
//			password = "R&ptil2020"; // sua senha de acesso
			
			if(Util.getLocalExec() == true)
			{
				//CONFIGURAÇÃO LOCAL
				serverName = "127.0.0.1:3306"; // caminho do servidor do BD
				mydatabase = "callme"; // nome do seu banco de dados
				url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
				username = "root"; // nome de um usu�rio de seu BD
				password = "R&ptil2020"; // sua senha de acesso	
				
			} else {			
				//CONFIGURAÇÃO INTEGRATOR
				serverName = "localhost:3306"; // caminho do servidor do BD
				mydatabase = "julio2021_CallMe"; // nome do seu banco de dados
				url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
				username = "julio2021_ksar"; // nome de um usu�rio de seu BD
				password = "R&ptil2020"; // sua senha de acesso
			}		
     
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) { // Driver n�o encontrado
			throw new ClassNotFoundException();

		} catch (SQLException e) {
			throw new SQLException();
		}
		return connection;
	}

	// M�todo que fecha sua conex�o
	public static void FecharConexao() {
		try {
			MySQLCnx.getConexaoMySQL().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// M�todo que reinicia sua conex�o
	public static java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		try {
			return MySQLCnx.getConexaoMySQL();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}