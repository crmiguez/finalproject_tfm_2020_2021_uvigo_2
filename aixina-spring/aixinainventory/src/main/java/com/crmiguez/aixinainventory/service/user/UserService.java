package com.crmiguez.aixinainventory.service.user;

import com.crmiguez.aixinainventory.entities.Employee;
import com.crmiguez.aixinainventory.entities.User;
import com.crmiguez.aixinainventory.repository.EmployeeRepository;
import com.crmiguez.aixinainventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("employeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    @Override
    public User addUser(User user) {
        Employee employeeExisted = null;
        User userExisted = null;
        List<User> listUsers = (List<User>) userRepository.findAll();

        if (user.getUserId() != null) {
            userExisted = userRepository.findById(user.getUserId()).get();
            return userExisted;
        } else {
            for(User u : listUsers){
                if((u.getUserName().equals(user.getUserName())
                        && u.getUserEmail().equals(user.getUserEmail()))
                        || (u.getUserName().equals(user.getUserName())
                        || u.getUserEmail().equals(user.getUserEmail())))
                    return null;
            }
            employeeExisted = employeeRepository.findById(user.getEmployee().getEmployeeId()).get();

            if ( employeeExisted == null){
                return null;
            } else {
                user.setEmployee(employeeExisted);
                user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
                return userRepository.save(user);
            }
        }
    }


    @Override
    public void deleteUser (User user){
        userRepository.delete(user);
    }


    @Override
    public User updateUser(User userDetails, User user){
        Employee employeeExisted = null;
        User userExisted = null;

        if (user.getUserId() != null) {
            userExisted = userRepository.findById(user.getUserId()).get();
            employeeExisted = employeeRepository.findById(user.getEmployee().getEmployeeId()).get();

            if ( employeeExisted != null){
                user.setEmployee(userDetails.getEmployee());
                user.setUserName(userDetails.getUserName());
                user.setUserEmail(userDetails.getUserEmail());
                //user.setUserPassword(userDetails.getUserPassword());
                user.setUserPassword(bcryptEncoder.encode(userDetails.getUserPassword()));
                user.setUserRegisterDate(userDetails.getUserRegisterDate());
                user.setUserShutDate(userDetails.getUserShutDate());
                user.setLastLogin(userDetails.getLastLogin());
                user.setAdmin(userDetails.getAdmin());

                return userRepository.save(user);
            }
        }
        return null;
    }

}
