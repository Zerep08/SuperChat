/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ContactoDAO;
import dao.MensajeDAO;
import dao.UsuarioDAO;
import hbn.HibernateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import pojo.Contacto;
import pojo.Usuario;

/**
 *
 * @author zerep08
 */
@WebServlet(name = "Consultas", urlPatterns = {"/Consultas"})
public class Consultas extends HttpServlet {

    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UsuarioDAO uDAO = new UsuarioDAO();
            ContactoDAO conDAO = new ContactoDAO();
            MensajeDAO mDAO = new MensajeDAO();
            HttpSession session = request.getSession();
            //Obtener con ANGULAR
            JSONObject json = new JSONObject();
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            StringBuilder sb = new StringBuilder();
            
            
                BufferedReader br = request.getReader();
                String str = null;

                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
            JSONObject jObj = new JSONObject(sb.toString());
            //
            String tipo = jObj.getString("tipo");
            //String tipo = request.getParameter("tipo");
            System.out.println("Tipo: "+tipo);
            
            if(tipo.equals("contactos")){
                //idSession
                String s = session.getAttribute("user").toString();
                Integer id = Integer.valueOf(s);
                List<Usuario> contactos = conDAO.getAllContactsByUser(id);
                jsonObject.put("contactos", contactos);
                response.setContentType("application/json");
                out.print(jsonObject.toString());
                 out.close();
                this.destroy();
            }
            else if(tipo.equals("entrar")){
                String phone = jObj.getString("phone");
                String password = jObj.getString("password");
                Usuario sessionUser= uDAO.getPersonaByPhonePassword(phone, password);
                 if(sessionUser!=null){
                    int idU = sessionUser.getIdUsuario();
                    session.setAttribute("user", sessionUser.getIdUsuario());
                    json.put("result", false);
                    response.setContentType("application/json");
                    out.print(json.toString());
                 }else{
                    json.put("result", true);
                    response.setContentType("application/json");
                    out.print(json.toString());
                     out.close();
                    this.destroy();
                 }
            }
            else if(tipo.equals("registrar")){
                String phone = jObj.getString("celular");
                String nameUser = jObj.getString("nombre");
                String password = jObj.getString("password");
                //saveUser
                uDAO.saveUsuario(nameUser, phone, true, ".", password, true);
                json.put("result", true);
                response.setContentType("application/json");
                out.print(json.toString());
                 out.close();
                this.destroy();
            }
            else if(tipo.equals("obtenerId")){
                String u = session.getAttribute("user").toString();
                Integer id = Integer.valueOf(u);
                List<Usuario> user = uDAO.getUserById(id);
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("usuario", user);
                response.setContentType("application/json");
                out.print(jsonUser.toString());
                System.out.println(jsonUser.toString());
                 out.close();
                this.destroy();
            }
            else if(tipo.equals("cerrarSesion")){
                json.put("result", true);
                response.setContentType("application/json");
                out.print(json.toString());
                 out.close();
                this.destroy();
            }
            else if(tipo.equals("mensajes")){
                Integer idA = Integer.valueOf(jObj.getInt("idA"));
                Integer idB = Integer.valueOf(jObj.getInt("idB"));
                
                List<Usuario> user = uDAO.getUserById(idB);
                JSONObject jsonUser = new JSONObject();
                jsonArray = mDAO.getChatMessages(idA,idB);
                jsonUser.put("contacto", user);
                jsonUser.put("mensaje", jsonArray);
                
                System.out.println("json: "+jsonArray.toString()); 
                //Alfonso 30772827 3310482094
                //Mercedes Elis 251
                response.setContentType("application/json");
                out.print(jsonUser.toString());
                out.close();
                this.destroy();
            }
            else if(tipo.equals("enviarMensaje")){
                String mensaje = jObj.getString("mensaje");
                Integer idUsuarioA = Integer.valueOf(jObj.getInt("idA"));
                Integer idUsuarioB = Integer.valueOf(jObj.getInt("idB"));
                mDAO.saveMensaje(idUsuarioA, idUsuarioB, mensaje);
                
            }
        }
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
