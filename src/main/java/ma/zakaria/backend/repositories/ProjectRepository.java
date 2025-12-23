package ma.zakaria.backend.repositories;

import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByUser(User user);

}
