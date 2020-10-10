package levy.daniel.application.model.metier.utilisateur.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IMonCerbere;
import levy.daniel.application.model.metier.utilisateur.IMonProfil;


/**
 * CLASSE MonCerbereTest :<br/>
 * Test JUnit de la classe {@link MonCerbere}.<br/>
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
public class MonCerbereTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "===============================================".<br/>
	 */
	public static final String TIRETS 
	= "===============================================";

	/**
	 * "testEquals()".<br/>
	 */
	public static final String TEST_EQUALS 
		= "testEquals()";
		
	/**
	 * "testCompareTo()".<br/>
	 */
	public static final String TEST_COMPARETO 
		= "testCompareTo()";
	
	/**
	 * "testClone()".<br/>
	 */
	public static final String TEST_CLONE 
		= "testClone()";
	
	/**
	 * "testToString()".<br/>
	 */
	public static final String TEST_TOSTRING 
		= "testToString()";
		
	/**
	 * "testFournirEnTeteCsv()".<br/>
	 */
	public static final String TEST_FOURNIRENTETECSV 
		= "testFournirEnTeteCsv()";
		
	/**
	 * "testFournirStringCsv()".<br/>
	 */
	public static final String TEST_FOURNIRSTRINGCSV 
		= "testFournirStringCsv()";
		
	/**
	 * "testFournirEnTeteColonne()".<br/>
	 */
	public static final String TEST_FOURNIRENTETECOLONNE 
		= "testFournirEnTeteColonne()";
		
	/**
	 * "testFournirValeurColonne()".<br/>
	 */
	public static final String TEST_FOURNIRVALEURCOLONNE 
		= "testFournirValeurColonne()";
	
	/**
	 * "GESTIONNAIRE".
	 */
	public static final String GESTIONNAIRE = "GESTIONNAIRE";
	
	/**
	 * "ADMINISTRATEUR".
	 */
	public static final String ADMINISTRATEUR = "ADMINISTRATEUR";
	
	/**
	 * "FRANCE ENTIERE".
	 */
	public static final String FRANCE_ENTIERE = "FRANCE ENTIERE";
	
	/**
	 * "objet1 : ".<br/>
	 */
	public static final String OBJET1 = "objet1 : ";
	
	/**
	 * "objetNull1 : ".<br/>
	 */
	public static final String OBJETNULL1 = "objetNull1 : ";
	
	/**
	 * "objet1.equals(objet2EqualsObj1) : ".<br/>
	 */
	public static final String OBJET1_EQUALS_OBJET2 
		= "objet1.equals(objet2EqualsObj1) : ";
	
	/**
	 * "vincent.robin".
	 */
	public static final String LOGIN_VINCENT_ROBIN = "vincent.robin";
	
	/**
	 * "avant.avant"
	 */
	public static final String LOGIN_AVANT_AVANT = "avant.avant";
	
	/**
	 * "zapres.zapres"
	 */
	public static final String LOGIN_ZAPRES_ZAPRES = "zapres.zapres";
	
	/**
	 * "dominique.alamichel".
	 */
	public static final String LOGIN_DOMINIQUE_ALAMICHEL_DIRA 
		= "dominique.alamichel";

	/**
	 * "papy.gonzales".
	 */
	public static final String LOGIN_PAPY_GONZALES_DIRCE 
		= "papy.gonzales";
	
	/**
	 * "daniel.levy".
	 */
	public static final String LOGIN_DANIEL_LEVY_ADMIN = "daniel.levy"; 
	
	/**
	 * "olivier.lesnes".
	 */
	public static final String LOGIN_OLIVIER_LESNES_DARWIN = "olivier.lesnes";
	
	/**
	 * "M".
	 */
	public static final String CIVILITE_M = "M";
	
	/**
	 * "F".
	 */
	public static final String CIVILITE_F = "F";
	
	/**
	 * "Vincent".
	 */
	public static final String PRENOM_VINCENT = "Vincent";
	
	/**
	 * "Dominique".
	 */
	public static final String PRENOM_DOMINIQUE = "Dominique";
	
	/**
	 * "Papy".
	 */
	public static final String PRENOM_PAPY = "Papy";
	
	/**
	 * "Daniel".
	 */
	public static final String PRENOM_DANIEL = "Daniel";

	/**
	 * "Olivier".
	 */
	public static final String PRENOM_OLIVIER = "Olivier";
	
	/**
	 * "Robin".
	 */
	public static final String NOM_ROBIN = "Robin";

	/**
	 * "Alamichel".
	 */
	public static final String NOM_ALAMICHEL = "Alamichel";
	
	/**
	 * "Gonzales".
	 */
	public static final String NOM_GONZALES = "Gonzales";
	
	/**
	 * "Lévy".
	 */
	public static final String NOM_LEVY = "Lévy";
	
	/**
	 * "Lesnes".
	 */
	public static final String NOM_LESNES = "Lesnes";
	
	/**
	 * "05 66 99 88 52".
	 */
	public static final String TELEPHONE_ROBIN = "05 66 99 88 52";
	
	/**
	 * "02 77 99 88 52".
	 */
	public static final String TELEPHONE_ALAMICHEL = "02 77 99 88 52";
	
	/**
	 * "05 33 99 88 89".
	 */
	public static final String TELEPHONE_GONZALES = "05 33 99 88 89";
	
	/**
	 * "01 60 52 30 32".
	 */
	public static final String TELEPHONE_LEVY = "01 60 52 30 32";
	
	/**
	 * "02 62 52 30 32".
	 */
	public static final String TELEPHONE_LESNES = "02 62 52 30 32";
	
	/**
	 * "vincent.robin@cerema.fr".
	 */
	public static final String MEL_ROBIN = "vincent.robin@cerema.fr";
	
	/**
	 * "dominique.alamichel@dira.fr".
	 */
	public static final String MEL_ALAMICHEL = "dominique.alamichel@dira.fr";
	
	/**
	 * "papy.gonzales@dirce.fr".
	 */
	public static final String MEL_GONZALES = "papy.gonzales@dirce.fr";
	
	/**
	 * "daniel.levy@cerema.fr".
	 */
	public static final String MEL_LEVY = "daniel.levy@cerema.fr";
	
	/**
	 * "olivier.lesnes@darwin.fr".
	 */
	public static final String MEL_LESNES = "olivier.lesnes@darwin.fr";
	
	/**
	 * "CEREMA/DTerMed/DCEDI/GTIE".
	 */
	public static final String UNITE_ROBIN = "CEREMA/DTerMed/DCEDI/GTIE";
	
	/**
	 * "DIRA/SIEER/CIGT/PC Bordeaux".
	 */
	public static final String UNITE_DIRA = "DIRA/SIEER/CIGT/PC Bordeaux";
	
	/**
	 * "DIRCE/SIEER/CIGT/PC Lyon".
	 */
	public static final String UNITE_DIRCE = "DIRCE/SIEER/CIGT/PC Lyon";
	
	/**
	 * "DGITM/DIT/GRN/GCABron/GCA2".
	 */
	public static final String UNITE_DARWIN = "DGITM/DIT/GRN/GCABron/GCA2";
	
	/**
	 * "CEREMA/DTecITM/CITS/DACSI".
	 */
	public static final String UNITE_LEVY = "CEREMA/DTecITM/CITS/DACSI";
	
	/**
	 * Administrateur.
	 */
	public static final IMonProfil PROFIL_ADMIN 
		= new MonProfil(1L
				, ADMINISTRATEUR
				, EnumGestionnaire.TOUT.getNomCourt()
				, EnumGestionnaire.TOUT.getNomComplet());
	
	/**
	 * Gestionnaire DIRA.
	 */
	public static final IMonProfil PROFIL_GESTIONNAIRE_DIRA 
		= new MonProfil(2L
			, GESTIONNAIRE
			, EnumGestionnaire.DIRA.getNomCourt()
			, EnumGestionnaire.DIRA.getNomComplet());
	
	/**
	 * Gestionnaire DIRCE.
	 */
	public static final IMonProfil PROFIL_GESTIONNAIRE_DIRCE 
		= new MonProfil(4L
			, GESTIONNAIRE
			, EnumGestionnaire.DIRCE.getNomCourt()
			, EnumGestionnaire.DIRCE.getNomComplet());
	
	/**
	 * Gestionnaire DARWIN.
	 */
	public static final IMonProfil PROFIL_GESTIONNAIRE_DARWIN 
	= new MonProfil(13L
		, GESTIONNAIRE
		, EnumGestionnaire.DARWIN.getNomCourt()
		, EnumGestionnaire.DARWIN.getNomComplet());
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IMonCerbere objet1 
		= new MonCerbere(1L
				, LOGIN_VINCENT_ROBIN
				, CIVILITE_M, PRENOM_VINCENT, NOM_ROBIN
				, TELEPHONE_ROBIN, MEL_ROBIN
				, UNITE_ROBIN
				, null
				, LocalDate.of(2020, 1, 1)
				, null);
	
	/**
	 * objet1MemeInstance doit être la même instance que objet1.<br/>
	 */
	public static transient IMonCerbere objet1MemeInstance = objet1;
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IMonCerbere objet2EqualsObj1 
		=  new MonCerbere(2L
				, LOGIN_VINCENT_ROBIN
				, CIVILITE_M, PRENOM_VINCENT, NOM_ROBIN
				, TELEPHONE_ROBIN, MEL_ROBIN
				, UNITE_ROBIN
				, null
				, LocalDate.of(2020, 1, 1)
				, null);
	
	/**
	 * objet1, objet2EqualsObj1, objet3EqualsObj1 doivent être equals().
	 */
	public static transient IMonCerbere objet3EqualsObj1 
		= new MonCerbere(3L
				, LOGIN_VINCENT_ROBIN
				, CIVILITE_M, PRENOM_VINCENT, NOM_ROBIN
				, TELEPHONE_ROBIN, MEL_ROBIN
				, UNITE_ROBIN
				, null
				, LocalDate.of(2020, 1, 1)
				, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IMonCerbere objetNull1 
		= new MonCerbere(0L
				, null
				, null, null, null
				, null, null
				, null
				, null
				, null
				, null);
	
	/**
	 * objetNull1 et objetNull2 doivent être instanciés 
	 * avec le constructeur d'arité nulle ou avoir 
	 * tous les attributs aux valeurs par défaut.
	 */
	public static transient IMonCerbere objetNull2 
	= new MonCerbere(7L
			, null
			, null, null, null
			, null, null
			, null
			, null
			, null
			, null);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient IMonCerbere objet1AvecNull 
		= new MonCerbere(41L
			, LOGIN_VINCENT_ROBIN
			, null, PRENOM_VINCENT, NOM_ROBIN
			, null, null
			, UNITE_ROBIN
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * objet1AvecNull et objet2EqualsObjet1AvecNull 
	 * doivent être equals() et avoir certains attributs à null.
	 */
	public static transient IMonCerbere objet2EqualsObjet1AvecNull 
		= new MonCerbere(44L
			, LOGIN_VINCENT_ROBIN
			, null, PRENOM_VINCENT, NOM_ROBIN
			, null, null
			, UNITE_ROBIN
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * objetDiff1 doit être différent de objetDiff2
	 */
	public static transient IMonCerbere objetDiff1 
		= new MonCerbere(21L
				, LOGIN_DOMINIQUE_ALAMICHEL_DIRA
				, CIVILITE_F, PRENOM_DOMINIQUE, NOM_ALAMICHEL
				, TELEPHONE_ALAMICHEL, MEL_ALAMICHEL
				, UNITE_DIRA
				, null
				, LocalDate.of(2020, 1, 1)
				, null);
	
	/**
	 * objetDiff2 doit être différent de objetDiff1
	 */
	public static transient IMonCerbere objetDiff2 
		= new MonCerbere(22L
			, LOGIN_PAPY_GONZALES_DIRCE
			, CIVILITE_M, PRENOM_PAPY, NOM_GONZALES
			, TELEPHONE_GONZALES, MEL_GONZALES
			, UNITE_DIRCE
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient IMonCerbere objetDiff1AvecNull 
		= new MonCerbere(301L
			, LOGIN_DANIEL_LEVY_ADMIN
			, CIVILITE_M, PRENOM_DANIEL, null
			, null, null
			, UNITE_LEVY
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * objetDiff1AvecNull doit être différent de objetDiff2AvecNull.<br/>
	 * objetDiff1AvecNull et objetDiff2AvecNull doivent avoir des attributs null.
	 */
	public static transient IMonCerbere objetDiff2AvecNull 
		= new MonCerbere(302L
				, LOGIN_OLIVIER_LESNES_DARWIN
				, CIVILITE_M, PRENOM_OLIVIER, null
				, null, null
				, UNITE_DARWIN
				, null
				, LocalDate.of(2020, 1, 1)
				, null);
	
	/**
	 * objetCompAvant doit être AVANT objetCompApres.
	 */
	public static transient IMonCerbere objetCompAvant 
		= new MonCerbere(303L
			, LOGIN_AVANT_AVANT
			, CIVILITE_F, "avant", "Avant"
			, "01 45 63 28 98", "avant.avant@free.fr"
			, UNITE_DIRA
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * objetCompApres doit être APRES objetCompAvant.
	 */
	public static transient IMonCerbere objetCompApres 
		= new MonCerbere(304L
			, LOGIN_ZAPRES_ZAPRES
			, CIVILITE_M, "zapres", "zapres"
			, TELEPHONE_LEVY, "zapres.zapres@free.fr"
			, UNITE_LEVY
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * clone de objetNull1.<br/>
	 */
	public static transient IMonCerbere objetNullClone1;
	
	/**
	 * clone de objet1.<br/>
	 */
	public static transient IMonCerbere objetClone1;

	/**
	 * userBrunoInchingoloAdmin.
	 */
	public static transient IMonCerbere userBrunoInchingoloAdmin 
	 	= new MonCerbere(101L
			, "bruno.inchingolo"
			, CIVILITE_M, "bruno", "Inchingolo"
			, "05 78 96 35 21", "bruno.inchingolo@cerema.fr"
			, UNITE_ROBIN
			, null
			, LocalDate.of(2020, 1, 1)
			, null);

	/**
	 * userBertrandPompeteAdmin.
	 */
	public static transient IMonCerbere userBertrandPompeteAdmin 
		= new MonCerbere(102L
			, "bertrand.pompete"
			, CIVILITE_M, "bertrand", "Pompète"
			, "03 46 59 86 32", "bertrand.pompete@cerema.fr"
			, UNITE_LEVY
			, null
			, LocalDate.of(2020, 1, 1)
			, null);

	/**
	 * userIsabelleLeroyDira.
	 */
	public static transient IMonCerbere userIsabelleLeroyDira 
		= new MonCerbere(103L
			, "isabelle.leroy"
			, CIVILITE_F, "Isabelle", "Leroy"
			, "01 60 62 35 62", "isabelle.leroy@cerema.fr"
			, UNITE_DIRA
			, null
			, LocalDate.of(2020, 1, 1)
			, null);
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(MonCerbereTest.class);

	// *************************METHODES************************************/



	/**
	* CONSTRUCTEUR D'ARITE NULLE.
	*/
	public MonCerbereTest() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * Teste la méthode <b>equals(Object pObject)</b> :
	 * <ul>
	 * <li>garantit le contrat Java reflexif x.equals(x).</li>
	 * <li>garantit le contrat Java symétrique 
	 * x.equals(y) ----> y.equals(x).</li>
	 * <li>garantit le contrat Java transitif 
	 * x.equals(y) et y.equals(z) ----> x.equals(z).</li>
	 * <li>garantit le contrat Java sur les hashcode 
	 * x.equals(y) ----> x.hashcode() == y.hashcode().</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans equals(Object pObj).</li>
	 * <li>garantit que x.equals(null) retourne false 
	 * (avec x non null).</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'égalité métier.</li>
	 * <li>garantit le bon fonctionnement de equals() 
	 * en cas d'inégalité métier.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testEquals() {
					
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE MonCerbereTest - méthode testEquals() ********** ");
		}

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 3 Objets equals ----------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println("objet2EqualsObj1.toString() : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1.toString() : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet1) : " + objet1.equals(objet1));
			System.out.println(OBJET1_EQUALS_OBJET2 + objet1.equals(objet2EqualsObj1));
			System.out.println("objet2EqualsObj1.equals(objet1) : " + objet2EqualsObj1.equals(objet1));
			System.out.println("objet2EqualsObj1.equals(objet3EqualsObj1) : " + objet2EqualsObj1.equals(objet3EqualsObj1));
			System.out.println("objet1.equals(objet3EqualsObj1) : " + objet1.equals(objet3EqualsObj1));
			System.out.println("objet1.hashCode() == objet2EqualsObj1.hashCode() : " + (objet1.hashCode() == objet2EqualsObj1.hashCode()));
		}
		
		
		/* garantit le contrat Java reflexif x.equals(x). */
		assertEquals("x.equals(x) : "
				, objet1
					, objet1);
				
		/* garantit le contrat Java symétrique 
		 * x.equals(y) ----> y.equals(x). */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		
		assertEquals("objet2EqualsObj1.equals(objet1) : "
				, objet2EqualsObj1
					, objet1);
		
		/* garantit le contrat Java transitif 
		 * x.equals(y) et y.equals(z) ----> x.equals(z). */
		assertEquals(OBJET1_EQUALS_OBJET2, objet1, objet2EqualsObj1);
		assertEquals("objet2EqualsObj1.equals(objet3EqualsObj1) : ", objet2EqualsObj1, objet3EqualsObj1);
		assertEquals("objet1.equals(objet3EqualsObj1) : ", objet1, objet3EqualsObj1);
		
		/* garantit le contrat Java sur les hashcode 
		 * x.equals(y) ----> x.hashcode() == y.hashcode(). */
		assertEquals("objet1.hashCode().equals(objet2EqualsObj1.hashCode()) : "
				, objet1.hashCode()
					, objet2EqualsObj1.hashCode());

				
		/* garantit que les null sont bien gérés dans equals(...). */
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou attributs par défaut) -------");
			System.out.println("OBJETNULL1 : " 
					+ objetNull1.toString());
			System.out.println("OBJETNULL2 : " 
					+ objetNull2.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNull2) : " + objetNull1.equals(objetNull2));
			System.out.println("objetNull1.hashCode().equals(objetNull2.hashCode()) : " + (objetNull1.hashCode() == objetNull2.hashCode()));
		}

		assertEquals("objetNull1.equals(objetNull2) : "
				, objetNull1
					, objetNull2);
		assertEquals("objetNull1.hashCode().equals(objetNull2.hashCode()) : "
				, objetNull1.hashCode()
					, objetNull2.hashCode());
		

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'OBJETS AVEC CERTAINS ATTRIBUTS NULL -----------");
			System.out.println("objet1AvecNull : " 
					+ objet1AvecNull.toString());
			System.out.println("objet2EqualsObjet1AvecNull : " 
					+ objet2EqualsObjet1AvecNull.toString());
			System.out.println();
			System.out.println("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : " + objet1AvecNull.equals(objet2EqualsObjet1AvecNull));
			System.out.println("objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()" + (objet1AvecNull.hashCode() == objet2EqualsObjet1AvecNull.hashCode()));
		}

		assertEquals("objet1AvecNull.equals(objet2EqualsObjet1AvecNull) : "
				, objet1AvecNull
					, objet2EqualsObjet1AvecNull);
		assertEquals("objet1AvecNull.hashCode()"
				+ ".equals(objet2EqualsObjet1AvecNull.hashCode()) : "
				, objet1AvecNull.hashCode()
					, objet2EqualsObjet1AvecNull.hashCode());


		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("OBJET EXISTANT EQUALS null --------------------------");
			System.out.println("objet1.toString() : " + objet1.toString());
			System.out.println();
			System.out.println("objet1.equals(null) : " + (objet1 == null));
		}
		
		/* garantit que x.equals(null) retourne false (avec x non null). */
		assertNotNull("objet1 pas equals(null) : "
				, objet1);

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'EGALITE METIER --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objet2EqualsObj1 : " 
					+ objet2EqualsObj1.toString());
			System.out.println();
			System.out.println("objet1.equals(objet2EqualsObj1) : " + objet1.equals(objet2EqualsObj1));
		}

		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'égalité métier. */
		assertEquals(OBJET1_EQUALS_OBJET2
				, objet1
					, objet2EqualsObj1);
		

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) ---------------------");
			System.out.println("objetDiff1 : " 
					+ objetDiff1.toString());
			System.out.println("objetDiff2 : " 
					+ objetDiff2.toString());
			System.out.println();
			System.out.println("objetDiff1.equals(objetDiff2) : " + objetDiff1.equals(objetDiff2));
			System.out.println("objetDiff1.hashcode() == objetDiff2.hashcode() : " + (objetDiff1.hashCode() == objetDiff2.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1 PAS equals(objetDiff2) : "
				, objetDiff1.equals(objetDiff2));
		assertFalse("objetDiff1.hashCode() "
				+ "PAS equals(objetDiff2.hashCode()) : "
				, objetDiff1.hashCode() == objetDiff2.hashCode());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE 2 Objets DIFFERENTS (pas equals) avec des attributs null---------------------");
			System.out.println("objetDiff1AvecNull : " 
					+ objetDiff1AvecNull.toString());
			System.out.println("objetDiff2AvecNull : " 
					+ objetDiff2AvecNull.toString());
			System.out.println();
			System.out.println("objetDiff1AvecNull.equals(objetDiff2AvecNull) : " + objetDiff1AvecNull.equals(objetDiff2AvecNull));
			System.out.println("objetDiff1AvecNull.hashcode() == objetDiff2AvecNull.hashcode() : " + (objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode()));
		}
		
		/* garantit le bon fonctionnement de equals() 
		 * en cas d'inégalité métier. */
		assertFalse("objetDiff1AvecNull PAS equals(objetDiff2AvecNull) : "
				, objetDiff1AvecNull.equals(objetDiff2AvecNull));
		assertFalse("objetDiff1AvecNull.hashCode() "
				+ "PAS equals(objetDiff2AvecNull.hashCode()) : "
				, objetDiff1AvecNull.hashCode() == objetDiff2AvecNull.hashCode());
				
	} // Fin de testEquals().______________________________________________



	/**
	 * Teste la méthode <b>compareTo(...)</b> :
	 * <ul>
	 * <li>garantit que compareTo(memeInstance) retourne 0.</li>
	 * <li>garantit que compareTo(null) retourne un nombre négatif.</li>
	 * <li>garantit le contrat Java Contrat Java : 
	 * x.equals(y) ---> x.compareTo(y) == 0.</li>
	 * <li>garantit que les null sont bien gérés 
	 * dans compareTo(...).</li>
	 * <li>garantit le bon fonctionnement (bon ordre) de compareTo().</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCompareTo() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE MonCerbereTest - méthode testCompareTo() ********** ");
		}

		
		/* garantit que compareTo(memeInstance) retourne 0. */		
		final int compareMemeInstance 
			= objet1.compareTo(objet1MemeInstance);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 MEMES INSTANCES ------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println("objet1MemeInstance : " + objet1MemeInstance.toString());
			System.out.println();
			System.out.println("COMPARETO() DE LA MEME INSTANCE (objet1.compareTo(objet1MemeInstance)) : " 
					+ compareMemeInstance);
		}
		
		assertTrue("compareTo(memeInstance) doit retourner 0 : "
				, compareMemeInstance == 0);

		
		/* garantit que compareTo(null) retourne -1. */
		final int compareToNull = objet1.compareTo(null);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE objet1 avec null ---------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("COMPARETO(null) (objet1.compareTo(null)) : " 
					+ compareToNull);
		}
		
		assertTrue("compareTo(null) doit retourner négatif : "
				, compareToNull < 0);

		
		/* garantit le contrat Java Contrat Java : 
		 * x.equals(y) ---> x.compareTo(y) == 0. */		
		final int compareToEquals 
			= objet2EqualsObj1.compareTo(objet3EqualsObj1);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 objets equals ---------------------");
			System.out.println("objet2EqualsObj1 : " + objet2EqualsObj1.toString());
			System.out.println("objet3EqualsObj1 : " + objet3EqualsObj1.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets equals)  (objet2EqualsObj1.compareTo(objet3EqualsObj1)) : " 
					+ compareToEquals);
		}

		assertTrue("Instance.compareTo(equalsInstance) doit retourner 0 : "
				, compareToEquals == 0);
		assertTrue("loc10.hashCode() == loc11.hashCode() : "
				, objet2EqualsObj1.hashCode() == objet3EqualsObj1.hashCode());
		
		
		
		/* garantit que les null sont bien gérés dans 
		 * compareTo(...). */		
		final int compareToEqualsNull 
			= objetNull1.compareTo(objetNull2);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE (ou valeurs par défaut) ------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println("objetNull2 : " + objetNull2.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets instanciés avec le constructeur d'arite nulle)  (objetNull1.compareTo(objetNull2)) : " 
					+ compareToEqualsNull);
		}
		
		assertTrue("InstanceNull.compareTo(equalsInstanceNull) doit retourner 0 : "
				, compareToEqualsNull == 0);
		assertTrue("locNull10.hashCode() == locNull11.hashCode() : "
				, objetNull1.hashCode() == objetNull2.hashCode());
		

		
		/* garantit le bon fonctionnement (bon ordre) de compareTo(). */		
		final int compare 
			= objetCompAvant.compareTo(objetCompApres);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("COMPARAISON DE 2 Objets différents -------------");
			System.out.println("objetCompAvant : " + objetCompAvant.toString());
			System.out.println("objetCompApres : " + objetCompApres.toString());
			System.out.println();
			System.out.println("COMPARETO(2 objets différents) (objetCompAvant.compareTo(this.objetCompApres) : " 
					+ compare);
			
		}
		
		assertTrue("objetCompAvant doit être avant objetCompApres : "
				, compare < 0);
		
	} // Fin de testCompareTo().___________________________________________
	

	
	/**
	 * Teste la méthode <b>clone()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans clone().</li>
	 * <li>garantit que clonex.equals(x).</li>
	 * <li>garantit que x et son clone ne sont pas la même instance.</li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testClone() throws CloneNotSupportedException {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE MonCerbereTest - méthode testClone() ********** ");
		}

		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJET INSTANCIES AVEC LE CONSTRUCTEUR D'ARITE NULLE ---------------------");
			System.out.println(OBJETNULL1 
					+ objetNull1.toString());
			System.out.println("objetNullClone1 : " 
					+ objetNullClone1.toString());
			System.out.println();
			System.out.println("objetNull1.equals(objetNullClone1) : " + objetNull1.equals(objetNullClone1));
		}
		
		/* garantit que les null sont bien gérés dans clone(). */
		assertEquals("objetNull1.equals(objetNullClone1) : "
				, objetNull1
					, objetNullClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DU CLONAGE D'OBJETS ORDINAIREMENT INSTANCIES --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1.equals(objetClone1) : " + objet1.equals(objetClone1));
		}
		
		/* garantit que clone(x).equals(x). */
		assertEquals("clone(x).equals(x) : "
				, objet1
					, objetClone1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("INSTANCE ET CLONE DIFFERENTS --------------------");
			System.out.println(OBJET1 
					+ objet1.toString());
			System.out.println("objetClone1 : " 
					+ objetClone1.toString());
			System.out.println();
			System.out.println("objet1 == objetClone1 : " + (objet1 == objetClone1));    
		}
		
		/* garantit que x et son clone ne sont pas la même instance. */
		assertNotSame("x != clonex : "
				, objet1
					, objetClone1);
				
	} // Fin de testClone()._______________________________________________
	
	
		
	/**
	 * Teste la méthode <b>toString()</b> :
	 * <ul>
	 * <li>garantit que les null sont bien gérés dans toString().</li>
	 * <li>garantit le bon affichage de toString().</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testToString() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE MonCerbereTest - méthode testToString() ********** ");
		}
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objetNull1.toString());
		}
		
		/* garantit que les null sont bien gérés dans toString(). */
		assertEquals("objetNull1.toString() retourne une chaine : "
				, "MonCerbere [id=0, login=null, civilite=null, prenom=null, nom=null, telephoneFixe=null, mel=null, service=null, unite=null, profils=null, dateCreation=null, dateDerniereModification=null]"
						, objetNull1.toString());
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(objet1.toString());
		}
		
		/* garantit le bon affichage de toString(). */
		assertEquals("affichage : "
				, "MonCerbere [id=1, login=vincent.robin, civilite=M, prenom=Vincent, nom=Robin, telephoneFixe=05 66 99 88 52, mel=vincent.robin@cerema.fr, service=CEREMA, unite=CEREMA/DTerMed/DCEDI/GTIE, profils={ROLE_ADMINISTRATEUR - TOUT, ROLE_GESTIONNAIRE - DIRA, ROLE_GESTIONNAIRE - DIRCE, ROLE_GESTIONNAIRE - DARWIN}, dateCreation=01/01/2020, dateDerniereModification=null]"
						, objet1.toString());
				
	} // Fin de testToString().____________________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteCsv()</b> :
	 * <ul>
	 * <li>garantit que fournirEnTeteCsv() retourne le bon en-tête csv.</li>
	 * <li><b>Pour les Développeurs : 
	 * Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE MonCerbereTest - méthode testFournirEnTeteCsv() ********** ");
		}
	

		/* garantit que fournirEnTeteCsv() retourne le bon en-tête csv. */
		final String entete = objet1.fournirEnTeteCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("EN-TETE CSV ------------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("En-tête csv : " + entete);
		}
		
		assertEquals("en-tête csv : "
				, "id;login;civilite;prenom;nom;telephoneFixe;mel;service;unite;profils;dateCreation;dateDerniereModification;"
					, entete);
				
	} // Fin de testFournirEnTeteCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirStringCsv()</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans fournirStringCsv().</li> 
	 * <li>garantit que fournirStringCsv() retourne la bonne ligne csv.</li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirStringCsv() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE MonCerbereTest - méthode testFournirStringCsv() ********** ");
		}

		/* garantit que les null sont gérés dans fournirStringCsv(). */
		final String ligneCsvNull = objetNull1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE -----------------------");
			System.out.println("objetNull1.toString() : " + objetNull1.toString());
			System.out.println();
			System.out.println("objetNull1.fournirStringCsv() : " 
					+ ligneCsvNull);
		}

		assertEquals("ligne csv null = "
				, "0;null;null;null;null;null;null;null;null;null;null;null;"
					, ligneCsvNull);
		
						
		/* garantit que fournirStringCsv() retourne la bonne ligne csv. */
		final String ligneCsv = objet1.fournirStringCsv();

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("STRING CSV D' UN OBJET INSTANIE AVEC LE CONSTRUCTEUR ORDINAIRE -----------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("objet1.fournirStringCsv() = " + ligneCsv);
		}

		assertEquals("ligne csv : "
				, "1;vincent.robin;M;Vincent;Robin;05 66 99 88 52;vincent.robin@cerema.fr;CEREMA;CEREMA/DTerMed/DCEDI/GTIE;{ROLE_ADMINISTRATEUR - TOUT, ROLE_GESTIONNAIRE - DIRA, ROLE_GESTIONNAIRE - DIRCE, ROLE_GESTIONNAIRE - DARWIN};01/01/2020;null;"
					, ligneCsv);
				
	} // Fin de testFournirStringCsv().____________________________________
	

	
	/**
	 * Teste la méthode <b>fournirEnTeteColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirEnTeteColonne(int pI).</li> 
	 * <li>garantit que fournirEnTeteColonne(int pI) retourne 
	 * la bonne en-tête de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirEnTeteColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE MonCerbereTest - méthode testFournirEnTeteColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirEnTeteColonne(int pI). */		
		final String enteteNull0 = objetNull1.fournirEnTeteColonne(0);
		final String enteteNull1 = objetNull1.fournirEnTeteColonne(1);
		final String enteteNull2 = objetNull1.fournirEnTeteColonne(2);
		final String enteteNull3 = objetNull1.fournirEnTeteColonne(3);
		final String enteteNull4 = objetNull1.fournirEnTeteColonne(4);
		final String enteteNull5 = objetNull1.fournirEnTeteColonne(5);
		final String enteteNull6 = objetNull1.fournirEnTeteColonne(6);
		final String enteteNull7 = objetNull1.fournirEnTeteColonne(7);
		final String enteteNull8 = objetNull1.fournirEnTeteColonne(8);
		final String enteteNull9 = objetNull1.fournirEnTeteColonne(9);
		final String enteteNull10 = objetNull1.fournirEnTeteColonne(10);
		final String enteteNull11 = objetNull1.fournirEnTeteColonne(11);
		
		/* garantit que fournirEnTeteColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String entete0 = objet1.fournirEnTeteColonne(0);
		final String entete1 = objet1.fournirEnTeteColonne(1);
		final String entete2 = objet1.fournirEnTeteColonne(2);
		final String entete3 = objet1.fournirEnTeteColonne(3);
		final String entete4 = objet1.fournirEnTeteColonne(4);
		final String entete5 = objet1.fournirEnTeteColonne(5);
		final String entete6 = objet1.fournirEnTeteColonne(6);
		final String entete7 = objet1.fournirEnTeteColonne(7);
		final String entete8 = objet1.fournirEnTeteColonne(8);
		final String entete9 = objet1.fournirEnTeteColonne(9);
		final String entete10 = objet1.fournirEnTeteColonne(10);
		final String entete11 = objet1.fournirEnTeteColonne(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println(OBJET1 + objet1.toString());
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("EN-TETES JTABLE -----------------------------");
			System.out.println("entete0 (objet1.fournirEnTeteColonne(0)) : " + entete0);
			System.out.println("enteteNull0 (objetNull1.fournirEnTeteColonne(0)) : " + enteteNull0);
			System.out.println();
			System.out.println("entete1 (objet1.fournirEnTeteColonne(1)) : " + entete1);
			System.out.println("enteteNull1 (objetNull1.fournirEnTeteColonne(1)) : " + enteteNull1);
			System.out.println();
			System.out.println("entete2 (objet1.fournirEnTeteColonne(2)) : " + entete2);
			System.out.println("enteteNull2 (objetNull1.fournirEnTeteColonne(2)) : " + enteteNull2);			
			System.out.println();
			System.out.println("entete3 (objet1.fournirEnTeteColonne(3)) : " + entete3);
			System.out.println("enteteNull3 (objetNull1.fournirEnTeteColonne(3)) : " + enteteNull3);			
			System.out.println();
			System.out.println("entete4 (objet1.fournirEnTeteColonne(4)) : " + entete4);
			System.out.println("enteteNull4 (objetNull1.fournirEnTeteColonne(4)) : " + enteteNull4);
			System.out.println();
			System.out.println("entete5 (objet1.fournirEnTeteColonne(5)) : " + entete5);
			System.out.println("enteteNull5 (objetNull1.fournirEnTeteColonne(5)) : " + enteteNull5);
			System.out.println();
			System.out.println("entete6 (objet1.fournirEnTeteColonne(6)) : " + entete6);
			System.out.println("enteteNull6 (objetNull1.fournirEnTeteColonne(6)) : " + enteteNull6);			
			System.out.println();
			System.out.println("entete7 (objet1.fournirEnTeteColonne(7)) : " + entete7);
			System.out.println("enteteNull7 (objetNull1.fournirEnTeteColonne(7)) : " + enteteNull7);			
			System.out.println();
			System.out.println("entete8 (objet1.fournirEnTeteColonne(8)) : " + entete8);
			System.out.println("enteteNull8 (objetNull1.fournirEnTeteColonne(8)) : " + enteteNull8);
			System.out.println();
			System.out.println("entete9 (objet1.fournirEnTeteColonne(9)) : " + entete9);
			System.out.println("enteteNull9 (objetNull1.fournirEnTeteColonne(9)) : " + enteteNull9);
			System.out.println();
			System.out.println("entete10 (objet1.fournirEnTeteColonne(10)) : " + entete10);
			System.out.println("enteteNull10 (objetNull1.fournirEnTeteColonne(10)) : " + enteteNull10);			
			System.out.println();
			System.out.println("entete11 (objet1.fournirEnTeteColonne(11)) : " + entete11);
			System.out.println("enteteNull11 (objetNull1.fournirEnTeteColonne(11)) : " + enteteNull11);			
			
		}

		assertEquals("entete0 : ", "id", entete0);
		assertEquals("enteteNull0 : ", "id", enteteNull0);
		
		assertEquals("entete1 : ", "login", entete1);
		assertEquals("enteteNull1 : ", "login", enteteNull1);
				
	} // Fin de testFournirEnTeteColonne().________________________________
	
	
	
	/**
	 * Teste la méthode <b>fournirValeurColonne(int pI)</b> :
	 * <ul>
	 * <li>garantit que les null sont gérés dans 
	 * fournirValeurColonne(int pI).</li> 
	 * <li>garantit que fournirValeurColonne(int pI) retourne 
	 * la bonne valeur de colonne.</li>
	 * <li><b>Compléter les colonnes.</b></li>
	 * <li><b>Adapter la chaîne affichée dans les assertEquals</b></li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFournirValeurColonne() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE MonCerbereTest - méthode testFournirValeurColonne() ********** ");
		}
		
		/* garantit que les null sont gérés 
		 * dans fournirValeurColonne(int pI). */
		final String valeurNull0 = (String) objetNull1.fournirValeurColonne(0);
		final String valeurNull1 = (String) objetNull1.fournirValeurColonne(1);
		final String valeurNull2 = (String) objetNull1.fournirValeurColonne(2);
		final String valeurNull3 = (String) objetNull1.fournirValeurColonne(3);
		final String valeurNull4 = (String) objetNull1.fournirValeurColonne(4);
		final String valeurNull5 = (String) objetNull1.fournirValeurColonne(5);
		final String valeurNull6 = (String) objetNull1.fournirValeurColonne(6);
		final String valeurNull7 = (String) objetNull1.fournirValeurColonne(7);
		final String valeurNull8 = (String) objetNull1.fournirValeurColonne(8);
		final String valeurNull9 = (String) objetNull1.fournirValeurColonne(9);
		final String valeurNull10 = (String) objetNull1.fournirValeurColonne(10);
		final String valeurNull11 = (String) objetNull1.fournirValeurColonne(11);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR D'ARITE NULLE --------------------");
			System.out.println(OBJETNULL1 + objetNull1.toString());
			System.out.println();
			System.out.println("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : " + valeurNull0);
			System.out.println("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull1);
			System.out.println("valeurNull2 ((String) objetNull1.fournirValeurColonne(2)) : " + valeurNull2);
			System.out.println("valeurNull3 ((String) objetNull1.fournirValeurColonne(3)) : " + valeurNull3);
			System.out.println("valeurNull4 ((String) objetNull1.fournirValeurColonne(4)) : " + valeurNull4);
			System.out.println("valeurNull5 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull5);
			System.out.println("valeurNull6 ((String) objetNull1.fournirValeurColonne(2)) : " + valeurNull6);
			System.out.println("valeurNull7 ((String) objetNull1.fournirValeurColonne(3)) : " + valeurNull7);
			System.out.println("valeurNull8 ((String) objetNull1.fournirValeurColonne(4)) : " + valeurNull8);
			System.out.println("valeurNull9 ((String) objetNull1.fournirValeurColonne(1)) : " + valeurNull9);
			System.out.println("valeurNull10 ((String) objetNull1.fournirValeurColonne(2)) : " + valeurNull10);
			System.out.println("valeurNull11 ((String) objetNull1.fournirValeurColonne(3)) : " + valeurNull11);
		}

		assertEquals("valeurNull0 ((String) objetNull1.fournirValeurColonne(0)) : ", "0", valeurNull0);
		assertEquals("valeurNull1 ((String) objetNull1.fournirValeurColonne(1)) : ", null, valeurNull1);

		
		/* garantit que fournirValeurColonne(int pI) retourne 
		 * la bonne en-tête de colonne. */
		final String valeur0 = (String) objet1.fournirValeurColonne(0);
		final String valeur1 = (String) objet1.fournirValeurColonne(1);
		final String valeur2 = (String) objet1.fournirValeurColonne(2);
		final String valeur3 = (String) objet1.fournirValeurColonne(3);
		final String valeur4 = (String) objet1.fournirValeurColonne(4);
		final String valeur5 = (String) objet1.fournirValeurColonne(5);
		final String valeur6 = (String) objet1.fournirValeurColonne(6);
		final String valeur7 = (String) objet1.fournirValeurColonne(7);
		final String valeur8 = (String) objet1.fournirValeurColonne(8);
		final String valeur9 = (String) objet1.fournirValeurColonne(9);
		final String valeur10 = (String) objet1.fournirValeurColonne(10);
		final String valeur11 = (String) objet1.fournirValeurColonne(11);
		
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {	
			System.out.println();
			System.out.println("CAS D'UN OBJET INSTANCIE AVEC LE CONSTRUCTEUR ORDINAIRE -------------------------");
			System.out.println(OBJET1 + objet1.toString());
			System.out.println();
			System.out.println("valeur0 ((String) objet1.fournirValeurColonne(0)) : " + valeur0);
			System.out.println("valeur1 ((String) objet1.fournirValeurColonne(1)) : " + valeur1);
			System.out.println("valeur2 ((String) objet1.fournirValeurColonne(2)) : " + valeur2);
			System.out.println("valeur3 ((String) objet1.fournirValeurColonne(3)) : " + valeur3);
			System.out.println("valeur4 ((String) objet1.fournirValeurColonne(4)) : " + valeur4);
			System.out.println("valeur5 ((String) objet1.fournirValeurColonne(1)) : " + valeur5);
			System.out.println("valeur6 ((String) objet1.fournirValeurColonne(2)) : " + valeur6);
			System.out.println("valeur7 ((String) objet1.fournirValeurColonne(3)) : " + valeur7);
			System.out.println("valeur8 ((String) objet1.fournirValeurColonne(4)) : " + valeur8);
			System.out.println("valeur9 ((String) objet1.fournirValeurColonne(1)) : " + valeur9);
			System.out.println("valeur10 ((String) objet1.fournirValeurColonne(2)) : " + valeur10);
			System.out.println("valeur11 ((String) objet1.fournirValeurColonne(3)) : " + valeur11);
		}
		
		assertEquals("valeur0 ((String) objet1.fournirValeurColonne(0)) : ", "1", valeur0);		
		assertEquals("valeur1 ((String) objet1.fournirValeurColonne(1)) : ", "vincent.robin", valeur1);
		
	} // Fin de testFournirValeurColonne().________________________________
	

	
	/**
	 * <ul>
	 * <li>garantit que setProfils(...) fonctionne.</li>
	 * <li>garantit que ajouterProfil(...) fonctionne.</li>
	 * <li>garantit que setProfils(...) alimente la collection bidirectionnelle liée.</li>
	 * <li>garantit que ajouterProfils(...) alimente la collection bidirectionnelle liée.</li>
	 * <li>garantit que retirerProfil(...) fonctionne.</li>
	 * <li>garantit que retirerProfil(...) met à jour la collection bidirectionnelle liée.</li>
	 * </ul>
	 *
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testCollectionsBidirectionnelles() {

		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println("********** CLASSE MonCerbereTest - méthode testCollectionsBidirectionnelles() ********** ");
		}
		
		final Collection<IMonProfil> profilsBrunoInchingolo 
			= userBrunoInchingoloAdmin.getProfils();
		
		final Collection<IMonProfil> profilsBertrandPompete 
			= userBertrandPompeteAdmin.getProfils();
		
		final Collection<IMonProfil> profilsIsabelleLeroyDira 
			= userIsabelleLeroyDira.getProfils(); 
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("Rôles de Bruno Inchingolo ADMIN ********** : ");
			System.out.println(TIRETS);
			userBrunoInchingoloAdmin.afficherConsoleCollectionLiee();
						
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("Rôles de Bertrand Pompète ADMIN ************ : ");
			System.out.println(TIRETS);
			userBertrandPompeteAdmin.afficherConsoleCollectionLiee();
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("Rôles de Isabelle Leroy DIRA ********** : ");
			System.out.println(TIRETS);
			userIsabelleLeroyDira.afficherConsoleCollectionLiee();
			
		}
		
		/* garantit que setProfils(...) fonctionne. */
		assertTrue(
				"Bruno Inchingolo doit avoir 4 rôles : "
					, profilsBrunoInchingolo.size() == 4);
		
		assertTrue(
				"Bertrand Pompète doit avoir 4 rôles : "
					, profilsBertrandPompete.size() == 4);
		/* garantit que ajouterProfil(...) fonctionne. */
		assertTrue(
				"Isabelle Leroy doit avoir 1 rôle : "
					, profilsIsabelleLeroyDira.size() == 1);
		

		// BI-DIRECTIONNALITE ***************
		final Collection<IMonCerbere> usersAyantRoleAdmin 
			= PROFIL_ADMIN.getMonCerberes();
		
		final Collection<IMonCerbere> usersAyantRoleGestionnaireDira 
			= PROFIL_GESTIONNAIRE_DIRA.getMonCerberes();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("USERS ayant le rôle ADMIN ***************** : ");
			System.out.println(TIRETS);
			PROFIL_ADMIN.afficherConsoleCollectionLiee();
			
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("USERS ayant le rôle GESTIONNAIRE_DIRA ****** : ");
			System.out.println(TIRETS);
			PROFIL_GESTIONNAIRE_DIRA.afficherConsoleCollectionLiee();
						
		}
		
		/* garantit que setProfils(...) alimente la collection bidirectionnelle liée. */
		assertTrue(
				"5 Users ont le rôle ADMIN : "
					, usersAyantRoleAdmin.size() == 5);
		
		/* garantit que ajouterProfils(...) alimente la collection bidirectionnelle liée.*/
		assertTrue(
				"8 Users ont le rôle GESTIONNAIRE DIRA : "
					, usersAyantRoleGestionnaireDira.size() == 8);
		
		// RETIRER.
		userBrunoInchingoloAdmin.retirerProfil(PROFIL_ADMIN);
		
		final Collection<IMonCerbere> usersAyantRoleAdminApresRetraitBruno 
		= PROFIL_ADMIN.getMonCerberes();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("Rôles de Bruno Inchingolo ADMIN APRES RETRAIT ROLE ADMIN ********** : ");
			System.out.println(TIRETS);
			userBrunoInchingoloAdmin.afficherConsoleCollectionLiee();
			
			
			System.out.println();
			System.out.println(TIRETS);
			System.out.println("USERS ayant le rôle ADMIN APRES RETRAIT ROLE ADMIN A BRUNO INCHINGOLO ***************** : ");
			System.out.println(TIRETS);
			PROFIL_ADMIN.afficherConsoleCollectionLiee();

		}
		
		
		/* garantit que retirerProfil(...) fonctionne. */
		assertTrue(
				"Bruno Inchingolo doit avoir 3 rôles : "
					, profilsBrunoInchingolo.size() == 3);

		/* garantit que retirerProfil(...) met à jour la collection bidirectionnelle liée. */
		assertTrue(
				"4 Users ont le rôle ADMIN : "
					, usersAyantRoleAdmin.size() == 4);
		
		assertFalse(
				"Le profil ADMIN ne doit plus contenir Bruno Inchingolo : "
				, usersAyantRoleAdminApresRetraitBruno.contains(userBrunoInchingoloAdmin));
		
	} // Fin de testCollectionsBidirectionnelles().________________________
	
	
	
	/**
	 * method avantTests() :
	 * <ul>
	 * <li>instructions exécutées <b>avant l'ensemble des tests</b> 
	 * de la classe JUnit.</li>
	 * <li><b>A REMPLIR A LA MAIN</b></li>
	 * </ul>
	 * @throws CloneNotSupportedException 
	 */
	@BeforeClass
   public static void avantTests() throws CloneNotSupportedException {
		
		final Collection<IMonProfil> listeAdmin 
			= new ArrayList<IMonProfil>();
		
		listeAdmin.add(PROFIL_ADMIN);
		listeAdmin.add(PROFIL_GESTIONNAIRE_DIRA);
		listeAdmin.add(PROFIL_GESTIONNAIRE_DIRCE);
		listeAdmin.add(PROFIL_GESTIONNAIRE_DARWIN);
		
		objet1.setProfils(listeAdmin);
		
		objetDiff1.ajouterProfil(PROFIL_GESTIONNAIRE_DIRA);
		objetDiff2.ajouterProfil(PROFIL_GESTIONNAIRE_DIRCE);
		
		objetDiff1AvecNull.setProfils(listeAdmin);
		objetDiff2AvecNull.ajouterProfil(PROFIL_GESTIONNAIRE_DARWIN);
		
		objetCompAvant.ajouterProfil(PROFIL_GESTIONNAIRE_DIRA);
		objetCompApres.setProfils(listeAdmin);
		
		objetNullClone1 = objetNull1.clone();
		objetClone1 = objet1.clone();

		
		final Collection<IMonProfil> listeAdmin2 
			= new ArrayList<IMonProfil>();
		
		listeAdmin2.add(PROFIL_ADMIN);
		listeAdmin2.add(PROFIL_GESTIONNAIRE_DIRA);
		listeAdmin2.add(PROFIL_GESTIONNAIRE_DIRCE);
		listeAdmin2.add(PROFIL_GESTIONNAIRE_DARWIN);

		userBrunoInchingoloAdmin.setProfils(listeAdmin2);
		userBertrandPompeteAdmin.setProfils(listeAdmin2);
		userIsabelleLeroyDira.ajouterProfil(PROFIL_GESTIONNAIRE_DIRA);
		
	} // Fin de avantTests().______________________________________________

	

} // FIN DE LA CLASSE MonCerbereTest.----------------------------------------
