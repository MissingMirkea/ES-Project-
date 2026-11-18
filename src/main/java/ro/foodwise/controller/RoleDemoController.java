package ro.foodwise.controller;

import org.springframework.web.bind.annotation.*;
import ro.foodwise.model.AppUser;
import ro.foodwise.service.RoleAccessService;
import java.util.Map;

@RestController
@RequestMapping("/api/access")
public class RoleDemoController {
    private final RoleAccessService access;
    public RoleDemoController(RoleAccessService access) { this.access = access; }

    @GetMapping("/{role}")
    public Map<String, Object> permissions(@PathVariable AppUser.Role role) {
        return Map.of("role", role.name(), "canReadOffers", access.canReadOffers(role),
                "canManageProducts", access.canManageProducts(role), "canManageOffers", access.canManageOffers(role));
    }
}
