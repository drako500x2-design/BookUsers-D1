package Trabajo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormMenu {
    private JPanel PanelPrincipal;
    private JButton btnregistrar;
    private JButton btnconsultar;
    private JButton btncreditos;
    private JLabel version;
    private JPanel LugarParaBotones;
    private ArrayList<Usuario> listaUsuarios;
    private int contadorId = 1;
    public FormMenu(){
        initComponents();
        listaUsuarios = new ArrayList<>(); // Aquí nace la lista única

        btnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormRegistro ventanaRegistro = new FormRegistro(listaUsuarios, contadorId);
                ventanaRegistro.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("BookUsers");
        ventana.setContentPane(new FormMenu().PanelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    private ArrayList<Usuario> listaUsuarios;
    private int contadorId = 1;

}


