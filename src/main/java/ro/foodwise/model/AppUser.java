package ro.foodwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    @NotBlank @Column(unique = true) private String email;
    @Enumerated(EnumType.STRING) private Role role = Role.CLIENT;
    public enum Role { ADMIN, CLIENT, SHOP_MANAGER }
    protected AppUser() {}
    public AppUser(String name, String email, Role role) { this.name=name; this.email=email; this.role=role; }
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public Role getRole(){return role;}
    public void setName(String name){this.name=name;} public void setEmail(String email){this.email=email;} public void setRole(Role role){this.role=role;}
}
