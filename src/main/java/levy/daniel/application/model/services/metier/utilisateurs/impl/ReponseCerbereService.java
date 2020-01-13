package levy.daniel.application.model.services.metier.utilisateurs.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;

import i2.application.cerbere.commun.Cerbere;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;
import levy.daniel.application.model.services.metier.utilisateurs.IReponseCerbereService;
import levy.daniel.application.security.MonCerbereUserDetails;


/**
 * CLASSE ReponseCerbereService :<br/>
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
 * @since 4 janv. 2020
 */
public class ReponseCerbereService implements IReponseCerbereService {

	// ************************ATTRIBUTS************************************/

	/**
	 * i2.application.cerbere.commun.Cerbere
	 */
	private Cerbere cerbere;
	
	/**
	 * levy.daniel.application.model.persistence.metier
	 * .utilisateur.entities.jpa.MonCerbereEntityJPA
	 */
	private MonCerbereEntityJPA monCerbereEntityJPA;
	
	/**
	 * levy.daniel.application.security.MonCerbereUserDetails
	 */
	private MonCerbereUserDetails userDetails;
	
	/**
	 * org.springframework.security.core.Authentication
	 */
	private Authentication authenticationCerbere;
	
	/**
	 * java.util.List&lt;IMonProfil&gt;
	 */
	private List<IMonProfil> listeDroitsTriee;
	
	/**
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil par defaut.
	 */
	private IMonProfil profilParDefaut;

	/**
	 * levy.daniel.application.model.metier.utilisateur.EnumGestionnaire 
	 */
	private EnumGestionnaire gestionnaire;
	
	/**
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil choisi.
	 */
	private IMonProfil profilChoisi;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ReponseCerbereService.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public ReponseCerbereService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final Cerbere getCerbere() {	
		return this.cerbere;	
	} // Fin de getCerbere().______________________________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setCerbere(final Cerbere pCerbere) {	
		this.cerbere = pCerbere;	
	} // Fin de setCerbere(...).___________________________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final MonCerbereEntityJPA getMonCerbereEntityJPA() {	
		return this.monCerbereEntityJPA;	
	} // Fin de getMonCerbereEntityJPA().__________________________________

	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setMonCerbereEntityJPA(
			final MonCerbereEntityJPA pMonCerbereEntityJPA) {	
		this.monCerbereEntityJPA = pMonCerbereEntityJPA;	
	} // Fin de setMonCerbereEntityJPA(...)._______________________________

	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final MonCerbereUserDetails getUserDetails() {	
		return this.userDetails;	
	} // Fin de getUserDetails().__________________________________________

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setUserDetails(
			final MonCerbereUserDetails pUserDetails) {	
		this.userDetails = pUserDetails;	
	} // Fin de setUserDetails(...)._______________________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final Authentication getAuthenticationCerbere() {	
		return this.authenticationCerbere;	
	} // Fin de getAuthenticationCerbere().________________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setAuthenticationCerbere(
			final Authentication pAuthenticationCerbere) {	
		this.authenticationCerbere = pAuthenticationCerbere;	
	} // Fin de setAuthenticationCerbere(...)._____________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final List<IMonProfil> getListeDroitsTriee() {	
		return this.listeDroitsTriee;	
	} // Fin de getListeDroitsTriee()._____________________________________
	

	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setListeDroitsTriee(
			final List<IMonProfil> pListeDroitsTriee) {	
		this.listeDroitsTriee = pListeDroitsTriee;	
	} // Fin de setListeDroitsTriee(...).__________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final IMonProfil getProfilParDefaut() {	
		return this.profilParDefaut;	
	} // Fin de getProfilParDefaut().______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setProfilParDefaut(final IMonProfil pProfilParDefaut) {	
		this.profilParDefaut = pProfilParDefaut;	
	} // Fin de setProfilParDefaut(...).___________________________________


		
	/**
	* {@inheritDoc}
	*/
	@Override
	public final EnumGestionnaire getGestionnaire() {	
		return this.gestionnaire;	
	} // Fin de getGestionnaire()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setGestionnaire(final EnumGestionnaire pGestionnaire) {	
		this.gestionnaire = pGestionnaire;	
	} // Fin de setGestionnaire(...).______________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final IMonProfil getProfilChoisi() {	
		return this.profilChoisi;	
	} // Fin de getProfilChoisi()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void setProfilChoisi(final IMonProfil pProfilChoisi) {	
		this.profilChoisi = pProfilChoisi;	
	} // Fin de setProfilChoisi(...).______________________________________
	
	
	
} // FIN DE LA CLASSE ReponseCerbereService.---------------------------------
