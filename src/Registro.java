import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Registro extends JFrame {
    private ArrayList<Usuario> listaUsuarios;
    private int contadorId;
    private String rutaImagenSeleccionada = "";

    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnFoto;
    private JLabel lblFotoPreview;
    private JButton btnGuardar;

    public Registro(ArrayList<Usuario> listaUsuarios, int contadorId) {
        this.listaUsuarios = listaUsuarios;
        this.contadorId = contadorId;

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Registro de Usuario");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        llenarComboBox();
        configurarEventos();
        configurarUsernameAutomatico();
    }

    private void llenarComboBox() {
        cmbTipoId.addItem("CC - Cédula de Ciudadanía");
        cmbTipoId.addItem("TI - Tarjeta de Identidad");
        cmbTipoId.addItem("PA - Pasaporte");
    }

    private void configurarEventos() {
        btnFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

                int resultado = fileChooser.showOpenDialog(null);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    rutaImagenSeleccionada = archivoSeleccionado.getAbsolutePath();

                    ImageIcon iconoOriginal = new ImageIcon(rutaImagenSeleccionada);
                    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    lblFotoPreview.setIcon(new ImageIcon(imagenEscalada));
                    lblFotoPreview.setText("");
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });
    }

    private void guardarUsuario() {
        if (txtNumeroId.getText().isEmpty() || txtPrimerNombre.getText().isEmpty() ||
                txtPrimerApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() || txtPassword.getPassword().length == 0) {

            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtCorreo.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener un '@'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoId = (String) cmbTipoId.getSelectedItem();
        String numId = txtNumeroId.getText();
        String primerNom = txtPrimerNombre.getText();
        String segundoNom = txtSegundoNombre.getText();
        String primerApe = txtPrimerApellido.getText();
        String segundoApe = txtSegundoApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String password = new String(txtPassword.getPassword());

        String username = generarUsername(primerNom, primerApe, numId);
        lblUsername.setText(username);

        Usuario nuevoUsuario = new Usuario(
                contadorId, tipoId, numId, primerNom, segundoNom,
                primerApe, segundoApe, username, password,
                telefono, correo, rutaImagenSeleccionada
        );

        listaUsuarios.add(nuevoUsuario);

        JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.\nUsername: " + username);
        this.dispose();
    }

    private String generarUsername(String nombre, String apellido, String id) {
        if(nombre.isEmpty() || apellido.isEmpty()) return "";
        String inicial = nombre.substring(0, 1).toLowerCase();
        return inicial + apellido.toLowerCase() + id;

    }
    private void configurarUsernameAutomatico() {
        txtPrimerNombre.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
        });

        txtPrimerApellido.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { actualizarUsername(); }
        });
    }

    private void actualizarUsername() {
        String nombre = txtPrimerNombre.getText();
        String apellido = txtPrimerApellido.getText();
        String id = txtNumeroId.getText();

        if (!nombre.isEmpty() && !apellido.isEmpty()) {
            String username = generarUsername(nombre, apellido, id);
            lblUsername.setText(username);
        } else {
            lblUsername.setText("");
        }
    }
}