package pi.project.languageApp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        logger.info("Configuring UserDetailsManager...");
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager
                .setUsersByUsernameQuery("select username, password, enabled from users where username=?");
        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select username, authority from authorities where username=?");

        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/css/**", "/js/**", "/images/**", "sharedElements/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/security/loginUser").permitAll()
                                .requestMatchers(HttpMethod.POST, "/security/manualLogout").permitAll()
                                .requestMatchers(HttpMethod.GET, "/security/showFormCreateUser").permitAll()
                                .requestMatchers(HttpMethod.POST, "/security/saveUser").permitAll()
                                .requestMatchers(HttpMethod.GET, "/testing/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/error").permitAll()

                                //LANGUAGES
                                .requestMatchers(HttpMethod.GET, "/languages/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/languages/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/languages/showFormForAddLanguage").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/languages/showFormForUpdateLanguage").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/languages/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/languages/delete").hasRole("ADMIN")

                                //PHRASES
                                .requestMatchers(HttpMethod.GET, "/phrases/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phrases/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phrases/showFormForAddPhrase").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phrases/showFormForUpdatePhrase").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/phrases/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phrases/delete").hasRole("ADMIN")

                                //WORDS
                                .requestMatchers(HttpMethod.GET, "/words/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/words/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/words/showFormForAddWord").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/words/showFormForUpdateWord").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/words/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/words/delete").hasRole("ADMIN")

                                //TRANSLATION PHRASE
                                .requestMatchers(HttpMethod.GET, "/translationPhrases/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationPhrases/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationPhrases/showFormForAddTranslationPhrase").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationPhrases/showFormForUpdateTranslationPhrase").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/translationPhrases/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationPhrases/delete").hasRole("ADMIN")

                                //TRANSLATION WORD
                                .requestMatchers(HttpMethod.GET, "/translationWords/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationWords/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationWords/showFormForAddTranslationWord").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationWords/showFormForUpdateTranslationWord").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/translationWords/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/translationWords/delete").hasRole("ADMIN")

                                //TASK-WORD
                                .requestMatchers(HttpMethod.GET, "/wordTasks/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wordTasks/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wordTasks/showFormForAddWordTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wordTasks/showFormForUpdateWordTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/wordTasks/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wordTasks/delete").hasRole("ADMIN")

                                //TASK-PHRASE
                                .requestMatchers(HttpMethod.GET, "/phraseTasks/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phraseTasks/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phraseTasks/showFormForAddPhraseTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phraseTasks/showFormForUpdatePhraseTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/phraseTasks/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/phraseTasks/delete").hasRole("ADMIN")

                                //TASK-CUSTOM
                                .requestMatchers(HttpMethod.GET, "/customTasks/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customTasks/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customTasks/showFormForAddCustomTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customTasks/showFormForUpdateCustomTask").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/customTasks/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customTasks/delete").hasRole("ADMIN")

                                //FORUM-POSTS
                                .requestMatchers(HttpMethod.GET, "/forums/list").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/forums/listSearch").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/forums/showFormForAddPost").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/forums/showFormForUpdatePost").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/forums/showPost/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/forums/addComment").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/forums/save").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/forums/delete").hasRole("ADMIN")

                                //GAMES/TASKS
                                .requestMatchers(HttpMethod.GET, "/games/phraseGame").hasRole("STUDENT")
                )
                .formLogin(form ->
                        form
                                .loginPage("/security/loginUser")
                                .loginProcessingUrl("/security/security-login")
                                .successHandler(new CustomAuthenticationSuccessHandler())
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/security/manualLogout")
                        .logoutSuccessUrl("/security/loginUser?logoutManual=true") // the URL to redirect to after logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());
        // disable Cross Site Request Forgery (CSRF)
        http.csrf(csfr->csfr.disable());
        return http.build();
    }
}