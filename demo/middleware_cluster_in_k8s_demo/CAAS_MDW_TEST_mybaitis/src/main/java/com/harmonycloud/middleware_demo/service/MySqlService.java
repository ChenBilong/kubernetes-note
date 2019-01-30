package com.harmonycloud.middleware_demo.service;

import com.harmonycloud.middleware_demo.annotation.Slave;
import com.harmonycloud.middleware_demo.config.DynamicDataSource;
import com.harmonycloud.middleware_demo.mapper.UserMapper;
import com.harmonycloud.middleware_demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class MySqlService {
    @Autowired
    private UserMapper mapper;

    @Slave
    public List<User> selectAll() {
        return mapper.selectAll();
    }

    public boolean addUser(User user) {
        return mapper.insertSelective(user) > 0;
    }

    public boolean updateUser(User user) {
        return mapper.updateByPrimaryKey(user) > 0;
    }

    public boolean deleteByid(int id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    @Transactional(rollbackFor = Exception.class )
    public boolean insertAndUpdate(User user){
        log.info("当前key："+ DynamicDataSource.getType().name());
        int count = 0;
        count += mapper.insertSelective(user);
        user = null;
        user.getId();
        count += mapper.updateByPrimaryKey(user);
        return count > 1;
    }
}
