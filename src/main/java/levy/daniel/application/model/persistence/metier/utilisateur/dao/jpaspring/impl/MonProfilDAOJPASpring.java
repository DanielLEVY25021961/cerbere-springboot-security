package levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonCerbereEntityJPA;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.MonProfilEntityJPA;


/**
 * INTERFACE MonProfilDAOJPASpring :<br/>
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
@Repository(value="MonProfilDAOJPASpring")
public interface MonProfilDAOJPASpring  
				extends JpaRepository<MonProfilEntityJPA, Long> {

		
	/**
	 * recherche dans le stockage un MonProfilEntityJPA via son role.
	 *
	 * @param pRole : String : rôle du MonProfilEntityJPA.
	 * 
	 * @return : MonProfilEntityJPA
	 */
	MonProfilEntityJPA findByRole(String pRole);

	
	
	/**
	 * détermine si un objet métier existe dans le stockage via son ID METIER.
	 *
	 * @param pRole : String : Role d'un MonProfilEntityJPA
	 * 
	 * @return boolean : true si l'objet existe dans le stockage.
	 */
	@Query("select case when count(profil)> 0 then true else false end from MonProfilEntityJPA profil where profil.role = :role")
	boolean existsByRole(@Param("role") String pRole);
	

	
	/* ""select t from Test t join User u where u.username = :username" "*/
	/* select user from MonCerbereEntityJPA user join MonProfilEntityJPA profil where user.profils.profil.role = :role" */
	/* SELECT a FROM Author a WHERE (SELECT count(b) FROM Book b WHERE a MEMBER OF b.authors ) > 1 */
	/**
	 * retourne la liste des Users (MonCerbereEntityJPA) 
	 * possédant le rôle pRole.
	 *
	 * @param pRole : String : Role d'un MonProfilEntityJPA.
	 * 
	 * @return List<MonCerbereEntityJPA>
	 */
	@Query("SELECT user FROM MonCerbereEntityJPA user WHERE (SELECT count(profil) FROM MonProfilEntityJPA profil where user MEMBER OF profil.monCerberes AND profil.role = :role) > 1 ")
	List<MonCerbereEntityJPA> findUsersByRole(@Param("role") String pRole);
	
	
	
} // FIN DE L'INTERFACE MonProfilDAOJPASpring.-------------------------------
