package levy.daniel.application.controllers.web.error;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import levy.daniel.application.IConstantesSeparateurs;

/**
 * CLASSE ExceptionHandlingControllerAdvice :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 8 nov. 2019
 *
 */
//@ControllerAdvice(basePackages="levy.daniel.application.controllers.web")
public class ExceptionHandlingControllerAdvice {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe ExceptionHandlingControllerAdvice".
	 */
	public static final String CLASSE_EXC_HANDLING_CONTROLLER 
		= "Classe ExceptionHandlingControllerAdvice";
	
	/**
	 * "Méthode handleError(HttpServletRequest pRequest, Exception pException)".
	 */
	public static final String METHODE_HANDLE_ERROR 
	= "Méthode handleError(HttpServletRequest pRequest, Exception pException)";

	/**
	 * "NOM DE L'ATTRIBUT : %1$-80s - VALEUR DE L'ATTRIBUT : %2$-150s".
	 */
	public static final String PATTERN_ATTRIBUT_VALUE 
		= "NOM DE L'ATTRIBUT : %1$-80s - VALEUR DE L'ATTRIBUT : %2$-150s";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ExceptionHandlingControllerAdvice.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ExceptionHandlingControllerAdvice() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pRequest
	 * @param pException
	 * @return : ModelAndView :  .<br/>
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(
			final HttpServletRequest pRequest
				, final Exception pException) {
		
		final ModelAndView modelAndView = new ModelAndView();
		
		/* URL de la requête ayant causé l'Exception. */
		final String requestUrl = pRequest.getRequestURL().toString();
		
		final String message 
			= CLASSE_EXC_HANDLING_CONTROLLER
			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
			+ METHODE_HANDLE_ERROR
			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
			+ "la requête HTTP " + requestUrl 
			+ " a levé une Exception " + pException;
		
		if (LOG.isFatalEnabled()) {
			LOG.fatal(message, pException);
		}
		
		/* path. */
		final String path = pRequest.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping").toString();

	    
		/* status de l'erreur. */
	    final Object statusObject 
	    	= pRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    Integer status = null;
	    
	    if (statusObject != null) {
	    	status = Integer.valueOf(statusObject.toString());
	    }
	    
	    /* exception. */
		final String exception 
			= pRequest.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR").toString();
				
		modelAndView.addObject("path", path);
		modelAndView.addObject("requestUrl", requestUrl);
		modelAndView.addObject("message", message);
		
		modelAndView.addObject("status", status);
		modelAndView.addObject("exception", exception);
		
		modelAndView.setViewName("error/erreurs");
		
		this.listerAttributsModelAndView(modelAndView);
		
		return modelAndView;
		
	} // Fin de handleError(...).__________________________________________


	
	/**
	 * Affiche à la console les attributs d'une HttpServletRequest.<br/>
	 *
	 * @param pRequest : HttpServletRequest
	 */
	private void listerAttributsHttpServletRequest(
			final HttpServletRequest pRequest) {
		
		final Enumeration<String> attributs = pRequest.getAttributeNames();
		
		int count = 0;
		
		System.out.println();
		System.out.println("********* ATTRIBUTS DE LA HttpServletRequest pRequest : *************");
		
		while (attributs.hasMoreElements()) {
			
			count++;
			
			final String attribut = attributs.nextElement();
			final Object valeur = pRequest.getAttribute(attribut);
			
			String valeurString = null;
			
			if (valeur != null) {
				valeurString = StringUtils.abbreviate(valeur.toString(), 150);
			}
			
			final String stringFormatee 
				= String.format(PATTERN_ATTRIBUT_VALUE, attribut, valeurString);
			
			System.out.println();
			System.out.println(stringFormatee);
			
		}
		
		if (count == 0) {
			System.out.println("LA HttpServletRequest pRequest N' A AUCUN ATTRIBUT");
		}
		
	} // Fin de listerAttributsHttpServletRequest(...).____________________


	
	/**
	 * Affiche à la console les attributs d'une HttpSession.<br/>
	 *
	 * @param pHttpSession : HttpSession
	 */
	private void listerAttributsHttpSession(
			final HttpSession pHttpSession) {
		
		final Enumeration<String> attributs = pHttpSession.getAttributeNames();
		
		int count = 0;
		
		System.out.println();
		System.out.println("********* ATTRIBUTS DE LA HttpSession pHttpSession : *************");
		
		while (attributs.hasMoreElements()) {
			
			count++;
			final String attribut = attributs.nextElement();
			final Object valeur = pHttpSession.getAttribute(attribut);
			
			String valeurString = null;
			
			if (valeur != null) {
				valeurString = StringUtils.abbreviate(valeur.toString(), 150);
			}
			
			final String stringFormatee 
				= String.format(PATTERN_ATTRIBUT_VALUE, attribut, valeurString);
			
			System.out.println();
			System.out.println(stringFormatee);
			
		}
		
		if (count == 0) {
			System.out.println("LA HttpSession pHttpSession N' A AUCUN ATTRIBUT");
		}
		
	} // Fin de listerAttributsHttpSession(...).___________________________


	
	/**
	 * Affiche à la console les attributs d'une WebRequest.<br/>
	 *
	 * @param pWebRequest : org.springframework.web.context.request.WebRequest
	 */
	private void listerAttributsWebRequest(
			final WebRequest pWebRequest) {
		
		final String[] attributs = pWebRequest.getAttributeNames(RequestAttributes.SCOPE_REQUEST);
		
		System.out.println();
		System.out.println("********* ATTRIBUTS DE LA WebRequest pWebRequest : *************");
		
		if (attributs.length == 0) {
			System.out.println("LA WebRequest pWebRequest N' A AUCUN ATTRIBUT");
			return;
		}
		
		for (int i = 0; i < attributs.length; i++) {
			
			final String attribut = attributs[i];
			final Object valeur = pWebRequest.getAttribute(attribut, RequestAttributes.SCOPE_REQUEST);
			
			String valeurString = null;
			
			if (valeur != null) {
				valeurString = StringUtils.abbreviate(valeur.toString(), 150);
			}
			
			final String stringFormatee 
				= String.format(PATTERN_ATTRIBUT_VALUE, attribut, valeurString);
			
			System.out.println();
			System.out.println(stringFormatee);
			
		}
		
	} // Fin de listerAttributsWebRequest(...).____________________________
	

	
	/**
	 * Affiche à la console les attributs d'un org.springframework.ui.Model.<br/>
	 *
	 * @param pModel : org.springframework.ui.Model
	 */
	private void listerAttributsModel(final Model pModel) {
		
		final Map<String, Object> mapAttributs = pModel.asMap();
		
		final Set<Entry<String, Object>> entrySet = mapAttributs.entrySet();
		final Iterator<Entry<String, Object>> ite = entrySet.iterator();
		
		int count = 0;
		
		System.out.println();
		System.out.println("********* ATTRIBUTS DU MODEL pModel : *************");
		
		while (ite.hasNext()) {
			
			count++;
			
			final Entry<String, Object> entry = ite.next();
			final String attribut = entry.getKey();
			final Object valeur = entry.getValue();
			
			String valeurString = null;
			
			if (valeur != null) {
				valeurString = StringUtils.abbreviate(valeur.toString(), 150);
			}
			
			final String stringFormatee 
				= String.format(PATTERN_ATTRIBUT_VALUE, attribut, valeurString);
			
			System.out.println();
			System.out.println(stringFormatee);
			
		}
		
		if (count == 0) {
			System.out.println("LE MODEL pModel N' A AUCUN ATTRIBUT");
		}
		
	} // Fin de listerAttributsModel(...)._________________________________
	

	
	/**
	 * Affiche à la console les attributs d'un 
	 * org.springframework.web.servlet.ModelAndView.<br/>
	 *
	 * @param pModelAndView : org.springframework.web.servlet.ModelAndView
	 */
	private void listerAttributsModelAndView(final ModelAndView pModelAndView) {
		
		final Map<String, Object> mapAttributs = pModelAndView.getModel();
		
		final Set<Entry<String, Object>> entrySet = mapAttributs.entrySet();
		final Iterator<Entry<String, Object>> ite = entrySet.iterator();
		
		int count = 0;
		
		System.out.println();
		System.out.println("********* ATTRIBUTS DU MODELANDVIEW pModelAndView : *************");
		
		while (ite.hasNext()) {
			
			count++;
			
			final Entry<String, Object> entry = ite.next();
			final String attribut = entry.getKey();
			final Object valeur = entry.getValue();
			
			String valeurString = null;
			
			if (valeur != null) {
				valeurString = StringUtils.abbreviate(valeur.toString(), 150);
			}
						
			final String stringFormatee 
				= String.format(PATTERN_ATTRIBUT_VALUE, attribut, valeurString);
			
			System.out.println();
			System.out.println(stringFormatee);
			
		}
		
		if (count == 0) {
			System.out.println("LE MODELANDVIEW pModelAndView N' A AUCUN ATTRIBUT");
		}

	} // Fin de listerAttributsModelAndView(...).__________________________

	
	
} // FIN DE LA CLASSE ExceptionHandlingControllerAdvice.---------------------
