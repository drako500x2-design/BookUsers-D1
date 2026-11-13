package Trabajo;

import javax.swing.*;

public class FormMenu {
    private JPanel PanelPrincipal;
    private JButton btnregistrar;
    private JButton btnconsultar;
    private JButton btncreditos;

    public FormMenu(){

    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("BookUsers");
        ventana.setContentPane(new FormMenu().PanelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}


