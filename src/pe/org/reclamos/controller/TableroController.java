package pe.org.reclamos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/tablero")
@Scope("session")
public class TableroController {

	private static final Logger logger = Logger.getLogger(FidelizarController.class );
	
	@RequestMapping(value = "/tablero.htm", method = RequestMethod.GET)
	public String tablero(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "tablero - ";
		try {
			logger.debug(METHODNAME + "INI");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");

		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: " + e.getMessage());
		} finally {
			logger.debug(METHODNAME + "FIN");
		}
		return "tablero/tablero";
	}

	@RequestMapping(value = "/tablero.htm", method = RequestMethod.POST)
	public String verTablero(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		final String METHODNAME = "verTablero - ";
		try {
			logger.debug(METHODNAME + "INI");
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");

		} catch (Exception e) {
			e.printStackTrace();
			model.put("msgError", "Error: " + e.getMessage());
		} finally {
			logger.debug(METHODNAME + "FIN");
		}
		return "tablero/tablero";
	}

}
