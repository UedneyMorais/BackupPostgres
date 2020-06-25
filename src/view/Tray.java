package view;

import controller.Address;
import controller.Exec;
import controller.Hash;
import controller.QuartzJob;
import controller.VerificaArquivo;
import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Tray {

    Address address = new Address();

    public static void main(String[] args) throws SchedulerException {

        VerificaArquivo verificaArquivo = new VerificaArquivo();
        verificaArquivo.verificaArqBanco();
        verificaArquivo.verificaArqEmail();
        //searchHash();
        Exec exec = new Exec();
        exec.mainVaccum();
        exec.mainReindex();
        exec.mainBackup();
    }

    public static void Inicia() {

        //VerificaArquivo verificaArquivo = new VerificaArquivo();
        //verificaArquivo.verificaArqBanco();
        //verificaArquivo.verificaArqEmail();
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

        Runnable runer = new Runnable() {

            public void run() {
                //tray inicia
                if (SystemTray.isSupported()) {
                    SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().getImage("C:\\BackupPostgresql\\images\\postgre.jpg");

                    PopupMenu popup = new PopupMenu();
                    MenuItem item = new MenuItem("Cofigurações");
                    MenuItem item2 = new MenuItem("Guia de uso");
                    MenuItem item3 = new MenuItem("Sobre");
                    MenuItem item4 = new MenuItem("Fechar");

                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            FrmConfig frmConfig = new FrmConfig();
                            frmConfig.setVisible(true);
                        }
                    });

                    item3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(null,
                                    "Desenvolvido por Uedney Morais (62)99186-1075 / uedneymorais@gmail.com\n Versão: 1.0 \nData da versão: 16/06/2020", "Sobre", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });

                    item4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });

                    item2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            FrmManual frmManual = new FrmManual();
                            frmManual.setVisible(true);
                        }
                    });

                    popup.add(item);
                    popup.add(item2);
                    popup.add(item3);
                    popup.add(item4);
                    TrayIcon trayIcon = new TrayIcon(image, "Backup Postgresql", popup);
                    trayIcon.setImageAutoSize(true);
                    try {
                        tray.add(trayIcon);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("tray Indisponivel");
                }
                //termina

            }
        };
        EventQueue.invokeLater(runer);
        //Definir uma job e uma Tie 

        try {
            Properties propertiesBanco = new Properties();
            //Setamos o arquivo que vai ser lido
            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
            propertiesBanco.load(fis);

            //metodo load faz a leitura atraves do objeto fis
            JobDetail job = JobBuilder.newJob(QuartzJob.class).build();

            //Trigger t1 = TriggerBuilder.newTrigger().withIdentity("Simple Name").startNow().build();
            //Executa com 1 minuto baseado no Cron do site http://www.cronmaker.com/;jsessionid=node0asw7dbtjjpjpybwa6tmjwf67205679.node0?0 -- "0 24,31 19,18 ? * *"
            String scriptManual = propertiesBanco.getProperty("jCheckBoxManual");
            String txtCrontabManual = propertiesBanco.getProperty("txtCrontabManual");
            if (scriptManual.equals("false") && !txtCrontabManual.equals("")) {
                try {
                    Trigger t1 = TriggerBuilder.newTrigger().withIdentity("Crontrigger").withSchedule(CronScheduleBuilder.cronSchedule(propertiesBanco.getProperty("txtCrontabAuto"))).build();
                    Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
                    sc.start();
                    sc.scheduleJob(job, t1);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro com a expressão Cron automática, corrija as configurações de horas. \nERRO: " + e, "ERRO: ", JOptionPane.ERROR_MESSAGE);
                    FrmConfig frmConfig = new FrmConfig();
                    frmConfig.setVisible(true);
                }

            } else {

                try {
                    Trigger t1 = TriggerBuilder.newTrigger().withIdentity("Crontrigger").withSchedule(CronScheduleBuilder.cronSchedule(propertiesBanco.getProperty("txtCrontab"))).build();
                    Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
                    sc.start();
                    sc.scheduleJob(job, t1);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro com a expressão Cron manual, corrija no campo Script Crontab. \nERRO: " + e, "ERRO: ", JOptionPane.ERROR_MESSAGE);
                    FrmConfig frmConfig = new FrmConfig();
                    frmConfig.show();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Trigger t1 = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(05).repeatForever()).build();
    }

    private static void searchHash() {

        //Setamos o arquivo que vai ser lido
        try {
            Hash hash = new Hash();
            Properties propertiesBanco = new Properties();
            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
            propertiesBanco.load(fis);
            VerificaArquivo verifica = new VerificaArquivo();
            String rsHashInit = propertiesBanco.getProperty("hashInit");

            if (rsHashInit.equals("")) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar o arquivo de configuração, será criado um arquivo com configurações padrão,\n ainda será necessário configura-lo!", "ERRO: arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
                verifica.GravaHash();
                System.out.println("CAIU NO VAZIO");
            }
            if (!rsHashInit.equals("")) {
                //hashcombinada
                String hashInet = verifica.saveHash();
                String md5HashInet = hash.hashMD5(hashInet);

                if (md5HashInet.equals(rsHashInit)) {
                    System.out.println("iguais");
                    Inicia();
                } else {
                    //CHAMA TELA DE VALIDAÇÃO
                    FrmCodigo frmCodigo = new FrmCodigo();
                    frmCodigo.setVisible(true);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    private static void searchHash() {
//        
//        //Setamos o arquivo que vai ser lido
//
//        try {
//            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
//            propertiesBanco.load(fis);
//            VerificaArquivo verifica = new VerificaArquivo();
//            String rsHashInit = propertiesBanco.getProperty("hashInit");
//
//            if (rsHashInit.equals("")) {
//                JOptionPane.showMessageDialog(null, "Erro ao buscar o arquivo de configuração, será criado um arquivo com configurações padrão,\n ainda será necessário configura-lo!", "ERRO: arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
//                verifica.GravaHash();
//                System.out.println("CAIU NO VAZIO");
//            }
//            if (!rsHashInit.equals("")) {
//                //hashcombinada
//                String hashInet = verifica.saveHash();
//                if (hashInet.equals(rsHashInit)) {
//                    System.out.println("iguais");
//                    JOptionPane.showMessageDialog(null,"IGUAIS");
//                } else {
//                    System.out.println("diferentes");
//                    JOptionPane.showMessageDialog(null,"DIFERENTES");
//                    //CHAMA 
//                }
//            }
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Tray.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}// Fim do código.
