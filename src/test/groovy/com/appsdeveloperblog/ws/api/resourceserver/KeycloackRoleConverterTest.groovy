package com.appsdeveloperblog.ws.api.resourceserver

import com.appsdeveloperblog.ws.api.resourceserver.security.KeycloakRoleConverter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import spock.lang.Specification

class KeycloackRoleConverterTest extends Specification{

    def "happy path"(){
        given:
        
        Jwt jwt = new Jwt("token",null,null,["headerkey": "headerValue"], ["realm_access" : ["roles" :  ["default-roles-appsdeveloperblog",
                                                             "offline_access",
                                                             "developer",
                                                             "uma_authorization"]]])
        KeycloakRoleConverter keycloakRoleConverter = new KeycloakRoleConverter()

        when:
            List<GrantedAuthority> result = keycloakRoleConverter.convert(jwt)

        then:
            result != null
            result.contains(new SimpleGrantedAuthority("ROLE_developer"))
    }

    def "test empty list when Jwt don't have any GrantedAuthority"(){
        given:

        Jwt jwt = new Jwt("token",null,null,["headerkey": "headerValue"], ["realm_access" : ["roles" :  []]])
        KeycloakRoleConverter keycloakRoleConverter = new KeycloakRoleConverter()

        when:
        List<GrantedAuthority> result = keycloakRoleConverter.convert(jwt)

        then:
        result.isEmpty()
        !result.contains(new SimpleGrantedAuthority("ROLE_developer"))
    }

    def "test empty list when Jwt realm_access section of the JSON is empty"(){
        given:

        Jwt jwt = new Jwt("token",null,null,["headerkey": "headerValue"], ["realm_access" : [: ]])
        KeycloakRoleConverter keycloakRoleConverter = new KeycloakRoleConverter()

        when:
        List<GrantedAuthority> result = keycloakRoleConverter.convert(jwt)

        then:
        result.isEmpty()
        !result.contains(new SimpleGrantedAuthority("ROLE_developer"))
    }

}
