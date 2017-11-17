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
import pojo.Contacto;
import pojo.Contenido;
import pojo.Mensaje;
import pojo.Usuario;

/**
 *
 * @author RigoBono
 */
public class MensajeDAO {

    Session session;

    public MensajeDAO() {
        session = HibernateUtil.getLocalSession();
    }

    public Mensaje getMensajeById(int id) {
        return (Mensaje) session.load(Mensaje.class, id);
    }

    public boolean saveMensaje(int idContenido, int idUsuario, int idContacto) {
        Mensaje mensajeDeTanque = new Mensaje();
        Usuario user = (Usuario)session.load(Usuario.class, idUsuario);
        Contenido cont = (Contenido)session.load(Contenido.class, idContenido);
        Contacto contacto = (Contacto)session.load(Contacto.class, idContacto);
        mensajeDeTanque.setDt_enviado(new Date());
        mensajeDeTanque.setDt_recibido(new Date());
        mensajeDeTanque.setDt_leido(new Date());
        mensajeDeTanque.setEnviado(true);
        mensajeDeTanque.setRecibido(true);
        mensajeDeTanque.setLeido(true);
        mensajeDeTanque.setIdContenido(cont);
        mensajeDeTanque.setIdUsuario(user);
        mensajeDeTanque.setIdContacto(contacto);
        try {
            Transaction transaccion = session.beginTransaction();
            session.save(mensajeDeTanque);
            transaccion.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }
        return true;
    }

    public void close() {
        HibernateUtil.closeLocalSession();
    }
}
