import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HP on 18.11.2015.
 */
public class ParCtrlServlet extends HttpServlet {
    private ServletConfig config;
    private PersonDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        dao = new PersonDao();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        sendParamForm(request, response);
    }

    private void sendParamForm(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        if ("true".equals(request.getParameter("add"))){
            sendAddForm(response,request);
        }
        if ("true".equals(request.getParameter("edit"))) {
            sendEditForm(request, response);
        } else {
            if ("false".equals(request.getParameter("edit"))) {
                sendViewForm(request, response);
            }
        }
        if ("true".equals(request.getParameter("del"))) {
            checkDel(request,response);
        }
        if("true".equals(request.getParameter("edittable"))){
            sendEditTable(response,request);
        }
        if ("true".equals(request.getParameter("bulkedit"))){
            sendBulkEdit(request,response);
        }
    }

    private void sendViewForm(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Map<String, String> params = dao.getFromDb(Integer.parseInt(request.getParameter("id")));
        response.setContentType("text/html");
        request.setAttribute("params", params);
        // Переходим на JSP страницу
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/parCtrlView.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
    private void checkDel(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=null;
        Map <String, String[]> param = request.getParameterMap();   //Получаем параметры
        if(request.getParameter("checkId")!=null) {
            int num = param.get("checkId").length;      //Количество удаляемых объектов
            ArrayList<Map<String, String>> del = new ArrayList<Map<String, String>>();
            for (int i = 0; i < num; i++) {
                del.add(dao.getFromDb(Integer.parseInt(param.get("checkId")[i])));
            }
            response.setContentType("text/html");
            request.setAttribute("params", del); //Передаём данные на jsp
        }
            // Переходим на JSP страницу
            dispatcher = request.getRequestDispatcher("/jsp/parCtrlDel.jsp");

            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
    }

    private void sendBulkEdit(ServletRequest request,ServletResponse response)throws ServletException, IOException{
        RequestDispatcher dispatcher=null;
        Map <String,String[]> param = request.getParameterMap();
        ArrayList<Map<String, String>> data=new ArrayList<Map<String, String>>();
        if(param.get("checkId")!=null) {
            int num = param.get("checkId").length;

            for (int i=0;i<num;i++) {
                data.add(dao.getFromDb(Integer.parseInt(param.get("checkId")[i])));
            }
        }
        response.setContentType("text/html");
        request.setAttribute("data",data);
        dispatcher = request.getRequestDispatcher("/jsp/parCtrlBulkEdit.jsp");

        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    private void sendEditTable(ServletResponse response,ServletRequest request) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<Map<String,String>> list=dao.getFromEditDb();
        request.setAttribute("data", list);
        RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/parCtrlEditTable.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    private void sendAddForm(ServletResponse response,ServletRequest request) throws ServletException, IOException{
        List<String> col=dao.getColumnsToDB();
        response.setContentType("text/html");
        request.setAttribute("col", col);
        // Переходим на JSP страницу
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/parCtrlAdd.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    private void sendEditForm(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Map<String, String> param = dao.getFromDb(Integer.parseInt(request.getParameter("id")));
        response.setContentType("text/html");
        request.setAttribute("param", param);
        // Переходим на JSP страницу
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/parCtrlEdit.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }


}
