package io.codeleaf.authz.types;

import io.codeleaf.authz.Permissions;

public interface FilePermissions extends Permissions {

    String getUser();

    String getGroup();

    int getUserBits();

    int getGroupBits();

    int getOtherBits();

}
