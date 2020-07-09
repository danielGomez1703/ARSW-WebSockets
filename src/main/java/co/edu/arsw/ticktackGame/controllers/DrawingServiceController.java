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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/salas")
public class DrawingServiceController {

    @Autowired
    private SalaRepository Salarepository;

    @RequestMapping(value = "/{id}", method = POST)
    @ResponseBody
    public void createSala(@PathVariable(value = "id") String id) {
        System.out.println("debe crear la sala num: " + id);
        try {
            Sala newSala = new Sala(id);
            Salarepository.save(newSala);
            for (Sala sala : Salarepository.findAll()) {
                System.out.println(sala);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/disp/{id}", method = GET)
    public boolean JoinSala(@PathVariable(value = "id") String id) {
        System.out.println("debe unirse a la sala : " + id);
        try {
            Sala sala = Salarepository.findBySalaId(id);
            System.out.println(sala);
            if (sala != null) {
                ObjectMapper obj = new ObjectMapper();
                String jsonStr = obj.writeValueAsString(sala);
              //  System.out.println(jsonStr);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    @RequestMapping(value = "/update/{id}", method = POST ) 

    public String UpdateSala(@RequestParam(value = "estado", defaultValue = "anonymous") String estado, @PathVariable(value = "id") String id) {
      //  System.out.println("--------------------------------------Actualizar------------------------------------");
       // System.out.println("debe actualizar  la sala : " + id);
        Sala newsala = new Gson().fromJson(estado, Sala.class);
        try {
            Sala sala = Salarepository.findBySalaId(id);
            Salarepository.deleteBySalaId(id);
            Salarepository.save(newsala); 
            ObjectMapper obj = new ObjectMapper();
            String jsonStr = obj.writeValueAsString(sala);
         //   System.out.println(jsonStr);
            return jsonStr;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @GetMapping("/status")
    public String status() {
        return "{\"status\":\"Greetings from Spring Boot. "
                + java.time.LocalDate.now() + ", "
                + java.time.LocalTime.now()
                + ". " + "The server is Runnig!\"}";
    }
}
