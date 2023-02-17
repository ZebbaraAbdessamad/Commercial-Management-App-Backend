package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
