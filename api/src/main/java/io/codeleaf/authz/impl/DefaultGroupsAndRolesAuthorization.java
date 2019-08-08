package io.codeleaf.authz.impl;

import io.codeleaf.authz.types.GroupAuthorization;
import io.codeleaf.authz.types.RolesAuthorization;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultGroupsAndRolesAuthorization implements GroupAuthorization, RolesAuthorization {

    private final Set<String> groups;
    private final Set<String> roles;

    private DefaultGroupsAndRolesAuthorization(Set<String> groups, Set<String> roles) {
        this.groups = groups;
        this.roles = roles;
    }

    @Override
    public Set<String> getGroups() {
        return groups;
    }

    @Override
    public Set<String> getRoles() {
        return roles;
    }

    public static DefaultGroupsAndRolesAuthorization create(Set<String> groups, Set<String> roles) {
        return new DefaultGroupsAndRolesAuthorization(
                Collections.unmodifiableSet(new LinkedHashSet<>(groups)),
                Collections.unmodifiableSet(new LinkedHashSet<>(roles)));
    }

}
