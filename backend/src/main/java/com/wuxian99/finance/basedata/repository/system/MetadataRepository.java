package com.wuxian99.finance.basedata.repository.system;

import com.wuxian99.finance.basedata.domain.entity.system.MetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadataRepository extends JpaRepository<MetadataEntity, String> {

}
