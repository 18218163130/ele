package com.ele.service.impl;

import com.ele.entity.User;
import com.ele.mapper.UserMapper;
import com.ele.service.UserService;
import com.ele.utils.DataGridView;
import com.ele.vo.UserVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户业务操作
 * @Author dongwf
 * @Date 2019/10/10
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User getUser(String userId, String userName) {
        return userMapper.findUser(userId,userName);
    }

    /**
     * 添加新的客户信息
     * @param user
     */
    @Transactional // 添加事务支持
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 查询客户列表信息
     * @return
     */
    @Override
    public DataGridView getUserList(UserVo userVo) {
        // 分页
        Page<User> page = PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> data = userMapper.queryUserList(userVo);
        // 将客户信息列表封装DataGridView返回
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 更新客户信息
     * @param userVo
     */
    @Transactional
    @Override
    public void updateUser(UserVo userVo) {
        userMapper.updateUser(userVo);
    }

    /**
     * 重置客户密码
     * @param userVo
     */
    @Transactional
    @Override
    public void resetUserPwd(UserVo userVo) {
        userMapper.resetUserPwd(userVo);
    }

    /**
     * 根据id删除客户信息
     * @param userId
     */
    @Transactional
    @Override
    public void deleteUser(String userId) {
        userMapper.deleteUserById(userId);
    }

    @Override
    @Transactional
    public User login(UserVo userVo) {
        return userMapper.login(userVo);
    }

    @Override
    public User checkUserPwd(String userId, String md5Pwd) {
        return userMapper.checkUserIdAndPwd(userId,md5Pwd);
    }

    @Override
    @Transactional
    public void updateUserPwd(String userId, String newPwd) {
        userMapper.updateNewPwd(userId,newPwd);
    }


}
