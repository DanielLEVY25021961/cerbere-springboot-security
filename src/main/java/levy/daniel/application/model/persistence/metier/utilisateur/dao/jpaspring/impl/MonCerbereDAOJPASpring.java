package levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;


/**
 * INTERFACE MonCerbereDAOJPASpring :<br/>
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
@Repository(value="MonCerbereDAOJPASpring")
public interface MonCerbereDAOJPASpring
		extends JpaRepository<MonCerbereEntityJPA, Long> {

	
	
	/**
	 * recherche dans le stockage un objet métier via son username (login).<br/>
	 * Pour SPRING SECURITY.
	 *
	 * @param pUsername : String : Login du User.
	 * 
	 * @return : MonCerbereEntityJPA : objet métier.
	 */
	@Query("select user from MonCerbereEntityJPA user where user.login = :x ")
	MonCerbereEntityJPA findByUsername(@Param("x") String pUsername);

	
	
	/**
	 * recherche dans le stockage un objet métier via son Login.
	 *
	 * @param pLogin : String : Login du User.
	 * 
	 * @return : MonCerbereEntityJPA : objet métier.
	 */
	@Query("select user from MonCerbereEntityJPA user where user.login = :x ")
	MonCerbereEntityJPA findByLogin(@Param("x") String pLogin);
	

	
	/**
	 * recherche dans le stockage une Page d'objets métier 
	 * dont le username (login) contient la String passée en paramètre.<br/>
	 *
	 * @param pUsername : String : paramètre de recherche.
	 * @param pPageable : 
	 * <code>org.springframework.data.domain.Pageable</code> : 
	 * Objet contenant le numéro (0-based) et la taille de la page à retourner.
	 * 
	 * @return : 
	 * <code>org.springframework.data.domain.Page&lt;MonCerbereEntityJPA&gt;</code> : 
	 * Page des résultats.
	 */
	@Query("select user from MonCerbereEntityJPA user where user.login LIKE :x ")
	Page<MonCerbereEntityJPA> rechercherRapideParPage(
			@Param("x") String pUsername, Pageable pPageable);

	
	
	/**
	 * recherche dans le stockage une Liste d'objets métier 
	 * dont le username (login) contient la String passée en paramètre.<br/>
	 *
	 * @param pUsername : String : paramètre de recherche.
	 * 
	 * @return : java.util.List&lt;MonCerbereEntityJPA&gt; : 
	 * liste des résultats.<br/>
	 */
	@Query("select user from MonCerbereEntityJPA user where user.login LIKE :x ")
	List<MonCerbereEntityJPA> rechercherRapide(
			@Param("x") String pUsername);

	
	
	/**
	 * détermine si un objet métier existe dans le stockage via son ID METIER.
	 *
	 * @param pLogin : String : Login d'un MonCerbereEntityJPA.
	 * 
	 * @return boolean : true si l'objet existe dans le stockage.
	 */
	@Query("select case when count(user)> 0 then true else false end from MonCerbereEntityJPA user where user.login = :login")
	boolean existsByLogin(@Param("login") String pLogin);

	

} // FIN DE L'INTERFACE MonCerbereDAOJPASpring.------------------------------
