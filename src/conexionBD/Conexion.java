package conexionBD;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class Conexion {

    private String nombreBd="guiveterinaria";
    private String usuario="root";
    private String password="pipe123.";
    private String url ="jdbc:mysql://127.0.0.1:3306/" + nombreBd + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    Connection conn = null;
    public String conectar(){
        String respuesta= "";
        try{
            //Obtener el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener la conexion
            conn= DriverManager.getConnection(url,usuario,password);
            if(conn!=null){
                respuesta= "Conectado";
            }else{
                respuesta= "No se pudo conectar a "+nombreBd;
            }

        }catch (SQLSyntaxErrorException e ){
            respuesta="Ocurre una SQLSyntaxErrorException: "+e.getMessage();
            respuesta+="Verifique que se esta usando la base de datos y las tablas correctas...";
        } catch (SQLException e) {
            respuesta="ocurre una SQLException: "+e.getMessage()+"\n";
            respuesta+="Este es un problema general de SQL, verifique con el administrador";
        } catch (ClassNotFoundException e) {
            respuesta="ocurre una ClassNotFoundException: "+e.getMessage();
        }
        return respuesta;

    }

    public Connection getConnection(){
        return conn;
    }
    public void desconectar(){
        conn=null;
    }

}
