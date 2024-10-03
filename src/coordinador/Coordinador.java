package coordinador;

import animal.MascotaVO;
import logica.MascotaDAO;
import logica.PersonaDAO;
import logica.ProcesosGenerales;
import persona.PersonaVO;
import ventanas.VentanaMascota;
import ventanas.VentanaPersona;
import ventanas.VentanaPrincipal;

import java.util.List;

public class Coordinador {

    private VentanaPrincipal miVPrincipal;
    private VentanaPersona vPersona;
    private VentanaMascota vMascota;
    private MascotaDAO mascotaDAO;
    private PersonaDAO personaDAO;
    private MascotaVO miMascota;
    private PersonaVO miPersona;
    private ProcesosGenerales miProceso;

    public void setVentanaP(VentanaPrincipal miVPrincipal) {
    
        this.miVPrincipal = miVPrincipal;
    }

    public void setVentanaPersona(VentanaPersona vPersona) {
        this.vPersona = vPersona;
    }

    public void setVentanaMusica(VentanaMascota vMascota) {
        this.vMascota = vMascota;
    }

    public void setPersonaDao(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public void setMascotaDao(MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }

    public void setMascotaVO(MascotaVO miMascota) {
        this.miMascota = miMascota;
    }

    public void setPersonaVO(PersonaVO miPersona) {
        this.miPersona = miPersona;
    }

    public void mostrarVentana(int ventana) {

        switch(ventana) {
            case 1: vPersona.setVisible(true); break;
            case 2: vMascota.setVisible(true); break;

        }

    }

    public void registrarPersona(String documento, String nombre, String telefono) {
        miProceso.registrarPersona(documento, nombre, telefono);
    }

    public void registrarMascota(String nombre, String raza, String sexo, String propietario) {
        miProceso.registrarMascota(nombre, raza, sexo, propietario);
    }

    public void setProcesos(ProcesosGenerales miProceso) {
        this.miProceso = miProceso;
    }


    public void eliminarPersona(String documento) {
        miProceso.eliminarPersona(documento);
    }

    public PersonaVO consultarPersona(String documento) {
       return miProceso.consultarPersona(documento);
    }

    public boolean actualizarPersona(String documento, String nombre, String telefono) {
        miProceso.actualizarPersona(documento, nombre, telefono);
        return false;
    }

    public List<PersonaVO> consultarListaPersonas() {
        miProceso.consultarAllPersona();


        return List.of();
    }

    public boolean eliminarMascota(String propietario) {
        miProceso.eliminarMascota(propietario);
        return true;
    }

    public MascotaVO consultarMascota(String propietario) {
        miProceso.consultarMascota(propietario);
        return null;
    }

    public boolean actualizarMascota(String propietario, String nombreMascota, String raza, String sexo) {
        miProceso.actualizarMascota(propietario, nombreMascota, raza, sexo);
        return true;
    }

    public List<MascotaVO> consultarListaMascotas() {
        miProceso.consultarAllMascotas();
        return List.of();
    }
}
