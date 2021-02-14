package servlets;

//IMPORTS QUE SE UTILIZARAN EN ESTA CLASE
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import Controllers.Hash;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*Esta clase sera el servlet al cual llamara el submit
	 * del form que se llena en el html inicial */
	
	//PRIMERO SE DEFINEN LAS VARIABLES QUE SE UTILIZAN PARA LA CONEXION A LA BASE DE DATOS
	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstmt;
	public static ResultSet rs;
	private String driverDB = "org.postgresql.Driver";
	//cambie el postgres por su usuario del pgadmin
	public String dbUser = "postgres";
	public String dbName = "REGISTER";
	public String urlDB = "jdbc:postgresql://localhost:5432/" + this.dbName;
	//cambie el asd por su contraseña del pgadmin
	public String passwordDB = "asd";
	
	/*Esta funcion se utilizara para validar los datos
	 * introducidos en la contraseña, solo se permitiran
	 * numeros y letras minusculas*/
	public static boolean validarDatos(String datos) {
		return datos.matches("[0-9a-z]*");
	}
	
    public register() {
        super();
    }

    //AQUI COMIENZA EL METODO doPost 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//AQUI SE HACE EL REQUEST DE LOS DATOS INTRODUCIDOS EN EL FORM DEL HTML Y SE LES ASIGNA UNA VARIABLE
		String n=request.getParameter("userName");
		String p=request.getParameter("userPass");
		String e=request.getParameter("userEmail");
		String c=request.getParameter("userCountry");
		String ci=request.getParameter("userCI");
		String t=request.getParameter("userTLFnumber");
		
		//ESTE ES EL IF QUE UTILIZA LA FUNCION QUE SE DESARROLLO AL PRINCIPIO PARA VALIDAR LOS DATOS DE LA CONTRASEÑA
		if (!validarDatos(p)) {
			out.println("<html><body>");
			out.print("No se completo el registro. <br><br>Solo se permiten letras minusculas y numeros en la contraseña.");
			out.println("<br></body></html>");
			JOptionPane.showMessageDialog(null,"Solo se permiten letras minusculas y numeros en la contraseña.");
		
		/*En este else esta el try-catch que contiene
		 * la conexion a la DB y el envio de los datos
		 * a ella. Tambien contiene el llamado a la
		 * funcion para el Hashing de la contraseña */	
		} else {
			
			//AQUI SE LLAMA A LA FUNCION PARA EL HASHING
			String phash = Hash.md5(p);
			
			out.println("<html><body>");
			out.print("Se realizo el registro de manera satisfactoria.<br><br>Puede chequear la base de datos para confirmar.");
			out.println("<br></body></html>");
			JOptionPane.showMessageDialog(null, "Se realizo el registro de manera satisfactoria.");
			
			//ESTE ES EL TRY-CATCH QUE CONTIENE TODO LO DE LA BASE DE DATOS
			try {
				Class.forName(driverDB);
				this.conn=DriverManager.getConnection(urlDB, dbUser, passwordDB);
				this.pstmt=conn.prepareStatement("insert into registeruser values(?,?,?,?,?,?)");
				pstmt.setString(1,n);
				pstmt.setString(2,phash);
				pstmt.setString(3,e);
				pstmt.setString(4,c);
				pstmt.setString(5,ci);
				pstmt.setString(6,t);
				int i=pstmt.executeUpdate();
				if(i>0);
			}catch (Exception e2) {System.out.println(e2);}
			out.close();
		}
		
		doGet(request, response);
	}
}
