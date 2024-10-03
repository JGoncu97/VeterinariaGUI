package ventanas;

import coordinador.Coordinador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class VentanaPrincipal extends JFrame implements ActionListener {

    VentanaMascota vMascota;
    VentanaPersona vPersona;
    private JPanel miPanelPpal;
    private JButton btnPersona;
    private JButton btnMascota;
    private JLabel titulo;
    private JLabel lblPortada;
    private Coordinador miCoordinador;

    public VentanaPrincipal() {
        iniciarComponentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setTitle("Sistema Veterinario ABC");
        setLocationRelativeTo(null);
        setResizable(false);

        vMascota = new VentanaMascota();
        vPersona = new VentanaPersona();


    }

    private void iniciarComponentes() {
        miPanelPpal = new JPanel();
        miPanelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(miPanelPpal);
        miPanelPpal.setLayout(null);

        titulo = new JLabel("SISTEMA VETERINARIA ABC");
        titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        titulo.setBounds(160, 20, 300, 30);
        miPanelPpal.add(titulo);
    /*
        lblPortada = new JLabel();
        lblPortada.setIcon(new ImageIcon(getClass().getResource("/resources/portada.jpg")));
        lblPortada.setBounds(0, 56, 600, 400);
        miPanelPpal.add(lblPortada);*/

        btnPersona = new JButton("Gestionar Personas");
        btnPersona.setBounds(60, 400, 200, 50);
        btnPersona.addActionListener(this);
        miPanelPpal.add(btnPersona);

        btnMascota = new JButton("Gestionar Mascotas");
        btnMascota.setBounds(320, 400, 200, 50);
        btnMascota.addActionListener(this);
        miPanelPpal.add(btnMascota);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPersona) {

                miCoordinador.mostrarVentana(1);
        } else if (e.getSource() == btnMascota) {
                miCoordinador.mostrarVentana(2);
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {

        this.miCoordinador = miCoordinador;

    }
}
