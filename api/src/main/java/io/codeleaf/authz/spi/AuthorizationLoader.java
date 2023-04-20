package io.codeleaf.authz.spi;

import io.codeleaf.authn.AuthenticationContext;
import io.codeleaf.authz.Authorization;

import java.util.Set;

public interface AuthorizationLoader {

    Set<Authorization> loadAuthorizations(AuthenticationContext context);

}
