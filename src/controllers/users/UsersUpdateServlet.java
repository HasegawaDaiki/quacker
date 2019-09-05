package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UsersUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = em.createNamedQuery("findUserByUser_id", User.class)
                    .setParameter("user_id", (String)request.getParameter("previous_user_id"))
                    .getSingleResult();

        // 現在の値と異なるユーザIDが入力されていたら
        // 重複チェックを行う指定をする
        Boolean user_id_duplicate_check = true;
        if(u.getUser_id().equals(request.getParameter("user_id"))) {
            user_id_duplicate_check = false;
        } else {
            u.setUser_id(request.getParameter("user_id"));
        }

        // 現在の値と異なるメールアドレスが入力されていたら
        // 重複チェックを行う指定をする
        Boolean email_duplicate_check = true;
        if(u.getEmail().equals(request.getParameter("email"))) {
            email_duplicate_check = false;
        } else {
            u.setEmail(request.getParameter("email"));
        }

        // パスワード欄に入力があったら
        // パスワードの入力値チェックを行う指定をする
        Boolean password_check_flag = true;
        String password = request.getParameter("password");
        if(password == null || password.equals("")) {
            password_check_flag = false;
        } else {
            u.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            password,
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
        }

        u.setName(request.getParameter("name"));
        u.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        List<String> errors = UserValidator.validate(u, user_id_duplicate_check, email_duplicate_check, password_check_flag);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("user", u);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
            rd.forward(request, response);
        } else {
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました");

            request.getSession().removeAttribute("user_id");

            response.sendRedirect(request.getContextPath() + "/users/show?user_id=" + u.getUser_id());

        }
    }

}
