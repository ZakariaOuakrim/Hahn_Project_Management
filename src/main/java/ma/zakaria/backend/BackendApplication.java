package ma.zakaria.backend;

import ma.zakaria.backend.entities.Project;
import ma.zakaria.backend.entities.Task;
import ma.zakaria.backend.entities.User;
import ma.zakaria.backend.repositories.ProjectRepository;
import ma.zakaria.backend.repositories.TaskRepository;
import ma.zakaria.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ProjectRepository projectRepository,
                                        TaskRepository taskRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {

            // Create Users
            User user1 = new User();
            user1.setUserName("Zakaria Ouakrim");
            user1.setEmail("zakaria@example.com");
            user1.setPassword(passwordEncoder.encode("zakaria"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setUserName("Omar hafidi");
            user2.setEmail("Omar@example.com");
            user2.setPassword(passwordEncoder.encode("omar"));
            userRepository.save(user2);


            // Create Projects for User 1
            Project project1 = new Project();
            project1.setTitle("E-Commerce Website");
            project1.setDescription("Build a full stack e-commerce platform with payment integration");
            project1.setCreatedDate(new Date());
            project1.setUser(user1);
            projectRepository.save(project1);

            Project project2 = new Project();
            project2.setTitle("Mobile App Development");
            project2.setDescription("Develop a cross-platform mobile application");
            project2.setCreatedDate(new Date());
            project2.setUser(user1);
            projectRepository.save(project2);

            // Create Projects for User 2
            Project project3 = new Project();
            project3.setTitle("Data Analytics Dashboard");
            project3.setDescription("Create an interactive dashboard for business intelligence");
            project3.setCreatedDate(new Date());
            project3.setUser(user2);
            projectRepository.save(project3);


            // Create Tasks for Project 1 (E-Commerce)
            Task task1 = new Task();
            task1.setTitle("Design Database Schema");
            task1.setDescription("Create ERD and design database tables");
            task1.setDueDate(new Date());
            task1.setCreatedDate(new Date());
            task1.setCompleted(true);
            task1.setProject(project1);
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setTitle("Implement User Authentication");
            task2.setDescription("Add JWT-based authentication system");
            task2.setDueDate(new Date());
            task2.setCreatedDate(new Date());
            task2.setCompleted(true);
            task2.setProject(project1);
            taskRepository.save(task2);

            Task task3 = new Task();
            task3.setTitle("Build Product Catalog");
            task3.setDescription("Create product listing and search functionality");
            task3.setDueDate(new Date());
            task3.setCreatedDate(new Date());
            task3.setCompleted(false);
            task3.setProject(project1);
            taskRepository.save(task3);

            Task task4 = new Task();
            task4.setTitle("Integrate Payment Gateway");
            task4.setDescription("Add Stripe payment integration");
            task4.setDueDate(new Date());
            task4.setCreatedDate(new Date());
            task4.setCompleted(false);
            task4.setProject(project1);
            taskRepository.save(task4);

            Task task5 = new Task();
            task5.setTitle("Setup React Native Project");
            task5.setDescription("Initialize project with necessary dependencies");
            task5.setDueDate(new Date());
            task5.setCreatedDate(new Date());
            task5.setCompleted(true);
            task5.setProject(project2);
            taskRepository.save(task5);

            Task task6 = new Task();
            task6.setTitle("Design UI/UX Mockups");
            task6.setDescription("Create wireframes and high-fidelity designs");
            task6.setDueDate(new Date());
            task6.setCreatedDate(new Date());
            task6.setCompleted(false);
            task6.setProject(project2);
            taskRepository.save(task6);

            Task task7 = new Task();
            task7.setTitle("Implement Navigation");
            task7.setDescription("Setup navigation between screens");
            task7.setDueDate(new Date());
            task7.setCreatedDate(new Date());
            task7.setCompleted(false);
            task7.setProject(project2);
            taskRepository.save(task7);

            Task task8 = new Task();
            task8.setTitle("Connect to Data Sources");
            task8.setDescription("Establish connections to databases and APIs");
            task8.setDueDate(new Date());
            task8.setCreatedDate(new Date());
            task8.setCompleted(true);
            task8.setProject(project3);
            taskRepository.save(task8);

            Task task9 = new Task();
            task9.setTitle("Create Chart Components");
            task9.setDescription("Build reusable chart components");
            task9.setDueDate(new Date());
            task9.setCreatedDate(new Date());
            task9.setCompleted(false);
            task9.setProject(project3);
            taskRepository.save(task9);

        };
    }

}
