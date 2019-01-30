package com.harmonycloud.middleware_demo.mapper;

import com.harmonycloud.middleware_demo.model.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserMapper extends Mapper<User> {
}
