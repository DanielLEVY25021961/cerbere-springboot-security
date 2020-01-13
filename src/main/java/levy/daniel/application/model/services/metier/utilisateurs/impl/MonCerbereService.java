package levy.daniel.application.model.services.metier.utilisateurs.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import i2.application.cerbere.commun.Cerbere;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MauvaisParametreRunTimeException;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;
import levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl.MonCerbereDAOJPASpring;
import levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl.MonProfilDAOJPASpring;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonProfilEntityJPA;
import levy.daniel.application.model.services.metier.utilisateurs.IMonCerbereService;
import levy.daniel.application.model.services.metier.utilisateurs.IReponseCerbereService;
import levy.daniel.application.security.MonCerbereUserDetails;


/**
 * CLASSE MonCerbereService :<br/>
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
@Service(value = "MonCerbereService")
@Transactional
public class MonCerbereService implements IMonCerbereService {

	// ************************ATTRIBUTS************************************/

	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';

	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";

	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";

	/**
	 * " - "
	 */
	public static final String MOINS_ESPACE = " - ";

	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * MonCerbereDAOJPASpring
	 */
	@Autowired
	@Qualifier("MonCerbereDAOJPASpring")
	private transient MonCerbereDAOJPASpring monCerbereDAO;
	
	/**
	 * MonProfilDAOJPASpring
	 */
	@Autowired
	@Qualifier("MonProfilDAOJPASpring")
	private transient MonProfilDAOJPASpring monProfilDAO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(MonCerbereService.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereService() {		
		super();		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	* {@inheritDoc}
	*/
	@Override
	public IReponseCerbereService loggerCerbere(final Cerbere pCerbere) 
			throws Exception {
		
		/* throw une Exception si pCerbere == null. */
		if (pCerbere == null) {
			
			final String message = "Pas d'objet CERBERE en session !";
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		final IReponseCerbereService reponse = new ReponseCerbereService();
		
		/* crée un MonCerbereEntityJPA à partir de l'Objet Cerbere en session. */
		final MonCerbereEntityJPA monCerbereEntityJPA 
			= new MonCerbereEntityJPA(pCerbere);
		
		/* crée un MonCerbereUserDetails à partir du monCerbereEntityJPA. */
		final MonCerbereUserDetails userDetails 
			= new MonCerbereUserDetails(monCerbereEntityJPA);
		
		/* crée un Authentication SPRING en lui passant le MonCerbereUserDetails. */
		final Authentication authenticationCerbere 
			= new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		
		/* trie les profils dans l'objet Cerbere. */
		final List<IMonProfil> listeTriee 
			= new ArrayList<IMonProfil>(monCerbereEntityJPA.getProfils());
		Collections.sort(listeTriee);

		/* détermine le profil par défaut. */
		final IMonProfil profilParDefaut 
			= this.fournirProfilParDefaut(monCerbereEntityJPA);
		
		/* détermine le gestionnaire si le CERBERE n'a qu'un seul profil. */
		final EnumGestionnaire gestionnaire 
			= this.fournirGestionnaire(monCerbereEntityJPA);
		
		/* détermine le profil choisi si le CERBERE n'a qu'un seul profil. */
		final IMonProfil profilChoisi 
			= this.fournirProfilChoisi(monCerbereEntityJPA);

		/* alimente la réponse du service. */
		reponse.setCerbere(pCerbere);
		reponse.setMonCerbereEntityJPA(monCerbereEntityJPA);
		reponse.setUserDetails(userDetails);
		reponse.setAuthenticationCerbere(authenticationCerbere);
		reponse.setListeDroitsTriee(listeTriee);
		reponse.setProfilParDefaut(profilParDefaut);
		reponse.setGestionnaire(gestionnaire);
		reponse.setProfilChoisi(profilChoisi);
		
		/* retourne la réponse du service. */
		return reponse;
		
	} // Fin de loggerCerbere(...).________________________________________

	
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public MonCerbereEntityJPA save(
			final MonCerbereEntityJPA pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		MonCerbereEntityJPA objetPersistant = null;
		
		/* récupère l'ID métier de l'objet à stocker. */
		final String login = pObject.getLogin();
		
		/* jette une MauvaisParametreRunTimeException 
		 * si l'ID METIER est blank. */
		if (StringUtils.isBlank(login)) {
			
			final String message = "l'objet métier ne possède pas de Login";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}
		
		/* retourne l'instance persistante si l'objet métier 
		 * existe déjà dans le stockage. */
		if (this.monCerbereDAO.existsByLogin(login)) {
			
			/* récupère l'objet métier persistant via son ID METIER 
			 * (sans Hibernate Proxy) auprès du DAO et le retourne. */
			objetPersistant 
				= (MonCerbereEntityJPA) Hibernate.unproxy(
						this.monCerbereDAO.findByLogin(login));
			
			return objetPersistant;
			
		}

		
		// TRAITEMENT POUR PERSISTENCE.
		try {
			
			/* Collection originelle d'Interfaces 
			 * contenues dans l'objet métier. */
			final Collection<IMonProfil> collection = pObject.getProfils();
			
			/* Collection d'Entities similaire à la Collection originelle 
			 * d'Interfaces dans laquelle toutes les instances d'Entities 
			 * déjà existantes dans le stockage sont remplacées 
			 * par les instances persistées. */
			/* Utile pour établir la liste des Entities liées à stocker. */
			final Collection<MonProfilEntityJPA> collectionIntermediaire 
				= new ArrayList<MonProfilEntityJPA>();
			
			/* Collection d'Entities similaire à la Collection 
			 * originelle d'Interfaces dans laquelle 
			 * toutes les Entities sont persistées. */
			final Collection<MonProfilEntityJPA> collectionPersistante 
			= new ArrayList<MonProfilEntityJPA>();
			
			/* transforme la Collection d'interfaces liée 
			 * à l'objet métier en List d'Entities. */
			final List<MonProfilEntityJPA> list 
				= new ArrayList<MonProfilEntityJPA>();
			
			if (collection != null) {

				for (final IMonProfil profil : collection) {

					/* caste l'interface en ENTITY. */
					final MonProfilEntityJPA object 
						= (MonProfilEntityJPA) profil;

					final String role = object.getRole();

					if (StringUtils.isBlank(role)) {

						continue;

					}

					if (this.monProfilDAO.existsByRole(role)) {

						/*
						 * remplace chaque Entite liée transiente de la
						 * Collection liée par l'Entité persistante si elle
						 * existe déjà.
						 */
						final MonProfilEntityJPA objectPersistant 
							= this.monProfilDAO.findByRole(role);

						collectionIntermediaire.add(objectPersistant);

					} else {

						collectionIntermediaire.add(object);

					}

				}

			}
			
			
			/* établit la liste des entities non persistées. */						
			for (final MonProfilEntityJPA profil : collectionIntermediaire) {
				
				final String role = profil.getRole();
				
				if (StringUtils.isBlank(role)) {
					continue;
				}
				
				/* vérifie l'existence de chaque Entité liée 
				 * avant de la sauvegarder. */
				if (profil.getId() == null) {
					
					list.add(profil);
					
				}
									
			}

				
			/* ************************************* */
			/* sauvegarde (BATCH) d'abord les Entities 
			 * liées inexistantes (non persistées). */
			if (!list.isEmpty()) {
				this.monProfilDAO.saveAll(list);
			}
			
			
			/* constitue la Collection d'Entities persistantes 
			 * liées à l'objet métier. */
			for (final MonProfilEntityJPA profil : collectionIntermediaire) {
				
				final String role = profil.getRole();
				
				if (StringUtils.isBlank(role)) {
					continue;
				}
				
				final MonProfilEntityJPA objetPersiste 
					= this.monProfilDAO.findByRole(role);
				
				collectionPersistante.add(objetPersiste);
				
			}

			
			/* Substitue chaque Entity liée persistante 
			 * à l'Entity liée transiente de l'objet métier. */
			for (final MonProfilEntityJPA profil : collectionPersistante) {
				
				pObject.getProfils().remove(profil);
				pObject.getProfils().add(profil);
				
			}
			
			/* fixe la date de création de l'objet métier. */
			pObject.setDateCreation(LocalDate.now());
			
			/* active l'objet métier dans le stockage. */
			pObject.setActive(true);

			
			/* ************************************************** */
			/* délègue au DAO la tâche de stocker l'objet métier. */
			objetPersistant = this.monCerbereDAO.save(pObject);

		} catch (final Exception e) {

			final String message 
				= "Impossible de stocker l'objet métier : " 
						+ pObject.toString() 
						+ MOINS_ESPACE 
						+ e.getMessage();
			
			throw new MauvaisParametreRunTimeException(message, e);

		}
		
		/* retourne l'objet persisté. */
		return objetPersistant;
		
	} // Fin de save(...)._________________________________________________
	
	
	
	/**
	 * fournit le profil par défaut à appliquer à l'utilisateur 
	 * en fonction de la liste de ses droits Cerbere.<br/>
	 * Par exemple : ADMINISTRATEUR si l'utilisateur possède 
	 * le droit ADMINISTRATEUR, GESTIONNAIRE 
	 * si c'est le droit le plus fort qu'il possède, ...<br/>
	 * ADMINISTRATEUR > GESTIONNAIRE > CONSULTANT > INVITE
	 *
	 * @param pMonCerbereEntityJPA : MonCerbereEntityJPA
	 * 
	 * @return levy.daniel.application.model.metier.utilisateur.IMonProfil
	 */
	private IMonProfil fournirProfilParDefaut(
			final MonCerbereEntityJPA pMonCerbereEntityJPA) {
		
		final List<IMonProfil> liste
			= new ArrayList<IMonProfil>(pMonCerbereEntityJPA.getProfils());
		Collections.sort(liste);
		
		if (liste.size() == 1) {
			return liste.get(0);
		}
		
		for (final IMonProfil profil : liste) {
			
			if (StringUtils.contains(profil.getProfil(), "ADMIN")) {
				return profil;
			}
			
		}
		
		for (final IMonProfil profil : liste) {
			
			if (StringUtils.contains(profil.getProfil(), "GESTIONNAIRE")) {
				return profil;
			}
			
		}
		
		for (final IMonProfil profil : liste) {
			
			if (StringUtils.contains(profil.getProfil(), "CONSULTANT")) {
				return profil;
			}
			
		}
		
		for (final IMonProfil profil : liste) {
			
			if (StringUtils.contains(profil.getProfil(), "INVITE")) {
				return profil;
			}
			
		}

		return new MonProfilEntityJPA("INVITE", "TOUT", "FRANCE ENTIERE");
		
	} // Fin de fournirProfilParDefaut(...)._______________________________
	

	
	/**
	 * Détermine le Gestionnaire pour le compte duquel 
	 * agit le CERBERE si celui-ci n'a qu'un seul profil.<br/>
	 * <br/>
	 * - retourne null si pMonCerbereEntityJPA == null.<br/>
	 * - retourne null si le CERBERE a plusieurs profils.<br/>
	 * <br/>
	 *
	 * @param pMonCerbereEntityJPA
	 * 
	 * @return EnumGestionnaire
	 */
	private EnumGestionnaire fournirGestionnaire(
			final MonCerbereEntityJPA pMonCerbereEntityJPA) {
		
		/* retourne null si pMonCerbereEntityJPA == null. */
		if (pMonCerbereEntityJPA == null) {
			return null;
		}

		
		final List<IMonProfil> liste
			= new ArrayList<IMonProfil>(pMonCerbereEntityJPA.getProfils());
		Collections.sort(liste);
		
		if (liste.size() == 1) {
			
			final IMonProfil profil = liste.get(0);
			final String portee = profil.getPortee();
			
			return EnumGestionnaire.getGestionnaireParNomCourt(portee);
		}
		
		/* retourne null si le CERBERE a plusieurs profils. */
		return null; 
		
	} // Fin de fournirGestionnaire(...).__________________________________

	
	
	/**
	 * Détermine le profil choisi 
	 * par le CERBERE si celui-ci n'a qu'un seul profil.<br/>
	 * <br/>
	 * - retourne null si pMonCerbereEntityJPA == null.<br/>
	 * - retourne null si le CERBERE a plusieurs profils.<br/>
	 * <br/>
	 *
	 * @param pMonCerbereEntityJPA
	 * 
	 * @return EnumGestionnaire
	 */
	private IMonProfil fournirProfilChoisi(
			final MonCerbereEntityJPA pMonCerbereEntityJPA) {

		/* retourne null si pMonCerbereEntityJPA == null. */
		if (pMonCerbereEntityJPA == null) {
			return null;
		}

		final List<IMonProfil> liste = new ArrayList<IMonProfil>(
				pMonCerbereEntityJPA.getProfils());
		Collections.sort(liste);

		if (liste.size() == 1) {

			final IMonProfil profil = liste.get(0);

			return profil;

		}

		/* retourne null si le CERBERE a plusieurs profils. */
		return null;

	} // Fin de fournirProfilChoisi(...).__________________________________
	
	
	
} // FIN DE LA CLASSE MonCerbereService.-------------------------------------
