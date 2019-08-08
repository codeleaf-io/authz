package io.codeleaf.authz;

import io.codeleaf.authn.AuthenticationContext;
import io.codeleaf.authn.NotAuthenticatedException;
import io.codeleaf.authz.spi.AuthorizationContextProvider;
import io.codeleaf.common.utils.SingletonServiceLoader;

public interface AuthorizationContext {

    AuthenticationContext getAuthenticationContext();

    Authorization getAuthorization();

    static AuthorizationContext get() throws NotAuthenticatedException {
        return AuthorizationContext.Holder.get().getAuthorizationContext();
    }


    /**
     * Holder for a authentication context provider, to obtain the authentication context, use {@link #get()}
     */
    final class Holder {

        private Holder() {
        }

        private static AuthorizationContextProvider INSTANCE;

        static {
            init();
        }

        private static void init() {
            try {
                INSTANCE = SingletonServiceLoader.load(AuthorizationContextProvider.class);
            } catch (Exception cause) {
                throw new ExceptionInInitializerError(cause);
            }
        }

        public static AuthorizationContextProvider get() {
            return INSTANCE;
        }

    }
}