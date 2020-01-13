package levy.daniel.application.model.metier.utilisateur;

import org.apache.commons.lang3.StringUtils;

/**
 * ENUMERATION EnumGestionnaire :<br/>
 * Enumération chargée de gérer tous les gestionnaires possibles.<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 13 juin 2019
 *
 */
public enum EnumGestionnaire {
	
	/**
	 * DIRA.<br/>
	 */
	DIRA("DIRA", "DIR ATLANTIQUE")
	
	, /**
	 * DIRCE.<br/>
	 */
	DIRCE("DIRCE", "DIR CENTRE-EST")
	
	, /**
	 * DIRCO.<br/>
	 */
	DIRCO("DIRCO", "DIR CENTRE-OUEST")
	
	, /**
	 * DIRE.<br/>
	 */
	DIRE("DIRE", "DIR EST")
	
	, /**
	 * DIRIF.<br/>
	 */
	DIRIF("DIRIF", "DIR ILE-DE-FRANCE")
	
	, /**
	 * DIRMC.<br/>
	 */
	DIRMC("DIRMC", "DIR MASSIF-CENTRAL")
	
	, /**
	 * DIRMED.<br/>
	 */
	DIRMED("DIRMED", "DIR MEDITERRANEE")
	
	, /**
	 * DIRN.<br/>
	 */
	DIRN("DIRN", "DIR NORD")
	
	, /**
	 * DIRNO.<br/>
	 */
	DIRNO("DIRNO", "DIR NORD-OUEST")
	
	, /**
	 * DIRO.<br/>
	 */
	DIRO("DIRO", "DIR OUEST")
	
	, /**
	 * DIRSO.<br/>
	 */
	DIRSO("DIRSO", "DIR SUD-OUEST")
	
	, /**
	 * DARWIN.<br/>
	 */
	DARWIN("DARWIN", "RESEAU ROUTIER NATIONAL CONCEDE")
	
	, /**
	 * TOUT.<br/>
	 */
	TOUT("TOUT", "RESEAU ROUTIER NATIONAL ENTIER");
	
	
	
	/**
	 * nom court du gestionnaire.<br/>
	 * Par exemple : "DIRA".
	 */
	private final String nomCourt;
	
	/**
	 * nom complet du gestionnaire.<br/>
	 * Par exemple : "DIR ATLANTIQUE".
	 */
	private final String nomComplet;
	

	
    /**
    * CONSTRUCTEUR COMPLET.<br/>
    *
    * @param pNomCourt : String : nom court du gestionnaire.
    * @param pNomComplet : String : nom complet du gestionnaire.
    */
	EnumGestionnaire(
   		final String pNomCourt, final String pNomComplet) {
       this.nomCourt = pNomCourt;
       this.nomComplet = pNomComplet;
   } // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * retourne l'Enumeration en fonction de son nom court.<br/>
	 * <br/>
	 * - retourne null si pNomCourt est blank.<br/>
	 * - retourne null si pNomCourt n'existe pas.<br/>
	 * <br/>
	 * 
	 *
	 * @param pNomCourt : String.
	 * 
	 * @return EnumGestionnaire
	 */
	public static EnumGestionnaire getGestionnaireParNomCourt(
			final String pNomCourt) {
		
		/* retourne null si pNomCourt est blank. */
		if (StringUtils.isBlank(pNomCourt)) {
			return null;
		}
		
		EnumGestionnaire resultat = null;
		
		switch (pNomCourt) {

		case "DIRA" :
			resultat = DIRA;
			break;
			
		case "DIRCE" :
			resultat = DIRCE;
			break;

		case "DIRE" :
			resultat = DIRE;
			break;

		case "DIRIF" :
			resultat = DIRIF;
			break;

		case "DIRMC" :
			resultat = DIRMC;
			break;

		case "DIRMED" :
			resultat = DIRMED;
			break;

		case "DIRN" :
			resultat = DIRN;
			break;

		case "DIRNO" :
			resultat = DIRNO;
			break;

		case "DIRO" :
			resultat = DIRO;
			break;

		case "DIRSO" :
			resultat = DIRSO;
			break;

		case "DARWIN" :
			resultat = DARWIN;
			break;

		case "TOUT" :
			resultat = TOUT;
			break;

		default:
			/* retourne null si pNomCourt n'existe pas. */
			break;
			
		}
		
		return resultat;
		
	} // Fin de getGestionnaireParNomCourt(...).___________________________
	

	
	/**
	 * Getter du nom court du gestionnaire.<br/>
	 * Par exemple : "DIRA".
	 *
	 * @return this.nomCourt : String.<br/>
	 */
	public final String getNomCourt() {
		return this.nomCourt;
	} // Fin de getNomCourt()._____________________________________________


	
	/**
	 * Getter du nom complet du gestionnaire.<br/>
	 * Par exemple : "DIR ATLANTIQUE".
	 *
	 * @return this.nomComplet : String.<br/>
	 */
	public final String getNomComplet() {
		return this.nomComplet;
	} // Fin de getNomComplet().___________________________________________

	
	
} // FIN DE L'ENUMERATION EnumGestionnaire.----------------------------------
