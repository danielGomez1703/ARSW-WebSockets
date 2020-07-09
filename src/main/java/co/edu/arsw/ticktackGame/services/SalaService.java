/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.services;

import co.edu.arsw.ticktackGame.endpoints.ChatEndpoint;
import co.edu.arsw.ticktackGame.entities.Sala;
import co.edu.arsw.ticktackGame.repositories.SalaRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 *
 * @author danip
 */
@Component
public class SalaService {

    private static final Logger logger = Logger.getLogger(SalaService.class.getName());

    @Autowired
    private SalaRepository salaRepository;

    public void SaveSala(String id) {
        try {
            Sala newSala = new Sala(id);
            salaRepository.save(newSala);
        } catch (Exception e) {
            logger.log(Level.INFO, e.toString());
        }
    }

}
