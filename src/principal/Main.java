package principal;

import animal.MascotaVO;
import coordinador.Coordinador;
import logica.MascotaDAO;
import logica.PersonaDAO;
import logica.ProcesosGenerales;
import persona.PersonaVO;
import ventanas.VentanaMascota;
import ventanas.VentanaPersona;
import ventanas.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        //Declaracion de Clases
        Coordinador miCoordinador= new Coordinador();
        VentanaPrincipal miVPrincipal = new VentanaPrincipal();
        VentanaPersona vPersona= new VentanaPersona();
        VentanaMascota vMascota = new VentanaMascota();
        PersonaDAO personaDAO= new PersonaDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        PersonaVO miPersona = new PersonaVO();
        MascotaVO miMascota= new MascotaVO();
        ProcesosGenerales miProceso= new ProcesosGenerales();

        //Establecer Relacion
        miVPrincipal.setCoordinador(miCoordinador);
        vPersona.setCoordinador(miCoordinador);
        vMascota.setCoordinador(miCoordinador);
        personaDAO.setCoordinador(miCoordinador);
        mascotaDAO.setCoordinador(miCoordinador);
        miMascota.setCoordinador(miCoordinador);
        miPersona.setCoordinador(miCoordinador);
        miProceso.setCoordinador(miCoordinador);


        //Enviarle una instancia de cada clase al coordinador
        miCoordinador.setVentanaP(miVPrincipal);
        miCoordinador.setVentanaPersona(vPersona);
        miCoordinador.setVentanaMusica(vMascota);
        miCoordinador.setPersonaDao(personaDAO);
        miCoordinador.setMascotaDao(mascotaDAO);
        miCoordinador.setMascotaVO(miMascota);
        miCoordinador.setPersonaVO(miPersona);
        miCoordinador.setProcesos(miProceso);

        //Llamado Ventana Principal
        miVPrincipal.setVisible(true);


    }
}