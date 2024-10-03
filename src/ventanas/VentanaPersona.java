package ventanas;

import coordinador.Coordinador;
import persona.PersonaVO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPersona extends JFrame implements ActionListener {

    private JPanel panelPersona;
    private JLabel tituloPersona;
    private JLabel lblNombre;
    private JLabel lblDocumento;
    private JLabel lblTelefono;
    private JTextField inputNombre;
    private JTextField inputDocumento;
    private JTextField inputTelefono;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarList;
    private JTextArea resultado;
    private JScrollPane scrollResultado;
    private Coordinador miCoordinador;

    public VentanaPersona(){

        setSize(600, 700);
        setTitle("Sistema Veterinario ABC");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();

    }

    private void iniciarComponentes() {

        panelPersona = new JPanel();
        panelPersona.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panelPersona);
        panelPersona.setLayout(null);


        tituloPersona = new JLabel("Gestionar Personas");
        tituloPersona.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPersona.setFont(new Font("Tahoma", Font.PLAIN, 25));
        tituloPersona.setBounds(140, 20, 300, 30);
        tituloPersona.setHorizontalAlignment(SwingConstants.CENTER);
        panelPersona.add(tituloPersona);

        lblDocumento = new JLabel("Documento :");
        lblTelefono= new JLabel("Telefono :");
        lblNombre = new JLabel("Nombre :");


        lblDocumento.setBounds(80,50,100,50);
        panelPersona.add(lblDocumento);

        inputDocumento= new JTextField();
        inputDocumento.setBounds(153,65,140,20);
        panelPersona.add(inputDocumento);

        lblNombre.setBounds(80,80,100,50);
        panelPersona.add(lblNombre);

        inputNombre = new JTextField();
        inputNombre.setBounds(135,95,365,20);
        panelPersona.add(inputNombre);

        lblTelefono.setBounds(300,50,100,50);
        panelPersona.add(lblTelefono);

        inputTelefono = new JTextField();
        inputTelefono.setBounds(358,65,143,20);
        panelPersona.add(inputTelefono);



        btnRegistrar = new JButton("Registrar");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnConsultarList = new JButton("Consultar Lista");

        btnRegistrar.setBounds(80,140,200,30);
        panelPersona.add(btnRegistrar);

        btnActualizar.setBounds(80,190,200,30);
        panelPersona.add(btnActualizar);

        btnConsultar.setBounds(300,140,200,30);
        panelPersona.add(btnConsultar);

        btnEliminar.setBounds(300,190,200,30);
        panelPersona.add(btnEliminar);

        btnConsultarList.setBounds(80,230,420,30);
        panelPersona.add(btnConsultarList);

        btnConsultar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnConsultarList.addActionListener(this);

        resultado = new JTextArea();
        resultado.setBorder(new LineBorder(Color.BLACK, 2));
        resultado.setEditable(false);
        scrollResultado = new JScrollPane(resultado);
        scrollResultado.setBounds(80,280,420,300);
        panelPersona.add(scrollResultado);



    }



    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador= miCoordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnRegistrar){
            registrarPersona();
        }else if(e.getSource() == btnEliminar){
            eliminarPersona();
        }else if(e.getSource() == btnConsultar){
            consultarPersona();
        }else if(e.getSource() == btnActualizar){
            actualizarPersona();
        }else if(e.getSource() == btnConsultarList){
            consultarListPersona();
        }
    }

    public void registrarPersona(){
        String documento = inputDocumento.getText().trim();
        String nombre = inputNombre.getText().trim();
        String telefono = inputTelefono.getText().trim();

        if (documento.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            resultado.setText("Por favor, complete todos los campos.");
            return;
        }

        miCoordinador.registrarPersona(documento, nombre, telefono);
        resultado.setText("Persona registrada con éxito: " + nombre);
    }

    public void eliminarPersona(){
        String documento = inputDocumento.getText().trim();
        if (documento.isEmpty()) {
            resultado.setText("Por favor, ingrese el documento para eliminar.");
            return;
        }

        miCoordinador.eliminarPersona(documento);
        resultado.setText("Persona eliminada con éxito.");
    }

    public void consultarPersona(){
        String documento = inputDocumento.getText().trim();
        if (documento.isEmpty()) {
            resultado.setText("Por favor, ingrese el documento para consultar.");
            return;
        }

        PersonaVO persona = miCoordinador.consultarPersona(documento);
        if (persona != null) {
            resultado.setText("Nombre: " + persona.getNombre() + "\nTeléfono: " + persona.getTelefono());
        } else {
            resultado.setText("Error: Persona no encontrada.");
        }
    }

    public void actualizarPersona(){
        String documento = inputDocumento.getText().trim();
        String nombre = inputNombre.getText().trim();
        String telefono = inputTelefono.getText().trim();

        if (documento.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
            resultado.setText("Por favor, complete todos los campos.");
            return;
        }

        boolean actualizado = miCoordinador.actualizarPersona(documento, nombre, telefono);
        if (actualizado) {
            resultado.setText("Persona actualizada con éxito.");
        } else {
            resultado.setText("Error: Persona no encontrada.");
        }
    }

    public void consultarListPersona(){
        StringBuilder listaPersonas = new StringBuilder();
        for (PersonaVO persona : miCoordinador.consultarListaPersonas()) {
            listaPersonas.append("Documento: ").append(persona.getDocumento())
                    .append(", Nombre: ").append(persona.getNombre())
                    .append(", Teléfono: ").append(persona.getTelefono()).append("\n");
        }
        resultado.setText(!listaPersonas.isEmpty() ? listaPersonas.toString() : "No hay personas registradas.");

    }

}
