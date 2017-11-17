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
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public JSONArray getChatMessages(int indexUser, int indexContact) {
        Usuario usuario1 = (Usuario) session.load(Usuario.class, indexUser);
        Usuario usuario2 = (Usuario) session.load(Usuario.class, indexContact);
        Criterion rest1 = Restrictions.and(Restrictions.eq("idContacto1", usuario2),
                Restrictions.eq("idUsuario1", usuario1));
        Criterion rest2 = Restrictions.and(Restrictions.eq("idUsuario1", usuario2),
                Restrictions.eq("idContacto1", usuario1));
        List<Mensaje> listaDeMensajes = (List<Mensaje>) session.createCriteria(Mensaje.class)
                .add(Restrictions.or(rest1, rest2)).list();        
        Gson gson = new Gson();
        List<Contenido> lista
                = (List<Contenido>)session.createCriteria(Mensaje.class).add(Restrictions.or(rest1, rest2))
                        .setProjection(Projections.projectionList()
                                .add(Projections.property("idContenido")
                              )).list();
       
        
        JSONArray json=new JSONArray(lista);
        
        String ids=gson.toJson(lista);
        System.out.println("json perron:"+ ids);
        System.out.println("La lista es: " + listaDeMensajes);

        return json;
    }

    public void close() {
        HibernateUtil.closeLocalSession();
    }

}
