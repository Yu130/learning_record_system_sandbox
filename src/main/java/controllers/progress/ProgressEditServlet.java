package controllers.progress;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ProgressEditServlet
 */
@WebServlet("/progress/edit")
public class ProgressEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgressEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();

        User u = (User) request.getSession().getAttribute("login_user");
        request.getSession().setAttribute("user_id", u.getUser_id());
        User login_user = em.find(User.class, request.getSession().getAttribute("user_id"));

        em.close();

        request.getSession().setAttribute("user", login_user);
        request.getSession().setAttribute("user_id", login_user.getUser_id());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/progress/edit.jsp");
        rd.forward(request, response);
    }

}