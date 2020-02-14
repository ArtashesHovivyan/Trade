package am.trade.tradeappapi.endpoint;

import am.trade.tradeappcommon.model.Role;
import am.trade.tradeappcommon.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/role")
public class RoleEndpoint {
    private final RoleService roleService;

    public RoleEndpoint(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> roleList() {
        return roleService.findAll();
    }
}
