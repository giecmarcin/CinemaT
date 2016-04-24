package com.app.services;

import com.app.models.Role;

/**
 * Created by Marcin on 24.04.2016.
 */
public interface RoleService extends GenericService<Role> {
    Role findByName(String name);
}
