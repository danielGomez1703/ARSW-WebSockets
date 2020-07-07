package co.edu.arsw.ticktackGame;

import co.edu.arsw.ticktackGame.entities.Sala;
import co.edu.arsw.ticktackGame.repositories.SalaRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicktackGameApplication  {

    @Autowired
    private SalaRepository repository;

    public static void main(String[] args) {
        
        SpringApplication app = new SpringApplication(TicktackGameApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.data.mongodb.uri", "mongodb+srv://arsw:arsw1234@cluster0.w7gdd.mongodb.net/arswdb?retryWrites=true&w=majority"));
         app.setDefaultProperties(Collections.singletonMap("server.port", getPort()));
        app.run(args);
    }

    /*@Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Sala("1", "Smith"));
        repository.save(new Sala("2", "Smith"));
        repository.save(new Sala("3", "andres"));
        repository.save(new Sala("4", "camilo"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Sala sala : repository.findAll()) {
            System.out.println(sala);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with find by id de sala('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByIdSala("1"));

        System.out.println("find by jugadas('Smith'):");
        System.out.println("--------------------------------");
        for (Sala sala : repository.findAll()) {
            System.out.println(sala);
        }

    }*/

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080; //returns default port if heroku-port isn't set (i.e. onlocalhost)
    }

}
