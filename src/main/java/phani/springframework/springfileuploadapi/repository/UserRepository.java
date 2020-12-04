package phani.springframework.springfileuploadapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phani.springframework.springfileuploadapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
