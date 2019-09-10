package controllers.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersFolloweesShowServlet
 */
@WebServlet("/users/followees")
public class UsersFolloweesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersFolloweesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = null;
        List<User> followees = new ArrayList<User>();

        try {
            u = em.createNamedQuery("findUserByUser_id", User.class)
                        .setParameter("user_id", (String)request.getParameter("user_id"))
                        .getSingleResult();
        } catch (NoResultException ex) {}

        if (u != null) {
            followees = em.createNamedQuery("getFolloweesOfFollowerUser_id", User.class)
                                    .setParameter("user_id", u.getUser_id())
                                    .getResultList();
        }

        em.close();

        request.setAttribute("user", u);
        request.setAttribute("followees", followees);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/followees.jsp");
        rd.forward(request, response);
    }

}
