/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.repositories;

import co.edu.arsw.ticktackGame.entities.Sala;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author danip
 */
@Component
public interface SalaRepository extends MongoRepository < Sala , String >{


    //public Sala save (Sala s);


    public Sala findBySalaId(String id);

    //public Iterable<Sala> findByMatriz(String matriz);
}
