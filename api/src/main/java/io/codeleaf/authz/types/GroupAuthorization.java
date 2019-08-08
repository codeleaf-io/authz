package io.codeleaf.authz.types;

import io.codeleaf.authz.Authorization;

import java.util.Set;

public interface GroupAuthorization extends Authorization {

    Set<String> getGroups();

}
