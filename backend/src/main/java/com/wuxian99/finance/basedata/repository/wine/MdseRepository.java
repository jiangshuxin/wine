package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.MdseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MdseRepository extends JpaRepository<MdseEntity, Long>, JpaSpecificationExecutor<MdseEntity>{

}
