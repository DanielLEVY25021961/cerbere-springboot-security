package levy.daniel.application.model.metier.utilisateur.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import i2.application.cerbere.commun.Application;
import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.Entreprise;
import i2.application.cerbere.commun.Habilitation;
import i2.application.cerbere.commun.Profil;
import i2.application.cerbere.commun.Utilisateur;
import levy.daniel.application.model.metier.utilisateur.IMonCerbere;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonProfilEntityJPA;


/**
 * CLASSE MonCerbere :<br/>
 * Classe modélisant la partie utile en base de 
 * l'OBJET CERBERE retourné par le SERVEUR CERBERE 
 * après l'authentification CERBERE d'un internaute.<br/>
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
 * @since 28 déc. 2019
 */
public class MonCerbere implements IMonCerbere {

	// ************************ATTRIBUTS************************************/

	/**
	 * id en base.<br/>
	 */
	private Long id;
	
	/**
	 * login (identifiant unique) de l'Utilisateur
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>) 
	 * de l'objet Cerbere.
	 */
	private String login;
	
	/**
	 * civilité (M, F) de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String civilite;

	/**
	 * prénom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String prenom;

	/**
	 * nom de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String nom;
	
	/**
	 * numéro de téléphone fixe de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String telephoneFixe;
	
	/**
	 * e-mail de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String mel;
	
	/**
	 * service de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	 * <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	 * <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 */
	private String service;
	
	/**
	 * unité 
	 * (par exemple "DIR ATLANTIQUE\Service des Routes\Unité Trafics") de 
	 * <code>i2.application.cerbere.commun.Utilisateur</code> 
	 * de l'objet Cerbere.
	 */
	private String unite;

	/**
	 * Collection des 
	 * <code>levy.daniel.application.model
	 * .metier.utilisateur.IMonProfil</code> 
	 * disponibles pour l'utilisateur Cerbere.
	 */
	private Collection<IMonProfil> profils 
		= new ArrayList<IMonProfil>();
	
	/**
	 * Date de création du User en base.
	 */
	private LocalDate dateCreation;
	
	/**
	 * Date de la dernière modification du User en base.
	 */
	private LocalDateTime dateDerniereModification;
	
	/**
	 * détermine si le USER est activé.
	 */
	private boolean active;
	
	/**
	 * objet Cerbere (<code>i2.application.cerbere.commun.Cerbere</code>) 
	 * récupéré en session en retour du filtre Cerbere.<br/>
	 */
	private transient Cerbere cerbere;
	
	/**
	 * objet Application 
	 * (<code>i2.application.cerbere.commun.Application</code>) 
	 * fourni par le <i>serveur CAS Cerbere</i> dans l'objet Cerbere 
	 * (<code>i2.application.cerbere.commun.Cerbere</code>) 
	 * récupéré en session en retour du filtre Cerbere
	 * modélisant la présente <strong>application</strong>.<br/>
	 */
	private transient Application application;
	
	/**
	 * objet Entreprise 
	 * (<code>i2.application.cerbere.commun.Entreprise</code>) 
	 * fourni par Cerbere modélisant l'éventuelle entreprise 
	 * (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	 */
	private transient Entreprise entreprise;
	
	/**
	 * objet Habilitation 
	 * (<code>i2.application.cerbere.commun.Habilitation</code>) 
	 * fourni par Cerbere modélisant 
	 * les divers profils 
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur.<br/>
	 */
	private transient Habilitation habilitation;
	
	/**
	 * liste des profils Cerbere
	 * (<code>i2.application.cerbere.commun.Profil</code>) 
	 * de l'utilisateur contenue 
	 * dans this.habilitation.<br/>
	 */
	private transient List<Profil> listeProfils;
	
