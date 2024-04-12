package com.cleverpine.query.config;

import com.cleverpine.cpspringerrorutil.mapper.BaseExceptionTypeMapper;
import com.cleverpine.cpspringerrorutil.mapper.ExceptionTypeMapper;
import com.cleverpine.query.auth.roles.Resources;
import com.cleverpine.query.auth.roles.Roles;
import com.cleverpine.viravaspringhelper.config.AuthTokenConfig;
import com.cleverpine.viravaspringhelper.config.RoleConfig;
import com.cleverpine.viravaspringhelper.filter.ViravaFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Setter
@Configuration
@ConfigurationProperties(prefix = "auth.token")
public class ViravaHelperConfig {

    private String usernamePath;
    private String rolesPath;
    private String secret;
    private String issuer;

    @Bean
    public RoleConfig<Roles, Resources> roleConfig() {
        return new RoleConfig<>(Roles.values(), Resources.values());
    }

    @Primary
    @Bean(name = "decodingAuthTokenConfig")
    public AuthTokenConfig decodingAuthTokenConfig() {
        return new AuthTokenConfig(usernamePath, rolesPath);
    }

    @Bean(name = "verifyingAuthTokenConfig")
    public AuthTokenConfig verifyingAuthTokenConfig() {
        return new AuthTokenConfig(usernamePath, rolesPath, secret, issuer);
    }

    @Bean
    public ViravaFilter viravaFilter(RoleConfig<Roles, Resources> roleConfig, ObjectMapper objectMapper, AuthTokenConfig authTokenConfig) {
        return new ViravaFilter(roleConfig, objectMapper, authTokenConfig);
    }

    @Bean
    public ExceptionTypeMapper exceptionTypeMapper() {
        return new BaseExceptionTypeMapper();
    }
}
