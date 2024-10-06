package logica;

import conexionBD.Conexion;
import vo.MascotaVO;
import vo.PersonaVO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MascotaDAO extends ProcesosGenerales {

    private Connection connection = null;
    Conexion conexion = null;
    PreparedStatement prepareStatement = null;

    public MascotaDAO(Connection connection) {
        this.connection = connection;
    }



    private String conectar() {
        conexion = new Conexion();
        String resultado = conexion.conectar();
        if (resultado.equals("Conectado")) {
            connection = conexion.getConnection();
            prepareStatement = null;
        } else {
            JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;

    }

    public String registrarMascota(MascotaVO miMascota) throws SQLException {
        String resultado = "";
        if (!conectar().equals("conectado")) {
            return "error";
        }

        String consulta = "INSERT INTO mascota (nombre, raza, sexo, propietario) VALUES (?, ?, ?, ?)";
        try {

            prepareStatement = connection.prepareStatement(consulta);
            prepareStatement.setString(1, miMascota.getNombre());
            prepareStatement.setString(2, miMascota.getRaza());
            prepareStatement.setString(3, miMascota.getSexo());
            prepareStatement.setString(4, miMascota.getPropietario());
            prepareStatement.execute();

            resultado = "ok";
        } catch (SQLException e) {
            System.out.println("No se pudo registrar la mascota, verique" +
                    "que el propietario no exista: " + e.getMessage());
            resultado = "error";
        } catch (Exception e) {
            System.out.println("No se pudo registrar la mascota: " + e.getMessage());
            resultado = "error";
        } finally {
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return resultado;
    }

    public String actualizarMascota(MascotaVO miMascota)  throws SQLException{
        String resultado="";
        if(!conectar().equals("conectado")){
            return "error";
        }
        String consulta = "UPDATE mascota SET sexo= ?, nombre = ?, raza = ? WHERE propietario = ?";
        try {
            prepareStatement = connection.prepareStatement(consulta);
            prepareStatement.setString(1, miMascota.getNombre());
            prepareStatement.setString(2, miMascota.getRaza());
            prepareStatement.setString(3, miMascota.getSexo());
            prepareStatement.setString(4, miMascota.getPropietario());
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

    public ArrayList<MascotaVO> consultarListaMascota() throws SQLException {
        ArrayList<MascotaVO> listaMascota = new ArrayList<MascotaVO>();
        if (!conectar().equals("conectado")) {
            return listaMascota;
        }
        ResultSet result = null;
        MascotaVO miMascota = null;

        String consulta = "SELECT * FROM mascota";

        try {
            prepareStatement = connection.prepareStatement(consulta);
            result = prepareStatement.executeQuery();
            while (result.next()) {
                miMascota = new MascotaVO();
                miMascota.setNombre(result.getString("nombre"));
                miMascota.setRaza(result.getString("raza"));
                miMascota.setSexo(result.getString("sexo"));
                miMascota.setPropietario(result.getString("propietario"));
                listaMascota.add(miMascota);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta de mascotas: " + e.getMessage());
        } finally {
            result.close();
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return listaMascota;
    }

    public MascotaVO consultarMascota(String propietario) throws SQLException {

        MascotaVO miMascota = null;

        if (!conectar().equals("conectado")) {
            return miMascota;
        }
        ResultSet result = null;
        String consulta = "SELECT * FROM mascota WHERE nombre = ?";

        try {
            prepareStatement = connection.prepareStatement(consulta);


            prepareStatement.setString(1, propietario);

            result = prepareStatement.executeQuery();
            if (result.next()) {
                miMascota = new MascotaVO();
                miMascota.setPropietario(result.getString("propietario"));
                miMascota.setNombre(result.getString("nombre"));
                miMascota.setRaza(result.getString("raza"));
                miMascota.setSexo(result.getString("sexo"));

            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta de la mascota" + e.getMessage());
        } finally {
            result.close();
            prepareStatement.close();
            connection.close();
            conexion.desconectar();
        }
        return miMascota;
    }

    public String eliminarMascota(String propietario) throws SQLException{
        String resultado="";
        if(!conectar().equals("conectado")){
            return "error";
        }

        try{
            String sentencia = "DELETE FROM mascota WHERE propietario = ?";

            PreparedStatement statement = connection.prepareStatement(sentencia);
            statement.setString(1, propietario);
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
