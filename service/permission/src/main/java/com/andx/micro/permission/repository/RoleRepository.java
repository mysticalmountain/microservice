package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public void deleteByPermissions(Set<Permission> permissions);
}
