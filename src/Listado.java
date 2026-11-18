import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Listado extends JFrame {
    private JPanel panelPrincipal;
    private JList<Usuario> listaUsuariosView;
    private JButton btnVerDetalle;
    private JButton btnVolver;

    private ArrayList<Usuario> listaUsuarios;
    private DefaultListModel<Usuario> modeloLista;

    public Listado(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Listado de Usuarios");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloLista = new DefaultListModel<>();
        listaUsuariosView.setModel(modeloLista);

        actualizarLista();

        btnVerDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario seleccionado = listaUsuariosView.getSelectedValue();

                if (seleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario de la lista.");
                    return;
                }

                Detalles ventanaDetalle = new Detalles(seleccionado);
                ventanaDetalle.setVisible(true);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void actualizarLista() {
        modeloLista.clear();
        for (Usuario u : listaUsuarios) {
            modeloLista.addElement(u);
        }
    }
}
