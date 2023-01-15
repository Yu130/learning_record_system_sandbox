package controllers.Measuring;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.History;
import models.validators.HistoryValidator;

/**
 * Servlet implementation class StartMeasuringServlet
 */
@WebServlet("/measuring/start")
public class StartMeasuringServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartMeasuringServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("_token", request.getSession().getId());

        History h = new History();
        Timestamp started_at = new Timestamp(System.currentTimeMillis());

        h.setLearned_date(new Date(System.currentTimeMillis()));
        h.setTitle(request.getParameter("title"));
        h.setContent(request.getParameter("content"));
        h.setStarted_at(started_at);
        h.setFinished_at(new Timestamp(System.currentTimeMillis()));
        h.setLearning_time(0);

        Date learned_date = new Date(System.currentTimeMillis());
        request.getSession().setAttribute("learned_date", learned_date);

        String title = (request.getParameter("title"));
        request.getSession().setAttribute("title", title);

        request.getSession().setAttribute("started_at", started_at);

        String content = (request.getParameter("content"));
        request.getSession().setAttribute("content", content);

        request.getSession().setAttribute("history", h);

        List<String> errors = HistoryValidator.validate(h);
        if (errors.size() > 0) {

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("history", h);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/measuring/startMeasuring.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/measuring/finishMeasuring.jsp");
            rd.forward(request, response);
        }
    }

}