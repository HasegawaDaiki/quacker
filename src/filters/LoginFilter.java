package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")) {             // CSSフォルダ内は認証処理から除外する
            HttpSession session = ((HttpServletRequest)request).getSession();

            // セッションスコープに保存されたユーザ情報を取得
            User u = (User)session.getAttribute("login_user");

            if(u != null) {                                                                   // ログインユーザ
                if(servlet_path.equals("/login") || servlet_path.equals("/users/new")) {      // ログイン画面とユーザ新規登録画面にはアクセスできない
                    session.setAttribute("error", "指定のページにアクセスするにはログアウトしてください");
                    ((HttpServletResponse)response).sendRedirect(context_path + "/home");
                    return;
                }

            } else {                                                                          // ログアウトユーザ
                if(!servlet_path.equals("/login") && !servlet_path.equals("/users/new")) {    // ログイン画面とユーザ新規登録画面のみアクセス可能
                    session.setAttribute("error", "ログインしてください");
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
