/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hbn.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Contacto;
import pojo.Usuario;

/**
 *
 * @author Yhair
 */
public class ContactoDAO {
    
    Session session;
    
    public ContactoDAO(){
        this.session=HibernateUtil.getLocalSession();
    }
    
    public ContactoDAO(Session session){
        this.session=session;
    }
    
    public void createContacto(Usuario a,Usuario b){
        Contacto contacto=new Contacto();
        contacto.setIdUsuarioA(a);
        contacto.setIdUsuarioB(b);
        try{
            Transaction t=session.beginTransaction();
            session.save(contacto);
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
            
            
    public void close(){
        HibernateUtil.closeLocalSession();  
    }
    
}
