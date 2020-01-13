package levy.daniel.application.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl.MonCerbereDAOJPASpring;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;


/**
 * CLASSE MonCerbereUserDetailsService :<br/>
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
@Service(value="MonCerbereUserDetailsService")
public class MonCerbereUserDetailsService implements UserDetailsService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * MonCerbereDAOJPASpring injecté par SPRING.
	 */
	@Autowired
	@Qualifier("MonCerbereDAOJPASpring")
	private MonCerbereDAOJPASpring userSpringDao;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbereUserDetailsService.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereUserDetailsService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(final String pUsername) 
			throws UsernameNotFoundException {
		
		System.out.println();
		System.out.println("=================================================");
		System.out.println("DANS MonCerbereUserDetailsService loadUserByUsername(final String pUsername)");
		System.out.println("=================================================");
		System.out.println();
		
		if (StringUtils.isBlank(pUsername)) {
			
			final String messageBadUsername = "pUsername ne peut être blank";
			throw new UsernameNotFoundException(messageBadUsername);
		}
		
		final String messageKO 
			= "Objet métier introuvable dans le stockage : " + pUsername;
		
		MonCerbereEntityJPA userSpringEntityJPA = null;
		
		try {
			
			userSpringEntityJPA 
				= this.userSpringDao.findByUsername(pUsername);
			
		} catch (final Exception e) {
			throw new UsernameNotFoundException(messageKO);
		}
		
		if (userSpringEntityJPA == null) {
			throw new UsernameNotFoundException(messageKO);
		}
		
		final UserDetails userDetails 
			= new MonCerbereUserDetails(userSpringEntityJPA);
		
		return userDetails;
		
	} // Fin de loadUserByUsername(...).___________________________________

	
	
} // FIN DE LA CLASSE MonCerbereUserDetailsService.--------------------------
