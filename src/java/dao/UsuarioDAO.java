/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hbn.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    //Devuelve todos los usuarios
    public List<Usuario> getAllUsers(){
        return (List<Usuario>) this.session.createCriteria(Usuario.class).list();
    }
    
    public List<Usuario> getAllUsersByName(){
        return (List<Usuario>) this.session.createCriteria(Usuario.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("nombre"))
                ).list();
    }
    
    public  Usuario getPersonaByPhonePassword(String phone, String password){
        //Retorna
        Usuario listaDePersonas=(Usuario)
                session.createCriteria(Usuario.class)
                .add(Restrictions.eq("telefono", phone))
                .add(Restrictions.eq("contrasena", password)).uniqueResult();
        return listaDePersonas;
    }
    
    public  List<Usuario> getUserById(int id){
        //Retorna
        return (List<Usuario>) this.session.createCriteria(Usuario.class)
                .add(Restrictions.eq("idUsuario", id))
                .list();
    }
    
    public void close(){
        HibernateUtil.closeLocalSession();  
    }
}
