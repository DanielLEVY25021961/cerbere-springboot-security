package levy.daniel.application.model.metier.utilisateur;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import i2.application.cerbere.commun.Application;
import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.Entreprise;
import i2.application.cerbere.commun.Habilitation;
import i2.application.cerbere.commun.Profil;
import i2.application.cerbere.commun.Utilisateur;
import levy.daniel.application.apptechnic.configurationmanagers.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IMonCerbere :<br/>
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
public interface IMonCerbere extends 
	Comparable<IMonCerbere>, Serializable, Cloneable
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
	 * " - "
	 */
	String MOINS_ESPACE = " - ";

	/**
	 * "null".<br/>
	 */
	String NULL = "null";

	/**
	 * "unused".<br/>
	 */
	String UNUSED = "unused";

	/**
	 * System.getProperty("line.separator")
	 */
	String SAUT_DE_LIGNE_PLATEFORME = System.getProperty("line.separator");

	/**
	 * "/"
	 */
	String SLASH = "/";

	/**
	 * "dd/MM/yyyy"<br/>
	 * "21/05/2010" par exemple.
	 */
	String PATTERN_DATE_FRANCAIS = "dd/MM/yyyy";
		
	/**
	 * "yyyy-MM-dd HH:mm:ss"<br/>
	 * "2020-05-21 23:52:14" par exemple.
	 */
	String PATTERN_DATETIME_LEXICO = "yyyy-MM-dd HH:mm:ss";

	
	
	/**
	 * Ajoute un Profil à <code>this.profils</code>.
	 * <ul>
	 * <li>ajoute l'objet métier à la collection dans l'objet lié.</li>
	 * </ul>
	 * - retourne false si pProfil == null.<br/>
	 *
	 * @param pProfil : 
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil
	 * 
	 * @return boolean : true si l'ajout a pu être effectué.
	 */
	boolean ajouterProfil(IMonProfil pProfil);

	
	
	/**
	 * Retire un Profil à <code>this.profils</code>.
	 * <ul>
	 * <li>retire l'objet métier à la collection dans l'objet lié.</li>
	 * </ul>
	 * - retourne false si pProfil == null.<br/>
	 *
	 * @param pProfil : 
	 * levy.daniel.application.model.metier.utilisateur.IMonProfil
	 * 
	 * @return boolean : true si le retrait a pu être effectué.
	 */
	boolean retirerProfil(IMonProfil pProfil);
	
	
	
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
	* <br/>
	* compare sur :
	* <ol>
	* <li>nom</li>
	* <li>prenom</li>
	* <li>login</li>
	* </ol>
	*/
	@Override
	int compareTo(IMonCerbere pObject);

	
	
	/**
	 * clone l'objet métier.
	 *
	 * @return IMonProfil : clone.
	 * 
	 * @throws CloneNotSupportedException
	 */
	IMonCerbere clone() 
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
	 * Getter du login (identifiant unique) de l'Utilisateur
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>) 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.login</code> : String
	 */
	String getLogin();



	/**
	 * Setter du login (identifiant unique) de l'Utilisateur
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>) 
	 * de l'objet Cerbere.
	 *
	 * @param pLogin : String :
	 * valeur à passer à <code>this.login</code>.
	 */
	void setLogin(String pLogin);



	/**
	 * Getter de la civilité (M, F) de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.civilite</code> : String
	 */
	String getCivilite();



	/**
	 * Setter de la civilité (M, F) de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pCivilite : String :
	 * valeur à passer à <code>this.civilite</code>.
	 */
	void setCivilite(String pCivilite);



	/**
	 * Getter du prénom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.prenom</code> : String
	 */
	String getPrenom();



	/**
	 * Setter du prénom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pPrenom : String :
	 * valeur à passer à <code>this.prenom</code>.
	 */
	void setPrenom(String pPrenom);



	/**
	 * Getter de nom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.nom</code> : String
	 */
	String getNom();



	/**
	 * Setter de nom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pNom : String :
	 * valeur à passer à <code>this.nom</code>.
	 */
	void setNom(String pNom);



	/**
	 * Getter du numéro de téléphone fixe de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.telephoneFixe</code> : String
	 */
	String getTelephoneFixe();



	/**
	 * Setter du numéro de téléphone fixe de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pTelephoneFixe : String :
	 * valeur à passer à <code>this.telephoneFixe</code>.
	 */
	void setTelephoneFixe(String pTelephoneFixe);



	/**
	 * Getter du e-mail de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.mel</code> : String
	 */
	String getMel();



	/**
	 * Setter du e-mail de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pMel : String :
	 * valeur à passer à <code>this.mel</code>.
	 */
	void setMel(String pMel);



	/**
	 * Getter du service de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	 * <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	 * <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 *
	 * @return <code>this.service</code> : String
	 */
	String getService();



	/**
	 * Setter du service de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	 * <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	 * <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 *
	 * @param pService : String :
	 * valeur à passer à <code>this.service</code>.
	 */
	void setService(String pService);



	/**
	 * Getter de unité 
	 * (par exemple "DIR ATLANTIQUE\Service des Routes\Unité Trafics") de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @return <code>this.unite</code> : String
	 */
	String getUnite();



	/**
	 * Setter de unité 
	 * (par exemple "DIR ATLANTIQUE\Service des Routes\Unité Trafics") de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 *
	 * @param pUnite : String :
	 * valeur à passer à <code>this.unite</code>.
	 */
	void setUnite(String pUnite);



	/**
	 * Getter de la Collection des 
	 * <code>levy.daniel.application.model
	 * .metier.utilisateur.IMonProfil</code> 
	 * disponibles pour l'utilisateur Cerbere.
	 *
	 * @return <code>this.profils</code> : Collection&lt;MonProfil&gt;
	 */
	Collection<IMonProfil> getProfils();



	/**
	 * Setter de la Collection des 
	 * <code>levy.daniel.application.model
	 * .metier.utilisateur.IMonProfil</code> 
	 * disponibles pour l'utilisateur Cerbere.<br/>
	 * actualise la collection bidirectionnelle des entités liées.
	 *
	 * @param pProfils : Collection&lt;MonProfil&gt; :
	 * valeur à passer à <code>this.profils</code>.
	 */
	void setProfils(Collection<IMonProfil> pProfils);



	/**
	 * Getter de la Date de création du User en base.
	 *
	 * @return <code>this.dateCreation</code> : java.time.LocalDate
	 */
	LocalDate getDateCreation();



	/**
	 * Setter de la Date de création du User en base.
	 *
	 * @param pDateCreation : java.time.LocalDate :
	 * valeur à passer à <code>this.dateCreation</code>.
	 */
	void setDateCreation(LocalDate pDateCreation);



	/**
	 * Getter de la Date de la dernière modification du User en base.
	 *
	 * @return <code>this.dateDerniereModification</code> : LocalDateTime
	 */
	LocalDateTime getDateDerniereModification();



	/**
	 * Setter de la Date de la dernière modification du User en base.
	 *
	 * @param pDateDerniereModification : LocalDateTime :
	 * valeur à passer à <code>this.dateDerniereModification</code>.
	 */
	void setDateDerniereModification(
			LocalDateTime pDateDerniereModification);


	
	/**
	 * Getter du boolean qui détermine si le USER est activé.
	 *
	 * @return <code>this.active</code> : boolean.<br/>
	 */
	boolean isActive();


	
	/**
	* Setter du boolean qui détermine si le USER est activé.
	*
	* @param pActive : boolean : 
	* valeur à passer à <code>this.active</code>.<br/>
	*/
	void setActive(boolean pActive);



	/**
	 * Getter de l'objet Cerbere 
	 * (<code>i2.application.cerbere.commun.Cerbere</code>) 
	 * récupéré en session en retour du filtre Cerbere.<br/>
	 *
	 * @return this.cerbere : i2.application.cerbere.commun.Cerbere.<br/>
	 */
	Cerbere getCerbere();



	/**
	* Setter de l'objet Cerbere 
	* (<code>i2.application.cerbere.commun.Cerbere</code>) 
	* récupéré en session en retour du filtre Cerbere.<br/>
	* <ul>
	* <li>passe le paramètre pCerbere 
	* à l'attribut <code>this.cerbere</code>.</li>
	* <li><b>alimente tous les attributs de la classe</b>.</li>
	* </ul>
	*
	* @param pCerbere : i2.application.cerbere.commun.Cerbere : 
	* valeur à passer à this.cerbere.<br/>
	*/
	void setCerbere(Cerbere pCerbere);



	/**
	 * Getter de l'objet Application 
	 * (<code>i2.application.cerbere.commun.Application</code>) 
	 * fourni par le <i>serveur CAS Cerbere</i> dans l'objet Cerbere 
	 * (<code>i2.application.cerbere.commun.Cerbere</code>) 
	 * récupéré en session en retour du filtre Cerbere
	 * modélisant la présente <strong>application</strong>.<br/>
	 *
	 * @return <code>this.application</code> : Application
	 */
	Application getApplication();



	/**
	 * Setter de l'objet Application 
	 * (<code>i2.application.cerbere.commun.Application</code>) 
	 * fourni par le <i>serveur CAS Cerbere</i> dans l'objet Cerbere 
	 * (<code>i2.application.cerbere.commun.Cerbere</code>) 
	 * récupéré en session en retour du filtre Cerbere
	 * modélisant la présente <strong>application</strong>.<br/>
	 *
	 * @return <code>this.application</code> : Application
	 *
	 * @param pApplication : Application :
	 * valeur à passer à <code>this.application</code>.
	 */
	void setApplication(Application pApplication);



	/**
	 * Getter de l'objet Entreprise 
	 * (<code>i2.application.cerbere.commun.Entreprise</code>) 
	 * fourni par Cerbere modélisant l'éventuelle entreprise 
	 * (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	 *
	 * @return <code>this.entreprise</code> : Entreprise
	 */
	Entreprise getEntreprise();



	/**
	 * Setter de l'objet Entreprise 
	 * (<code>i2.application.cerbere.commun.Entreprise</code>) 
	 * fourni par Cerbere modélisant l'éventuelle entreprise 
	 * (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	 *
	 * @param pEntreprise : Entreprise :
	 * valeur à passer à <code>this.entreprise</code>.
	 */
	void setEntreprise(Entreprise pEntreprise);



	/**
	 * Getter de l'objet Habilitation 
	 * (<code>i2.application.cerbere.commun.Habilitation</code>) 
	 * fourni par Cerbere modélisant 
	 * les divers profils 
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur.<br/>
	 *
	 * @return <code>this.habilitation</code> : Habilitation
	 */
	Habilitation getHabilitation();



	/**
	 * Setter de l'objet Habilitation 
	 * (<code>i2.application.cerbere.commun.Habilitation</code>) 
	 * fourni par Cerbere modélisant 
	 * les divers profils 
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur.<br/>
	 *
	 * @param pHabilitation : Habilitation :
	 * valeur à passer à <code>this.habilitation</code>.
	 */
	void setHabilitation(Habilitation pHabilitation);



	/**
	 * Getter de la liste des profils Cerbere
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur contenue 
	 * dans this.habilitation.<br/>
	 *
	 * @return <code>this.listeProfils</code> : List<Profil>
	 */
	List<Profil> getListeProfils();



	/**
	 * Setter de la liste des profils Cerbere
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur contenue 
	 * dans this.habilitation.<br/>
	 *
	 * @param pListeProfils : List<Profil> :
	 * valeur à passer à <code>this.listeProfils</code>.
	 */
	void setListeProfils(List<Profil> pListeProfils);



	/**
	 * Getter de l'objet Utilisateur 
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>)
	 * fourni par Cerbere modélisant l'utilisateur connecté.<br/>
	 *
	 * @return <code>this.utilisateur</code> : Utilisateur
	 */
	Utilisateur getUtilisateur();



	/**
	 * Setter de l'objet Utilisateur 
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>)
	 * fourni par Cerbere modélisant l'utilisateur connecté.<br/>
	 *
	 * @param pUtilisateur : Utilisateur :
	 * valeur à passer à <code>this.utilisateur</code>.
	 */
	void setUtilisateur(Utilisateur pUtilisateur);
	
	

} // FIN DE L'INTERFACE IMonCerbere.-----------------------------------------