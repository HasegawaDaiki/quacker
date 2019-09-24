package controllers.home;

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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class HomeIndexServlet
 */
@WebServlet("/home")
public class HomeIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", (String)request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        if(request.getSession().getAttribute("error") != null) {
            request.setAttribute("error", (String)request.getSession().getAttribute("error"));
            request.getSession().removeAttribute("error");
        }

        List<User> followee_users = em.createNamedQuery("getFolloweesOfFollowerUser_id", User.class)
                    .setParameter("user_id", ((User) (request.getSession().getAttribute("login_user"))).getUser_id())
                    .getResultList();

        if(followee_users != null) {
            String user_ids = "";
            for(int i = 0; i < followee_users.size(); i++) {
                if(user_ids == "") {
                    user_ids += followee_users.get(i).getUser_id();
                } else {
                    user_ids = user_ids + "," + followee_users.get(i).getUser_id();
                }
            };

            System.out.println(user_ids);

            List<Quack> quacks = em.createNamedQuery("getQuacksOfFollowingUser", Quack.class)
                    .setParameter("user_id", user_ids)
                    .getResultList();

            request.setAttribute("quacks", quacks);
        }

        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/home/index.jsp");
        rd.forward(request, response);
    }

}
