/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar;

import static java.lang.Double.max;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jonata
 */
public class Philosofer implements Runnable {
    // Statistics variables
    private int timesEat;
    private int timesThink; 
    private int failedToGetFork;
  
    // Fork Variables
    private Fork leftFork;
    private Fork rightFork;
    
    // Philosofer Variables 
    // State : 2 = Eat, 1 = Think
    private String name;
    private int state; 
    
    // Init
    public Philosofer (String name, Fork left, Fork right) {
        this.state = 1;
        this.name = name; 
        this.leftFork = left;
        this.rightFork = right;
        
    }
    
    public int getFailedTimes() {
        return this.failedToGetFork;
    }
    
    public int getEatTimes() {
        return this.timesEat;
    }
    
    public int getThinkTimes() {
        return this.timesThink;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void eat() throws InterruptedException {
        
        if (!leftFork.used) {
            if (!rightFork.used) {
                
                leftFork.take();
                rightFork.take();
                
                this.state = 2;
                System.out.println("Philosofer "+this.name+" is eating");
                timesEat++;
                Thread.sleep(2000);
                
                leftFork.release();
                rightFork.release();

                this.think();
            }
        } else {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 2999 + 1);
            Thread.sleep(randomNum);
            failedToGetFork++;
        }
        
    }
    
    public void think() throws InterruptedException {
        this.state = 1;
        System.out.println("Philosofer "+this.name+" is thinking");
        timesThink++;
        Thread.sleep(5000);               
    }
    
    
    
    
    @Override
    public void run() {
        
        while (!Thread.interrupted()) {
            try {
                eat();
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosofer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
}
