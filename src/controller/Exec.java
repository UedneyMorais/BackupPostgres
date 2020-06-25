/*
 * Criado por Uedney Cristiano de Morais
 * Contato do desenvolvedor: uedneymorais@gmail.com (62)-991861075
 * Classe responsável por executar o backup
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Exec {

    public static void mainBackup() {
        Backup backup = new Backup();
        EnviarEmail enviarEmail = new EnviarEmail();
        Hash hash = new Hash();
        try {

            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dtFormatada2 = dateFormat2.format(date);

            Properties propertiesBanco = new Properties();
            Properties propertiesEmail = new Properties();
            File dadosBanco = new File("C:\\BackupPostgresql\\config\\dados-banco.properties");
            File dadosEmail = new File("C:\\BackupPostgresql\\config\\dados.properties");

            if (!dadosBanco.exists() || !dadosEmail.exists()) {
                enviarEmail.enviar("BackupAutomático", "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique!");
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique! ", "ERRO: ", JOptionPane.ERROR_MESSAGE);

            } else {
                FileInputStream fis = new FileInputStream(dadosBanco);
                FileInputStream fisEmail = new FileInputStream(dadosEmail);
                //Cria as properties
                propertiesBanco.load(fis);
                propertiesEmail.load(fisEmail);

                //Decodifica as senhas
                String SenhaWrpdvDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaWrpdv"));
                String SenhaFlexDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaFlex"));
//                String SenhaWrpdvDecoded = propertiesBanco.getProperty("txtSenhaWrpdv");
//                String SenhaFlexDecoded = propertiesBanco.getProperty("txtSenhaFlex");

                if (SenhaWrpdvDecoded == "erro" || SenhaFlexDecoded == "erro") {
                    enviarEmail.enviar("BackupAutomático ERRO:", "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite a senhas corretamentes e salve!");
                    JOptionPane.showMessageDialog(null, "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite as senhas corretamente e salve!", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                } else {
                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha baseflex
                    String dirPostgres = propertiesBanco.getProperty("txtDirPostgreSql");
                    String IpFlex = propertiesBanco.getProperty("txtIpFlex");
                    String PortaFlex = propertiesBanco.getProperty("txtPortaFlex");
                    String BaseFlex = propertiesBanco.getProperty("txtBaseFlex");
                    String UserFlex = propertiesBanco.getProperty("txtUsuarioFlex");
                    String dirBackupFlex = propertiesBanco.getProperty("txtDesTBackupFlex");
                    String SenhaFlex = SenhaFlexDecoded;
                    String bkpFlex = propertiesBanco.getProperty("jCheckBoxUsaFlex");

                    //Email
                    String assuntoEmail = propertiesEmail.getProperty("assunto");
                    String inicioEmail = propertiesEmail.getProperty("mensagemini");

                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha wrpdv
                    String IpWrpdv = propertiesBanco.getProperty("txtIpWrpdv");
                    String PortaWrpdv = propertiesBanco.getProperty("txtPortaWrpdv");
                    String BaseWrpdv = propertiesBanco.getProperty("txtBaseWrpdv");
                    String UserWrpdv = propertiesBanco.getProperty("txtUsuarioWrpdv");
                    String dirBackupWrpdv = propertiesBanco.getProperty("txtDesTBackupWrpdv");
                    String SenhaWrpdv = SenhaWrpdvDecoded;
                    String bkpwrpdv = propertiesBanco.getProperty("jCheckBoxUsaWrpdv");
                    String formato = propertiesBanco.getProperty("jComboBoxFormato");
                    if (dirPostgres == null || IpFlex == null || PortaFlex == null || BaseFlex == null || UserFlex == null || dirBackupFlex == null || SenhaFlex == null || assuntoEmail == null || bkpFlex == null
                            || IpWrpdv == null || PortaWrpdv == null || BaseWrpdv == null || UserWrpdv == null || dirBackupWrpdv == null || SenhaWrpdv == null || bkpwrpdv == null
                            || formato == null) {

                        JOptionPane.showMessageDialog(null, "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \nPor favor faça a configuração para continuar", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                        enviarEmail.enviar("BackupAutomático ERRO:", "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \n Por favor faça a configuração para continuar");
                        fis.close();
                        fisEmail.close();
                    } else {
                        if (bkpFlex.equals("true")) {
                            enviarEmail.enviar(assuntoEmail, inicioEmail + "\n Base: " + BaseFlex + "\n Data e Hora: " + dtFormatada2);
                            backup.realizaBackup(dirPostgres + "\\", IpFlex, PortaFlex, BaseFlex, UserFlex, dirBackupFlex + "\\", SenhaFlex, formato);
                            fis.close();
                        }
                        if (bkpwrpdv.equals("true")) {
                            enviarEmail.enviar(assuntoEmail, inicioEmail + "\n Base: " + BaseWrpdv + "\n Data e Hora: " + dtFormatada2);
                            backup.realizaBackup(dirPostgres + "\\", IpWrpdv, PortaWrpdv, BaseWrpdv, UserWrpdv, dirBackupWrpdv + "\\", SenhaWrpdv, formato);
                            fisEmail.close();
                        }
                        if (bkpFlex.equals("false") && bkpwrpdv.equals("false")) {
                            fis.close();
                            fisEmail.close();
                            enviarEmail.enviar("BackupAutomático ERRO:", "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.");
                            JOptionPane.showMessageDialog(null, "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.", "ERRO: ", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("FileNotFoundException: " + ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException: " + ex);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("InterruptedException: " + ex);
        }

    }

    public static void mainVaccum() {
        Manuntecao manutencao = new Manuntecao();
        EnviarEmail enviarEmail = new EnviarEmail();
        Hash hash = new Hash();
        try {

            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dtFormatada2 = dateFormat2.format(date);

            Properties propertiesBanco = new Properties();
            Properties propertiesEmail = new Properties();
            File dadosBanco = new File("C:\\BackupPostgresql\\config\\dados-banco.properties");
            File dadosEmail = new File("C:\\BackupPostgresql\\config\\dados.properties");

            if (!dadosBanco.exists() || !dadosEmail.exists()) {
                enviarEmail.enviar("BackupAutomático", "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique!");
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique! ", "ERRO: ", JOptionPane.ERROR_MESSAGE);

            } else {
                FileInputStream fis = new FileInputStream(dadosBanco);
                FileInputStream fisEmail = new FileInputStream(dadosEmail);
                //Cria as properties
                propertiesBanco.load(fis);
                propertiesEmail.load(fisEmail);

                //Decodifica as senhas
                String SenhaWrpdvDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaWrpdv"));
                String SenhaFlexDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaFlex"));
                //String SenhaWrpdvDecoded = propertiesBanco.getProperty("txtSenhaWrpdv");
                //String SenhaFlexDecoded = propertiesBanco.getProperty("txtSenhaFlex");

                if (SenhaWrpdvDecoded == "erro" || SenhaFlexDecoded == "erro") {
                    enviarEmail.enviar("BackupAutomático ERRO:", "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite a senhas corretamentes e salve!");
                    JOptionPane.showMessageDialog(null, "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite as senhas corretamente e salve!", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                } else {
                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha baseflex
                    String dirPostgres = propertiesBanco.getProperty("txtDirPostgreSql");
                    String IpFlex = propertiesBanco.getProperty("txtIpFlex");
                    String PortaFlex = propertiesBanco.getProperty("txtPortaFlex");
                    String BaseFlex = propertiesBanco.getProperty("txtBaseFlex");
                    String UserFlex = propertiesBanco.getProperty("txtUsuarioFlex");
                    String dirBackupFlex = propertiesBanco.getProperty("txtDesTBackupFlex");
                    String SenhaFlex = SenhaFlexDecoded;
                    String bkpFlex = propertiesBanco.getProperty("jCheckBoxUsaFlex");

                    //Email
                    String assuntoEmail = propertiesEmail.getProperty("assunto");
                    String inicioEmail = propertiesEmail.getProperty("mensagemini");

                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha wrpdv
                    String IpWrpdv = propertiesBanco.getProperty("txtIpWrpdv");
                    String PortaWrpdv = propertiesBanco.getProperty("txtPortaWrpdv");
                    String BaseWrpdv = propertiesBanco.getProperty("txtBaseWrpdv");
                    String UserWrpdv = propertiesBanco.getProperty("txtUsuarioWrpdv");
                    String dirBackupWrpdv = propertiesBanco.getProperty("txtDesTBackupWrpdv");
                    String SenhaWrpdv = SenhaWrpdvDecoded;
                    String bkpwrpdv = propertiesBanco.getProperty("jCheckBoxUsaWrpdv");
                    String formato = propertiesBanco.getProperty("jComboBoxFormato");
                    if (dirPostgres == null || IpFlex == null || PortaFlex == null || BaseFlex == null || UserFlex == null || dirBackupFlex == null || SenhaFlex == null || assuntoEmail == null || bkpFlex == null
                            || IpWrpdv == null || PortaWrpdv == null || BaseWrpdv == null || UserWrpdv == null || dirBackupWrpdv == null || SenhaWrpdv == null || bkpwrpdv == null
                            || formato == null) {

                        JOptionPane.showMessageDialog(null, "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \nPor favor faça a configuração para continuar", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                        enviarEmail.enviar("VACUUM Automático ERRO:", "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \n Por favor faça a configuração para continuar");
                        fis.close();
                        fisEmail.close();
                    } else {
                        if (bkpFlex.equals("true")) {
                            //enviarEmail.enviar("VACUUM Automático", "INICIO DO VACCUM" + "\n Base: " + BaseFlex + "\n Data e Hora: " + dtFormatada2);
                            manutencao.realizaVaccum(dirPostgres + "\\", IpFlex, PortaFlex, BaseFlex, UserFlex, dirBackupFlex + "\\", SenhaFlex, formato);
                            fis.close();
                        }
                        if (bkpwrpdv.equals("true")) {
                            //enviarEmail.enviar("VACUUM Automático", "INICIO DO VACCUM"+ "\n Base: " + BaseWrpdv + "\n Data e Hora: " + dtFormatada2);
                            manutencao.realizaVaccum(dirPostgres + "\\", IpWrpdv, PortaWrpdv, BaseWrpdv, UserWrpdv, dirBackupWrpdv + "\\", SenhaWrpdv, formato);
                            fisEmail.close();
                        }
                        if (bkpFlex.equals("false") && bkpwrpdv.equals("false")) {
                            fis.close();
                            fisEmail.close();
                            enviarEmail.enviar("VACUUM Automático ERRO:", "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.");
                            JOptionPane.showMessageDialog(null, "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.", "ERRO: ", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("FileNotFoundException: " + ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException: " + ex);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("InterruptedException: " + ex);
        }

    }

    public static void mainReindex() {
        Manuntecao manutencao = new Manuntecao();
        EnviarEmail enviarEmail = new EnviarEmail();
        Hash hash = new Hash();
        try {

            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String dtFormatada2 = dateFormat2.format(date);

            Properties propertiesBanco = new Properties();
            Properties propertiesEmail = new Properties();
            File dadosBanco = new File("C:\\BackupPostgresql\\config\\dados-banco.properties");
            File dadosEmail = new File("C:\\BackupPostgresql\\config\\dados.properties");

            if (!dadosBanco.exists() || !dadosEmail.exists()) {
                enviarEmail.enviar("BackupAutomático", "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique!");
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar abrir os arquivos de configuração \"dados\" e \"dados-banco\" \n por favor verifique! ", "ERRO: ", JOptionPane.ERROR_MESSAGE);

            } else {
                FileInputStream fis = new FileInputStream(dadosBanco);
                FileInputStream fisEmail = new FileInputStream(dadosEmail);
                //Cria as properties
                propertiesBanco.load(fis);
                propertiesEmail.load(fisEmail);

                //Decodifica as senhas
                String SenhaWrpdvDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaWrpdv"));
                String SenhaFlexDecoded = hash.HashBase64Decoded(propertiesBanco.getProperty("txtSenhaFlex"));
//                String SenhaWrpdvDecoded = propertiesBanco.getProperty("txtSenhaWrpdv");
//                String SenhaFlexDecoded = propertiesBanco.getProperty("txtSenhaFlex");

                if (SenhaWrpdvDecoded == "erro" || SenhaFlexDecoded == "erro") {
                    enviarEmail.enviar("BackupAutomático ERRO:", "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite a senhas corretamentes e salve!");
                    JOptionPane.showMessageDialog(null, "Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite as senhas corretamente e salve!", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                } else {
                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha baseflex
                    String dirPostgres = propertiesBanco.getProperty("txtDirPostgreSql");
                    String IpFlex = propertiesBanco.getProperty("txtIpFlex");
                    String PortaFlex = propertiesBanco.getProperty("txtPortaFlex");
                    String BaseFlex = propertiesBanco.getProperty("txtBaseFlex");
                    String UserFlex = propertiesBanco.getProperty("txtUsuarioFlex");
                    String dirBackupFlex = propertiesBanco.getProperty("txtDesTBackupFlex");
                    String SenhaFlex = SenhaFlexDecoded;
                    String bkpFlex = propertiesBanco.getProperty("jCheckBoxUsaFlex");

                    //Email
                    String assuntoEmail = propertiesEmail.getProperty("assunto");
                    String inicioEmail = propertiesEmail.getProperty("mensagemini");

                    //dirPostgres, Ip, Port, Base, User, dirBackup, Senha wrpdv
                    String IpWrpdv = propertiesBanco.getProperty("txtIpWrpdv");
                    String PortaWrpdv = propertiesBanco.getProperty("txtPortaWrpdv");
                    String BaseWrpdv = propertiesBanco.getProperty("txtBaseWrpdv");
                    String UserWrpdv = propertiesBanco.getProperty("txtUsuarioWrpdv");
                    String dirBackupWrpdv = propertiesBanco.getProperty("txtDesTBackupWrpdv");
                    String SenhaWrpdv = SenhaWrpdvDecoded;
                    String bkpwrpdv = propertiesBanco.getProperty("jCheckBoxUsaWrpdv");
                    String formato = propertiesBanco.getProperty("jComboBoxFormato");
                    if (dirPostgres == null || IpFlex == null || PortaFlex == null || BaseFlex == null || UserFlex == null || dirBackupFlex == null || SenhaFlex == null || assuntoEmail == null || bkpFlex == null
                            || IpWrpdv == null || PortaWrpdv == null || BaseWrpdv == null || UserWrpdv == null || dirBackupWrpdv == null || SenhaWrpdv == null || bkpwrpdv == null
                            || formato == null) {

                        JOptionPane.showMessageDialog(null, "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \nPor favor faça a configuração para continuar", "ERRO: ", JOptionPane.ERROR_MESSAGE);
                        enviarEmail.enviar("REINDEX Automático ERRO:", "Existem informações vazias vindas do arquivo de configuração: dados-banco.properties ou dados.properties \n Por favor faça a configuração para continuar");
                        fis.close();
                        fisEmail.close();
                    } else {
                        if (bkpFlex.equals("true")) {
                            //enviarEmail.enviar("REINDEX Automático", "INICIO DO REINDEX" + "\n Base: " + BaseFlex + "\n Data e Hora: " + dtFormatada2);
                            manutencao.realizaReindex(dirPostgres + "\\", IpFlex, PortaFlex, BaseFlex, UserFlex, dirBackupFlex + "\\", SenhaFlex, formato);
                            fis.close();
                        }
                        if (bkpwrpdv.equals("true")) {
                            //enviarEmail.enviar("REINDEX Automático", "INICIO DO REINDEX"+ "\n Base: " + BaseWrpdv + "\n Data e Hora: " + dtFormatada2);
                            manutencao.realizaReindex(dirPostgres + "\\", IpWrpdv, PortaWrpdv, BaseWrpdv, UserWrpdv, dirBackupWrpdv + "\\", SenhaWrpdv, formato);
                            fisEmail.close();
                        }
                        if (bkpFlex.equals("false") && bkpwrpdv.equals("false")) {
                            fis.close();
                            fisEmail.close();
                            enviarEmail.enviar("REINDEX Automático", "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.");
                            JOptionPane.showMessageDialog(null, "Nenhuma base foi selecionada \n Por favor selecione pelo menos uma base.", "ERRO: ", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("FileNotFoundException: " + ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("IOException: " + ex);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("InterruptedException: " + ex);
        }

    }

}
