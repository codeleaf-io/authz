package io.codeleaf.authz.spi;

import io.codeleaf.authz.Authorization;
import io.codeleaf.authz.Permissions;

import java.util.Set;

public interface Authorizer {

    boolean isAuthorized(Permissions permissions, Set<Authorization> authorizations);

}
