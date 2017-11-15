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
 * @author Francisco
 */
public class UsuarioDAO {
    Session session;
    
    public UsuarioDAO(){
        session = HibernateUtil.getLocalSession();
    }
    
    public Session getSession(){
        return this.session;
    }
    
    public void saveUsuario(String nombre, String telefono, boolean conectado, String fotografia, String contrasena, boolean eliminado){
    Usuario usuario = new Usuario();
    
    usuario.setNombre(nombre);
    usuario.setTelefono(telefono);
    usuario.setConectado(conectado);
    usuario.setFotografia(fotografia);
    usuario.setEliminado(eliminado);
    usuario.setContrasena(contrasena);
    

    
    try{
        
        Transaction transaction = session.beginTransaction();
        session.save(usuario);
        
        transaction.commit();
    }catch(Exception e){
         e.printStackTrace();
    }
    }
    
    public void close(){
        HibernateUtil.closeLocalSession();  
    }
}
