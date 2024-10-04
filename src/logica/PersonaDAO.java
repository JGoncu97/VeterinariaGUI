package logica;

import conexionBD.Conexion;

import vo.PersonaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PersonaDAO extends ProcesosGenerales {

   Connection connection=null;
   Conexion conexion = null;
   PreparedStatement prepareStatement= null;

    public PersonaDAO(Connection connection) {
        this.connection = connection;
    }

    public PersonaDAO(PersonaDAO personaDAO, MascotaDAO mascotaDAO) {
        super(personaDAO, mascotaDAO);
    }



    private String conectar(){
       conexion= new Conexion();
       String resultado = conexion.conectar();
       if(resultado.equals("Conectado")){
           connection = conexion.getConnection();
           prepareStatement=null;
       }else{
           JOptionPane.showMessageDialog(null, resultado, "Error",JOptionPane.ERROR_MESSAGE);
       }
       return resultado;

   }


    public String registrarPersona(PersonaVO miPersona) throws SQLException {
        String resultado="";
        if(!conectar().equals("conectado")){
            return "error";
        }
        String consulta = "INSERT INTO persona (documento, telefono, nombre) VALUES (?, ?, ?)";
        try {

            prepareStatement = connection.prepareStatement(consulta);

            prepareStatement.setString(1, miPersona.getDocumento());
            prepareStatement.setString(2, miPersona.getTelefono());
            prepareStatement.setString(3, miPersona.getNombre());
            prepareStatement.execute();

            resultado = "ok";
        } catch (SQLException e) {
            System.out.println("No se pudo registrar la persona, verique" +
                    "que el documento no exista: "+e.getMessage());
            resultado="error";
        }catch (Exception e) {
            System.out.println("No se pudo registrar la persona: "+ e.getMessage());
            resultado="error";
        }finally {
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return resultado;
    }


    public String actualizarPersona(PersonaVO miPersona)  throws SQLException{
        String resultado="";
        if(!conectar().equals("conectado")){
            return "error";
        }
        String consulta = "UPDATE persona SET nombre = ?, telefono = ? WHERE documento = ?";
        try {
            prepareStatement = connection.prepareStatement(consulta);
            prepareStatement.setString(1, miPersona.getDocumento());
            prepareStatement.setString(2, miPersona.getTelefono());
            prepareStatement.setString(3, miPersona.getNombre());
            prepareStatement.executeUpdate();

            resultado="ok";
        } catch (SQLException e) {
            System.out.println("Ocurrio una excepcion de SQL " +
                    "al momento de actualizar" +e.getMessage());
            resultado="error";
        }finally {
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return resultado;
    }

    public ArrayList<PersonaVO> consultarListaPersona() throws SQLException {
       ArrayList<PersonaVO> listaPersona = new ArrayList<PersonaVO>();
        if(!conectar().equals("conectado")){
            return listaPersona;
        }
        ResultSet result= null;
        PersonaVO miPersona= null;

        String consulta = "SELECT * FROM persona";


        try {
            prepareStatement = connection.prepareStatement(consulta);
            result = prepareStatement.executeQuery();

            while (result.next()) {
                miPersona = new PersonaVO();
                miPersona.setDocumento(result.getString("documento"));
                miPersona.setNombre(result.getString("nombre"));
                miPersona.setTelefono(result.getString("telefono"));
                listaPersona.add(miPersona);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta de personas: "+e.getMessage());
        }finally {
            result.close();
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return listaPersona;
    }

    public PersonaVO consultarPersona(String documento) throws SQLException {
       PersonaVO miPersona=null;

       if(!conectar().equals("conectado")){
           return miPersona;
       }
       ResultSet result=null;

        String consulta = "SELECT * FROM persona WHERE documento = ?";


        try {
            prepareStatement = connection.prepareStatement(consulta);


            prepareStatement.setString(1, documento);

            result=prepareStatement.executeQuery();

                if (result.next()) {
                    miPersona = new PersonaVO();
                    miPersona.setDocumento(result.getString("documento"));
                    miPersona.setNombre(result.getString("nombre"));
                    miPersona.setTelefono(result.getString("telefono"));
                }

        } catch (SQLException e) {
            System.out.println("Error en la consulta de la persona"+e.getMessage());
        }finally {
            result.close();
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return miPersona;
    }

    public String eliminarPersona(String documento) throws SQLException{
        String resultado="";
        if(!conectar().equals("conectado")){
            return "error";
        }

        try{
            String sentencia = "DELETE FROM persona WHERE documento = ?";

            PreparedStatement statement = connection.prepareStatement(sentencia);
            statement.setString(1, documento);
            statement.executeUpdate();

            resultado="ok";
        } catch (SQLException e) {
            System.out.println("Ocurrio una excepcion de SQL" +
                    "al momento de eliminar"+e.getMessage());
            resultado="error";
        }finally {
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return resultado;
    }


}
