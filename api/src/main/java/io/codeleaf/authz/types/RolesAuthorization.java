package io.codeleaf.authz.types;

import io.codeleaf.authz.Authorization;

import java.util.Set;

public interface RolesAuthorization extends Authorization {

    Set<String> getRoles();

}
