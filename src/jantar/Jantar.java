/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import jantar.Philosofer;
/**
 *
 * @author jonata
 */
public class Jantar {

    /* 
    PROBLEMS:
    
    Algumas threads especificas estão em starvation
    
    
    - Acho que o problema está nos if's encadeados no Philosofer
    
    */
    
    
    public static void main(String[] args) throws InterruptedException {
        
        //Threads
        Thread[] threads;
        
        // Scanner
        Scanner scan = new Scanner (System.in);
        
        
        // Setting number of philosofers
        System.out.println("Digite o número de filosófos:");
        int numberOfPhilosofers = scan.nextInt();
        Philosofer [] philosofers = new Philosofer[numberOfPhilosofers];    
        
        //Threads: 
        threads = new Thread[numberOfPhilosofers];
        
        // WARNING: treat in case numberOfPhilosofers = 2
        Fork [] forks = new Fork[numberOfPhilosofers];
        
        
        //
        System.out.println("Digite o tempo de execução (em segundos)");
        int maxExecutionTimeInSeconds = scan.nextInt();
        
        for(int i=0; i< forks.length; i++){
            forks[i] = new Fork("C: "+i);
	} 
       
        for (int i =0; i<philosofers.length;i++) {
            String name = "Philosofer: "+i;
            int leftFork = i;
            int rightFork = i-1; 
            
            if (rightFork == -1) {
                rightFork = philosofers.length-1;
            } 
          
            
            philosofers[i] = new Philosofer(name, forks[leftFork], forks[rightFork]);
            System.out.println("Creating " + philosofers[i].getName() + " Left: " + leftFork + " Rigth: " + rightFork);
         }
        
        // Starting Threads
        for (Philosofer philosofer : philosofers) {
            int i = 0;
            threads[i] = new Thread(philosofer);
            threads[i].start();
            i++;
        }
        
        
        // Waits execution time
        Thread.sleep(maxExecutionTimeInSeconds * 1000);

        
        //report
        for (Philosofer philosofer : philosofers) {
            System.out.println(philosofer.getName() + " Think: " + philosofer.getThinkTimes() + " Eat: " + philosofer.getEatTimes() + " Failed: " + philosofer.getFailedTimes());
        }
        
        System.exit(0);
    }
    
}
