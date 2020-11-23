package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * 项目前置准备，查询所有的Admin
     * @return
     */
    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    /**
     * 用户登录业务层操作
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        //1、根据登录账号查询Admin对象
        //  1）创建一个AdminExample对象
        AdminExample adminExample = new AdminExample();

        //  2）创建一个Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        //  3）在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        //2、判断Admin对象是否为null
        List<Admin> list = adminMapper.selectByExample(adminExample);
        if (list == null || list.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if (list.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);

        //3、如果Admin对象为null则抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //4、如果Admin对象不为null则将数据库密码从admin对象中取出
        String userPswdDB = admin.getUserPswd();

        //5、将表单提交的的明文密码加密
        String userPswdForm = CrowdUtil.md5(userPswd);


        //6、对密码进行比较
        if (Objects.equals(userPswd,userPswdForm)) {
            //7、如果比较结果不一样则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //8、如果一致则返回admin对象
        return admin;
    }

    /**
     * 分页业务的操作
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        //1、调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        //2、执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        //3、封装到PageInfo对象中
        return new PageInfo<>(list);

    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }
}
