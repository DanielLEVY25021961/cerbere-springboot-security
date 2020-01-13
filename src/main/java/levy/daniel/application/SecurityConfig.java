package levy.daniel.application;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import levy.daniel.application.security.MonCerbereAuthenticationProvider;
import levy.daniel.application.security.MonCerbereUserDetailsService;



/**
 * CLASSE SecurityConfig :<br/>
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
 * @since 18 nov. 2019
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true, 
		securedEnabled = true, 
		jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * AuthenticationProvider injecté par SPRING.
	 */
	@Autowired
	@Qualifier("MonCerbereAuthenticationProvider")
	private transient MonCerbereAuthenticationProvider cerbereAuthenticationProvider;
	
	/**
	 * UserDetailsService injecté par SPRING.
	 */
	@Autowired
	@Qualifier("MonCerbereUserDetailsService")
    private transient MonCerbereUserDetailsService userDetailsService;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SecurityConfig.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public SecurityConfig() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pAuthenticationManagerBuilder : 
	 * <code>org.springframework.security.config.annotation.authentication
	 * .builders.AuthenticationManagerBuilder</code>.
	 * @param pDataSource : <code>javax.sql.DataSource</code> : 
     * DataSource déclaré dans application.properties injecté par SPRING.
	 * 
	 * @throws Exception 
	 */
	@Autowired
	public void configurationUsers(
			final AuthenticationManagerBuilder pAuthenticationManagerBuilder
				, final DataSource pDataSource) 
					throws Exception {
		
		pAuthenticationManagerBuilder
			.authenticationProvider(this.cerbereAuthenticationProvider);
		pAuthenticationManagerBuilder
    		.userDetailsService(this.userDetailsService);
		       	
//		pAuthenticationManagerBuilder.jdbcAuthentication()
//			.dataSource(pDataSource)       	  		
//			.usersByUsernameQuery("select u.login as principal, 'true' from USERS u where u.login = ? ")
//			.authoritiesByUsernameQuery("select u.login as principal, r.role as role from ROLES r join USERS_ROLES ur on r.id=ur.role_id join USERS u on ur.USER_ID=u.id where u.login = ? ")
//			.rolePrefix("ROLE_");

		
	} // Fin de configurationUsers(...).___________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(final HttpSecurity pHttpSecurity) throws Exception {
		
		pHttpSecurity
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "static/**", "/css/**", "/js/**", "/icones/**", "/images/**", "/versLogout*")
			.permitAll()
			.anyRequest().authenticated();


	} // Fin de configure(...).____________________________________________
	
	
	
} // FIN DE LA CLASSE SecurityConfig.----------------------------------------
