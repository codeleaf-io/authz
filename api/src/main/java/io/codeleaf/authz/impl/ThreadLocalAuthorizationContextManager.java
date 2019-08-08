package io.codeleaf.authz.impl;

import io.codeleaf.authz.AuthorizationContext;
import io.codeleaf.authz.spi.AuthorizationContextProvider;

public class ThreadLocalAuthorizationContextManager implements AuthorizationContextProvider {

    private final ThreadLocal<AuthorizationContext> authorizationContextThreadLocal = new ThreadLocal<>();

    @Override
    public AuthorizationContext getAuthorizationContext() {
        return authorizationContextThreadLocal.get();
    }

    public void setAuthorizationContext(AuthorizationContext authorizationContext) {
        authorizationContextThreadLocal.set(authorizationContext);
    }

    public void clearAuthorizationContext() {
        authorizationContextThreadLocal.remove();
    }
}
