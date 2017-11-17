/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

//import Servicios.Click;
//import com.byteslounge.websockets.ServletAwareConfig;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websock",configurator=ServletAwareConfig.class)
public class YourSocket {

    private EndpointConfig config;
    public static String nombre;
    
    
/*
    @OnOpen
    public void onOpen(Session websocketSession, EndpointConfig config) {
        this.config = config;
    }

    @OnMessage
    public void onMessage(String message) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        ServletContext servletContext = httpSession.getServletContext();
        // ...
    }*/
    
    

    

   
  public static Set<Session> clients = 
    Collections.synchronizedSet(new HashSet<Session>());
  
  public static Map<String,Session> clientsMap = 
    Collections.synchronizedMap(new HashMap<String,Session>());
  
  
    private Object RequestFilter;
    private Object HttpSessionLocal;
  
  @OnMessage
  public void onMessage(String message, Session session) 
    throws IOException {
      /*
      HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
      String user=(String)httpSession.getAttribute("user");
      System.out.println("useronmessageservlet"+user);*/
      
      System.out.println("mensaje: "+message);      
      String[] mensajes=message.split("&");
      
          
      if(mensajes[1].equals("null")&&mensajes[2].equals("null")){
          clientsMap.put(mensajes[0], session);   
          return;        
      }else{    
          System.out.println("tamano:"+clientsMap.size());
         Session toSend=clientsMap.get(mensajes[1]);         
         toSend.getBasicRemote().sendText(message);        
          }
      
      
     ///////////// 
  /*    
HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
//httpSession.setAttribute("user", message);

String user=(String)httpSession.getAttribute("user");

      System.out.println("useronmessage"+user);
      System.out.println("idusuario"+Click.idUsuario.get());
      
    synchronized(clients){
      // Iterate over the connected sessions
      // and broadcast the received message
      for(Session client : clients){
        if (!client.equals(session)){
            
          client.getBasicRemote().sendText(message);
          
          
        }
      }
    }*/
    
  }
  
  @OnOpen
  public void onOpen (Session session, EndpointConfig config) {
  // Add session to the connected sessions set
    // HttpSession m=session.getHttpSession();
    this.config = config;
    HttpSession httpSession= (HttpSession) config.getUserProperties().get("httpSession");
    String user=(String)httpSession.getAttribute("user");
      System.out.println("idsocket:"+session.getId());
    nombre=user;
      System.out.println("useronopen:"+user);
   clients.add(session);
    System.out.println("open session");
  }

  @OnClose
  public void onClose (Session session) {
    // Remove session from the connected sessions set
    clients.remove(session);
    
    clientsMap.values().remove(session);
    
  }


}