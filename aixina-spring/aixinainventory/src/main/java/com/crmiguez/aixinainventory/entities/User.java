package com.crmiguez.aixinainventory.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Table
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "userId")
    private Long userId;

    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Employee employee;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "lastLogin")
    private String lastLogin;

    @Column(name = "userRegisterDate")
    private String userRegisterDate;

    @Column(name = "userShutDate")
    private String userShutDate;

    @Column(name="admin")
    @ColumnDefault(value = "0")
    private byte admin;

    @Column(name="enabled")
    @ColumnDefault(value = "true")
    private boolean enabled;

    public User() {
    }

    public User(Long userId, Employee employee, String userName, String userEmail, String userPassword, String lastLogin, String userRegisterDate, String userShutDate, byte admin, boolean enabled) {
        this.userId = userId;
        this.employee = employee;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.lastLogin = lastLogin;
        this.userRegisterDate = userRegisterDate;
        this.userShutDate = userShutDate;
        this.admin = admin;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(String userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public String getUserShutDate() {
        return userShutDate;
    }

    public void setUserShutDate(String userShutDate) {
        this.userShutDate = userShutDate;
    }

    public byte getAdmin() {
        return admin;
    }

    public void setAdmin(byte admin) {
        this.admin = admin;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //Override methods UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Extract list of permissions (name)
        this.getPermissionList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });

        // Extract list of roles (ROLE_name)
        this.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled() == true;
    }

    public List<String> getRoleList(){
        List<String> userRoleList = new ArrayList<>();
        if (this.getAdmin() == 1)
            userRoleList.add("ADMIN");
        else
            userRoleList.add("GUEST");
        return userRoleList;
    }

    public List<String> getPermissionList(){
        List<String> userPermissionsList = new ArrayList<>();
        if (this.getAdmin() == 1) {
            userPermissionsList.add("PERM_CREATE_INVENTORY");
            userPermissionsList.add("PERM_READ_INVENTORY");
            userPermissionsList.add("PERM_READ_ALL_INVENTORIES");
            userPermissionsList.add("PERM_UPDATE_INVENTORY");
            userPermissionsList.add("PERM_DELETE_INVENTORY");

            userPermissionsList.add("PERM_CREATE_MOVEMENT");
            userPermissionsList.add("PERM_READ_MOVEMENT");
            userPermissionsList.add("PERM_READ_ALL_MOVEMENTS");
            userPermissionsList.add("PERM_UPDATE_MOVEMENT");
            userPermissionsList.add("PERM_READ_ALL_DOWNS");

            userPermissionsList.add("PERM_CREATE_MOVE_TYPE");
            userPermissionsList.add("PERM_READ_MOVE_TYPE");
            userPermissionsList.add("PERM_READ_ALL_MOVE_TYPES");
            userPermissionsList.add("PERM_UPDATE_MOVE_TYPE");
            userPermissionsList.add("PERM_DELETE_MOVE_TYPE");

            userPermissionsList.add("PERM_CREATE_INVOICE");
            userPermissionsList.add("PERM_READ_INVOICE");
            userPermissionsList.add("PERM_READ_ALL_INVOICES");
            userPermissionsList.add("PERM_UPDATE_INVOICE");
            userPermissionsList.add("PERM_DELETE_INVOICE");

            userPermissionsList.add("PERM_CREATE_LINE");
            userPermissionsList.add("PERM_READ_LINE");
            userPermissionsList.add("PERM_READ_ALL_LINES");
            userPermissionsList.add("PERM_UPDATE_LINE");
            userPermissionsList.add("PERM_DELETE_LINE");

            userPermissionsList.add("PERM_CREATE_ITEM");
            userPermissionsList.add("PERM_READ_ITEM");
            userPermissionsList.add("PERM_READ_ALL_ITEMS");
            userPermissionsList.add("PERM_UPDATE_ITEM");
            userPermissionsList.add("PERM_DELETE_ITEM");

            userPermissionsList.add("PERM_CREATE_ITEM_SET");
            userPermissionsList.add("PERM_READ_ITEM_SET");
            userPermissionsList.add("PERM_READ_ALL_ITEM_SETS");
            userPermissionsList.add("PERM_UPDATE_ITEM_SET");
            userPermissionsList.add("PERM_DELETE_ITEM_SET");

            userPermissionsList.add("PERM_CREATE_ITEM_TYPE");
            userPermissionsList.add("PERM_READ_ITEM_TYPE");
            userPermissionsList.add("PERM_READ_ALL_ITEM_TYPES");
            userPermissionsList.add("PERM_UPDATE_ITEM_TYPE");
            userPermissionsList.add("PERM_DELETE_ITEM_TYPE");

            userPermissionsList.add("PERM_CREATE_ITEM_IMAGE");
            userPermissionsList.add("PERM_READ_ITEM_IMAGE");
            userPermissionsList.add("PERM_READ_ALL_ITEM_IMAGES");
            userPermissionsList.add("PERM_UPDATE_ITEM_IMAGE");
            userPermissionsList.add("PERM_DELETE_ITEM_IMAGE");

            userPermissionsList.add("PERM_CREATE_BRAND");
            userPermissionsList.add("PERM_READ_BRAND");
            userPermissionsList.add("PERM_READ_ALL_BRANDS");
            userPermissionsList.add("PERM_UPDATE_BRAND");
            userPermissionsList.add("PERM_DELETE_BRAND");

            userPermissionsList.add("PERM_CREATE_EMPLOYEE");
            userPermissionsList.add("PERM_READ_EMPLOYEE");
            userPermissionsList.add("PERM_READ_ALL_EMPLOYEES");
            userPermissionsList.add("PERM_UPDATE_EMPLOYEE");
            userPermissionsList.add("PERM_DELETE_EMPLOYEE");

            userPermissionsList.add("PERM_CREATE_HEADQUARTER");
            userPermissionsList.add("PERM_READ_HEADQUARTER");
            userPermissionsList.add("PERM_READ_ALL_HEADQUARTERS");
            userPermissionsList.add("PERM_UPDATE_HEADQUARTERS");
            userPermissionsList.add("PERM_DELETE_HEADQUARTERS");

            userPermissionsList.add("PERM_CREATE_DEPARTMENT");
            userPermissionsList.add("PERM_READ_DEPARTMENT");
            userPermissionsList.add("PERM_READ_ALL_DEPARTMENTS");
            userPermissionsList.add("PERM_UPDATE_DEPARTMENT");
            userPermissionsList.add("PERM_DELETE_DEPARTMENT");

            userPermissionsList.add("PERM_CREATE_LOCATION");
            userPermissionsList.add("PERM_READ_LOCATION");
            userPermissionsList.add("PERM_READ_ALL_LOCATIONS");
            userPermissionsList.add("PERM_UPDATE_LOCATION");
            userPermissionsList.add("PERM_DELETE_LOCATION");


        }
        else if (this.getAdmin() == 0) {
            userPermissionsList.add("PERM_CREATE_INVENTORY");
            userPermissionsList.add("PERM_READ_INVENTORY");
            userPermissionsList.add("PERM_READ_ALL_INVENTORIES");
            userPermissionsList.add("PERM_UPDATE_INVENTORY");
            userPermissionsList.add("PERM_DELETE_INVENTORY");

            userPermissionsList.add("PERM_CREATE_MOVEMENT");
            userPermissionsList.add("PERM_READ_MOVEMENT");
            userPermissionsList.add("PERM_READ_ALL_MOVEMENTS");
            userPermissionsList.add("PERM_UPDATE_MOVEMENT");
            userPermissionsList.add("PERM_READ_ALL_DOWNS");

            userPermissionsList.add("PERM_CREATE_MOVE_TYPE");
            userPermissionsList.add("PERM_READ_MOVE_TYPE");
            userPermissionsList.add("PERM_READ_ALL_MOVE_TYPES");
            userPermissionsList.add("PERM_UPDATE_MOVE_TYPE");
            userPermissionsList.add("PERM_DELETE_MOVE_TYPE");

            userPermissionsList.add("PERM_CREATE_INVOICE");
            userPermissionsList.add("PERM_READ_INVOICE");
            userPermissionsList.add("PERM_READ_ALL_INVOICES");
            userPermissionsList.add("PERM_UPDATE_INVOICE");
            userPermissionsList.add("PERM_DELETE_INVOICE");

            userPermissionsList.add("PERM_CREATE_LINE");
            userPermissionsList.add("PERM_READ_LINE");
            userPermissionsList.add("PERM_READ_ALL_LINES");
            userPermissionsList.add("PERM_UPDATE_LINE");
            userPermissionsList.add("PERM_DELETE_LINE");

            userPermissionsList.add("PERM_CREATE_ITEM");
            userPermissionsList.add("PERM_READ_ITEM");
            userPermissionsList.add("PERM_READ_ALL_ITEMS");
            userPermissionsList.add("PERM_UPDATE_ITEM");
            userPermissionsList.add("PERM_DELETE_ITEM");

            userPermissionsList.add("PERM_CREATE_ITEM_SET");
            userPermissionsList.add("PERM_READ_ITEM_SET");
            userPermissionsList.add("PERM_READ_ALL_ITEM_SETS");
            userPermissionsList.add("PERM_UPDATE_ITEM_SET");
            userPermissionsList.add("PERM_DELETE_ITEM_SET");

            userPermissionsList.add("PERM_CREATE_ITEM_TYPE");
            userPermissionsList.add("PERM_READ_ITEM_TYPE");
            userPermissionsList.add("PERM_READ_ALL_ITEM_TYPES");
            userPermissionsList.add("PERM_UPDATE_ITEM_TYPE");
            userPermissionsList.add("PERM_DELETE_ITEM_TYPE");

            userPermissionsList.add("PERM_CREATE_ITEM_IMAGE");
            userPermissionsList.add("PERM_READ_ITEM_IMAGE");
            userPermissionsList.add("PERM_READ_ALL_ITEM_IMAGES");
            userPermissionsList.add("PERM_UPDATE_ITEM_IMAGE");
            userPermissionsList.add("PERM_DELETE_ITEM_IMAGE");

            userPermissionsList.add("PERM_CREATE_BRAND");
            userPermissionsList.add("PERM_READ_BRAND");
            userPermissionsList.add("PERM_READ_ALL_BRANDS");
            userPermissionsList.add("PERM_UPDATE_BRAND");
            userPermissionsList.add("PERM_DELETE_BRAND");

            //AL EMPLEADO SOLAMENTE PUEDE HACER LECTURA DE LAS SIGUIENTES ENTIDADES
            userPermissionsList.add("PERM_READ_HEADQUARTER");
            userPermissionsList.add("PERM_READ_ALL_HEADQUARTERS");

            userPermissionsList.add("PERM_READ_DEPARTMENT");
            userPermissionsList.add("PERM_READ_ALL_DEPARTMENTS");

            userPermissionsList.add("PERM_READ_LOCATION");
            userPermissionsList.add("PERM_READ_ALL_LOCATIONS");

            userPermissionsList.add("PERM_READ_EMPLOYEE");
            userPermissionsList.add("PERM_READ_ALL_EMPLOYEES");


        }
        return userPermissionsList;

    }

}

