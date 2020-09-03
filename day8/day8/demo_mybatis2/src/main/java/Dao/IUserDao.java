package Dao;

import Domain.QueryVo;
import Domain.QueryVoIds;
import Domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(@Param(value = "userId") Integer userId);
    int saveUser(User user);
    int updateUserById(User user);
    int deleteUerById(@Param("uid") Integer uerId);
    List<User> findByName(String query);
    int count();
    List<User> findByVo(QueryVo queryVo);
    List<User> findInIds(QueryVoIds queryVoIds);
}
