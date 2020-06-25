package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Crontab {

    public static String retCron() {

        String cronhorasFim = "";

        try {
            Properties propertiesBanco = new Properties();
           
            //Setamos o arquivo que vai ser lido
            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados-banco.properties");
            //metodo load faz a leitura atraves do objeto fis
            propertiesBanco.load(fis);

            String j00 = propertiesBanco.getProperty("j00");
            String j01 = propertiesBanco.getProperty("j01");
            String j02 = propertiesBanco.getProperty("j02");
            String j03 = propertiesBanco.getProperty("j03");
            String j04 = propertiesBanco.getProperty("j04");
            String j05 = propertiesBanco.getProperty("j05");
            String j06 = propertiesBanco.getProperty("j06");
            String j07 = propertiesBanco.getProperty("j07");
            String j08 = propertiesBanco.getProperty("j08");
            String j09 = propertiesBanco.getProperty("j09");
            String j10 = propertiesBanco.getProperty("j10");
            String j11 = propertiesBanco.getProperty("j11");
            String j12 = propertiesBanco.getProperty("j12");
            String j13 = propertiesBanco.getProperty("j13");
            String j14 = propertiesBanco.getProperty("j14");
            String j15 = propertiesBanco.getProperty("j15");
            String j16 = propertiesBanco.getProperty("j16");
            String j17 = propertiesBanco.getProperty("j17");
            String j18 = propertiesBanco.getProperty("j18");
            String j19 = propertiesBanco.getProperty("j19");
            String j20 = propertiesBanco.getProperty("j20");
            String j21 = propertiesBanco.getProperty("j21");
            String j22 = propertiesBanco.getProperty("j22");
            String j23 = propertiesBanco.getProperty("j23");

            String d0 = "";
            String d1 = "";
            String d2 = "";
            String d3 = "";
            String d4 = "";
            String d5 = "";
            String d6 = "";
            String d7 = "";
            String d8 = "";
            String d9 = "";
            String d10 = "";
            String d11 = "";
            String d12 = "";
            String d13 = "";
            String d14 = "";
            String d15 = "";
            String d16 = "";
            String d17 = "";
            String d18 = "";
            String d19 = "";
            String d20 = "";
            String d21 = "";
            String d22 = "";
            String d23 = "";

            if (j00.equals("true")) {
                d0 = ",00";
            }
            if (j01.equals("true")) {
                d1 = ",01";
            }
            if (j02.equals("true")) {
                d2 = ",02";
            }
            if (j03.equals("true")) {
                d3 = ",03";
            }
            if (j04.equals("true")) {
                d4 = ",04";
            }
            if (j05.equals("true")) {
                d5 = ",05";
            }
            if (j06.equals("true")) {
                d6 = ",06";
            }
            if (j07.equals("true")) {
                d7 = ",07";
            }
            if (j08.equals("true")) {
                d8 = ",08";
            }
            if (j09.equals("true")) {
                d9 = "09";
            }
            if (j10.equals("true")) {
                d10 = ",10";
            }
            if (j11.equals("true")) {
                d11 = ",11";
            }
            if (j12.equals("true")) {
                d12 = ",12";
            }
            if (j13.equals("true")) {
                d13 = ",13";
            }
            if (j14.equals("true")) {
                d14 = ",14";
            }
            if (j15.equals("true")) {
                d15 = ",15";
            }
            if (j16.equals("true")) {
                d16 = ",16";
            }
            if (j17.equals("true")) {
                d17 = ",17";
            }
            if (j18.equals("true")) {
                d18 = ",18";
            }
            if (j19.equals("true")) {
                d19 = ",19";
            }
            if (j20.equals("true")) {
                d20 = ",20";
            }
            if (j21.equals("true")) {
                d21 = ",21";
            }
            if (j22.equals("true")) {
                d22 = ",22";
            }
            if (j23.equals("true")) {
                d23 = ",23";
            }

            String variavelfim = d0 + d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + d10 + d11 + d12 + d13 + d14 + d15 + d16 + d17 + d18 + d19 + d20 + d21 + d22 + d23;

            String cronHoras = "0 0 " + variavelfim + " ? * *";
            cronhorasFim = cronHoras;
            //return cronhorasFim;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cronhorasFim;
    }

}
