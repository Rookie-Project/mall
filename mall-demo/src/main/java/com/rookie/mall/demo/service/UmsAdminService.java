package com.rookie.mall.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookie.mall.demo.dao.UmsAdminMapper;
import com.rookie.mall.demo.dao.UmsPermissionMapper;
import com.rookie.mall.demo.entity.UmsAdmin;
import com.rookie.mall.demo.entity.UmsPermission;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: chen
 * @date: 2022/1/17
 * @description: TODO 类描述
 **/
@Service
public class UmsAdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsPermissionMapper permissionMapper;

    public UmsAdmin getUmsAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        //username不唯一，后续需要指定唯一性条件
        List<UmsAdmin> umsAdmins = adminMapper.selectList(wrapper);
        if (umsAdmins != null && umsAdmins.size() > 0) {
            return umsAdmins.get(0);
        }
        return null;
    }

    public List<UmsPermission> getPermission(long adminId) {
        //List<UmsPermission> permissionList = permissionMapper.
        //TODO
        return null;
    }


    /*
     * @Author chen
     * @Description 注册
     * @Date  2022/2/10
     * @Param
     * @param admin
     * @return
     **/
    public void register(UmsAdmin admin) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(admin,umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        if (getUmsAdminByUsername(umsAdmin.getUsername()) != null) {
            return;
        }

        String password = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(password);
        adminMapper.insert(umsAdmin);

    }
}
