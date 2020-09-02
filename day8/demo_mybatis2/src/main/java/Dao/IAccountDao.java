package Dao;

import Domain.AccountUser;
import Domain.User;

import java.util.List;

public interface IAccountDao {
    List<AccountUser> findAll();
    List<User> findAll2();
}
