/*
 * Criado por Uedney Cristiano de Morais
 * Contato do desenvolvedor: uedneymorais@gmail.com (62)-991861075
 * Classe responsável por verificar os arquivos de configuração
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.FrmConfig;
import view.Tray;

/**
 *
 * @author pc
 */
public class VerificaArquivo {

    Address address = new Address();

    public void verificaArqBanco() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                // If Nimbus is not available, you can set the GUI to another look and feel.
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//cria o arquivo caso não exista
        File file = new File("C:\\BackupPostgresql\\config\\dados-banco.properties");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o arquivo de configuração, será criado um com configurações padrão,\n ainda será necessário configura-lo!", "ERRO: arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
            try {
                String hashInit = saveHash();

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
                properties.setProperty("hashInit", hashInit);
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
                FrmConfig frmConfig = new FrmConfig();
                frmConfig.setVisible(true);
                //preencher();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void GravaHash() {
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
                properties.setProperty("hashInit", "c27f9cf862ecf23b759662cfd77e5ec");
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
                FrmConfig frmConfig = new FrmConfig();
                frmConfig.setVisible(true);
                //preencher();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void verificaArqEmail() {
//cria o arquivo caso não exista
        File file = new File("C:\\BackupPostgresql\\config\\dados.properties");
        if (!file.exists()) {
            try {
                Properties properties = new Properties();
                //setando as propriedades(key) e os seus valores(value)
                properties.setProperty("host", "smtp.gmail.com");
                properties.setProperty("port", "465");
                properties.setProperty("senha", "MTIzNDU2");
                properties.setProperty("email", "emailqueenvia@email.com");
                properties.setProperty("emailcontador", "emailquerecebe@email.com");
                properties.setProperty("assunto", "Aqui é adicionado o Assunto do Email");
                properties.setProperty("mensagem", "Aqui é adicionado o corpo do Email");
                properties.setProperty("caminho", "");

                //Criamos um objeto FileOutputStream            
                FileOutputStream fos = new FileOutputStream("C:\\BackupPostgresql\\config\\dados.properties");
                //grava os dados no arquivo
                properties.store(fos, "ARQUIVO DE CONFIGURAÇÃO:");
                //fecha o arquivo
                fos.close();
                JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso, entre nas configurações para configura-lo", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                //preencher();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String saveHash() {
        Hash hash = new Hash();
        String mac = hash.HashBase64(address.getInet() + "UEDNEY2020");
        System.out.println(mac);
        return mac;
    }

}
