package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.User;

import java.util.List;


public interface UserService {
    void registerUser(User user);

    boolean findUserByLogin(String login);

    User getByLogin(String login);

    User findById(int id) throws UserNotFoundExeption;

    List<User> findAllUsers();

    void deleteById(int id);


}
