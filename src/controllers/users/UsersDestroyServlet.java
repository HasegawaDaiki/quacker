package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersDestroyServlet
 */
@WebServlet("/users/destroy")
public class UsersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = em.createNamedQuery("findUserByUser_id", User.class)
                    .setParameter("user_id", (String)request.getParameter("user_id"))
                    .getSingleResult();

        if (u.getId() == ((User)request.getSession().getAttribute("login_user")).getId()) {
            u.setDelete_flag(1);
            u.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "削除が完了しました");
        } else {
            em.close();

            request.getSession().setAttribute("error", "操作を完了できませんでした");
        }

        request.getSession().removeAttribute("login_user");
        response.sendRedirect(request.getContextPath() + "/login");
    }

}
