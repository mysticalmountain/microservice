package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Operate;
import com.andx.micro.permission.model.Permission;
import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    public Set<Permission> findByRoles(Role role);

    public Permission findByResource(Resource resource);

    public Permission findByResourceAndOperate(Resource resource, Operate operate);
}
