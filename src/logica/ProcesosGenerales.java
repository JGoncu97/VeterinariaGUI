package logica;

import animal.MascotaVO;
import coordinador.Coordinador;
import persona.PersonaVO;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcesosGenerales  {

    private HashMap<String, PersonaVO> miPersona = new HashMap<>();
    private HashMap<String, MascotaVO> miMascota = new HashMap<>();
    Coordinador miCoordinador;

    public void registrarPersona(String documento, String nombre, String telefono){
        PersonaVO persona = new PersonaVO();
        persona.setDocumento(documento);
        persona.setNombre(nombre);
        persona.setTelefono(telefono);
        miPersona.put(documento, persona);
    }

    public void registrarMascota(String nombre, String raza, String sexo, String propietario){
        MascotaVO mascota = new MascotaVO();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setPropietario(propietario);
        miMascota.put(propietario, mascota);
    }

    public PersonaVO consultarPersona(String documento){
        return miPersona.get(documento);
    }

    public MascotaVO consultarMascota(String propietario){
        return miMascota.get(propietario);
    }

    public void eliminarPersona(String documento){
            miPersona.remove(documento);
    }

    public void eliminarMascota(String propietario){
            miMascota.remove(propietario);
    }

    public void actualizarPersona(String documento, String nombre, String telefono){
        PersonaVO persona = miPersona.get(documento);
        if (persona != null) {
            persona.setNombre(nombre);
            persona.setTelefono(telefono);
        }
    }

    public void actualizarMascota(String nombre, String raza, String sexo, String propietario){
        MascotaVO mascota = miMascota.get(propietario);
        if (mascota !=null){
            mascota.setNombre(nombre);
            mascota.setRaza(raza);
            mascota.setSexo(sexo);
        }
    }

    public ArrayList<MascotaVO> consultarAllMascotas(){
        return new ArrayList<>(miMascota.values());
    }

    public ArrayList<PersonaVO> consultarAllPersona(){
        return new ArrayList<>(miPersona.values());
    }


    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }


}
