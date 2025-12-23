package ma.zakaria.backend.repositories;

import ma.zakaria.backend.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
