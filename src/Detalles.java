import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Detalles extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JLabel lblNombreCompleto;
    private JLabel lblTipoId;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblUsername;
    private JButton btnGenerarPDF;
    private JButton btnCerrar;

    private Usuario usuario;

    public Detalles(Usuario usuario) {
        this.usuario = usuario;

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Detalle de Usuario");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarDatos();
        configurarEventos();
    }

    private void cargarDatos() {
        String nombreCompleto = usuario.getPrimerNombre();
        if (usuario.getSegundoNombre() != null && !usuario.getSegundoNombre().isEmpty()) {
            nombreCompleto += " " + usuario.getSegundoNombre();
        }
        nombreCompleto += " " + usuario.getPrimerApellido();
        if (usuario.getSegundoApellido() != null && !usuario.getSegundoApellido().isEmpty()) {
            nombreCompleto += " " + usuario.getSegundoApellido();
        }
        lblNombreCompleto.setText(nombreCompleto);
        lblTipoId.setText(usuario.getTipoIdentificacion() + ": " + usuario.getNumeroIdentificacion());
        lblTelefono.setText("Teléfono: " + usuario.getTelefono());
        lblCorreo.setText("Correo: " + usuario.getCorreo());
        lblUsername.setText("Username: " + usuario.getUsername());

        String ruta = usuario.getRutaFoto();
        if (ruta != null && !ruta.isEmpty()) {
            File archivoImagen = new File(ruta);
            if (archivoImagen.exists()) {
                ImageIcon iconoOriginal = new ImageIcon(ruta);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(imagenEscalada));
                lblFoto.setText("");
            } else {
                lblFoto.setText("Imagen no encontrada");
            }
        } else {
            lblFoto.setText("Sin foto");
        }
    }

    private void configurarEventos() {
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnGenerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aquí generaremos el PDF pronto.");
            }
        });
    }
}
