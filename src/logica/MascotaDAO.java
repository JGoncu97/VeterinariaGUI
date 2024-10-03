package logica;

import coordinador.Coordinador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class MascotaDAO extends ProcesosGenerales {

    private Coordinador miCoordinador;

    public boolean registrar(String propietario, String nombre, String raza, String sexo) {
        String sql = "INSERT INTO mascotas (propietario, nombre, raza, sexo) VALUES (?, ?, ?, ?)";
        try (Connection connection = new Conexion().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, propietario);
            statement.setString(2, nombre);
            statement.setString(3, raza);
            statement.setString(4, sexo);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha registrado a " + nombre);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo registrar a la mascota", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<MascotaVo> listar() {
        ArrayList<MascotaVo> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";

        try (Connection connection = new Conexion().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet res = statement.executeQuery()) {

            while (res.next()) {
                String propietario = "No tiene dueño";
                PersonaVo propietarioData = miCoordinador.consultarPersona(res.getString("propietario"), false);
                if (propietarioData != null) {
                    propietario = propietarioData.getNombre();
                }
                MascotaVo mascota = new MascotaVo();
                mascota.setPropietario(propietario);
                mascota.setNombre(res.getString("nombre"));
                mascota.setRaza(res.getString("raza"));
                mascota.setSexo(res.getString("sexo"));
                lista.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista; // Retorna una lista vacía si no hay resultados
    }

    public MascotaVo consultar(String idPropietario, String nombre) {
        MascotaVo mascotaConsultada = null;
        String sql = "SELECT * FROM mascotas WHERE propietario=? AND nombre=?";

        try (Connection connection = new Conexion().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idPropietario);
            statement.setString(2, nombre);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                mascotaConsultada = new MascotaVo();
                mascotaConsultada.setNombre(res.getString("nombre"));
                mascotaConsultada.setRaza(res.getString("raza"));
                mascotaConsultada.setSexo(res.getString("sexo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mascotaConsultada; // Retorna null si no se encuentra
    }

    public boolean eliminar(String idPropietario, String nombre) {
        String sql = "DELETE FROM mascotas WHERE propietario=? AND nombre=?";
        try (Connection connection = new Conexion().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idPropietario);
            statement.setString(2, nombre);
            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void actualizar(String propietario, String nombre, String raza, String sexo) {
        String sql = "UPDATE mascotas SET nombre=?, propietario=?, raza=?, sexo=? WHERE propietario=? AND nombre=?";
        try (Connection connection = new Conexion().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre);
            statement.setString(2, propietario);
            statement.setString(3, raza);
            statement.setString(4, sexo);
            statement.setString(5, propietario); // Reutilizar el propietario
            statement.setString(6, nombre); // Reutilizar el nombre
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha actualizado a " + nombre);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la mascota", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}





