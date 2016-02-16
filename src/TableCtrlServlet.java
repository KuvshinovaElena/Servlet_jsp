import bean.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 18.11.2015.
 */
public class TableCtrlServlet extends HttpServlet {
    List<Person> persons = new ArrayList();
    private ServletConfig config;
    PersonDao dao;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        dao=new PersonDao();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        persons=dao.getFromDb();
        resp.setContentType("text/html");
        req.setAttribute("data", persons);
        // Переходим на JSP страницу
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/tableCtrl.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }


    }
}

