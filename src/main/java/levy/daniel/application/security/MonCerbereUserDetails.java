package levy.daniel.application.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;


/**
 * CLASSE MonCerbereUserDetails :<br/>
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
public class MonCerbereUserDetails implements UserDetails {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Login (identifiant) du User en session.
	 */
	private transient String username;
	
	/**
	 * password (mot de passe) du User en session.
	 */
	private transient String password;
	
	/**
	 * rôles du User en session.
	 */
	private Collection<IMonProfil> roles;
	

	/**
	 * détermine si le USER est activé.
	 */
	private transient boolean active;

	/**
	 * MonCerbereEntityJPA ayant créé le UserDetails.
	 */
	private MonCerbereEntityJPA user;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbereUserDetails.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereUserDetails() {
		this(null);
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pUser : MonCerbereEntityJPA.
	 */
	public MonCerbereUserDetails(final MonCerbereEntityJPA pUser) {
		
		super();
		
		if (pUser != null) {
			
			this.user = pUser;
			this.username = pUser.getLogin();
			this.password = null;
			this.active = pUser.isActive();
			this.roles = pUser.getProfils();
			
		}		

	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		final List<GrantedAuthority> resultat 
			= new ArrayList<GrantedAuthority>();
		
		for (final IMonProfil role : this.roles) {
			
			final GrantedAuthority grantedAuthority 
				= new SimpleGrantedAuthority(role.getRole());
			
			resultat.add(grantedAuthority);
			
		}
		
		return resultat;
		
	} // Fin de getAuthorities().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return this.username;
	} // Fin de getUsername()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	} // Fin de isAccountNonExpired()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	} // Fin de isAccountNonLocked().______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	} // Fin de isCredentialsNonExpired()._________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return this.active;
	} // Fin de isEnabled()._______________________________________________


	
	/**
	 * Getter du MonCerbereEntityJPA ayant créé le UserDetails.
	 *
	 * @return <code>this.user</code> : MonCerbereEntityJPA
	 */
	public MonCerbereEntityJPA getUser() {	
		return this.user;	
	} // Fin de getUser()._________________________________________________


	
	/**
	 * Setter du MonCerbereEntityJPA ayant créé le UserDetails.
	 *
	 * @param pUser : MonCerbereEntityJPA :
	 * valeur à passer à <code>this.user</code>.
	 */
	public void setUser(final MonCerbereEntityJPA pUser) {	
		
		if (pUser != null) {
			
			this.user = pUser;
			this.username = pUser.getLogin();
			this.password = null;
			this.active = pUser.isActive();
			this.roles = pUser.getProfils();
			
		}		

	} // Fin de setUser(...).______________________________________________

	
	
} // FIN DE LA CLASSE MonCerbereUserDetails.---------------------------------
