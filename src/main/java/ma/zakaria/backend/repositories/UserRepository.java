package ma.zakaria.backend.repositories;

import ma.zakaria.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
