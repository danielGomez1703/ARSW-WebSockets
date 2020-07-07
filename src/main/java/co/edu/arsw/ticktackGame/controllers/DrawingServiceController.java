/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.controllers;

/**
 *
 * @author danip
 */
import co.edu.arsw.ticktackGame.entities.Sala;
import co.edu.arsw.ticktackGame.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrawingServiceController {

    @Autowired
    private SalaRepository Salarepository;

    @RequestMapping(value = "/salas/{id}", method = GET)
    @ResponseBody
    public String createSala(@PathVariable("id") String id) {

        System.out.println("debe crear la sala num: " + id);
        try {
            Sala newSala = new Sala(id, "");
            Salarepository.save(newSala);
            for (Sala sala : Salarepository.findAll()) {
                System.out.println(sala);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/index.html";
    }

    @GetMapping("/status")
    public String status() {
        return "{\"status\":\"Greetings from Spring Boot. "
                + java.time.LocalDate.now() + ", "
                + java.time.LocalTime.now()
                + ". " + "The server is Runnig!\"}";
    }
}
