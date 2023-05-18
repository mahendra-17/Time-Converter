

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TimeConversionServlet")
public class TimeConversionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TimeConversionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String time=request.getParameter("time");
		String fromcountry=request.getParameter("fromcountry");
		String tocountry=request.getParameter("tocountry");
		ZoneId fromZone = ZoneId.of(fromcountry);
        ZoneId toZone = ZoneId.of(tocountry);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        ZonedDateTime fromZonedDateTime = ZonedDateTime.of(localDateTime, fromZone);
        ZonedDateTime toZonedDateTime = fromZonedDateTime.withZoneSameInstant(toZone);
        String convertedTime = toZonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd   hh:mm:a"));
		response.setContentType("text/html");
		response.getWriter().write(convertedTime);

	}

}
