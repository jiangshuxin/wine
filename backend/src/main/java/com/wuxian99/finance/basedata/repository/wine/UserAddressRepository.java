package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.UserAddressEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

    Page<UserAddressEntity> findByUserId(Long userId, PageRequest page);

}
