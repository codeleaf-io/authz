package io.codeleaf.authz.types;

import io.codeleaf.authz.Permissions;

public interface GroupPermissions extends Permissions {

    String getGroup();

}
