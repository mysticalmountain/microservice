package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
