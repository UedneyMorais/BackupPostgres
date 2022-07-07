package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Backup {

    public static void realizaBackup(String dirPostgres, String Ip, String Port, String Base, String User, String dirBackup, String Senha, String formato) throws IOException, InterruptedException {
        EnviarEmail email = new EnviarEmail();
        DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");
        DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dtFormatada = dateFormat.format(date);
        String dtFormatada2 = dateFormat2.format(date);
        final List<String> comandos = new ArrayList<String>();
        //String dir = "C:\\BackupPostgresql";  
        //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\9.4\\bin\\pg_dump.exe"); //cecom
        //comandos.add("C:\\Arquivos de programas\\PostgreSQL\\9.2\\bin\\pg_dump.exe"); 
        //pg_dump.exe  -Z9 --host localhost --port 5432 --username postgres --format custom --blobs --encoding LATIN1 --verbose --file C:\Enviar sgemp
        comandos.add(dirPostgres + "pg_dump.exe");

        //comandos.add("-i");      
        comandos.add("-h");
        comandos.add(Ip);
        //comandos.add("192.168.0.25");
        comandos.add("--port");
        comandos.add(Port);
        comandos.add("-U");
        comandos.add(User);
        comandos.add("--format=custom");
        comandos.add("-b");
        comandos.add("--encoding=LATIN1");
        comandos.add("-v");
        comandos.add("--file");

        //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+(Character.getNumericValue(recebe)+1)+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add(dirBackup + "BKP_" + Base + dtFormatada + formato);   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add(Base);
        ProcessBuilder pb = new ProcessBuilder(comandos);

        pb.environment().put("PGPASSWORD", "123456");
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados.properties");
        try {
            
            properties.load(fis);
            final Process process = pb.start();

            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            int result = 1;
            String res[] = {line};
            while (line != null) {
                System.out.println(line);
                result++;
                line = r.readLine();
            }
            r.close();

            if (result == 2 || result == 3) {
                email.enviar(properties.getProperty("assunto")+" ERRO", "Ocorreu um erro ao criar o backup \n ERRO: " + res[0] + "\n Data e Hora do erro: " + dtFormatada2);
            } else if (result >= 4) {
                email.enviar(properties.getProperty("assunto"), properties.getProperty("mensagemfim")+"\n Base : " + Base + "\n Data e Hora do término: " + dtFormatada2);
            }
            process.waitFor();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
            email.enviar(properties.getProperty("assunto")+" ERRO", "Ocorreu um erro ao criar o backup \n ERRO: IOException \n " + e);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            System.out.println("InterruptedException " + ie);
            email.enviar(properties.getProperty("assunto")+" ERRO", "Ocorreu um erro ao criar o backup \n ERRO: InterruptedException \n " + ie);
        }
    }

}
