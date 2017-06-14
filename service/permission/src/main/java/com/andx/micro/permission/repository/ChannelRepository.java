package com.andx.micro.permission.repository;

import com.andx.micro.permission.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    public Channel findByCode(String code);

}
