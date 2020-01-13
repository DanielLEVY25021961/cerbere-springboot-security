package levy.daniel.application.controllers.web.accueil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.CerbereConnexionException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.SauvegardeImpossibleException;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;
import levy.daniel.application.model.services.metier.utilisateurs.IMonCerbereService;
import levy.daniel.application.model.services.metier.utilisateurs.IReponseCerbereService;
import levy.daniel.application.security.MonCerbereUserDetails;

/**
 * CLASSE AccueilCerbereController :<br/>
 * Controller Web SPRING BOOT chargé d'aiguiller 
 * vers la <strong>page d'accueil de l'application "/index.html"</strong> 
 * à chaque demande du path context "/" (action "/").<br/>
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
@Controller(value="AccueilCerbereController")
@RequestMapping("/")
public class AccueilCerbereController {

	// ************************ATTRIBUTS************************************/

	/**
	 * IMonCerbereService
	 */
	@Autowired
	@Qualifier("MonCerbereService")
	private transient IMonCerbereService monCerbereService;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AccueilCerbereController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public AccueilCerbereController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


    
	/**
	 * - <strong>aiguille</strong> vers la page d'accueil 
	 * <strong><code>/index.html</code></strong> si le CERBERE 
	 * en Session ne possède qu'un seul profil disponible 
	 * pour l'application (GESTIONNAIRE par exemple).<br/>
	 * - <strong>aiguille</strong> vers la page 
	 * <strong><code>/accueil/choisirRole.html</code></strong> 
	 * si le CERBERE en Session possède plusieurs profils disponibles 
	 * (ADMINISTRATEUR, GESTIONNAIRE, CONSULTANT, ...) 
	 * pour l'application et qu'il doit donc choisir 
	 * comment se <i>logger</i>.<br/>
	 * - appelé à chaque appel de l'<strong>action</strong> <code>"/"</code> 
	 * qui est invoquée à chaque retour du serveur CERBERE 
	 * fournissant les données d'authentification.<br/>  
	 * La path context "/" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 * <ul>
	 * <li>récupère l'objet Cerbere en Session.</li>
	 * <li>délègue au SERVICE la tâche de réaliser 
	 * les préparatifs de sécurité sur l'objet CERBERE.</li>
	 * <ul>
	 * <li>récupère l'Entity dans la réponse du SERVICE.</li>
	 * <li>récupère le UserDetails dans la réponse du SERVICE.</li>
	 * <li>récupère le Authentication dans la réponse du SERVICE.</li>
	 * <li>récupère la liste des Entities liées à l'objet métier 
	 * triée dans la réponse du SERVICE.</li>
	 * <li>récupère le profil par défaut dans la réponse du service.</li>
	 * <li>récupère le gestionnaire dans la réponse du SERVICE 
	 * (null si le gestionnaire ne peut être déterminé).</li>
	 * <li>récupère le profil choisi dans la réponse du SERVICE 
	 * (null si le profil choisi ne peut être déterminé).</li>
	 * </ul>
	 * <li>PASSE l'AUTHENTICATION au SecurityContextHolder 
	 * de SPRING SECURITY.</li>
	 * <li>enregistre l'objet métier USER CERBERE dans le stockage 
	 * si il n'était pas déjà persisté (délègue au SERVICE).</li>
	 * <li>passe les attributs au Model.</li>
	 * <li>passe les attributs à la session.</li>
	 * <li>aiguille vers la bonne vue en fonction du nombre de profils.</li>
	 * </ul>
	 * 
	 * @param pHttpServletRequest : 
	 * <code>javax.servlet.http.HttpServletRequest</code>.
	 *
	 * @return : <code>org.springframework.web.servlet.ModelAndView</code> 
	 * : Vue "index.html" et Model (sans données).<br/>
	 * 
	 * @throws CerbereConnexionException 
	 */
	@GetMapping()
	public ModelAndView versAccueil(
			final HttpSession pSession
				, final HttpServletRequest pHttpServletRequest) 
						throws CerbereConnexionException {

		final ModelAndView modelAndVew = new ModelAndView("accueil");
		
		/* récupère l'objet Cerbere en session. */
		final Cerbere cerbere = Cerbere.creation();
		
		try {

			/* ********************************************** */
			/* délègue au SERVICE la tâche de réaliser 
			 * les préparatifs de sécurité sur l'objet CERBERE. */
			final IReponseCerbereService reponse 
				= monCerbereService.loggerCerbere(cerbere);
			
			/* récupère l'Entity dans la réponse du SERVICE. */
			final MonCerbereEntityJPA monCerbereEntityJPA 
				= reponse.getMonCerbereEntityJPA();
			
			/* récupère le UserDetails dans la réponse du SERVICE. */
			final MonCerbereUserDetails userDetails 
				= reponse.getUserDetails();
			
			/* récupère le Authentication dans la réponse du SERVICE. */
			final Authentication authenticationCerbere 
				= reponse.getAuthenticationCerbere();

			/* récupère la liste des Entities liées à l'objet métier triée 
			 * dans la réponse du SERVICE. */
			final List<IMonProfil> listeTriee = reponse.getListeDroitsTriee();
			
			/* récupère le profil par défaut dans la réponse du SERVICE. */
			final IMonProfil profilParDefaut = reponse.getProfilParDefaut();
			
			/* récupère le gestionnaire dans la réponse du SERVICE 
			 * (null si le gestionnaire ne peut être déterminé). */
			final EnumGestionnaire gestionnaire = reponse.getGestionnaire();
			
			/* récupère le profil choisi dans la réponse du SERVICE 
			 * (null si le profil choisi ne peut être déterminé). */
			final IMonProfil profilChoisi = reponse.getProfilChoisi();

			
			/* ************************************************ */
			/* PASSE l'AUTHENTICATION au SecurityContextHolder 
			 * de SPRING SECURITY. */
			SecurityContextHolder.getContext()
				.setAuthentication(authenticationCerbere);

			
			/* ************************************************ */
			/* enregistre l'objet métier USER dans le stockage 
			 * si il n'était pas déjà persisté (délègue au SERVICE). */
			MonCerbereEntityJPA monCerbereEntityJPAPersistant = null;

			try {

				monCerbereEntityJPAPersistant 
					= this.monCerbereService.save(monCerbereEntityJPA);

			} catch (final Exception e) {

				final String message = e.getMessage();
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e);
				}
				
				throw new SauvegardeImpossibleException(message, e);

			}

			
			/* passe les attributs au Model. */
			modelAndVew.addObject("cerbere", cerbere);
			modelAndVew.addObject(
					"monCerbereEntityJPA", monCerbereEntityJPAPersistant);
			modelAndVew.addObject("userDetails", userDetails);
			modelAndVew.addObject(
					"authenticationCerbere", authenticationCerbere);
			modelAndVew.addObject("listeTriee", listeTriee);
			modelAndVew.addObject("profilParDefaut", profilParDefaut);
			modelAndVew.addObject("gestionnaire", gestionnaire);
			modelAndVew.addObject("profilChoisi", profilChoisi);
			
			/* passe les attributs à la session. */
			pSession.setAttribute("cerbere", cerbere);
			pSession.setAttribute(
					"monCerbereEntityJPA", monCerbereEntityJPAPersistant);
			pSession.setAttribute("userDetails", userDetails);
			pSession.setAttribute(
					"authenticationCerbere", authenticationCerbere);
			pSession.setAttribute("listeTriee", listeTriee);
			pSession.setAttribute("profilParDefaut", profilParDefaut);
			pSession.setAttribute("gestionnaire", gestionnaire);
			pSession.setAttribute("profilChoisi", profilChoisi);
			
			
			/* aiguille vers la bonne vue en fonction du nombre de profils. */
			if (listeTriee != null) {
				
				if (listeTriee.size() == 1) {
					
					modelAndVew.setViewName("/index");
					return modelAndVew;
					
				} 
					
				modelAndVew.setViewName("/accueil/choisirRole");
				return modelAndVew;
					
			}
			
