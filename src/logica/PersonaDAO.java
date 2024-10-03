package logica;

import coordinador.Coordinador;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class PersonaDAO extends ProcesosGenerales {

    private Coordinador miCoordinador;

    public boolean registrar(String documento, String telefono, String nombre) {
        String sql = "INSERT INTO personas (documento, telefono, nombre) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, documento);
            pstmt.setString(2, telefono);
            pstmt.setString(3, nombre);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha registrado a " + nombre);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo registrar a la persona", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean actualizar(String documento, String nombre, String telefono) {
        String sql = "UPDATE personas SET nombre = ?, telefono = ? WHERE documento = ?";
        try (Connection conn = new Conexion().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, telefono);
            pstmt.setString(3, documento);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha actualizado a " + nombre);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<PersonaVo> listar() {
        String sql = "SELECT * FROM personas";
        ArrayList<PersonaVo> lista = new ArrayList<>();
        try (Connection conn = new Conexion().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet res = pstmt.executeQuery()) {
            while (res.next()) {
                PersonaVo persona = new PersonaVo();
                persona.setDocumento(res.getString("documento"));
                persona.setNombre(res.getString("nombre"));
                persona.setTelefono(res.getString("telefono"));
                lista.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public PersonaVo consultar(String documento) {
        String sql = "SELECT * FROM personas WHERE documento = ?";
        PersonaVo personaConsultada = null;
        try (Connection conn = new Conexion().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, documento);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    personaConsultada = new PersonaVo();
                    personaConsultada.setDocumento(res.getString("documento"));
                    personaConsultada.setNombre(res.getString("nombre"));
                    personaConsultada.setTelefono(res.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personaConsultada;
    }

    public boolean eliminar(String documento) {
        String sql = "DELETE FROM personas WHERE documento = ?";
        try (Connection conn = new Conexion().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, documento);
            return pstmt.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}
