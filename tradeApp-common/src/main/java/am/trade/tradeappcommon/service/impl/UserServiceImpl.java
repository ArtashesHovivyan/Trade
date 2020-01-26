package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.exeption.UserNotFoundExeption;
import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.repository.UserRepository;
import am.trade.tradeappcommon.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean findUserByLogin(String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }

    @Override
    public User findById(int id) throws UserNotFoundExeption {
        return userRepository.findById(id).orElseThrow(UserNotFoundExeption::new);
    }


}
