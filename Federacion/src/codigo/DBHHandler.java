package codigo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHHandler {

	private static java.sql.Connection conn = null;
	private static String driver = "jdbc:mysql://dbhost1516imerino.cloudapp.net:3306";
	private static String url = "jdbc:mysql://localhost/";
	private static String dbName = "ciclismo";
	private static String userName = "user";
	private static String password = "pass";
	private static PreparedStatement ps;
	
	
	public boolean openConexion(){
		//Open Connection
				try{
					Class.forName(driver);
					conn=DriverManager.getConnection(
							"jdbc:mysql://dbhost1516imerino.cloudapp.net:3306",
							userName, password);
					conn.setAutoCommit(true);
					return true;
				}catch (SQLException e){
					e.printStackTrace();
					return false;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
	}
	public void closeConexion(){
    	try{
    		conn.close();
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    }
	public Connection getConnection(){
    	return conn;
    }
    
	
	public boolean craerUsuario(String nombre, String apellidos, String direccion, Date fecha, int telefono, String mail ){
		// Conectar
				this.openConexion();
				
				try {
					ps = conn.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?,?)");
					ps.setString(1, userName);
					ps.setString(2, password);
					ps.setString(3, nombre);
					ps.setString(4, apellidos);
					ps.setString(5, direccion);
					ps.setDate(6, fecha);
					ps.setInt(7, telefono);
					ps.setString(8, mail);
					ps.executeUpdate();
					
				} catch (SQLException e) {
					System.out.println("Error.");
					e.printStackTrace();
					return false;
				}
				this.closeConexion();
				return true;
	}
	public boolean comprobarUsuario(String nombre, String contraseña) throws SQLException{
		
		this.openConexion();
		ResultSet rs = null;
		String s = "";
		PreparedStatement ps2;
		boolean b = false;
		ps2 = conn.prepareStatement("select password from usuarios where username like '%"+nombre+"%'");
		rs = ps2.executeQuery();
		while(rs.next()){
			s = rs.getString(1);
			if(contraseña.equals(s)){
				b = true;
			}
		}
		
		return b;
		
	}
	//Al dar de alta un usuario k ya este k salga error
	public boolean actualizarDatos(String nombre, String apellidos, String direccion, Date fecha, int telefono, String mail){
		this.openConexion();
		try {
			ps = conn.prepareStatement("update ciclismo.usuario set cName=?, cApellidos=?, cDireccion=?, cFechaN=?, cTelefono=?, cMail=?");
			ps.setString(1, nombre);
			ps.setString(2, apellidos);
			ps.setString(3, direccion);
			ps.setDate(4, fecha);
			ps.setInt(5, telefono);
			ps.setDate(6, mail);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
			return false;
		}
		this.closeConexion();
		return true;
	}
	public boolean actualizarUsuario(){
		this.openConexion();
		try {
			ps = conn.prepareStatement("update ciclismo.usuario set cUser=?, cContraseña=?");
			ps.setString(1, nombre);
			ps.setString(2, apellidos);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
			return false;
		}
		this.closeConexion();
		return true;
	}
	public void solicitarLicencia(String tipo){
		this.openConexion();
		
		try {
			ps = conn.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?,?)");
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, nombre);
			ps.setString(4, apellidos);
			ps.setString(5, direccion);
			ps.setDate(6, fecha);
			ps.setInt(7, telefono);
			ps.setString(8, mail);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error.");
			e.printStackTrace();
			return false;
		}
		this.closeConexion();
		return true;
	}
	public void actualizarLicencia(){
		
	}
	public boolean tieneLicencia(){
		this.openConexion();
		try{
			ps=conn.prepareStatement();
			String sql="Select * from ciclismo.licencia where cUsuario=userName;";
			ResultSet resultado=conectar.getStatement().executeQuery(sql);
			while (resultado.next()){
				String oferta= "Codigo"+resultado.getString(1)+" Dadena "+resultado.getString(2)+" Entrada "+resultado.getString(3)+" Salida "+resultado.getString(4)+" Precio"+resultado.getString(5);
				lista.add(oferta);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		conectar.closeConexion();
	}
}
