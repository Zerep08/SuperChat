/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import dao.ContactoDAO;
import dao.MensajeDAO;
import dao.UsuarioDAO;
import hbn.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.Mensaje;
import pojo.Usuario;




/**
 *
 * @author zerep08
 */
public class PruebaUsuario {
    
    public PruebaUsuario() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
 
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void hello() {       
        UsuarioDAO uDAO=new UsuarioDAO();
        uDAO.saveUsuario("nombre","3030", true,"ff","ss", false);
        MensajeDAO mDAO=new MensajeDAO();       
        JSONArray json = mDAO.getChatMessages(1, 4);
        System.out.println("json: "+json.toString());       
    }
}
