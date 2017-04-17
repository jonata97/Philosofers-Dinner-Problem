/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jantar;

/**
 *
 * @author jonata
 */
public class Fork {
    
    public boolean used; 
    public String name;
    
    public Fork(String name) {
        this.name = name; 
    }
    
    //Synchronized methods can be called once at a time 
    public synchronized void take() {
       this.used = true;
    }
    
    public synchronized void release() {
        this.used = false; 
    }
}
