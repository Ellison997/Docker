package top.allviewit.lightlist.activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityModel, Integer> {
}