package com.andx.micro.user.repository;

/**
 * Created by andongxu on 17-6-30.
 */

import com.andx.micro.user.model.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {

}