	/**
	 * objet Utilisateur 
	 * (<code>i2.application.cerbere.commun.Utilisateur</code>)
	 * fourni par Cerbere modélisant l'utilisateur connecté.<br/>
	 */
	private transient Utilisateur utilisateur;

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbere.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbere() {
		
		this(null
				, null
				, null, null, null
				, null, null
				, null
				, null
				, null
				, null);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * alimente automatiquement <code>this.service</code>.
	 *
	 * @param pLogin : String : login (identifiant unique) de l'Utilisateur.
	 * @param pCivilite : String : civilité (M, F).
	 * @param pPrenom : String : prénom
	 * @param pNom : String : nom
	 * @param pTelephoneFixe : String : numéro de téléphone fixe.
	 * @param pMel : String : e-mail. 
	 * @param pService : String : Service de l'utilisateur.
	 * @param pUnite : String : Unité de l'utilisateur.
	 * @param pProfils : Collection&lt;IMonProfil&gt; : 
	 * Collection des profils de l'utilisateur.
	 * @param pDateCreation : LocalDate : date de création de l'utilisateur.
	 * @param pDateDerniereModification : LocalDateTime : 
	 * date de dernière modification de l'utilisateur.
	 */
	public MonCerbere(
			final String pLogin
			, final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pTelephoneFixe
			, final String pMel
			, final String pUnite
			, final Collection<IMonProfil> pProfils
			, final LocalDate pDateCreation
			, final LocalDateTime pDateDerniereModification) {
		
		this(null
				, pLogin
				, pCivilite, pPrenom, pNom
				, pTelephoneFixe, pMel
				, pUnite
				, pProfils
				, pDateCreation
				, pDateDerniereModification);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	/**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * <ul>
	 * <li>alimente automatiquement <code>this.service</code>.</li>
	 * <li>actualise la collection bidirectionnelle des entités liées.</li>
	 * </ul>
	 *
	 * @param pId : Long : id en base.<br/>
	 * @param pLogin : String : login (identifiant unique) de l'Utilisateur.
	 * @param pCivilite : String : civilité (M, F).
	 * @param pPrenom : String : prénom
	 * @param pNom : String : nom
	 * @param pTelephoneFixe : String : numéro de téléphone fixe.
	 * @param pMel : String : e-mail. 
	 * @param pService : String : Service de l'utilisateur.
	 * @param pUnite : String : Unité de l'utilisateur.
	 * @param pProfils : Collection&lt;IMonProfil&gt; : 
	 * Collection des profils de l'utilisateur.
	 * @param pDateCreation : LocalDate : date de création de l'utilisateur.
	 * @param pDateDerniereModification : LocalDateTime : 
	 * date de dernière modification de l'utilisateur.
	 */
	public MonCerbere(
			final Long pId
			, final String pLogin
			, final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pTelephoneFixe
			, final String pMel
			, final String pUnite
			, final Collection<IMonProfil> pProfils
			, final LocalDate pDateCreation
			, final LocalDateTime pDateDerniereModification) {

		super();
		
		this.id = pId;
		this.login = pLogin;
		this.civilite = pCivilite;
		this.prenom = pPrenom;
		this.nom = pNom;
		this.telephoneFixe = pTelephoneFixe;
		this.mel = pMel;
		this.unite = pUnite;
		this.profils = pProfils;
		this.dateCreation = pDateCreation;
		this.dateDerniereModification = pDateDerniereModification;
		
		/* alimente automatiquement this.service. */
		this.service = this.fournirService(this.unite);
				
		/* actualise la collection bidirectionnelle des entités liées. */
		this.actualiserCollectionBidirectionnelle();

	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________


	
	/**
	 * CONSTRUCTEUR MALIN.<br/>
	 * alimente tous les attributs de la classe.
	 *
	 * @param pCerbere : i2.application.cerbere.commun.Cerbere
	 */
	public MonCerbere(final Cerbere pCerbere) {
		
		super();
		
		this.cerbere = pCerbere;
				
		/* alimente tous les attributs de la classe. */
		this.alimenterAttributsCerbere();
		
	} // Fin de CONSTRUCTEUR MALIN.________________________________________
	
	
	
	/**
	 * alimente tous les attributs Cerbere de la présente classe.<br/>
	 * <ul>
	 * <li>méthode <b>appelée directement dans le constructeur</b>.</li>
	 * <li>méthode appelée par le SETTER setCerbere(...).</li>
	 * <li>alimente <code>this.application</code>.</li>
	 * <li>alimente <code>this.entreprise</code> 
	 * (null si le compte professionnel SIRENXXX n'existe pas).</li>
	 * <li>alimente <code>this.habilitation</code>.</li>
	 * <li>alimente <code>this.listeProfils</code>.</li>
	 * <li>alimente <code>this.utilisateur</code>.</li>
	 * <li>alimente <code>this.login</code>.</li>
	 * <li>alimente <code>this.civilite</code>.</li>
	 * <li>alimente <code>this.prenom</code>.</li>
	 * <li>alimente <code>this.nom</code>.</li>
	 * <li>alimente <code>this.mel</code>.</li>
	 * <li>alimente <code>this.unite</code>.</li>
	 * <li>alimente <code>this.service</code>.</li>
	 * <li>alimente <code>this.profils</code>.</li>
	 * </ul>
	 * - ne fait rien si this.cerbere == null.<br/>
	 * <br/>
	 */
	private void alimenterAttributsCerbere() {
		
		/* ne fait rien si this.cerbere == null. */
		if (this.cerbere == null) {
			return;
		}
		
		/* alimente this.application. */
		this.application = this.cerbere.getApplication();
		
		/* alimente this.entreprise. */
		this.entreprise = this.cerbere.getEntreprise();
		
		/* alimente this.habilitation. */
		this.habilitation = this.cerbere.getHabilitation();
		
		/* alimente this.listeProfils. */
		if (this.habilitation != null) {
			this.listeProfils = this.habilitation.getTousProfils();
		}
		
		/* alimente this.utilisateur. */
		this.utilisateur = this.cerbere.getUtilisateur();
		
		if (this.utilisateur != null) {
			
			/* alimente this.login. */
			this.login = this.utilisateur.getLogin();
			
			/* alimente this.civilite. */
			this.civilite = this.utilisateur.getCivilite();
			
			/* alimente this.prenom. */
			this.prenom = this.utilisateur.getPrenom();
			
			/* alimente this.nom. */
			this.nom = this.utilisateur.getNom();
			
			/* alimente this.telephoneFixe. */
			this.telephoneFixe = this.utilisateur.getNumTelFixe();
			
			/* alimente this.mel. */
			this.mel = this.utilisateur.getMel();
			
			/* alimente this.unite. */
			this.unite = this.utilisateur.getUnite();
			
			/* alimente this.service. */
			this.service = this.fournirService(this.unite);
			
			/* alimente this.profils. */
			this.alimenterProfils();
			
		}
		
	} // Fin de alimenterAttributsCerbere()._______________________________


	
	/**
	 * Alimente la Collection&ltIMonProfil&gt; 
	 * <code>this.profils</code> à partir des 
	 * <code>i2.application.cerbere.commun.Profil</code> contenus dans 
	 * <code>this.listeProfils</code>.<br/>
	 * <ul>
	 * <li>actualise la collection bidirectionnelle des entités liées.</li>
	 * </ul>
	 */
	private void alimenterProfils() {
		
		/* ne fait rien si this.listeProfils == null. */
		if (this.listeProfils == null) {
			return;
		}
		
		for (final Profil profil : this.listeProfils) {
			
			final IMonProfil monProfil 
				= new MonProfilEntityJPA(
						profil.getNom()
						, profil.getPortee()
						, profil.getRestriction());
			
			/* actualise la collection bidirectionnelle des entités liées. */
			if (!monProfil.getMonCerberes().contains(this)) {
				monProfil.getMonCerberes().add(this);
			}

			/* ajoute le profil à this.profil si pas de doublons. */
			if (!this.profils.contains(monProfil)) {
				this.profils.add(monProfil);
			}
						
		}
		
	} // Fin de alimenterProfils().________________________________________


	
	/**
	 * actualise la collection bidirectionnelle des entités liées.
	 */
	private void actualiserCollectionBidirectionnelle() {
		
		if (this.profils != null) {
			
			for (final IMonProfil profil : this.profils) {
				
				/* ajoute le présent objet métier à la collection 
				 * bidirectionnelle liée si pas de doublons. */
				if (!profil.getMonCerberes().contains(this)) {
					
					profil.getMonCerberes().add(this);
					
				}				
			}
		}
		
	} // Fin de actualiserCollectionBidirectionnelle().____________________
	

	
	/**
	 *  extrait le service d'une Unité.<br/>
	 *  Par exemple, retourne <b>DIRA</b> pour 
	 *  "DIRA/SIEER/CIGT/PC Bordeaux".<br/>
	 *  <br/>
	 *  - retourne null si pUnite est blank.<br/>
	 *  - retourne null si exception de split.
	 *  <br/>
	 *
	 * @param pUnite : String : unité d'un User 
	 * comme "DIRA/SIEER/CIGT/PC Bordeaux".
	 * 
	 * @return String : le premier élément de l'unité.
	 */
	private String fournirService(final String pUnite) {
		
		/* retourne null si pUnite est blank. */
		if (StringUtils.isBlank(pUnite)) {
			return null;
		}
		
		final Pattern patternSlash = Pattern.compile(SLASH);
		
		try {

			final String[] elements = patternSlash.split(pUnite);
			
			/* retourne le premier élément de l'unité. */
			return elements[0];

		} catch (final Exception e) {

			/* retourne null si exception de split. */
			return null;

		}
		
	} // Fin de fournirService(...)._______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final boolean ajouterProfil(final IMonProfil pProfil) {
		
		/* retourne false si pProfil == null. */
		if (pProfil == null) {
			return false;
		}
		
		if (this.profils == null) {
			this.profils = new ArrayList<IMonProfil>();
		}
		
		if (!this.profils.contains(pProfil)) {
			
			/* ajoute l'objet métier à la collection dans l'objet lié. */
			pProfil.getMonCerberes().add(this);
			
			return this.profils.add(pProfil);
			
		}
		
		return false;
		
	} // Fin de ajouterProfil(...).________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public final boolean retirerProfil(final IMonProfil pProfil) {
		
		/* retourne false si pProfil == null. */
		if (pProfil == null) {
			return false;
		}
		
		if (this.profils == null) {
			this.profils = new ArrayList<IMonProfil>();
		}
		
		/* retire l'objet métier à la collection dans l'objet lié. */
		pProfil.getMonCerberes().remove(this);
		
		return this.profils.remove(pProfil);
		
	} // Fin de retirerProfil(...).________________________________________

	
			
	/**
	* {@inheritDoc}
	*/
	@Override
	public final int hashCode() {
		return Objects.hash(this.getLogin());
	} // Fin de hashCode().________________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final boolean equals(final Object pObject) {

		if (this == pObject) {
			return true;
		}

		if (!(pObject instanceof IMonCerbere)) {
			return false;
		}

		final IMonCerbere other = (IMonCerbere) pObject;
		
		return Objects.equals(this.getLogin(), other.getLogin());

	} // Fin de equals(...)._______________________________________________
	
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public final int compareTo(final IMonCerbere pObject) {
		
		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}
		
