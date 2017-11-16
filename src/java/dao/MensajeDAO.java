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
 * @author RigoBono
 */
public class MensajeDAO {
    Session session;
    
    public MensajeDAO(){
        session=HibernateUtil.getLocalSession();
    }
    
    public  Mensaje getMensajeById(int id){
        return (Mensaje)session.load(Mensaje.class, id);
    }
    
  public boolean saveMensaje(Contenido contenido,Usuario usuario){
        Mensaje mensajeDeTanque=new Mensaje();
        mensajeDeTanque.setDt_enviado(new Date());
        mensajeDeTanque.setEnviado(true);
        mensajeDeTanque.setIdContenido(contenido);
        mensajeDeTanque.setIdUsuario(usuario);
        try{
            Transaction transaccion=session.beginTransaction();
            session.save(mensajeDeTanque);
            transaccion.commit();
            return true;
        }catch(Exception e){
            
        }finally{
            
        }
        return true;
    }
}