package com.app.repositories;

import com.app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 22.04.2016.
 */
public interface RoleRepository extends JpaRepository<Role, Serializable> {
}
