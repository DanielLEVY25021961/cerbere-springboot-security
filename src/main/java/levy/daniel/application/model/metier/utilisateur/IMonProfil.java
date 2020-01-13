package levy.daniel.application.model.metier.utilisateur;

import java.io.Serializable;
import java.util.Collection;

import levy.daniel.application.apptechnic.configurationmanagers.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;
import levy.daniel.application.model.metier.utilisateur.impl.MonCerbere;

/**
 * INTERFACE IMonProfil :<br/>
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
public interface IMonProfil 
		extends Comparable<IMonProfil>, Serializable, Cloneable
		, IExportateurCsv, IExportateurJTable {

	/**
	 * ';'.<br/>
	 */
	char POINT_VIRGULE = ';';

	/**
	 * ", ".<br/>
	 */
	String VIRGULE_ESPACE = ", ";

	/**
	 * "null".<br/>
	 */
	String NULL = "null";

	/**
	 * " - "
	 */
	String MOINS_ESPACE = " - ";

	/**
	 * "unused".<br/>
	 */
	String UNUSED = "unused";

	/**
	 * System.getProperty("line.separator")
	 */
	String SAUT_DE_LIGNE_PLATEFORME = System.getProperty("line.separator");
	
	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	int hashCode();



	/**
	* {@inheritDoc}
	*/
	@Override
	boolean equals(Object pObject);


	
	/**
	* {@inheritDoc}<br/>
	* <ol>
	* <li>met ADMINISTRATEUR en premier</li>
	* <li>compare sur la portée (DIRA avant DIRCE, ...) 
	* pour les GESTIONNAIRES</li>
	* </ol>
	*/
	@Override
	int compareTo(IMonProfil pObject);

	
	
	/**
	 * clone l'objet métier.
	 *
	 * @return IMonProfil : clone.
	 * 
	 * @throws CloneNotSupportedException
	 */
	IMonProfil clone() 
				throws CloneNotSupportedException;

	

	/**
	* {@inheritDoc}
	*/
	@Override
	String toString();
	

	
	/**
	 * Affiche à la Console un objet métier (avec sauts à la ligne).<br/>
	 * Utile pour les développeurs.
	 *
	 * @return String : affichage console.
	 */
	String toStringConsole();

	
	
	/**
	 * Affiche à la Console la collection liée (avec sauts à la ligne).<br/>
	 * Utile pour les développeurs.
	 */
	void afficherConsoleCollectionLiee();
	
	
	
	/**
	 * Getter de l'ID en base.<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();



	/**
	* Setter de l'ID en base.<br/>
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);



	/**
	 * Getter du rôle de l'utilisateur dans la session courante.<br/>
	 * obtenu en concaténant ROLE_+profil+portée.<br/>
	 * Par exemple :
	 * <li>ROLE_ADMINISTRATEUR - FRANCE ENTIERE</li>
	 * <li>ROLE_GESTIONNAIRE - DIRA</li>
	 *
	 * @return <code>this.role</code> : String
	 */
	String getRole();



	/**
	 * Setter du rôle de l'utilisateur dans la session courante.<br/>
	 * obtenu en concaténant ROLE_+profil+portée.<br/>
	 * Par exemple :
	 * <li>ROLE_ADMINISTRATEUR - FRANCE ENTIERE</li>
	 * <li>ROLE_GESTIONNAIRE - DIRA</li>
	 *
	 * @param pRole : String :
	 * valeur à passer à <code>this.role</code>.
	 */
	void setRole(String pRole);



	/**
	 * Getter du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>ADMINISTRATEUR</li>
	 * <li>GESTIONNAIRE</li>
	 * </ul>
	 *
	 * @return <code>this.profil</code> : String
	 */
	String getProfil();



	/**
	 * Setter du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>ADMINISTRATEUR</li>
	 * <li>GESTIONNAIRE</li>
	 * </ul>
	 *
	 * @param pProfil : String :
	 * valeur à passer à <code>this.profil</code>.
	 */
	void setProfil(String pProfil);



	/**
	 * Getter de la portée du profil de l'utilisateur 
	 * dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 *
	 * @return <code>this.portee</code> : String
	 */
	String getPortee();



	/**
	 * Setter de la portée du profil de l'utilisateur 
	 * dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 *
	 * @param pPortee : String :
	 * valeur à passer à <code>this.portee</code>.
	 */
	void setPortee(String pPortee);



	/**
	 * Getter de la restriction (facultative) sur la portée 
	 * du profil de l'utilisateur dans la session courante.<br/>
	 *
	 * @return <code>this.restriction</code> : String
	 */
	String getRestriction();



	/**
	 * Setter de la restriction (facultative) sur la portée 
	 * du profil de l'utilisateur dans la session courante.<br/>
	 *
	 * @param pRestriction : String :
	 * valeur à passer à <code>this.restriction</code>.
	 */
	void setRestriction(String pRestriction);


	
	/**
	 * Getter de la Collection d'utilisateurs {@link MonCerbere} 
	 * possédant le présent profil.
	 *
	 * @return <code>this.monCerberes</code> : Collection&lt;IMonCerbere&gt;.
	 */
	Collection<IMonCerbere> getMonCerberes();


	
	/**
	 * Setter de la Collection d'utilisateurs {@link MonCerbere} 
	 * possédant le présent profil.
	 *
	 * @param pMonCerberes : Collection&lt;IMonCerbere&gt; :
	 * valeur à passer à <code>this.monCerberes</code>.
	 */
	void setMonCerberes(Collection<IMonCerbere> pMonCerberes);

	
	
} // FIN DE L'INTERFACE IMonProfil.------------------------------------------