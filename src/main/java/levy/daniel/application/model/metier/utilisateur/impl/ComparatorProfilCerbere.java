package levy.daniel.application.model.metier.utilisateur.impl;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import i2.application.cerbere.commun.Profil;


/**
 * CLASSE ComparatorProfilCerbere :<br/>
 * Comparator pour les <code>i2.application.cerbere.commun.Profil</code>.<br/>
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
public class ComparatorProfilCerbere implements Comparator<Profil> {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ComparatorProfilCerbere.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public ComparatorProfilCerbere() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	* {@inheritDoc}
	*/
	@Override
	public final int compare(
			final Profil pO1, final Profil pO2) {
		
		if (pO1 == pO2) {
			return 0;
		}

		if (pO1 != null && pO2 == null) {
			return -1;
		}
		
		if (pO1 == null && pO2 != null) {
			return +1;
		}
		
		if (pO1 == null && pO2 == null) {
			return 0;
		}

		if (pO1 != null && pO2 != null) {
			
			if (pO1.getNom() == null) {
				
				if (pO2.getNom() != null) {
					return +1;
				}
				
			} else {
				
				if (pO2.getNom() == null) {
					return -1;
				}
				
				if (pO1.getNom().contains("ADMIN")) {
					
					if (!pO2.getNom().contains("ADMIN")) {
						return -1;
					}
					
					return pO1.getPortee()
							.compareToIgnoreCase(pO2.getPortee());
					
				} else {
					
					return pO1.getPortee()
							.compareToIgnoreCase(pO2.getPortee());
					
				}
				
			}
			
			return 0;

		}
		
		return 0;

	} // Fin de compare(...).______________________________________________

	
	
} // FIN DE LA CLASSE ComparatorProfilCerbere.-------------------------------
