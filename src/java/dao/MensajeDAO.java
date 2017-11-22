/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import hbn.HibernateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Contacto;
import pojo.Contenido;
import pojo.Mensaje;
import pojo.Usuario;

/**
 *
 * @author Yhair
 */
public class MensajeDAO {

    Session session;
    public MensajeDAO() {
        session = HibernateUtil.getLocalSession();
    }

    public Session getSession() {
        return this.session;
    }

    /**
     * Funcion para obtener los ids de los mensajes entre 2 usuarios
     * @param indexUser
     * @param indexContact
     * @return JSONArray
     */
    
    public boolean saveMensaje(int idUsuarioA,int idUsuarioB, String texto/* String emojio*/) {
        ContenidoDAO cDAO = new ContenidoDAO();
        ContactoDAO conDAO = new ContactoDAO();
        cDAO.saveContenido(texto, "");
        int idContenido = cDAO.getLastContent();
        int idContacto = conDAO.getIdContactByUsers(idUsuarioA, idUsuarioB);
        Mensaje mensajeDeTanque = new Mensaje();
        Usuario user = (Usuario)session.load(Usuario.class, idUsuarioA);
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
    
    public JSONArray getChatMessages(int indexUser, int indexContact) {
        Usuario usuario11 = (Usuario) session.load(Usuario.class, indexUser);
        Usuario usuario22 = (Usuario) session.load(Usuario.class, indexContact);
        Criterion rest11 = Restrictions.and(Restrictions.eq("idUsuarioA", usuario22),
                Restrictions.eq("idUsuarioB", usuario11));
        Criterion rest22 = Restrictions.and(Restrictions.eq("idUsuarioB", usuario22),
                Restrictions.eq("idUsuarioA", usuario11));

        //obtiene id de contacto
        List<Contacto> contacto = (List<Contacto>) session.createCriteria(Contacto.class)
                .add(Restrictions.or(rest11, rest22)).list();
        Contacto c = contacto.get(0);
        int idContacto = c.getIdContacto();
        ////obtiene id de contacto
        Contacto contacto1 = (Contacto) session.load(Contacto.class, idContacto);
        Criterion contactousuario = Restrictions.and(Restrictions.eq("idUsuario", usuario11),
                Restrictions.eq("idContacto", contacto1));
        Criterion contactousuario1 = Restrictions.and(Restrictions.eq("idUsuario", usuario22),
                Restrictions.eq("idContacto", contacto1));
        List<Contenido> contenidos = (List<Contenido>) session.createCriteria(Mensaje.class)
                .add(Restrictions.or(contactousuario, contactousuario1))
                .setProjection(Projections.projectionList()
                        .add(Projections.property("idUsuario.idUsuario"))
                        .add(Projections.property("dt_enviado"))
                        .add(Projections.property("idContenido"))
                ).list();
        JSONArray array2 = new JSONArray();
        for (Object cpro : contenidos) {
            JSONArray aux = new JSONArray(cpro);
            JSONObject pro = new JSONObject();
            pro.put("idUsuario", aux.get(0));
            pro.put("horaEnviado", aux.get(1));
            pro.put("mensaje", aux.getJSONObject(2));
            array2.put(pro);
        }
        return array2;
    }

    public void close() {
        HibernateUtil.closeLocalSession();
    }

}
