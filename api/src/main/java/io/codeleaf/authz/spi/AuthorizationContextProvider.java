package io.codeleaf.authz.spi;

import io.codeleaf.authn.NotAuthenticatedException;
import io.codeleaf.authz.AuthorizationContext;

public interface AuthorizationContextProvider {

    AuthorizationContext getAuthorizationContext() throws NotAuthenticatedException;

}
