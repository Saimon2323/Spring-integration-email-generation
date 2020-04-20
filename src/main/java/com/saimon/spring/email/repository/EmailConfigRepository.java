package com.saimon.spring.email.repository;

import com.saimon.spring.email.entity.EmailConfigEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmailConfigRepository extends CrudRepository<EmailConfigEntity, Long> {
    EmailConfigEntity findByEventName(String eventName);
}
