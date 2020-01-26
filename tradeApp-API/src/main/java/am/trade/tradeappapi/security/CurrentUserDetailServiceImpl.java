package am.trade.tradeappapi.security;

import am.trade.tradeappcommon.model.User;
import am.trade.tradeappcommon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(s).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CurrentUser(user);
    }
}