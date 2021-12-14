package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Vamos a trabajar la clase HttpSession
        /*
        Vamos a solicitar el objeto Session y en caso de que sea la primera 
        vez, se creara un objeto de tipo Session.
         */
        HttpSession session = request.getSession();

        //Ahora podemos recuperar la informacion de la sesion o agregar
        String titulo = null;

        Integer contadorVisitas = (Integer) session.getAttribute("contadorVisitas");

        //Si contadorVisitas es null => es la primera vez que accedemos
        if (contadorVisitas == null) {
            contadorVisitas = 1;
            titulo = "Bienvenido por primera vez";
        } else {
            contadorVisitas++;
            titulo = "Bienvenido de nuevo";
        }
        
        //Añadimos el atributo contador visitas a nuestro objeto sesion
        session.setAttribute("contadorVisitas", contadorVisitas);
        
        //y ahora mandamos la informacion del cliente
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(titulo);
        out.print("<br>");
        out.print("Numero de acceso al recurso: "+contadorVisitas );
        out.print("<br>");
        out.print("Identificador de sesión: "+session.getId() );
        out.close();
        
        

    }
}
