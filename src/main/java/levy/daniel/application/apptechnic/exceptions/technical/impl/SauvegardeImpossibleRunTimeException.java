package levy.daniel.application.apptechnic.exceptions.technical.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractRunTimeTechnicalException;

/**
 * class SauvegardeImpossibleRunTimeException :<br/>
 * HERITE DE AbstractRunTimeTechnicalException ce qui lui
 * permet de s'inscrire dans une liste static partagée
 * par toutes les AbstractRunTimeTechnicalException.<br/>
 * Exception concrète de type RunTime lancée lorsque
 * l'application n'a pu accéder à un fichier .properties.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 *
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Levy Lévy
 * @version 1.0
 * @since 24 juin 2011
 *
 */
public class SauvegardeImpossibleRunTimeException extends
		AbstractRunTimeTechnicalException {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(SauvegardeImpossibleRunTimeException.class);

	// *************************METHODES************************************/
	
	

	/**
	 * method CONSTRUCTEUR SauvegardeImpossibleRunTimeException() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Permet de construire une SauvegardeImpossibleRunTimeException
	 * sans message ni propagation d'exception cause.<br/>
	 *
	 */
	public SauvegardeImpossibleRunTimeException() {
		super();
	} // Fin de SauvegardeImpossibleRunTimeException().______________________
	


	/**
	 * method CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 * String pMessage) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une SauvegardeImpossibleRunTimeException
	 * qui propage un message.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 */
	public SauvegardeImpossibleRunTimeException(final String pMessage) {
		super(pMessage);
	} // Fin de CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	// String pMessage).___________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 * Throwable pCause) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une SauvegardeImpossibleRunTimeException
	 * qui propage un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public SauvegardeImpossibleRunTimeException(final Throwable pCause) {
		super(pCause);
	} // Fin de CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	// Throwable pCause).__________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 * String pMessage
	 * , Throwable pCause) :<br/>
	 * Constructeur d'arité 2.<br/>
	 * Permet de construire une SauvegardeImpossibleRunTimeException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public SauvegardeImpossibleRunTimeException(
			final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	} // Fin de CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 // String pMessage, Throwable pCause).________________________________

	
	
	/**
	 * method CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 * String pMessage
	 * , Throwable pCause
	 * , List<String> pListeExceptions) :<br/>
	 * Constructeur d'arité 3.<br/>
	 * Permet de construire une SauvegardeImpossibleRunTimeException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * - Une liste de String susceptible d'encapsuler
	 * le message de la présente Exception.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 * @param pListeExceptions : Liste dans laquelle on veut
	 * insérer le message de la présente Exception.<br/>
	 */
	public SauvegardeImpossibleRunTimeException(
			final String pMessage
				, final Throwable pCause
					, final List<String> pListeExceptions) {
		
		super(pMessage, pCause, pListeExceptions);
		
	} // Fin de CONSTRUCTEUR SauvegardeImpossibleRunTimeException(
	 // String pMessage
	 // , Throwable pCause
	 // , List<String> pListeExceptions).__________________________________
	

	
} // FIN DE LA CLASSE SauvegardeImpossibleRunTimeException.------------------
