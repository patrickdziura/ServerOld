/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author patri
 */
@Path("/RestLogin")
public class RestLogin {
    final static String url = "jdbc:mysql://localhost:3306/android?useSSL=false";
    final static String user = "root";
    final static String pass = "root";
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormParam("login") String login, @FormParam("senha") String senha) throws SQLException {
        String result = "false";
                        Usuario u = new Usuario();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("SELECT login_usuario, senha_usuario FROM android.tb_usuario WHERE login_usuario = ? AND senha_usuario = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                u.setLogin(rs.getString("login_usuario"));
                u.setSenha(rs.getString("senha_usuario"));                
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (u.getLogin() != null) {
        
        Gson gson = new Gson();
        String jsonO;
        jsonO = gson.toJson(u);
            System.out.println(jsonO);
            result = jsonO;
        } else {
            System.out.println("Nao deu");
        }
        return result;
    }

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestLogin
     */
    public RestLogin() {
    }

    /**
     * Retrieves representation of an instance of ws.RestLogin
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RestLogin
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
