/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.arsw.ticktackGame.endpoints;

/**
 *
 * @author danip
 */
import co.edu.arsw.ticktackGame.entities.Sala;
import co.edu.arsw.ticktackGame.repositories.SalaRepository;
import co.edu.arsw.ticktackGame.services.SalaService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/bbService/{room}")
public class ChatEndpoint {
    
    @Autowired
    private SalaRepository salaService;

    
    private static final Logger logger = Logger.getLogger(ChatEndpoint.class.getName());
     //Queue for all open WebSocket sessions 
    static Queue<Session> queue = new ConcurrentLinkedQueue<>();
    Session ownSession = null;

    //Call this method to send a message to all clients
    
    public void send(String msg, final String room) {
        System.out.println("estoy enviando un mensaje en la sala: " + room);
        try {
            /* Send updates to all open WebSocket sessions */
            for (Session session : queue) {
                if ((!session.equals(this.ownSession) && (session.getUserProperties().get("room").equals(room)))){
                    session.getBasicRemote().sendText(msg);
                }
                logger.log(Level.INFO, "Sent: {0}", msg);
            }
        } catch (IOException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @OnMessage
    public void processPoint(String message, Session session, @PathParam("room") String room) {
        logger.log(Level.INFO,"Point received:" + message + ". From session: "
                + session);
       // Sala newsala = new Gson().fromJson(message, Sala.class);
        //Sala oldsala= salaService.findBySalaId(room);
       // salaService.delete(oldsala);
       // salaService.save(newsala);
        //System.out.println(newsala);
        this.send(message,room);
    }

    @OnOpen
    public void openConnection(Session session,@PathParam("room") final String room) {
        /* crea la sala y guarda el numero en la session*/
        session.getUserProperties().put("room",room);
        queue.add(session);
        ownSession = session;
        logger.log(Level.INFO, "Connection opened.");
        try {
            session.getBasicRemote().sendText("Connection established.");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Remove this connection from the queue */
        queue.remove(session);
        logger.log(Level.INFO, "Connection closed.");
    }

    @OnError
    public void error(Session session, Throwable t) {
        /* Remove this connection from the queue */
        queue.remove(session);
        logger.log(Level.INFO, t.toString());
        logger.log(Level.INFO, "Connection error.");
    }
}
