/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;


/**
 *
 * @author pc
 */
public class EnviarEmail {

    public void preencher() {

    }

    public static int enviar(String assunto,String mensagem) {
        Hash hash = new Hash();

        try {     
            Properties properties = new Properties();
            //System.out.println("Isso vem da classe ENviar "+caminho+" - "+desc_arquivo+ " - "+nome_arquivo );
            //Setamos o arquivo que vai ser lido
            FileInputStream fis = new FileInputStream("C:\\BackupPostgresql\\config\\dados.properties");
            //metodo load faz a leitura atraves do objeto fis
            properties.load(fis);
            //Captura o valor da propriedade, atraves do nome da propriedade(Key           
            
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(properties.getProperty("host"));
            email.setSslSmtpPort(properties.getProperty("port"));
            email.setStartTLSRequired(true);
            email.setStartTLSEnabled(true);
            email.setSSLOnConnect(true);
            
            String senhaEmail = hash.HashBase64Decoded(properties.getProperty("senha"));
            
            if (senhaEmail=="erro") {
                JOptionPane.showMessageDialog(null,"Erro ao descodificar as senhas dos bancos de dados\n Por favor entre nas configurações,digite as senhas corretamente e salve!","ERRO: ",JOptionPane.ERROR_MESSAGE);
                properties.setProperty("senha", "MTIzNDU2");
            }else{
                email.setAuthenticator(new DefaultAuthenticator(properties.getProperty("email"), senhaEmail));
            try {
                email.setFrom(properties.getProperty("email"));
                //Email
                //email.setSubject(properties.getProperty("assunto"));
                email.setSubject(assunto);
                //email.setMsg(properties.getProperty("mensagemini")+" "+assunto+"/"+mensagem);
                email.setMsg(" "+mensagem);
                email.addTo(properties.getProperty("emailcontador"));

                //Anexando um arquivo
                //EmailAttachment attachment = new EmailAttachment();
                //attachment.setPath(caminho);
               //attachment.setDisposition(EmailAttachment.ATTACHMENT);
                //attachment.setDescription(desc_arquivo);
                //attachment.setName(nome_arquivo);
                //email.attach(attachment);
                email.send();
                return 0;
            } catch (EmailException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao enviar o email! \n ERRO: "+e+"\nVerifique a senha, \nAntivírus podem bloquear o envio de Email","ERRO",JOptionPane.ERROR_MESSAGE);
                return 1;
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;
    }

}
