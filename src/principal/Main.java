package principal;

import logica.MascotaDAO;
import logica.PersonaDAO;
import vo.MascotaVO;
import coordinador.Coordinador;

import logica.ProcesosGenerales;
import vo.PersonaVO;
import ventanas.VentanaMascota;
import ventanas.VentanaPersona;
import ventanas.VentanaPrincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Coordinador miCoordinador = new Coordinador();


        // Crear la conexión a la base de datos
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/guiveterinaria?serverTimezone=UTC", "root", "pipe123.");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        PersonaDAO personaDAO = new PersonaDAO(connection);
        MascotaDAO mascotaDAO = new MascotaDAO(connection);

        // Pasar los DAOs a ProcesosGenerales
        ProcesosGenerales miProceso = new ProcesosGenerales(personaDAO, mascotaDAO);

        miCoordinador.setProcesos(miProceso);
        miCoordinador.setPersonaDao(personaDAO);
        miCoordinador.setMascotaDao(mascotaDAO);

        // Configuración de las ventanas
        VentanaPrincipal miVPrincipal = new VentanaPrincipal();
        VentanaMascota vMascota = new VentanaMascota();
        VentanaPersona vPersona = new VentanaPersona();

        miCoordinador.setVentanaP(miVPrincipal);
        miCoordinador.setVentanaMascota(vMascota);
        miCoordinador.setVentanaPersona(vPersona);

        miVPrincipal.setCoordinador(miCoordinador);
        vPersona.setCoordinador(miCoordinador);
        vMascota.setCoordinador(miCoordinador);

        miVPrincipal.setVisible(true);
    }
}