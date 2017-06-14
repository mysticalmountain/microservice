package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Resource;
import com.andx.micro.permission.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    public Service findByCode(String code);

    public Service findByResource(Resource resource);

    @Query(value = "select * from service s where s.isAudit = 0 order by created_time desc limit ?1, ?2", nativeQuery = true)
    public List<Service> findService(int start, int count);
}
