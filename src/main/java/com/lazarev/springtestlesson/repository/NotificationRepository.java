package com.lazarev.springtestlesson.repository;

import com.lazarev.springtestlesson.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
