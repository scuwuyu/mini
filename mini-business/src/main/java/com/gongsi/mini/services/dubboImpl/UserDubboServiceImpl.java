package com.gongsi.mini.services.dubboImpl;

import com.gongsi.mini.dtos.*;
import com.gongsi.mini.entities.QueryUserResult;
import com.gongsi.mini.apis.UserDubboService;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.TestService;
import com.gongsi.mini.utils.BeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class UserDubboServiceImpl implements UserDubboService {

    @Autowired
    private TestService testService;

    public QueryUserResultDto findUser(String name, Integer age) {
        QueryUserDto dto = new QueryUserDto();
        dto.setName(name);
        dto.setAge(age);
        QueryUserResult result = testService.findResult(dto);
        return BeanMapper.map(result, QueryUserResultDto.class);
    }

    public QueryUserResultDto findUser(FindUserParamDto paramDto) {
        QueryUserDto dto = BeanMapper.map(paramDto, QueryUserDto.class);
        QueryUserResult result = testService.findResult(dto);
        return BeanMapper.map(result, QueryUserResultDto.class);
    }

    public Boolean updateUser(UpdateUserDto dto) {
        return null;
    }

    public Long saveUser(SaveUserDto dto) {
        User user = BeanMapper.map(dto, User.class);
        return testService.saveUser(user);
    }

    public Boolean deleteUser(DeleteUserDto dto) {
        return null;
    }

}
