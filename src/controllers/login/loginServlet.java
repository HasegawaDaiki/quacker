package controllers.login;

import java.io.IOException;

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
import utils.EncryptUtil;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    // ログイン画面を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null) {
                request.setAttribute("flush",  request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 認識結果を格納する変数
        Boolean check_result = false;

        String user_id = request.getParameter("user_id");
        String plain_pass = request.getParameter("password");

        User u = null;

        if(user_id != null && !user_id.equals("") && plain_pass != null && !plain_pass.equals("")) {
            EntityManager em = DBUtil.createEntityManager();

            String password = EncryptUtil.getPasswordEncrypt(
                    plain_pass,
                    (String)this.getServletContext().getAttribute("salt")
                    );

            // ユーザIDとパスワードが正しいかチェックする
            try {
                u = em.createNamedQuery("checkLoginUser_idAndPassword", User.class)
                        .setParameter("user_id",user_id)
                        .setParameter("pass", password)
                        .getSingleResult();
            } catch(NoResultException ex) {}

            em.close();

            if(u != null) {
                check_result = true;
            }
        }

        if(!check_result) {
            // 認証できなかったらログイン画面に戻る
            request.setAttribute("error", "ユーザIDかパスワードが間違っています");
            request.setAttribute("user_id", user_id);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request,  response);
        } else {
            // 認証できたらログイン状態にしてホームへリダイレクト
            request.getSession().setAttribute("login_user", u);
            request.getSession().setAttribute("flush", "ログインに成功しました");
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

}
