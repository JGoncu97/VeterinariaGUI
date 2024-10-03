package animal;

import coordinador.Coordinador;

public class MascotaVO extends AnimalVO {

    String nombre;
    String propietario;
    private Coordinador miCoordinador;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota : \n\n" +
                "Propietario = " + propietario + '\n' +
                "Nombre = " + nombre + '\n' +
                "Raza = " + raza + '\n' +
                "Sexo = " + sexo +"\n\n";

    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}
