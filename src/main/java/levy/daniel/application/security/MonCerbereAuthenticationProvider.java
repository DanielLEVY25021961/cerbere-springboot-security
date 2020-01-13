package levy.daniel.application.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * CLASSE MonCerbereAuthenticationProvider :<br/>
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
@Component(value = "MonCerbereAuthenticationProvider")
public class MonCerbereAuthenticationProvider
				implements AuthenticationProvider {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbereAuthenticationProvider.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereAuthenticationProvider() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public Authentication authenticate(
			final Authentication pAuthentication)
							throws AuthenticationException {
		       
        try {

            final String username = pAuthentication.getName();
            
            final Authentication resultat 
            	= new UsernamePasswordAuthenticationToken(
            		username, null, pAuthentication.getAuthorities());
            
            /* The AuthenticationManager implementation will often return an 
             * Authentication containing richer information as the principal 
             * for use bythe application. 
             * Many of the authentication providers will create 
             * a UserDetails object as the principal.*/
            
            resultat.setAuthenticated(true);
            
            System.out.println();
            System.out.println("=============================================");
            System.out.println("MonCerbereAuthenticationProvider authenticate(pAuthentication)");
            System.out.println("=============================================");
            System.out.println();
            
            return resultat;
            
		} catch (final Exception finalE) {

			throw new
            	BadCredentialsException("Le Login CERBERE a échoué");
			
		}

	} // Fin de authenticate(...)._________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean supports(final Class<?> pAuthentication) {
		return pAuthentication.equals(
				UsernamePasswordAuthenticationToken.class);
	} // Fin de supports(...)._____________________________________________

	
	
} // FIN DE LA CLASSE MonCerbereAuthenticationProvider.----------------------
