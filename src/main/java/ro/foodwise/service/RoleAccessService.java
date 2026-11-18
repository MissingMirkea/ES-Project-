package ro.foodwise.service;

import org.springframework.stereotype.Service;
import ro.foodwise.model.AppUser;

@Service
public class RoleAccessService {
    public boolean canReadOffers(AppUser.Role role) { return role == AppUser.Role.ADMIN || role == AppUser.Role.CLIENT || role == AppUser.Role.SHOP_MANAGER; }
    public boolean canManageProducts(AppUser.Role role) { return role == AppUser.Role.ADMIN; }
    public boolean canManageOffers(AppUser.Role role) { return role == AppUser.Role.ADMIN || role == AppUser.Role.SHOP_MANAGER; }

    public void require(boolean allowed, String message) {
        if (!allowed) throw new SecurityException(message);
    }
}
