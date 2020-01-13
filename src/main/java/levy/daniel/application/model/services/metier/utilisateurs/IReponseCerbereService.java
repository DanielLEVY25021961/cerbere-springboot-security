package levy.daniel.application.model.services.metier.utilisateurs;

import java.util.List;

import org.springframework.security.core.Authentication;

import i2.application.cerbere.commun.Cerbere;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;
import levy.daniel.application.security.MonCerbereUserDetails;

/**
 * INTERFACE IReponseCerbereService :<br/>
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
public interface IReponseCerbereService {

	
	
	/**
	 * Getter du i2.application.cerbere.commun.Cerbere
	 *
	 * @return <code>this.cerbere</code> : Cerbere
	 */
	Cerbere getCerbere();



	/**
	 * Setter du i2.application.cerbere.commun.Cerbere
	 *
	 * @param pCerbere : Cerbere :
	 * valeur à passer à <code>this.cerbere</code>.
	 */
	void setCerbere(Cerbere pCerbere);



	/**
	 * Getter du levy.daniel.application.model.persistence.metier
	 * .utilisateur.entities.jpa.MonCerbereEntityJPA
	 *
	 * @return <code>this.monCerbereEntityJPA</code> : MonCerbereEntityJPA
	 */
	MonCerbereEntityJPA getMonCerbereEntityJPA();



	/**
	 * Setter du levy.daniel.application.model.persistence.metier
	 * .utilisateur.entities.jpa.MonCerbereEntityJPA
	 *
	 * @param pMonCerbereEntityJPA : MonCerbereEntityJPA :
	 * valeur à passer à <code>this.monCerbereEntityJPA</code>.
	 */
	void setMonCerbereEntityJPA(MonCerbereEntityJPA pMonCerbereEntityJPA);



	/**
	 * Getter du levy.daniel.application.security.MonCerbereUserDetails
	 *
	 * @return <code>this.userDetails</code> : MonCerbereUserDetails
	 */
	MonCerbereUserDetails getUserDetails();



	/**
	 * Setter du levy.daniel.application.security.MonCerbereUserDetails
	 *
	 * @param pUserDetails : MonCerbereUserDetails :
	 * valeur à passer à <code>this.userDetails</code>.
	 */
	void setUserDetails(MonCerbereUserDetails pUserDetails);



	/**
	 * Getter du org.springframework.security.core.Authentication
	 *
	 * @return <code>this.authenticationCerbere</code> : Authentication
	 */
	Authentication getAuthenticationCerbere();



	/**
	 * Setter du org.springframework.security.core.Authentication
	 *
	 * @param pAuthenticationCerbere : Authentication :
	 * valeur à passer à <code>this.authenticationCerbere</code>.
	 */
	void setAuthenticationCerbere(Authentication pAuthenticationCerbere);



	/**
	 * Getter du java.util.List&lt;IMonProfil&gt;
	 *
	 * @return <code>this.listeDroitsTriee</code> : List&lt;IMonProfil&gt;
	 */
	List<IMonProfil> getListeDroitsTriee();



	/**
	 * Setter du java.util.List&lt;IMonProfil&gt;
	 *
	 * @param pListeDroitsTriee : List&lt;IMonProfil&gt; :
	 * valeur à passer à <code>this.listeDroitsTriee</code>.
	 */
	void setListeDroitsTriee(List<IMonProfil> pListeDroitsTriee);


	
	/**
	 * Getter du levy.daniel.application.model.metier.utilisateur.IMonProfil 
	 * par defaut.
	 *
	 * @return <code>this.profilParDefaut</code> : IMonProfil
	 */
	IMonProfil getProfilParDefaut();


	
	/**
	 * Setter du levy.daniel.application.model.metier.utilisateur.IMonProfil 
	 * par defaut.
	 *
	 * @param pProfilParDefaut : IMonProfil :
	 * valeur à passer à <code>this.profilParDefaut</code>.
	 */
	void setProfilParDefaut(IMonProfil pProfilParDefaut);	


	
	/**
	 * Getter du 
	 * levy.daniel.application.model.metier.utilisateur.EnumGestionnaire
	 *
	 * @return <code>this.gestionnaire</code> : EnumGestionnaire
	 */
	EnumGestionnaire getGestionnaire();


	
	/**
	 * Setter du 
	 * levy.daniel.application.model.metier.utilisateur.EnumGestionnaire
	 *
	 * @param pGestionnaire : EnumGestionnaire :
	 * valeur à passer à <code>this.gestionnaire</code>.
	 */
	void setGestionnaire(EnumGestionnaire pGestionnaire);


	
	/**
	 * Getter du 
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil choisi.
	 *
	 * @return <code>this.profilChoisi</code> : IMonProfil
	 */
	IMonProfil getProfilChoisi();


	
	/**
	 * Setter du 
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil choisi.
	 *
	 * @param pProfilChoisi : IMonProfil :
	 * valeur à passer à <code>this.profilChoisi</code>.
	 */
	void setProfilChoisi(IMonProfil pProfilChoisi);

	
	
} // FIN DE L'INTERFACE IReponseCerbereService.------------------------------