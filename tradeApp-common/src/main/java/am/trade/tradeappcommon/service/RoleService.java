package am.trade.tradeappcommon.service;

import am.trade.tradeappcommon.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role getRollById (int id);
}
