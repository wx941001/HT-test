package com.example.demo.service.impl;


import com.example.demo.Dom.Response;
import com.example.demo.Dom.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    /**
     * 注册
     * @param user 参数封装
     * @return Response
     */
    public Response register(User user) {
        Response result = new Response();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User resUser = userDao.selectUserByName(user.getUsername());
            if(resUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");

            }else{
                userDao.insertUser(user);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 登录
     * @param user 用户名和密码
     * @return Response
     */
    public Response login(User user) {
        Response result = new Response();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userId= userDao.login(user);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
