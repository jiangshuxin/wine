package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
