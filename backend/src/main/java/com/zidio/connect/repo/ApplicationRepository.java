package com.zidio.connect.repo;

import com.zidio.connect.model.ApplicationEntity;
import com.zidio.connect.model.Job;
import com.zidio.connect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findByStudent(User student);
    List<ApplicationEntity> findByJob(Job job);
}
