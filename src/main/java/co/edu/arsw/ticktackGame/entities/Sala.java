/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.entities;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.websocket.Session;

/**
 *
 * @author danip
 */
@Entity
public class Sala {
    @Id
    private String idSala;
   
    private String  matriz;    
    
    static Queue<Session> sessions = new ConcurrentLinkedQueue<>();
    
    private String Historial;

    public Sala(String idSala, String matriz) {
        this.idSala = idSala;
        this.matriz = matriz;
    }
    
    public void addSession (Session session){
        sessions.add(session);
    }

    
    
        
    public String getIdSala() {
        return idSala;
    }
    
    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(String matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        return "Sala{" + "idSala=" + idSala + ", matriz=" + matriz + '}';
    }
    
    
    
            
    
    
}
