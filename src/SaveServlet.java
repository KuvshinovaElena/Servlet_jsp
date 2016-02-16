import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by HP on 18.11.2015.
 */
public class SaveServlet extends GenericServlet {
    PersonDao dao;
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        dao=new PersonDao();
        Map<String, String[]> params = request.getParameterMap();
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher=null;
        if ("edit".equals(request.getParameter("meth"))) {
            String id = params.get("id")[0];
            dao.updateToDb(params, id,0);
            dispatcher = context.getRequestDispatcher("/person?id=" + id + "&edit=false");
        }
        if("edittable".equals(request.getParameter("meth"))){
            String id;
            if (params.get("id")!=null) {
                int num = params.get("id").length;
                for (int i = 0; i < num; i++) {
                    id = params.get("id")[i];
                    dao.updateToDb(params, id,i);
                }
            }
                dispatcher = context.getRequestDispatcher("/table");
        }
        if ("bulkedit".equals(request.getParameter("meth"))){
            String id;
            if (params.get("id")!=null) {
                int num = params.get("id").length;
                for (int i = 0; i < num; i++) {
                    id = params.get("id")[i];
                    dao.updateToDb(params, id);
                }
            }
            dispatcher = context.getRequestDispatcher("/table");
        }
        if("add".equals(request.getParameter("meth"))){
            dao.updateToDb(params);
            dispatcher = context.getRequestDispatcher("/table");
        }
        if("del".equals(request.getParameter("meth"))) {
            String id = null;
            if (params.get("id") != null) {
                int num = params.get("id").length;
                for (int i = 0; i < num; i++) {
                    id = params.get("id")[i];
                    dao.updateToDb(id);
                }
            }
            dispatcher = context.getRequestDispatcher("/table");
        }
        if("clean".equals(request.getParameter("meth"))){
            dao.updateToDb();
            dispatcher = context.getRequestDispatcher("/table");
        }
        dispatcher.forward(request,response);
    }
}

