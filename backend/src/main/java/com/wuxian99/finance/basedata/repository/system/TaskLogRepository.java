package com.wuxian99.finance.basedata.repository.system;

import com.wuxian99.finance.basedata.domain.entity.system.TaskLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLogRepository extends JpaRepository<TaskLogEntity, Integer> {

}
