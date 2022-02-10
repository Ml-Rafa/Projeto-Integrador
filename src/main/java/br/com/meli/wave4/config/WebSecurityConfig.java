package br.com.meli.wave4.config;

import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private Environment env;


    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
    };

    //autenticacao
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        http.cors();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html/**", "/swagger-resources/**", "/webjars/**","/").permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/fresh-products/inboundorder/register-inbound-order/**")
                .hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.PUT, "/api/v1/fresh-products/inboundorder/update-inbound-order/**")
                .hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/list/**").hasAnyAuthority("SELLER", "CLIENT","REPRESENTATIVE")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/due-date/**").hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/due-date-all/**").hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/warehouse/**").hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/**").hasAnyAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/v1/fresh-products/**").hasAnyAuthority("CLIENT")
                .antMatchers(HttpMethod.PUT, "/api/v1/fresh-products/**").hasAnyAuthority("CLIENT")
                .antMatchers(HttpMethod.GET, "/api/v1/recurrent-order/list").hasAnyAuthority("REPRESENTATIVE")
                .antMatchers(HttpMethod.POST, "/api/v1/recurrent-order/create" ).hasAnyAuthority("CLIENT")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationViaTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
    }

    //autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}