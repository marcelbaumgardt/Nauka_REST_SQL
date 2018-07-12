package pl.marcelbaumgardt.naukarestsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcelbaumgardt.naukarestsql.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
