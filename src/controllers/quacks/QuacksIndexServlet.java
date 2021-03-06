package controllers.quacks;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class QuacksIndexServlet
 */
@WebServlet("/quacks/index")
public class QuacksIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuacksIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Quack> quacks = em.createNamedQuery("getAllQuacks", Quack.class)
                                .setFirstResult(15 * (page - 1))
                                .setMaxResults(15)
                                .getResultList();
        long quacks_count = (long)em.createNamedQuery("getQuacksCount", Long.class)
                                    .getSingleResult();
        em.close();
        request.setAttribute("quacks",  quacks);
        request.setAttribute("quacks_count", quacks_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/quacks/index.jsp");
        rd.forward(request, response);
    }

}
