/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Hash;
import controller.VerificaArquivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class FrmCodigo extends javax.swing.JFrame {

    /**
     * Creates new form FrmCodigo
     */
    public FrmCodigo() {
        this.setIconImage(new ImageIcon(getClass().getResource("/images/postgre.jpg")).getImage());
        initComponents();
        aviso();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodValida = new javax.swing.JTextField();
        btnValida = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblCod = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Validação do sistema");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Validação do sistema");

        jLabel2.setText("Entre em contato e informe o código seguinte:");

        btnValida.setText("Validar");
        btnValida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidaActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        jLabel3.setText("Código de validação:");

        lblCod.setEditable(false);
        lblCod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblCodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(btnValida))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCodValida))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(178, 178, 178)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(178, 178, 178)
                                        .addComponent(jLabel2)))
                                .addGap(117, 117, 117)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCod)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodValida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValida)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCodActionPerformed

    private void btnValidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidaActionPerformed
        // TODO add your handling code here:
        if (txtCodValida.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Digite o código para liberar o sistema!");
            txtCodValida.requestFocus();
        } else {
            Hash hash = new Hash();
            String hashCode = lblCod.getText();
            String hashCodeMD5 = hash.hashMD5(hashCode);
            if (hashCodeMD5.equals(txtCodValida.getText())) {
                File file = new File("C:\\BackupPostgresql\\config\\dados-banco.properties");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "Inconsistências no arquivo de configuração foram encontradas \n um novo aquivo sera criado!,\n ainda será necessário configura-lo!", "ERRO: arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
                    try {
                        //String hashInit = saveHash();

                        Properties properties = new Properties();
                        //setando as propriedades(key) e os seus valores(value)
                        properties.setProperty("txtIpFlex", "localhost");
                        properties.setProperty("txtPortaFlex", "5432");
                        properties.setProperty("txtBaseFlex", "baseflex");
                        properties.setProperty("txtUsuarioFlex", "postgres");
                        properties.setProperty("txtSenhaFlex", "MTIzNDU2");
                        properties.setProperty("txtDesTBackupFlex", "C:\\BackupPostgresql");
                        properties.setProperty("txtIpWrpdv", "localhost");
                        properties.setProperty("txtPortaWrpdv", "5432");
                        properties.setProperty("txtBaseWrpdv", "baseWrpdv");
                        properties.setProperty("txtUsuarioWrpdv", "postgres");
                        properties.setProperty("txtSenhaWrpdv", "MTIzNDU2");
                        properties.setProperty("txtDesTBackupWrpdv", "C:\\BackupPostgresql");
                        properties.setProperty("jCheckBoxUsaWrpdv", "true");
                        properties.setProperty("jCheckBoxUsaFlex", "true");
                        properties.setProperty("txtDirPostgreSql", "C:\\Program Files\\PostgreSQL\\12\\bin");
                        properties.setProperty("txtCrontabManual", "0 0 ,13,15 ? * *");
                        properties.setProperty("txtCrontabAuto", "0 0 ,12,16,18 ? * *");
                        properties.setProperty("jCheckBoxManual", "false");
                        properties.setProperty("jComboBoxFormato", ".backup");
                        properties.setProperty("hashInit", txtCodValida.getText());
                        properties.setProperty("j00", "true");
                        properties.setProperty("j01", "false");
                        properties.setProperty("j02", "false");
                        properties.setProperty("j03", "false");
                        properties.setProperty("j04", "false");
                        properties.setProperty("j05", "false");
                        properties.setProperty("j06", "false");
                        properties.setProperty("j07", "false");
                        properties.setProperty("j08", "false");
                        properties.setProperty("j09", "false");
                        properties.setProperty("j10", "false");
                        properties.setProperty("j11", "false");
                        properties.setProperty("j12", "true");
                        properties.setProperty("j13", "false");
                        properties.setProperty("j14", "false");
                        properties.setProperty("j15", "false");
                        properties.setProperty("j16", "true");
                        properties.setProperty("j17", "false");
                        properties.setProperty("j18", "true");
                        properties.setProperty("j19", "false");
                        properties.setProperty("j20", "false");
                        properties.setProperty("j21", "false");
                        properties.setProperty("j22", "false");
                        properties.setProperty("j23", "false");

                        //Criamos um objeto FileOutputStream            
                        FileOutputStream fos = new FileOutputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
                        //grava os dados no arquivo
                        properties.store(fos, "ARQUIVO DE CONFIGURAÇÃO: FLEX e WRPDV");
                        //fecha o arquivo
                        fos.close();
                        JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso, entre nas configurações para configura-lo", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        //preencher();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        //String hashInit = saveHash();

                        Properties properties = new Properties();
                        //setando as propriedades(key) e os seus valores(value)
                        properties.setProperty("txtIpFlex", "localhost");
                        properties.setProperty("txtPortaFlex", "5432");
                        properties.setProperty("txtBaseFlex", "baseflex");
                        properties.setProperty("txtUsuarioFlex", "postgres");
                        properties.setProperty("txtSenhaFlex", "MTIzNDU2");
                        properties.setProperty("txtDesTBackupFlex", "C:\\BackupPostgresql");
                        properties.setProperty("txtIpWrpdv", "localhost");
                        properties.setProperty("txtPortaWrpdv", "5432");
                        properties.setProperty("txtBaseWrpdv", "baseWrpdv");
                        properties.setProperty("txtUsuarioWrpdv", "postgres");
                        properties.setProperty("txtSenhaWrpdv", "MTIzNDU2");
                        properties.setProperty("txtDesTBackupWrpdv", "C:\\BackupPostgresql");
                        properties.setProperty("jCheckBoxUsaWrpdv", "true");
                        properties.setProperty("jCheckBoxUsaFlex", "true");
                        properties.setProperty("txtDirPostgreSql", "C:\\Program Files\\PostgreSQL\\12\\bin");
                        properties.setProperty("txtCrontabManual", "0 0 ,13,15 ? * *");
                        properties.setProperty("txtCrontabAuto", "0 0 ,12,16,18 ? * *");
                        properties.setProperty("jCheckBoxManual", "false");
                        properties.setProperty("jComboBoxFormato", ".backup");
                        properties.setProperty("hashInit", txtCodValida.getText());
                        properties.setProperty("j00", "true");
                        properties.setProperty("j01", "false");
                        properties.setProperty("j02", "false");
                        properties.setProperty("j03", "false");
                        properties.setProperty("j04", "false");
                        properties.setProperty("j05", "false");
                        properties.setProperty("j06", "false");
                        properties.setProperty("j07", "false");
                        properties.setProperty("j08", "false");
                        properties.setProperty("j09", "false");
                        properties.setProperty("j10", "false");
                        properties.setProperty("j11", "false");
                        properties.setProperty("j12", "true");
                        properties.setProperty("j13", "false");
                        properties.setProperty("j14", "false");
                        properties.setProperty("j15", "false");
                        properties.setProperty("j16", "true");
                        properties.setProperty("j17", "false");
                        properties.setProperty("j18", "true");
                        properties.setProperty("j19", "false");
                        properties.setProperty("j20", "false");
                        properties.setProperty("j21", "false");
                        properties.setProperty("j22", "false");
                        properties.setProperty("j23", "false");

                        //Criamos um objeto FileOutputStream            
                        FileOutputStream fos = new FileOutputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
                        //grava os dados no arquivo
                        properties.store(fos, "ARQUIVO DE CONFIGURAÇÃO: FLEX e WRPDV");
                        //fecha o arquivo
                        fos.close();
                        JOptionPane.showMessageDialog(null, "Sistema validado com sucesso, é necessário reconfigura-lo", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        FrmConfig frmConfig = new FrmConfig();
                        frmConfig.setVisible(true);
                        this.dispose();
                        //preencher();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Código informado inválido! \nEntre em contato com o desenvolvedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnValidaActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmCodigo().setVisible(true);
//            }
//        });
//    }

    private void aviso() {
        VerificaArquivo verificaArquivo = new VerificaArquivo();
        String hashInet = verificaArquivo.saveHash();
        lblCod.setText(hashInet);

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValida;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lblCod;
    private javax.swing.JTextField txtCodValida;
    // End of variables declaration//GEN-END:variables
}