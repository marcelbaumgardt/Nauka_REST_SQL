package pl.marcelbaumgardt.naukarestsql.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.marcelbaumgardt.naukarestsql.model.User;
import pl.marcelbaumgardt.naukarestsql.repository.UserRepository;

@Component
public class Bootstrap  implements CommandLineRunner {

    UserRepository userRepository;

    @Autowired
    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        userRepository.save(User.builder().username("cosmo123").email("marcelidt1994@gmail.com").age(24).password("admin").activated(true).build());
        userRepository.save(User.builder().username("cosmo1234").email("marcelidt19944@gmail.com").age(244).password("admin4").activated(true).build());

    }
}
