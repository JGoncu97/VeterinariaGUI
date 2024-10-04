package logica;


import coordinador.Coordinador;
import vo.PersonaVO;
import vo.MascotaVO;

import java.sql.SQLException;
import java.util.List;

public class ProcesosGenerales {


    private Coordinador miCoordinador;
    private PersonaDAO personaDAO;
    private MascotaDAO mascotaDAO;

    public ProcesosGenerales(PersonaDAO personaDAO, MascotaDAO mascotaDAO) {
        this.personaDAO = personaDAO;
        this.mascotaDAO = mascotaDAO;
    }

    public ProcesosGenerales() {
    }

    // Persona operations
    public String registrarPersona(String documento, String nombre, String telefono) {
        PersonaVO persona = new PersonaVO();
        persona.setDocumento(documento);
        persona.setNombre(nombre);
        persona.setTelefono(telefono);

        try {
            return personaDAO.registrarPersona(persona);
        } catch (Exception e) {
            System.out.println("Error al registrar persona: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public String eliminarPersona(String documento) throws SQLException {
        try {
            return personaDAO.eliminarPersona(documento);
        } catch (Exception e) {
            System.out.println("Error al eliminar persona: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public PersonaVO consultarPersona(String documento) throws SQLException {
        try {
            return personaDAO.consultarPersona(documento);
        } catch (Exception e) {
            System.out.println("Error al consultar persona: " + e.getMessage());
            return null;
        }
    }

    public String actualizarPersona(String documento, String nombre, String telefono) {
        PersonaVO persona = new PersonaVO();
        persona.setDocumento(documento);
        persona.setNombre(nombre);
        persona.setTelefono(telefono);

        try {
            return personaDAO.actualizarPersona(persona);
        } catch (Exception e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public List<PersonaVO> consultarAllPersona() {
        try {
            return personaDAO.consultarListaPersona();
        } catch (Exception e) {
            System.out.println("Error al consultar lista de personas: " + e.getMessage());
            return null;
        }
    }


    public String registrarMascota(String nombre, String raza, String sexo, String propietario) {
        MascotaVO mascota = new MascotaVO();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setPropietario(propietario);

        try {
            return mascotaDAO.registrarMascota(mascota);
        } catch (Exception e) {
            System.out.println("Error al registrar mascota: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public String eliminarMascota(String propietario) throws SQLException {
        try {
            return mascotaDAO.eliminarMascota(propietario);
        } catch (Exception e) {
            System.out.println("Error al eliminar mascota: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public MascotaVO consultarMascota(String propietario) throws SQLException {
        try {
            return mascotaDAO.consultarMascota(propietario);
        } catch (Exception e) {
            System.out.println("Error al consultar mascota: " + e.getMessage());
            return null;
        }
    }

    public String actualizarMascota(String propietario, String nombreMascota, String raza, String sexo) {
        MascotaVO mascota = new MascotaVO();
        mascota.setNombre(nombreMascota);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setPropietario(propietario);

        try {
            return mascotaDAO.actualizarMascota(mascota);
        } catch (Exception e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
            return String.valueOf(false);
        }
    }

    public List<MascotaVO> consultarAllMascotas() {
        try {
            return mascotaDAO.consultarListaMascota();
        } catch (Exception e) {
            System.out.println("Error al consultar lista de mascotas: " + e.getMessage());
            return null;
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}
