/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author danip
 */
@Entity
public class HistorialSala {
    @Id
    private Sala sala;
    @Id
    private int numJugada;
    private int [][][] historial;
    
    
}
