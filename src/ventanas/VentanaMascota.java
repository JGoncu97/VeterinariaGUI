package ventanas;

import vo.MascotaVO;
import coordinador.Coordinador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class VentanaMascota extends JFrame implements ActionListener {

    private JPanel panelMascota;
    private JLabel tituloMascotas;
    private JLabel lblId;
    private JLabel lblRaza;
    private JLabel lblNombre;
    private JLabel lblSexo;
    private JTextField inputId;
    private JTextField inputRaza;
    private JTextField inputNombre;
    private JTextField inputSexo;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarList;
    private JTextArea resultado;
    private JScrollPane scrollResultado;
    private Coordinador miCoordinador;

    public VentanaMascota(){

        setSize(600, 700);
        setTitle("Sistema Veterinario ABC");
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();

    }

    private void iniciarComponentes() {

        panelMascota = new JPanel();
        panelMascota.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panelMascota);
        panelMascota.setLayout(null);


        tituloMascotas = new JLabel("Gestionar Mascotas");
        tituloMascotas.setHorizontalAlignment(SwingConstants.CENTER);
        tituloMascotas.setFont(new Font("Tahoma", Font.PLAIN, 25));
        tituloMascotas.setBounds(140, 20, 300, 30);
        tituloMascotas.setHorizontalAlignment(SwingConstants.CENTER);
        panelMascota.add(tituloMascotas);

        lblId = new JLabel("ID Dueño :");
        lblRaza= new JLabel("Raza :");
        lblNombre = new JLabel("Nombre :");
        lblSexo = new JLabel("Sexo :");

        lblId.setBounds(80,50,100,50);
        panelMascota.add(lblId);

        inputId= new JTextField();
        inputId.setBounds(150,65,140,20);
        panelMascota.add(inputId);

        lblNombre.setBounds(80,80,100,50);
        panelMascota.add(lblNombre);

        inputNombre = new JTextField();
        inputNombre.setBounds(150,95,140,20);
        panelMascota.add(inputNombre);

        lblRaza.setBounds(300,50,100,50);
        panelMascota.add(lblRaza);

        inputRaza = new JTextField();
        inputRaza.setBounds(350,65,150,20);
        panelMascota.add(inputRaza);

        lblSexo.setBounds(300,80,100,50);
        panelMascota.add(lblSexo);

        inputSexo = new JTextField();
        inputSexo.setBounds(350,95,150,20);
        panelMascota.add(inputSexo);

        btnRegistrar = new JButton("Registrar");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnConsultarList = new JButton("Consultar Lista");

        btnRegistrar.setBounds(80,140,200,30);
        panelMascota.add(btnRegistrar);

        btnActualizar.setBounds(80,190,200,30);
        panelMascota.add(btnActualizar);

        btnConsultar.setBounds(300,140,200,30);
        panelMascota.add(btnConsultar);

        btnEliminar.setBounds(300,190,200,30);
        panelMascota.add(btnEliminar);

        btnConsultarList.setBounds(80,230,420,30);
        panelMascota.add(btnConsultarList);

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
        panelMascota.add(scrollResultado);



    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnRegistrar){
            registrarMascota();
        }else if(e.getSource() == btnEliminar){
            try {
                eliminarMascota();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else if(e.getSource() == btnConsultar){
            try {
                consultarMascota();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else if(e.getSource() == btnActualizar){
            actualizarMascota();
        }else if(e.getSource() == btnConsultarList){
            consultarListMascota();
        }
    }

    public void registrarMascota(){
        String idDueño = inputId.getText().trim();
        String nombreMascota = inputNombre.getText().trim();
        String raza = inputRaza.getText().trim();
        String sexo = inputSexo.getText().trim();

        if (idDueño.isEmpty() || nombreMascota.isEmpty() || raza.isEmpty() || sexo.isEmpty()) {
            resultado.setText("Por favor, complete todos los campos.");
            return;
        }

        miCoordinador.registrarMascota(idDueño, nombreMascota, raza, sexo);
        resultado.setText("Mascota registrada con éxito: " + nombreMascota);
    }

    public void eliminarMascota() throws SQLException {
        String propietario = inputId.getText().trim();
        if (propietario.isEmpty()) {
            resultado.setText("Por favor, ingrese el nombre de la mascota para eliminar.");
            return;
        }

        String eliminada =miCoordinador.eliminarMascota(propietario);
        if(eliminada == null) {
            resultado.setText("Mascota eliminada con éxito.");
        }else {
            resultado.setText("Error: Mascota no encontrada.");
        }
    }

    public void consultarMascota() throws SQLException {
        String propietario = inputId.getText().trim();
        if (propietario.isEmpty()) {
            resultado.setText("Por favor, ingrese el nombre de la mascota para consultar.");
            return;
        }

        MascotaVO mascota = miCoordinador.consultarMascota(propietario);
        if (mascota != null) {
            resultado.setText("Dueño: " + mascota.getPropietario() + "\nNombre: " + mascota.getNombre() +
                    "\nRaza: " + mascota.getRaza() + "\nSexo: " + mascota.getSexo());
        } else {
            resultado.setText("Error: Mascota no encontrada.");
        }
    }

    public void actualizarMascota(){
        String propietario = inputId.getText().trim();
        String nombreMascota = inputNombre.getText().trim();
        String raza = inputRaza.getText().trim();
        String sexo = inputSexo.getText().trim();

        if (propietario.isEmpty() || nombreMascota.isEmpty() || raza.isEmpty() || sexo.isEmpty()) {
            resultado.setText("Por favor, complete todos los campos.");
            return;
        }

        String actualizado = miCoordinador.actualizarMascota(propietario, nombreMascota, raza, sexo);
        if (actualizado !=null) {
            resultado.setText("Mascota actualizada con éxito.");
        } else {
            resultado.setText("Error: Mascota no encontrada.");
        }
    }

    public void consultarListMascota(){
        StringBuilder listaMascotas = new StringBuilder();
        for (MascotaVO mascota : miCoordinador.consultarListaMascotas()) {
            listaMascotas.append("Dueño: ").append(mascota.getPropietario())
                    .append(", Nombre: ").append(mascota.getNombre())
                    .append(", Raza: ").append(mascota.getRaza())
                    .append(", Sexo: ").append(mascota.getSexo()).append("\n");
        }
        resultado.setText(listaMascotas.length() > 0 ? listaMascotas.toString() : "No hay mascotas registradas.");
    }


}
