package servlet;

import store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HbmStore hb = new HbmStore();
        req.setAttribute("items", hb.findAll());
        req.setAttribute("isntdone", HbmStore.instOf().findAll());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
