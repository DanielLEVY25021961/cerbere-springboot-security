package levy.daniel.application.controllers.web.metier.utilisateur.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;


/**
 * CLASSE MonCerbereController :<br/>
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
 * @author Daniel Lévy
 * @version 1.0
 * @since 2 janv. 2020
 */
@Controller(value="MonCerbereController")
@RequestMapping(value="/MonCerbereController")
public class MonCerbereController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbereController.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereController() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * aiguille vers la page exposant le User Cerbere en session 
	 * <code>/metier/utilisateurs/userCerbereEnSession.html</code> 
	 * à chaque déclenchement de l'<strong>action</strong> 
	 * <code>/MonCerbereController/versUserCerbereEnSession</code>.<br/>
	 * Le début de path "/MonCerbereController" est déjà indiqué 
	 * dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 *
	 * @param pModel : org.springframework.ui.Model
	 * @param pSession : javax.servlet.http.HttpSession
	 * 
	 * @return String : "/metier/utilisateurs/userCerbereEnSession" : 
	 * le chemin vers la page visée.
	 */
//	@PreAuthorize(value="hasRole({'ROLE_ADMINISTRATEUR - TOUT'})")
	@GetMapping(value = "/versUserCerbereEnSession")
	public String versUserCerbereEnSession(
			final Model pModel
				, final HttpSession pSession) {
		
		/* récupère le MonCerbereEntityJPA en session. */
		final MonCerbereEntityJPA monCerbereEntityJPA 
			= (MonCerbereEntityJPA) pSession.getAttribute("monCerbereEntityJPA");
		
		final List<IMonProfil> listeTriee 
			= (List<IMonProfil>) pSession.getAttribute("listeTriee");
		
		pModel.addAttribute("monCerbereEntityJPA", monCerbereEntityJPA);
		pModel.addAttribute("listeTriee", listeTriee);
		
		return "/metier/utilisateurs/userCerbereEnSession";
		
	} // Fin de versUserCerbereEnSession(...)._____________________________
	
	
	
} // FIN DE LA CLASSE MonCerbereController.----------------------------------
