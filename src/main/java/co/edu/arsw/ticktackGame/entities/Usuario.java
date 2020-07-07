/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.entities;

import javax.persistence.Entity;

/**
 *
 * @author danip
 */

@Entity
public class Usuario {
    
    private String apodo;
    private Sala sala;

    public String getNombre() {
        return apodo;
    }

    public void setNombre(String apodo) {
        this.apodo = apodo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    
    
}
