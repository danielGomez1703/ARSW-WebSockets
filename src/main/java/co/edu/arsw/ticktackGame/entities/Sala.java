/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.entities;

import java.util.ArrayList;
import java.util.List;
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
     private String salaId;
     private List<String> squares;
     private int stepNumber;
     private boolean xIsNext;
     private String mensaje;
     private String [] mensajesR;
     private String [] mensajesS;
     
     public Sala (){}
     public Sala (String salaId){
         this.salaId=salaId;
         squares =new ArrayList<>();
         mensajesR = new String [100];
         mensajesS = new String [100];
         
         
     }
     
    /* history: [
                {
                    squares: Array(9).fill(null)
                }],
            stepNumber: 0,
            xIsNext: true,
            mensaje: "",
            mensajesR: [],
            mensajesS: []

        };*/

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }
     
     

    public List<String> getSquares() {
        return squares;
    }

    public void setSquares(List<String> squares) {
        this.squares = squares;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public boolean isxIsNext() {
        return xIsNext;
    }

    public void setxIsNext(boolean xIsNext) {
        this.xIsNext = xIsNext;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String[] getMensajesR() {
        return mensajesR;
    }

    public void setMensajesR(String[] mensajesR) {
        this.mensajesR = mensajesR;
    }

    public String[] getMensajesS() {
        return mensajesS;
    }

    public void setMensajesS(String[] mensajesS) {
        this.mensajesS = mensajesS;
    }

    @Override
    public String toString() {
        return "Sala{" + "salaId=" + salaId + ", stepNumber=" + stepNumber + ", xIsNext=" + xIsNext + '}';
    }

            
    
    
}
