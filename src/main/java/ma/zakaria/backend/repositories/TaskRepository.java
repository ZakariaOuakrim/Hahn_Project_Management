package ma.zakaria.backend.repositories;

import ma.zakaria.backend.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
