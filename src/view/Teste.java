/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Exec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author pc
 */
public class Teste {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Exec exec = new Exec();
        exec.mainVaccum();
        exec.mainReindex();
        exec.mainBackup();

    }

}
