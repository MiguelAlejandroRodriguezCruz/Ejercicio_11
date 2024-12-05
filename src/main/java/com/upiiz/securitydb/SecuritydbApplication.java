package com.upiiz.securitydb;

import java.util.Set;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.upiiz.securitydb.entities.PermissionEntity;
import com.upiiz.securitydb.entities.RolEntity;
import com.upiiz.securitydb.entities.RolEnum;
import com.upiiz.securitydb.entities.UserEntity;
import com.upiiz.securitydb.repository.UserRepository;

@SpringBootApplication
public class SecuritydbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritydbApplication.class, args);
	}

	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			PermissionEntity createPermission = new PermissionEntity()
					.builder()
					.name("CREATE")
					.build();
			PermissionEntity deletePermission = new PermissionEntity()
					.builder()
					.name("DELETE")
					.build();
			PermissionEntity updatePermission = new PermissionEntity()
					.builder()
					.name("UPDATE")
					.build();
			PermissionEntity readPermission = new PermissionEntity()
					.builder()
					.name("READ")
					.build();
			PermissionEntity deployPermission = new PermissionEntity()
					.builder()
					.name("DEPLOY")
					.build();

			RolEntity adminRol = new RolEntity()
					.builder()
					.roleEnum(RolEnum.ADMIN)
					.permissions(Set.of(createPermission, updatePermission, deletePermission, readPermission))
					.build();
			RolEntity guestRol = new RolEntity()
					.builder()
					.roleEnum(RolEnum.GUEST)
					.permissions(Set.of(readPermission))
					.build();
			RolEntity userRol = new RolEntity()
					.builder()
					.roleEnum(RolEnum.GUEST)
					.permissions(Set.of(createPermission, readPermission))
					.build();
			RolEntity developerRol = new RolEntity()
					.builder()
					.roleEnum(RolEnum.DEVELOPER)
					.permissions(Set.of(createPermission, updatePermission, deletePermission, readPermission,
							deployPermission))
					.build();

			UserEntity juan = new UserEntity()
					.builder()
					.userName("juan")
					.password("1234")
					.isEnable(true)
					.accountNoExpired(false)
					.accountNoLocked(false)
					.credentialExpired(false)
					.roles(Set.of(developerRol))
					.build();
			UserEntity jose = new UserEntity()
					.builder()
					.userName("jose")
					.password("1234")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialExpired(true)
					.roles(Set.of(userRol))
					.build();
			UserEntity guest = new UserEntity()
					.builder()
					.userName("guest")
					.password("1234")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialExpired(true)
					.roles(Set.of(guestRol))
					.build();
			UserEntity anna = new UserEntity()
					.builder()
					.userName("anna")
					.password("1234")
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialExpired(true)
					.roles(Set.of(developerRol))
					.build();

			// guardar a los usuarios = crear un repositorio
			userRepository.save(juan);
			userRepository.save(jose);
			userRepository.save(guest);
			userRepository.save(anna);
			// userRepository.saveAll(List.of(juan, jose, guest, anna));
		};
	}

}
