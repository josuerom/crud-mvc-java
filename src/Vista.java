import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.ResultSet;

public class Vista extends javax.swing.JFrame {
    private static Statement st = null;
    private static ResultSet rs = null;
    private static Connection cx = null;
    private static Conexion con = null;

    public Vista() {
        initComponents();
        con = new Conexion();
        // Conexión a la base de datos
        cx = con.conectarDB();
        if (cx != null)
            System.out.println("Conexión BD exitosa!");
        else
            System.out.println("Conexión BD erronea!");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16));
        jLabel1.setText("LOGIN UNIMINUTO");
        jLabel2.setText("Usuario:");
        jLabel3.setText("Contraseña:");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnLogin)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnLogin)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }                     

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                           
    }                                          

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {                                              
    }                                             

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            st = cx.createStatement();
            rs = st.executeQuery("SELECT * FROM login");
            boolean esta_usario = false;
            while (rs.next()) {
                String usuario = rs.getString("usuario"), clave = rs.getString("clave");
                if (usuario.equals(txtUsuario.getText()) && clave.equals(txtContraseña.getText())) {
                    JOptionPane.showMessageDialog(null, "El usuario SI esta en la BD.");
                    esta_usario = true;
                }
            }
            if (!esta_usario)
                JOptionPane.showMessageDialog(null, "El usuario NO esta en la BD.");
        } catch (Exception e) {
            e.printStackTrace();
        }                                       
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        con.desconectarDB();                                         
    }

    private void leerUsuarios() {
        try {
            st = cx.createStatement();
            rs = st.executeQuery("SELECT * FROM login");
            while (rs.next()) {
                System.out.println(rs.getString("usuario") + " | " + rs.getString("clave"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertarUsuario(String usuario, String clave) {
        String comando = "INSERT INTO login VALUES ('" + usuario + "', '" + clave + "');";
        try {
            st = cx.createStatement();
            rs = st.executeQuery(comando);
            while (rs.next()) {
                System.out.println(rs.getString("usuario") + " | " + rs.getString("clave"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarUsuario() {
    }

    private void eliminarUsuario() {
    }

    public static void main(String args[]) {
        Vista vista = new Vista();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtUsuario;
}