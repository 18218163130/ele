package com.ele.service;

import com.ele.entity.User;
import com.ele.utils.DataGridView;
import com.ele.vo.UserVo;

/**
 * 客户业务操作接口
 *
 * @Author dongwf
 * @Date 2019/10/10
 */
public interface UserService {
    /**
     * 通过客户名称获取客户信息
     *
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * 通过客户ID和客户名称查询客户信息
     *
     * @param userId
     * @param userName
     * @return
     */
    User getUser(String userId, String userName);

    /**
     * 添加一个新的客户
     *
     * @param userVo
     */
    void addUser(UserVo userVo);

    /**
     * 查询客户列表
     *
     * @return
     */
    DataGridView getUserList(UserVo userVo);

    /**
     * 修改客户信息
     *
     * @param userVo
     */
    void updateUser(UserVo userVo);

    /**
     * 重置客户密码
     *
     * @param userVo
     */
    void resetUserPwd(UserVo userVo);

    /**
     * 删除指定客户
     *
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 客户登录
     *
     * @param userVo
     */
    User login(UserVo userVo);

    /**
     * 校验客户账号和密码
     *
     * @param userId
     * @param md5Pwd
     * @return
     */
    User checkUserPwd(String userId, String md5Pwd);

    /**
     * 更新客户密码
     *
     * @param userId
     * @param newPwd
     */
    void updateUserPwd(String userId, String newPwd);
}