//			final Authentication principal 
//				= (Authentication) pHttpServletRequest.getUserPrincipal();
//			
//			final Collection<? extends GrantedAuthority> droits = principal.getAuthorities();


		} catch (final Exception e) {

			modelAndVew.setViewName("/index");
			return modelAndVew;

		}
				
		return modelAndVew;
		
	} // Fin de versAccueil()._____________________________________________
	
	
    
	/**
	 * redirige vers la page de login CERBERE obtenue 
	 * au moyen de l'action 
	 * <code>"/AccueilCerbereController/login"</code>.<br/>
	 * La path context "/" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 *
	 * @return : String : "redirect:/".<br/>
	 */
	@GetMapping("AccueilCerbereController/login")
	public String versLogin() {
		return "redirect:/";
	} // Fin de versLogin()._______________________________________________

	
	
	/**
	 * redirige vers la page de login CERBERE <code>/</code> 
	 * à chaque
	 * lancement de l'action <code>"/versLogout?logout"</code>.<br/>
	 * Le path "/" est déjà indiqué dans l'annotation 
	 * RequestMapping
	 * au dessus de la classe.
	 * <ul>
	 * <li>ajoute un message "Vous êtes bien déloggé" au Model dans l'attribut
	 * <strong>'message'</strong> si le paramètre 'logout' 
	 * figure dans la requête.</li>
	 * <li>récupère 
	 * <code>org.springframework.security.core.Authentication</code>
	 * dans le contexte du
	 * <code>org.springframework.security.core.context.SecurityContextHolder</code>
	 * si 'logout' dans la requête.</li>
	 * <li><strong>logout l'authentication</strong> 
	 * si elle est encore présente dans
	 * la requête.</li>
	 * <li>retourne la page "/accueil/login.html".</li>
	 * </ul>
	 * 
	 * @param pLogout : String : 
	 * paramètre 'logout' passé dans la requête en cas
	 * de demande de Logout.
	 * @param pModel    : org.springframework.ui.Model
	 * @param pRequest  : javax.servlet.http.HttpServletRequest
	 * @param pResponse : javax.servlet.http.HttpServletResponse
	 * @param pSession : javax.servlet.http.HttpSession
	 *
	 * @return : String : "/accueil/login".<br/>
	 * 
	 * @throws CerbereConnexionException 
	 */
	@GetMapping("versLogout")
	public void versLogout(
			@RequestParam(value = "logout", required = false) final String pLogout
			, final Model pModel
			, final HttpServletRequest pRequest
			, final HttpServletResponse pResponse
			, final HttpSession pSession) throws CerbereConnexionException {

		pModel.addAttribute("logout", pLogout);

		if (pLogout != null) {

			/* */
			if (pSession != null) {
				
				/*
				 * récupère org.springframework.security.core.Authentication 
				 * dans le contexte du
				 * org.springframework.security.core.context.SecurityContextHolder 
				 * si le paramètre 'logout'
				 * dans la requête.
				 */
				final Authentication authentication 
				= SecurityContextHolder.getContext().getAuthentication();

				/* DECONNEXION SPRING. */
				/* logout l'authentication si elle 
				 * est encore présente dans la requête. */
				if (authentication != null) {
					
					SecurityContextHolder.getContext().setAuthentication(null);
					
				}
				
				final String message 
					= "Vous êtes bien déloggé pour SPRING SECURITY";
	
				/*
				 * ajoute un message "Vous êtes bien déloggé" 
				 * au Model dans l'attribut 'message'
				 * si 'error' dans la requête.
				 */
				pModel.addAttribute("message", message);

				
				/* DECONNEXION CERBERE. */
				final Cerbere cerbere 
					= (Cerbere) pSession.getAttribute("cerbere");
				
				if (cerbere != null) {
					cerbere.logoff(pRequest, pResponse);
				}
								
			}
						
		}

	} // Fin de versLogout()._______________________________________________

	
		
	
} // FIN DE LA CLASSE AccueilCerbereController.------------------------------
