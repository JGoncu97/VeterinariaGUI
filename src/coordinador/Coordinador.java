package coordinador;

import logica.MascotaDAO;
import logica.PersonaDAO;
import vo.MascotaVO;

import logica.ProcesosGenerales;
import vo.PersonaVO;
import ventanas.VentanaMascota;
import ventanas.VentanaPersona;
import ventanas.VentanaPrincipal;

import java.sql.SQLException;
import java.util.List;

public class Coordinador {

    private VentanaPrincipal miVPrincipal;
    private VentanaPersona vPersona;
    private VentanaMascota vMascota;
    private MascotaVO miMascota;
    private PersonaVO miPersona;
    private ProcesosGenerales miProceso;
    private MascotaDAO mascotaDAO;
    private PersonaDAO personaDAO;


    public void setVentanaP(VentanaPrincipal miVPrincipal) {
    
        this.miVPrincipal = miVPrincipal;
    }

    public void setVentanaPersona(VentanaPersona vPersona) {
        this.vPersona = vPersona;
    }

    public void setVentanaMascota(VentanaMascota vMascota) {
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
            case 1:
                if (vPersona==null){
                    vPersona= new VentanaPersona();
                }
                vPersona.setVisible(true)
                ; break;
            case 2:
                if (vMascota == null) {
                vMascota = new VentanaMascota(); // Asegúrate de inicializarla
                }
                vMascota.setVisible(true);
                break;

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


    public void eliminarPersona(String documento) throws SQLException {
        miProceso.eliminarPersona(documento);
    }

    public PersonaVO consultarPersona(String documento) throws SQLException {
       return miProceso.consultarPersona(documento);
    }

    public String actualizarPersona(String documento, String nombre, String telefono) {
        return miProceso.actualizarPersona(documento, nombre, telefono);

    }

    public List<PersonaVO> consultarListaPersonas() {
       return miProceso.consultarAllPersona();

    }

    public String eliminarMascota(String propietario) throws SQLException {
        return miProceso.eliminarMascota(propietario);

    }

    public MascotaVO consultarMascota(String propietario) throws SQLException {
        return miProceso.consultarMascota(propietario);

    }

    public String actualizarMascota(String propietario, String nombreMascota, String raza, String sexo) {
        return  miProceso.actualizarMascota(propietario, nombreMascota, raza, sexo);

    }

    public List<MascotaVO> consultarListaMascotas() {
       return miProceso.consultarAllMascotas();
    }
}
