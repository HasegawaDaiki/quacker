package controllers.quacks;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quack;
import models.User;
import models.validators.QuackValidator;
import utils.DBUtil;

/**
 * Servlet implementation class QuacksCreateServlet
 */
@WebServlet("/quacks/create")
public class QuacksCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuacksCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Quack q = new Quack();

        q.setUser((User)request.getSession().getAttribute("login_user"));
        q.setContent(request.getParameter("content"));

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        q.setCreated_at(currentTime);
        q.setUpdated_at(currentTime);

        q.setDelete_flag(0);

        String error = QuackValidator.validate(q);
        if(error != null && error != "") {
            em.close();

            request.setAttribute("quack", q);
            request.setAttribute("error", error);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/quacks/new/jsp");
            rd.forward(request,response);
        } else {
            em.getTransaction().begin();
            em.persist(q);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "投稿完了!");

            response.sendRedirect(request.getContextPath() + "/home");
        }

    }

}
