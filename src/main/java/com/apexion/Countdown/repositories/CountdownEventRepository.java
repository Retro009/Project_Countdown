package com.apexion.Countdown.repositories;

import com.apexion.Countdown.models.CountdownEvent;
import com.apexion.Countdown.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountdownEventRepository extends JpaRepository<CountdownEvent, Long> {
    List<CountdownEvent> findByUser(User user);
}
