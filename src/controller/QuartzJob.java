/*
 * Criado por Uedney Cristiano de Morais
 * Contato do desenvolvedor: uedneymorais@gmail.com (62)-991861075
 * Classe responsável por executar o Job
 */
package controller;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author pc
 */
public class QuartzJob  implements Job{
int n;

        EnviarEmail email = new EnviarEmail();
        Backup backup = new Backup();
        

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
       
        Exec exec = new Exec();
        
        exec.mainVaccum();
        exec.mainReindex();
        exec.mainBackup();
        
    }
  
}
