package io.codeleaf.authz.dummy;

import io.codeleaf.authn.AuthenticationContext;
import io.codeleaf.authn.impl.DefaultAuthenticationContext;
import io.codeleaf.authn.password.spi.Credentials;
import io.codeleaf.authn.password.spi.PasswordRequestAuthenticator;
import io.codeleaf.authz.Authorization;
import io.codeleaf.authz.impl.DefaultGroupsAndRolesAuthorization;
import io.codeleaf.authz.spi.AuthorizationLoader;
import io.codeleaf.config.ConfigurationException;
import io.codeleaf.config.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public final class DummyAuthorizationLoader implements PasswordRequestAuthenticator, AuthorizationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyAuthorizationLoader.class);

    private final String userName;
    private final String password;
    private final Set<Authorization> authorizations;

    private DummyAuthorizationLoader(String userName, String password, Set<Authorization> authorizations) {
        this.userName = userName;
        this.password = password;
        this.authorizations = authorizations;
    }

    @Override
    public AuthenticationContext authenticate(Credentials credentials) {
        boolean matches = true;
        if (!userName.equals(credentials.getUserName())) {
            LOGGER.debug("Username not matching!");
            matches = false;
        }
        if (!password.equals(credentials.getPassword())) {
            LOGGER.debug("Password not matching!");
            matches = false;
        }
        if (matches) {
            LOGGER.debug("Correct credentials");
        }
        return matches ? createAuthenticationContext(userName) : null;
    }

    private AuthenticationContext createAuthenticationContext(String identity) {
        Objects.requireNonNull(identity);
        return new DefaultAuthenticationContext(() -> {
            return identity;
        }, Collections.singletonMap(DummyAuthorizationLoader.class.getName() + ".authorization", authorizations), false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Authorization> loadAuthorizations(AuthenticationContext context) {
        return (Set<Authorization>) context.getAttributes().getOrDefault(DummyAuthorizationLoader.class.getName() + ".authorization", Collections.emptySet());
    }

    public DummyAuthorizationLoader() throws ConfigurationException, IOException {
        this(ConfigurationProvider.get().getConfiguration(DummyConfiguration.class));
    }

    public DummyAuthorizationLoader(DummyConfiguration dummyConfiguration) {
        this(dummyConfiguration.getUserName(), dummyConfiguration.getPassword(),
                Collections.singleton(DefaultGroupsAndRolesAuthorization.create(dummyConfiguration.getGroups(), dummyConfiguration.getRoles())));
    }
}