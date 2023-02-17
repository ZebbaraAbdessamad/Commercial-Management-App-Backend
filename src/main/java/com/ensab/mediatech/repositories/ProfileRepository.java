package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
