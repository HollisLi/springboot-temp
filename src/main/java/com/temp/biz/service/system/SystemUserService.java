package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.entity.system.SystemUser;
import com.temp.biz.mapper.system.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * 系统用户 Service
 *
 * @author Hollis
 * @since 2024/12/17 11:30
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> {

}
