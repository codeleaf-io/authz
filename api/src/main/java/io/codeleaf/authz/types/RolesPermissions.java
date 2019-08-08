package io.codeleaf.authz.types;

import io.codeleaf.authz.Permissions;

import java.util.Set;

public interface RolesPermissions extends Permissions {

    Set<String> getAllowedRoles();

}
