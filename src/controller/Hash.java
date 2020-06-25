/*
 * Criado por Uedney Cristiano de Morais
 * Contato do desenvolvedor: uedneymorais@gmail.com (62)-991861075
 * Classe responsável por codificar e decodificar os hashies
 */
package controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Hash {

    String stringDecodificada;

    public String HashBase64(String texto) {
        String stringSimples = texto;
        String stringHash = Base64.getEncoder().encodeToString(stringSimples.getBytes());
        return stringHash;
    }

    public String HashBase64Decoded(String texto) {

        try {
            String stringHash = texto;
            byte[] hashDecodificado = Base64.getDecoder().decode(stringHash);
            stringDecodificada = new String(hashDecodificado);

        } catch (Exception e) {
            e.printStackTrace();
            return "erro";
        }
        return stringDecodificada;
    }

    public static String hashMD5(String hash) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hash.getBytes());
            byte[] b = md.digest();
            for (byte b1 : b) {
                sb.append(Integer.toHexString(b1 & 0xff).toString());
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public static String stringHexa(String hash) throws NoSuchAlgorithmException {
        String s = hash;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        //System.out.println("MD5: " + new BigInteger(1, m.digest()).toString(16));
        String hexaMD5 = new BigInteger(1, m.digest()).toString(16);
        return  hexaMD5;
    }

}
