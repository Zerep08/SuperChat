/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ContactoDAO;
import dao.MensajeDAO;
import dao.UsuarioDAO;
import hbn.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
    Session s=HibernateUtil.getLocalSession();
    Usuario a=(Usuario)s.load(Usuario.class, 1);
    Usuario b=(Usuario)s.load(Usuario.class, 2);
    
    ContactoDAO cDAO=new ContactoDAO(s);
    cDAO.createContacto(a, b);
    cDAO.close();
    
 
    
    }
}
