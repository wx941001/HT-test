package Domain;

import java.util.Date;

public class AccountUser {
    private Integer id;
    private Account account;
    private User user;
    public AccountUser (){
        this.account = new Account();
        this.user = new User();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Integer getUid(){
        return account.getUid();
    }
    public void setUid(Integer uid){
        account.setUid(uid);
    }

    public void setMoney(Double money){
        account.setMoney(money);
    }
    public Double getMoney(){
        return account.getMoney();
    }
    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String userName) {
        user.setUserName(userName);
    }

    public Date getUserBirthday() {
        return user.getUserBirthday();
    }

    public void setUserBirthday(Date userBirthday) {
        user.setUserBirthday(userBirthday);;
    }

    public String getUserSex() {
        return user.getUserSex();
    }

    public void setUserSex(String userSex) {
        user.setUserSex(userSex);
    }

    public String getUserAddress() {
        return user.getUserAddress();
    }

    public void setUserAddress(String userAddress) {
        user.setUserAddress(userAddress);
    }
}
