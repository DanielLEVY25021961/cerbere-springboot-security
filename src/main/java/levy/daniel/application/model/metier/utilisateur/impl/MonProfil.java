package levy.daniel.application.model.metier.utilisateur.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.utilisateur.IMonCerbere;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;


/**
 * CLASSE MonProfil :<br/>
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
 * @since 1 janv. 2020
 */
public class MonProfil implements IMonProfil {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id en base.<br/>
	 */
	private Long id;

	/**
	 * rôle de l'utilisateur dans la session courante.<br/>
	 * obtenu en concaténant ROLE_+profil+portée.<br/>
	 * Par exemple :
	 * <li>ROLE_ADMINISTRATEUR - FRANCE ENTIERE</li>
	 * <li>ROLE_GESTIONNAIRE - DIRA</li>
	 */
	private String role;
	
	/**
	 * profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>ADMINISTRATEUR</li>
	 * <li>GESTIONNAIRE</li>
	 * </ul>
	 */
	private String profil;
	
	/**
	 * portée du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 */
	private String portee;
	
	/**
	 * restriction (facultative) sur la portée 
	 * du profil de l'utilisateur dans la session courante.<br/>
	 */
	private String restriction;
	
	/**
	 * Collection d'utilisateurs {@link MonCerbere} 
	 * possédant le présent profil.
	 */
	private Collection<IMonCerbere> monCerberes 
		= new ArrayList<IMonCerbere>();

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonProfil.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonProfil() {
		this(null, null, null, null);
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * alimente automatiquement <code>this.role</code>.
	 *
	 * @param pProfil : String :
	 * profil de l'utilisateur dans la session courante 
	 * (ADMINISTRATEUR, GESTIONNAIRE, ...).<br/>
	 * @param pPortee : String :
	 * portée du profil de l'utilisateur dans la session courante 
	 * (DIRA, FRANCE ENTIERE, ...).<br/>
	 * @param pRestriction : String : restriction (facultative) sur la portée.
	 */
	public MonProfil(
			final String pProfil
			, final String pPortee
			, final String pRestriction) {

		this(null, pProfil, pPortee, pRestriction);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * alimente automatiquement <code>this.role</code>.
	 *
	 * @param pId : Long : ID en base.	
	 * @param pProfil : String :
	 * profil de l'utilisateur dans la session courante 
	 * (ADMINISTRATEUR, GESTIONNAIRE, ...).<br/>
	 * @param pPortee : String :
	 * portée du profil de l'utilisateur dans la session courante 
	 * (DIRA, FRANCE ENTIERE, ...).<br/>
	 * @param pRestriction : String : restriction (facultative) sur la portée.
	 */
	public MonProfil(
			final Long pId
			, final String pProfil
			, final String pPortee
			, final String pRestriction) {
		
		super();
		
		this.id = pId;
		this.profil = pProfil;
		this.portee = pPortee;
		this.restriction = pRestriction;
		
		/* alimente automatiquement this.role. */
		this.role = this.fournirRole();
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________
	
	
	
	/**
	 * fournit le ROLE en concaténant ROLE_+profil+portée.<br/>.
	 * par exemple : ROLE_GESTIONNAIRE - DIRA.<br/>
	 * <br/>
	 * - retourne null si this.profil est blank.<br/>
	 * - retourne null si this.portee est blank.<br/>
	 * <br/>
	 *
	 * @return String : rôle de l'utilisateur.
	 */
	private String fournirRole() {
		
		/* retourne null si this.profil est blank. */
		if (StringUtils.isBlank(this.profil)) {
			return null;
		}
		
		/* retourne null si this.portee est blank. */
		if (StringUtils.isBlank(this.portee)) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append("ROLE_");
		stb.append(this.profil);
		stb.append(MOINS_ESPACE);
		stb.append(this.portee);
		
		return stb.toString();
		
	} // Fin de fournirRole()._____________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final int hashCode() {
		return Objects.hash(this.getRole());
	} // Fin de hashCode().________________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final boolean equals(final Object pObject) {

		if (this == pObject) {
			return true;
		}

		if (!(pObject instanceof IMonProfil)) {
			return false;
		}

		final IMonProfil other = (IMonProfil) pObject;
		
		return Objects.equals(this.getRole(), other.getRole());

	} // Fin de equals(...)._______________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final int compareTo(final IMonProfil pObject) {
		
		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}

		if (this.getRole() == null) {
			
			if (pObject.getRole() != null) {
				return +1;
			}
			
		} else {
			
			if (pObject.getRole() == null) {
				return -1;
			}
			
			if (this.getRole().contains("ADMIN")) {
				
				if (!pObject.getRole().contains("ADMIN")) {
					return -1;
				}
				
				return this.getPortee()
						.compareToIgnoreCase(pObject.getPortee());
				
			} else {
				
				return this.getPortee()
						.compareToIgnoreCase(pObject.getPortee());
				
			}
			
		}
		
