package Trabajo;

import java.util.ArrayList;

public class FormRegistro extends javax.swing.JFrame {

    private ArrayList<Usuario> listaUsuarios;
    private int idAsignado;

    public FormRegistro(ArrayList<Usuario> lista, int id) {
        initComponents();

        this.listaUsuarios = lista;
        this.idAsignado = id;
        }
}