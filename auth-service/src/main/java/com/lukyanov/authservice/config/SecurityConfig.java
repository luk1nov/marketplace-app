package com.lukyanov.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityConfig {
    private static final String AUTHORITIES = "authorities";
    private static final String LOGIN = "/login";

    /**
     * First will be applied the OAuth2 security filters configuration.
     * In this configuration, I only indicate that all the failing request will be redirected to the /login page.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint(LOGIN))
                )
        ;

        return http.build();
    }

    /**
     * Then, will be applied the rest of the security filters.
     * Here, I indicate that all the endpoints require authentication and which is the login form.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Configuration of the OAuth2 client.
     * Multiple clients can be configured. The current implementation stores them in memory, but a table
     * in the database can be used to store all the registered clients.
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                // client-id and client-secret that must be used from all the OAuth2 clients
                .clientId("gateway-client-id")
                .clientSecret("$2a$12$ZUamdYa4buAoIoxI6lGOMunBKesogEKoenlUSjCfyX8.y.x53HYUG")
                // the Basic authentication method will be used between backend-gateway-client and backend-auth
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                // grant types to be used
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // permitted redirect URI after the authentication is successful
                .redirectUri("http://backend-gateway-client:8090/login/oauth2/code/gateway")
                .redirectUri("http://backend-gateway-client:8090/authorized")
                // acceptable scopes for the authorization
                .scope(OidcScopes.OPENID)
                .tokenSettings(tokenSettings())
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    /**
     * Acceptable URL of the authorization server
     */
    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                .issuer("http://backend-auth:8081")
                .build();
    }


    @Bean
    public TokenSettings tokenSettings() {
        // @formatter:off
        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofDays(1L))
                .build();
        // @formatter:on
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
                Authentication principal = context.getPrincipal();
                Set<String> authorities = principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                context.getClaims().claim(AUTHORITIES, authorities);
            }
        };
    }
}
