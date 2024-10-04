package vo;

import coordinador.Coordinador;

public class PersonaVO {

    String documento;
    String nombre;
    String telefono;
    private Coordinador miCoordinador;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Persona \n\n" +
                "Documento = " + documento + '\n' +
                "Nombre = " + nombre + '\n' +
                "Telefono = " + telefono + "\n\n";
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}
