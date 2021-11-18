package com.saimon.spring.email.repository;

import com.saimon.spring.email.entity.EmailConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmailConfigRepository extends JpaRepository<EmailConfigEntity, Long> {
    EmailConfigEntity findByEventName(String eventName);
}
