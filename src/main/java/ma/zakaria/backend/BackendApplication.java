package ma.zakaria.backend;

import ma.zakaria.backend.repositories.ProjectRepository;
import ma.zakaria.backend.repositories.TaskRepository;
import ma.zakaria.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProjectRepository projectRepository,
                                        TaskRepository taskRepository,
                                        UserRepository userRepository){

    }
}
