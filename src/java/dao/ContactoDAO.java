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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    
    public int getIdContactByUsers (int a, int b){
        Usuario usuario11 = (Usuario) session.load(Usuario.class, a);
        Usuario usuario22 = (Usuario) session.load(Usuario.class, b);
        Criterion rest11 = Restrictions.and(Restrictions.eq("idUsuarioA", usuario22),
                Restrictions.eq("idUsuarioB", usuario11));
        Criterion rest22 = Restrictions.and(Restrictions.eq("idUsuarioB", usuario22),
                Restrictions.eq("idUsuarioA", usuario11));

        //obtiene id de contacto
        List<Contacto> contacto = (List<Contacto>) session.createCriteria(Contacto.class)
                .add(Restrictions.or(rest11, rest22)).list();
        Contacto c = contacto.get(0);
        return c.getIdContacto();
    }
            
    public List<Usuario> getAllContactsByUser(int id){
        Usuario a=(Usuario)session.load(Usuario.class,id);
        return (List<Usuario>) this.session.createCriteria(Contacto.class)
                .add(Restrictions.eq("idUsuarioA", a))
                .setProjection(Projections.projectionList()
                .add(Projections.property("idUsuarioB")))
                .list();
    }
    
    public void close(){
        HibernateUtil.closeLocalSession();  
    }
    
}
