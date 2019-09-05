package controllers.quacks;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quack;
import utils.DBUtil;

/**
 * Servlet implementation class QuacksDestroyServlet
 */
@WebServlet("/quacks/destroy")
public class QuacksDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuacksDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Quack q = em.find(Quack.class, Integer.parseInt(request.getParameter("quack_id")));
        q.setDelete_flag(1);
        q.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        request.getSession().setAttribute("flush", "削除が完了しました");

        response.sendRedirect(request.getContextPath() + "/home");
    }

}