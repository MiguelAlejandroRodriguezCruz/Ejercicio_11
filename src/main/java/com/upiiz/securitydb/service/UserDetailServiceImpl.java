package com.upiiz.securitydb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upiiz.securitydb.entities.UserEntity;
import com.upiiz.securitydb.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // requerimos consultar un usuario por username, con todos sus detalles
        // quien lo realiza - el repositorio
        UserEntity userEntity = userRepository.findUserEntityByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // recuperar roles
        userEntity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()));
        });
        // recuperar permisos
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialExpired(),
                userEntity.isAccountNoLocked(),
                authorities);
    }

}
