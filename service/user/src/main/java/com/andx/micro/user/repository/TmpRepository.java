package com.andx.micro.user.repository;

import com.andx.micro.support.jpa.model.CheckType;
import com.andx.micro.user.model.Tmp;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by andongxu on 17-6-8.
 */
@Repository
public interface TmpRepository extends JpaRepository<Tmp, Long> {

    public List<Tmp> findByCheckType(CheckType checkType);

    public List<Tmp> findByCheckTypeIn(Collection<CheckType> checkTypes, Sort sort);

}
