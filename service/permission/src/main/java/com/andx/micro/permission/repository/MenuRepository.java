package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Menu;
import com.andx.micro.permission.model.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-24.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    public Menu findByName(String name, Sort sort);

    public Menu findByName(String name);

    public Menu findByResource(Resource resource);
}
