package am.trade.tradeappapi.security;

import am.trade.tradeappcommon.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getLogin()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}