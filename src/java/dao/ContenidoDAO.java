/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import hbn.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Contenido;
import pojo.Mensaje;
import pojo.Usuario;

/**
 *
 * @author Yhair
 */
public class ContenidoDAO {
   Session session;
    
    public ContenidoDAO(){
        session=HibernateUtil.getLocalSession();
    }
    
    public  Mensaje getContenidoById(int id){
        return (Mensaje)session.load(Mensaje.class, id);
    }
    
  public boolean saveContenido(String texto,String emoji){
        Contenido contenidoDeTanque=new Contenido();
        contenidoDeTanque.setEmoji(emoji);
        contenidoDeTanque.setTexto(texto);
        try{
            Transaction transaccion=session.beginTransaction();
            session.save(contenidoDeTanque);
            transaccion.commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
        }
        return true;
}
    public void close(){
        HibernateUtil.closeLocalSession();  
    }
    
}
