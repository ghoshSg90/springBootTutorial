package com.ecom.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public class UserRepository extends CrudRepository { // CrudRespository can be found in some projects
public interface UserRepository extends JpaRepository<User, Long> {
}
