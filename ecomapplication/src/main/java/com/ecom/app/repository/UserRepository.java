package com.ecom.app.repository;

import com.ecom.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public class UserRepository extends CrudRepository { // CrudRespository can be found in some projects
public interface UserRepository extends JpaRepository<User, Long> {
}
