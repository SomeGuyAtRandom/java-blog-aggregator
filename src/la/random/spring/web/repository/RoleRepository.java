package la.random.spring.web.repository;

import la.random.spring.web.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
}