		return 0;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final MonProfil clone() 
				throws CloneNotSupportedException {
		
		final IMonProfil clone 
			= (IMonProfil) super.clone();
				
		clone.setId(this.getId());
		clone.setRole(this.getRole());
		clone.setProfil(this.getProfil());
		clone.setPortee(this.getPortee());
		clone.setRestriction(this.getRestriction());
		
		return (MonProfil) clone;
		
	} // Fin de clone().___________________________________________________

	

	/**
	* {@inheritDoc}
	*/
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("MonProfil [");

		stb.append("id=");
		if (this.id != null) {			
			stb.append(this.id);
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);
	
		stb.append("role=");
		if (this.role != null) {		
			stb.append(this.role);
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("profil=");
		if (this.profil != null) {
			stb.append(this.profil);
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("portee=");
		if (this.portee != null) {			
			stb.append(this.portee);
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);
		
		stb.append("restriction=");
		if (this.restriction != null) {			
			stb.append(this.restriction);
		} else {
			stb.append(NULL);
		}

		stb.append(']');
		
		return stb.toString();

	} // Fin de toString().________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final String toStringConsole() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("id=");
		if (this.id != null) {			
			stb.append(this.id);
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);
	
		stb.append("role=");
		if (this.role != null) {		
			stb.append(this.role);
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("profil=");
		if (this.profil != null) {
			stb.append(this.profil);
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("portee=");
		if (this.portee != null) {			
			stb.append(this.portee);
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);
		
		stb.append("restriction=");
		if (this.restriction != null) {			
			stb.append(this.restriction);
		} else {
			stb.append(NULL);
		}
		
		return stb.toString();
		
	} // Fin de toStringConsole()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final void afficherConsoleCollectionLiee() {
		
		if (this.getMonCerberes() != null) {
			
			for (final IMonCerbere user : this.getMonCerberes()) {
				
				System.out.println(user.toStringConsole());
				System.out.println();
				
			}

		}
				
	} // Fin de afficherConsoleCollectionLiee().___________________________
	
	
	
	/**
	* {@inheritDoc}<br/>
	* <code>"id;role;profil;portee;restriction;"</code><br/>
	* <br/>
	*/
	@Override
	public final String fournirEnTeteCsv() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("id");
		stb.append(POINT_VIRGULE);
		stb.append("role");
		stb.append(POINT_VIRGULE);
		stb.append("profil");
		stb.append(POINT_VIRGULE);
		stb.append("portee");
		stb.append(POINT_VIRGULE);
		stb.append("restriction");
		stb.append(POINT_VIRGULE);
		
		return stb.toString();

	} // Fin de fournirEnTeteCsv().________________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;role;profil;portee;restriction;"</code><br/>
	* <br/>
	*/
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getRole());
		stb.append(POINT_VIRGULE);
		stb.append(this.getProfil());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPortee());
		stb.append(POINT_VIRGULE);
		stb.append(this.getRestriction());
		stb.append(POINT_VIRGULE);
		
		return stb.toString();
		
	} // Fin de fournirStringCsv().________________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;role;profil;portee;restriction;"</code><br/>
	* <br/>
	*/
	@Override
	public final String fournirEnTeteColonne(final int pI) {
		
		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "role";
			break;

		case 2:
			entete = "profil";
			break;

		case 3:
			entete = "portee";
			break;
			
		case 4:
			entete = "restriction";
			break;
			
		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;role;profil;portee;restriction;"</code><br/>
	* <br/>
	*/
	@Override
	public final Object fournirValeurColonne(final int pI) {
		
		Object valeur = null;

		switch (pI) {

		case 0:
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}
			break;

		case 1:
			valeur = this.getRole();
			break;

		case 2:
			valeur = this.getProfil();
			break;

		case 3:
			valeur = this.getPortee();
			break;

		case 4:
			valeur = this.getRestriction();
			break;
			
		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de fournirValeurColonne(...)._________________________________
	


	/**
	* {@inheritDoc}
	*/
	@Override
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


		
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getRole() {	
		return this.role;	
	} // Fin de getRole()._________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setRole(final String pRole) {	
		this.role = pRole;	
	} // Fin de setRole(...).______________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public String getProfil() {	
		return this.profil;	
	} // Fin de getProfil()._______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setProfil(final String pProfil) {	
		this.profil = pProfil;	
	} // Fin de setProfil(...).____________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getPortee() {	
		return this.portee;	
	} // Fin de getPortee()._______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setPortee(final String pPortee) {	
		this.portee = pPortee;	
	} // Fin de setPortee(...).____________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getRestriction() {	
		return this.restriction;	
	} // Fin de getRestriction().__________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setRestriction(final String pRestriction) {	
		this.restriction = pRestriction;	
	} // Fin de setRestriction(...)._______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Collection<IMonCerbere> getMonCerberes() {	
		return this.monCerberes;	
	} // Fin de getMonCerberes().__________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setMonCerberes(
			final Collection<IMonCerbere> pMonCerberes) {	
		this.monCerberes = pMonCerberes;	
	} // Fin de setMonCerberes(...)._______________________________________

	
	
} // FIN DE LA CLASSE MonProfil.---------------------------------------------
