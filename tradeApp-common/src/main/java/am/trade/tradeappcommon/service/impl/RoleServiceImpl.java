package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Role;
import am.trade.tradeappcommon.repository.RoleRepository;
import am.trade.tradeappcommon.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRollById(int id) {
        return roleRepository.getOne(id);
    }
}
