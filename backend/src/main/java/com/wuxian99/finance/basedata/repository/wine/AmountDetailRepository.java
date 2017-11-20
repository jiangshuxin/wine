package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.AmountDetailEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AmountDetailRepository extends JpaRepository<AmountDetailEntity, Long> {

}
