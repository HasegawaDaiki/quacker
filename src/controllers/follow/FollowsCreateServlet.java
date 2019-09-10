package controllers.follow;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersCreateServlet
 */
@WebServlet("/users/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Follow f = new Follow();

        f.setFollower((User)request.getSession().getAttribute("login_user"));
        f.setFollowee(
                em.createNamedQuery("findUserByUser_id", User.class)
                  .setParameter("user_id", request.getParameter("followee_id"))
                  .getSingleResult()
                );

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        f.setCreated_at(currentTime);
        f.setUpdated_at(currentTime);
        f.setDelete_flag(0);

        Follow registered_follow = null;

        try {
            registered_follow = em.createNamedQuery("checkRegisteredFollowerAndFollowee", Follow.class)
                    .setParameter("follower_id", f.getFollower().getUser_id())
                    .setParameter("followee_id", f.getFollowee().getUser_id())
                    .getSingleResult();
        } catch (NoResultException ex) {}

        em.getTransaction().begin();

        if(registered_follow == null) {
            em.persist(f);
            request.getSession().setAttribute("flush", "フォローしました");
        } else {
            em.remove(registered_follow);
            request.getSession().setAttribute("flush",  "フォローを解除しました");
        }

        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath() + "/home");
    }

}
