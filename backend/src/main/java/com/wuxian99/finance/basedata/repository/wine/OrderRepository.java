package com.wuxian99.finance.basedata.repository.wine;

import com.wuxian99.finance.basedata.domain.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * 查询超时未支付订单
     * @param expiredTime
     * @return
     */
    @Query("select t from OrderEntity t where t.status=1 and t.time<?1")
    public List<OrderEntity> findExpiredOrders(String expiredTime);

    /**
     * 查询订单已完成，未计算返佣的订单
     * @return
     */
    @Query("select t from OrderEntity t where t.status=3 and t.commissionFlag=0")
    public List<OrderEntity> findCommissionOrders();

    @Modifying
    @Query("update OrderEntity o set o.status = ?1 where o.id = ?2")
    int updateOrderStatus(Long status,Long id);
}
