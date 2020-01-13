package levy.daniel.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl.MonProfilDAOJPASpring;

/**
 * CLASSE CerbereSpringbootSecurityApplication :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 16 nov. 2019
 *
 */
@SpringBootApplication
public class CerbereSpringbootSecurityApplication implements CommandLineRunner {
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("MonProfilDAOJPASpring")
	private transient MonProfilDAOJPASpring monProfilDAOJPASpring;
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CerbereSpringbootSecurityApplication() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * Point d'entrée de l'application.<br/>
	 * <br/>
	 *
	 * @param pArgs : String[]
	 */
	public static void main(final String... pArgs) {
		
		SpringApplication.run(CerbereSpringbootSecurityApplication.class, pArgs);
		
	} // Fin de main(...)._________________________________________________



	/**
	* {@inheritDoc}
	*/
	@Override
	public void run(final String... pArgs) throws Exception {
//
//		final String role = "ROLE_ADMINISTRATEUR - TOUT";
//		
//		final List<MonCerbereEntityJPA> users 
//			= this.monProfilDAOJPASpring.findUsersByRole(role);
//		
//		if (users != null) {
//			
//			for (final MonCerbereEntityJPA user : users) {
//				System.out.println(user.toString());
//			}
//			
//		} else {
//			
//			System.out.println();
//			System.out.println("=============================================================");
//			System.out.println("CLASSE CerbereSpringbootSecurityApplication RUN - users NULL");
//			System.out.println("=============================================================");
//			System.out.println();
//			
//		}
//		
	} // Fin de run(...).__________________________________________________
	
	

} // FIN DE LA CLASSE CerbereSpringbootSecurityApplication.------------------
