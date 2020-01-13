package levy.daniel.application.model.services.metier.utilisateurs;

import i2.application.cerbere.commun.Cerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;

/**
 * INTERFACE IMonCerbereService :<br/>
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
public interface IMonCerbereService {

	
	
	/**
	 * prépare tous les objets de SPRING SECURITY 
	 * (UserDetails, Authentication, ...) à partir d'un objet CERBERE
	 * et les retourne encapsulés dans une IReponseCerbereService.
	 * <ul>
	 * <li>throw une Exception si pCerbere == null.</li>
	 * <li>crée un MonCerbereEntityJPA à partir 
	 * de l'Objet Cerbere en session.</li>
	 * <li>crée un MonCerbereUserDetails à partir du monCerbereEntityJPA.</li>
	 * <li>crée un Authentication SPRING en lui passant 
	 * le MonCerbereUserDetails.</li>
	 * <li>trie les profils dans l'objet Cerbere.</li>
	 * <li>détermine le profil par défaut.</li>
	 * <li>détermine le gestionnaire si le CERBERE n'a qu'un seul profil.</li>
	 * <li>détermine le profil choisi si le CERBERE n'a qu'un seul profil.</li>
	 * <li>alimente la réponse du service.</li>
	 * <li>retourne la réponse du service.</li>
	 * </ul>
	 *
	 * @param pCerbere : i2.application.cerbere.commun.Cerbere
	 * 
	 * @return levy.daniel.application.model.services.metier
	 * .utilisateurs.IReponseCerbereService
	 * 
	 * @throws Exception si pCerbere est null.
	 */
	IReponseCerbereService loggerCerbere(Cerbere pCerbere) throws Exception;

	
	
	/**
	 * sauvegarde l'objet métier dans le stockage 
	 * et/ou retourne l'instance persistante.
	 * <ul>
	 * <li>NE CREE JAMAIS DE DOUBLONS (vérifie l'existence des entities 
	 * dans le stockage AVANT d'appeler save()).</li>
	 * <li>récupère l'<strong>ID METIER</strong> de l'objet à stocker.</li>
	 * <li>jette une MauvaisParametreRunTimeException si l'ID METIER 
	 * est blank.</li>
	 * <li>retourne l'instance persistante si l'objet métier 
	 * existe déjà dans le stockage.</li>
	 * <li>récupère l'objet métier persistant via son ID METIER 
	 * (sans Hibernate Proxy) auprès du DAO et le retourne.</li>
	 * <li>transforme la Collection d'interfaces liée à l'objet métier 
	 * en List d'Entities.</li>
	 * <li>vérifie l'existence de chaque Entité liée 
	 * avant de la sauvegarder.</li>
	 * <li>sauvegarde (BATCH) d'abord les Entities liées inexistantes.</li>
	 * <li>constitue la Collection d'Entities persistantes liées 
	 * à l'objet métier.</li>
	 * <li>Substitue chaque Entity liée persistante à l'
	 * Entity liée transiente de l'objet métier.</li>
	 * <li>fixe la date de création de l'objet métier.</li>
	 * <li>active l'objet métier dans le stockage.</li>
	 * <li>délègue au DAO la tâche de stocker l'objet métier.</li>
	 * <li>retourne l'objet métier persisté.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : MonCerbereEntityJPA
	 * 
	 * @return MonCerbereEntityJPA : l'objet métier persisté.
	 * 
	 * @throws Exception
	 */
	MonCerbereEntityJPA save(MonCerbereEntityJPA pObject) throws Exception;
	
	
	
} // FIN DE L'INTERFACE IMonCerbereService.----------------------------------