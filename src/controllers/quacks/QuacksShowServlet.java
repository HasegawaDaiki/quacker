package controllers.quacks;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quack;
import utils.DBUtil;

/**
 * Servlet implementation class QuacksShowServlet
 */
@WebServlet("/quacks/show")
public class QuacksShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuacksShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Quack q = em.find(Quack.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("quack", q);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/quacks/show.jsp");
        rd.forward(request, response);
    }

}
