package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.User;

import java.util.Optional;

public interface UserService {
    void registerUser(User user);

    boolean findUserByLogin(String login);

    User findById(int id) throws UserNotFoundExeption;
}