		int compareNom = 0;
		int comparePrenom = 0;
		int compareLogin = 0;
		
		if (this.getNom() == null) {
			if (pObject.getNom() != null) {
				return +1;
			}
		} else {
			
			if (pObject.getNom() == null) {
				return -1;
			}
			
			compareNom 
				= this.getNom().compareToIgnoreCase(pObject.getNom());
			
			if (compareNom != 0) {
				return compareNom;
			}
		}
		
		if (this.getPrenom() == null) {
			if (pObject.getPrenom() != null) {
				return +1;
			}
		} else {
			
			if (pObject.getPrenom() == null) {
				return -1;
			}
			
			comparePrenom 
				= this.getPrenom().compareToIgnoreCase(pObject.getPrenom());
			
			if (comparePrenom != 0) {
				return comparePrenom;
			}
		}
		
		if (this.getLogin() == null) {
			
			if (pObject.getLogin() != null) {
				return +1;
			}
			
			return 0;
		}
		
		compareLogin 
			= this.getLogin().compareToIgnoreCase(pObject.getLogin());
		
		return compareLogin;
		
	} // Fin de compareTo(...).____________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public final MonCerbere clone() throws CloneNotSupportedException {
		
		final IMonCerbere clone 
			= (IMonCerbere) super.clone();
		
		/* clonage profond de la collection. */
		Collection<IMonProfil> profilsClone = null;
		
		if (this.profils != null) {
			profilsClone 
				= new ArrayList<IMonProfil>(this.profils);
		}
				
		/* clonage profond de la dateCreation. */
		LocalDate dateCreationClone = null;
		
		if (this.dateCreation != null) {
			dateCreationClone 
				= LocalDate.from(this.dateCreation);
		}
		
		/* clonage profond de la dateDerniereModification. */
		LocalDateTime dateDerniereModificationClone = null;
		
		if (this.dateDerniereModification != null) {
			dateDerniereModificationClone 
				= LocalDateTime.from(this.dateDerniereModification);
		}
					
		clone.setId(this.getId());
		clone.setLogin(this.getLogin());
		clone.setCivilite(this.getCivilite());
		clone.setPrenom(this.getPrenom());
		clone.setNom(this.getNom());
		clone.setTelephoneFixe(this.getTelephoneFixe());
		clone.setMel(this.getMel());
		clone.setService(this.getService());
		clone.setUnite(this.getUnite());
		clone.setProfils(profilsClone);
		clone.setDateCreation(dateCreationClone);
		clone.setDateDerniereModification(dateDerniereModificationClone);
		
		clone.setCerbere(this.getCerbere());
		clone.setApplication(this.getApplication());
		clone.setEntreprise(this.getEntreprise());
		clone.setHabilitation(this.getHabilitation());
		clone.setListeProfils(this.getListeProfils());
		clone.setUtilisateur(this.getUtilisateur());
		
		return (MonCerbere) clone;
		
	} // Fin de clone().___________________________________________________
	


	/**
	* {@inheritDoc}
	*/
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("MonCerbere [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("login=");
		if (this.getLogin() != null) {			
			stb.append(this.getLogin());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("civilite=");
		if (this.getCivilite() != null) {
			stb.append(this.getCivilite());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("prenom=");
		if (this.getPrenom() != null) {
			stb.append(this.getPrenom());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("nom=");
		if (this.getNom() != null) {
			stb.append(this.getNom());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("telephoneFixe=");
		if (this.getTelephoneFixe() != null) {
			stb.append(this.getTelephoneFixe());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("mel=");
		if (this.getMel() != null) {
			stb.append(this.getMel());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("service=");
		if (this.getService() != null) {
			stb.append(this.getService());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("unite=");
		if (this.getUnite() != null) {
			stb.append(this.getUnite());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("profils=");
		if (this.getProfils() != null) {
			
			stb.append('{');
			
			final int taille = this.getProfils().size();
			int compteur = 0;
			
			for (final IMonProfil profil : this.getProfils()) {
				
				compteur++;
				
				stb.append(profil.getRole());
				
				if (compteur < taille) {
					stb.append(VIRGULE_ESPACE);
				}
				
			}
			
			stb.append('}');
			
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("dateCreation=");
		if (this.getDateCreation() != null) {
			
			final DateTimeFormatter formatter 
				= DateTimeFormatter.ofPattern(PATTERN_DATE_FRANCAIS);
		
			stb.append(this.getDateCreation().format(formatter));
			
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("dateDerniereModification=");
		if (this.getDateDerniereModification() != null) {
			
			final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern(PATTERN_DATETIME_LEXICO);
	
			stb.append(this.getDateDerniereModification().format(formatter));
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
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("login=");
		if (this.getLogin() != null) {			
			stb.append(this.getLogin());
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("civilite=");
		if (this.getCivilite() != null) {
			stb.append(this.getCivilite());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("prenom=");
		if (this.getPrenom() != null) {
			stb.append(this.getPrenom());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("nom=");
		if (this.getNom() != null) {
			stb.append(this.getNom());
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("telephoneFixe=");
		if (this.getTelephoneFixe() != null) {
			stb.append(this.getTelephoneFixe());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("mel=");
		if (this.getMel() != null) {
			stb.append(this.getMel());
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("service=");
		if (this.getService() != null) {
			stb.append(this.getService());
		} else {
			stb.append(NULL);
		}

		stb.append(VIRGULE_ESPACE);

		stb.append("unite=");
		if (this.getUnite() != null) {
			stb.append(this.getUnite());
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("profils ************ ");
		stb.append(SAUT_DE_LIGNE_PLATEFORME);
		if (this.getProfils() != null) {
						
			final int taille = this.getProfils().size();
			int compteur = 0;
			
			for (final IMonProfil profil : this.getProfils()) {
				
				compteur++;
				
				stb.append(profil.getRole());
				
				if (compteur < taille) {
					stb.append(SAUT_DE_LIGNE_PLATEFORME);
				}
				
			}
						
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("dateCreation=");
		if (this.getDateCreation() != null) {
			
			final DateTimeFormatter formatter 
				= DateTimeFormatter.ofPattern(PATTERN_DATE_FRANCAIS);
		
			stb.append(this.getDateCreation().format(formatter));
			
		} else {
			stb.append(NULL);
		}

		stb.append(SAUT_DE_LIGNE_PLATEFORME);

		stb.append("dateDerniereModification=");
		if (this.getDateDerniereModification() != null) {
			
			final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern(PATTERN_DATETIME_LEXICO);
	
			stb.append(this.getDateDerniereModification().format(formatter));
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
		
		if (this.getProfils() != null) {
			
			for (final IMonProfil profil : this.getProfils()) {
				
				System.out.println(profil.toStringConsole());
				System.out.println();
				
			}

		}
				
	} // Fin de afficherConsoleCollectionLiee().___________________________
	
	
	
	/**
	* {@inheritDoc}<br/>
	* <code>"id;login;civilite;prenom;nom;telephoneFixe;mel;service;unite;
	* profils;dateCreation;dateDerniereModification;"</code><br/>
	* <br/>
	*/
	@Override
	public final String fournirEnTeteCsv() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append("id");
		stb.append(POINT_VIRGULE);
		stb.append("login");
		stb.append(POINT_VIRGULE);
		stb.append("civilite");
		stb.append(POINT_VIRGULE);
		stb.append("prenom");
		stb.append(POINT_VIRGULE);
		stb.append("nom");
		stb.append(POINT_VIRGULE);
		stb.append("telephoneFixe");
		stb.append(POINT_VIRGULE);
		stb.append("mel");
		stb.append(POINT_VIRGULE);
		stb.append("service");
		stb.append(POINT_VIRGULE);
		stb.append("unite");
		stb.append(POINT_VIRGULE);
		stb.append("profils");
		stb.append(POINT_VIRGULE);
		stb.append("dateCreation");
		stb.append(POINT_VIRGULE);
		stb.append("dateDerniereModification");
		stb.append(POINT_VIRGULE);
		
		return stb.toString();

	} // Fin de fournirEnTeteCsv().________________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;login;civilite;prenom;nom;telephoneFixe;mel;service;unite;
	* profils;dateCreation;dateDerniereModification;"</code><br/>
	* <br/>
	*/
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();
		
		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLogin());
		stb.append(POINT_VIRGULE);
		stb.append(this.getCivilite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrenom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTelephoneFixe());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMel());
		stb.append(POINT_VIRGULE);
		stb.append(this.getService());
		stb.append(POINT_VIRGULE);
		stb.append(this.getUnite());
		stb.append(POINT_VIRGULE);
		if (this.getProfils() != null) {
			
			stb.append('{');
			
			final int taille = this.getProfils().size();
			int compteur = 0;
			
			for (final IMonProfil profil : this.getProfils()) {
				
				compteur++;
				
				stb.append(profil.getRole());
				
				if (compteur < taille) {
					stb.append(VIRGULE_ESPACE);
				}
				
			}
			
			stb.append('}');
		} else {
			stb.append(NULL);
		}

		stb.append(POINT_VIRGULE);
		
		if (this.getDateCreation() != null) {
			
			final DateTimeFormatter formatter 
				= DateTimeFormatter.ofPattern(PATTERN_DATE_FRANCAIS);
		
			stb.append(this.getDateCreation().format(formatter));
			
		} else {
			stb.append(NULL);
		}

		stb.append(POINT_VIRGULE);
		
		if (this.getDateDerniereModification() != null) {
			
			final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern(PATTERN_DATETIME_LEXICO);
	
			stb.append(this.getDateDerniereModification().format(formatter));
		} else {
			stb.append(NULL);
		}

		stb.append(POINT_VIRGULE);
		
		return stb.toString();
		
	} // Fin de fournirStringCsv().________________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;login;civilite;prenom;nom;telephoneFixe;mel;service;unite;
	* profils;dateCreation;dateDerniereModification;"</code><br/>
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
			entete = "login";
			break;

		case 2:
			entete = "civilite";
			break;

		case 3:
			entete = "prenom";
			break;
			
		case 4:
			entete = "nom";
			break;
			
		case 5:
			entete = "telephoneFixe";
			break;
			
		case 6:
			entete = "mel";
			break;
			
		case 7:
			entete = "service";
			break;
			
		case 8:
			entete = "unite";
			break;
			
		case 9:
			entete = "profils";
			break;
			
		case 10:
			entete = "dateCreation";
			break;
			
		case 11:
			entete = "dateDerniereModification";
			break;
			
		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________



	/**
	* {@inheritDoc}<br/>
	* <code>"id;login;civilite;prenom;nom;telephoneFixe;mel;service;unite;
	* profils;dateCreation;dateDerniereModification;"</code><br/>
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
			valeur = this.getLogin();
			break;

		case 2:
			valeur = this.getCivilite();
			break;

		case 3:
			valeur = this.getPrenom();
			break;

		case 4:
			valeur = this.getNom();
			break;

		case 5:
			valeur = this.getTelephoneFixe();
			break;

		case 6:
			valeur = this.getMel();
			break;

		case 7:
			valeur = this.getService();
			break;

		case 8:
			valeur = this.getUnite();
			break;

		case 9:
			
			final StringBuffer stb = new StringBuffer();
			
			if (this.getProfils() != null) {
				
				stb.append('{');
				
				final int taille = this.getProfils().size();
				int compteur = 0;
				
				for (final IMonProfil profil : this.getProfils()) {
					
					compteur++;
					
					stb.append(profil.getRole());
					
					if (compteur < taille) {
						stb.append(VIRGULE_ESPACE);
					}
					
				}
				
				stb.append('}');
				
			} else {
				stb.append(NULL);
			}

			valeur = stb.toString();
			break;

		case 10:
			if (this.getDateCreation() != null) {
				
				final DateTimeFormatter formatter 
					= DateTimeFormatter.ofPattern(PATTERN_DATE_FRANCAIS);
			
				valeur = this.getDateCreation().format(formatter);
				
			} else {
				valeur = NULL;
			}

			break;

		case 11:
			if (this.getDateDerniereModification() != null) {
				
				final DateTimeFormatter formatter 
					= DateTimeFormatter.ofPattern(PATTERN_DATETIME_LEXICO);
		
				valeur = this.getDateDerniereModification().format(formatter);
			} else {
				valeur = NULL;
			}
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
	public String getLogin() {	
		return this.login;	
	} // Fin de getLogin().________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setLogin(final String pLogin) {	
		this.login = pLogin;	
	} // Fin de setLogin(...)._____________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public String getCivilite() {	
		return this.civilite;	
	} // Fin de getCivilite()._____________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setCivilite(final String pCivilite) {	
		this.civilite = pCivilite;	
	} // Fin de setCivilite(...).__________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getPrenom() {	
		return this.prenom;	
	} // Fin de getPrenom()._______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setPrenom(final String pPrenom) {	
		this.prenom = pPrenom;	
	} // Fin de setPrenom(...).____________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getNom() {	
		return this.nom;	
	} // Fin de getNom().__________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setNom(final String pNom) {	
		this.nom = pNom;	
	} // Fin de setNom(...)._______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getTelephoneFixe() {	
		return this.telephoneFixe;	
	} // Fin de getTelephoneFixe().________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setTelephoneFixe(final String pTelephoneFixe) {	
		this.telephoneFixe = pTelephoneFixe;	
	} // Fin de setTelephoneFixe(...)._____________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getMel() {	
		return this.mel;	
	} // Fin de getMel().__________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setMel(final String pMel) {	
		this.mel = pMel;	
	} // Fin de setMel(...)._______________________________________________


		
	/**
	* {@inheritDoc}
	*/
	@Override
	public String getService() {	
		return this.service;	
	} // Fin de getService().______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setService(final String pService) {	
		this.service = pService;	
	} // Fin de setService(...).___________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public String getUnite() {	
		return this.unite;	
	} // Fin de getUnite().________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setUnite(final String pUnite) {	
		this.unite = pUnite;	
	} // Fin de setUnite(...)._____________________________________________

	
			
	/**
	* {@inheritDoc}
	*/
	@Override
	public Collection<IMonProfil> getProfils() {	
		return this.profils;	
	} // Fin de getProfils().______________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setProfils(final Collection<IMonProfil> pProfils) {	
		
		this.profils = pProfils;
		
		/* actualise la collection bidirectionnelle des entités liées. */
		this.actualiserCollectionBidirectionnelle();
		
	} // Fin de setProfils(...).___________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public LocalDate getDateCreation() {	
		return this.dateCreation;	
	} // Fin de getDateCreation()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setDateCreation(final LocalDate pDateCreation) {	
		this.dateCreation = pDateCreation;	
	} // Fin de setDateCreation(...).______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public LocalDateTime getDateDerniereModification() {	
		return this.dateDerniereModification;	
	} // Fin de getDateDerniereModification()._____________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setDateDerniereModification(
			final LocalDateTime pDateDerniereModification) {	
		this.dateDerniereModification = pDateDerniereModification;	
	} // Fin de setDateDerniereModification(...).__________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean isActive() {
		return this.active;
	} // Fin de isActive().________________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setActive(final boolean pActive) {
		this.active = pActive;
	} // Fin de setActive(...).____________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public Cerbere getCerbere() {
		return this.cerbere;
	} // Fin de getCerbere().______________________________________________


		
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setCerbere(
			final Cerbere pCerbere) {
		
		this.cerbere = pCerbere;
		
		/* alimente tous les attributs de la classe. */
		this.alimenterAttributsCerbere();
		
	} // Fin de setCerbere(...).___________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Application getApplication() {	
		return this.application;	
	} // Fin de getApplication().__________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setApplication(final Application pApplication) {	
		this.application = pApplication;	
	} // Fin de setApplication(...)._______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Entreprise getEntreprise() {	
		return this.entreprise;	
	} // Fin de getEntreprise().___________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setEntreprise(final Entreprise pEntreprise) {	
		this.entreprise = pEntreprise;	
	} // Fin de setEntreprise(...).________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Habilitation getHabilitation() {	
		return this.habilitation;	
	} // Fin de getHabilitation()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setHabilitation(final Habilitation pHabilitation) {	
		this.habilitation = pHabilitation;	
	} // Fin de setHabilitation(...).______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public List<Profil> getListeProfils() {	
		return this.listeProfils;	
	} // Fin de getListeProfils()._________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setListeProfils(final List<Profil> pListeProfils) {	
		this.listeProfils = pListeProfils;	
	} // Fin de setListeProfils(...).______________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Utilisateur getUtilisateur() {	
		return this.utilisateur;	
	} // Fin de getUtilisateur().__________________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public void setUtilisateur(final Utilisateur pUtilisateur) {	
		this.utilisateur = pUtilisateur;	
	} // Fin de setUtilisateur(...)._______________________________________
	
		
	
} // FIN DE LA CLASSE MonCerbere.--------------------------------------
