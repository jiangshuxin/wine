package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);

    UserEntity findByReferralCode(String referralCode);

    @Modifying
    @Query("update UserEntity o set o.balance = ?1 where o.id = ?2")
    int updateBalanceByUserId(Long balance,Long id);
}
