package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-24.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
