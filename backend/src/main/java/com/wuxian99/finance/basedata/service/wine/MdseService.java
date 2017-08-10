package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.MdseEntity;
import com.wuxian99.finance.basedata.repository.wine.MdseRepository;
import com.wuxian99.finance.basedata.web.dto.QueryMdseListDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class MdseService {

    @Autowired
    MdseRepository mdseRepository;

    /**
     * 按条件分页查询商品列表
     * @param queryMdseListDto
     * @return
     */
    public Page<MdseEntity> findMdses(QueryMdseListDto queryMdseListDto) {
        Sort sort = new Sort(Sort.Direction.ASC,"price");
        PageRequest pageRequest = queryMdseListDto.convert(sort);
        return mdseRepository.findAll(new Specification<MdseEntity>() {
            public Predicate toPredicate(Root<MdseEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Path<String> merchantIdPath = root.get("merchantId");
                Predicate predicate = cb.equal(merchantIdPath, queryMdseListDto.getMerchantId());

                Path<String> statusPath = root.get("status");
                predicate = cb.and(predicate, cb.notEqual(statusPath, 0));

                String catagory = queryMdseListDto.getCatagory();
                if(StringUtils.isNotBlank(catagory)){
                    Path<String> catagoryPath = root.get("catagory");
                    predicate = cb.and(predicate, cb.equal(catagoryPath, catagory));
                }

                String year = queryMdseListDto.getYear();
                if(StringUtils.isNotBlank(year)){
                    Path<String> yearPath = root.get("year");
                    predicate = cb.and(predicate, cb.equal(yearPath, year));
                }

                String price = queryMdseListDto.getPrice();
                if(StringUtils.isNotBlank(price)){
                    Path<Integer> pricePath = root.get("price");
                    predicate = cb.and(predicate, cb.between(pricePath, (Integer.parseInt(price)-100)*100, Integer.parseInt(price)*100));
                }

                String mdseIds = queryMdseListDto.getMdseIds();
                if(StringUtils.isNotBlank(mdseIds)){
                    Path<Integer> pricePath = root.get("id");
                    CriteriaBuilder.In<Integer> mdseIdsPre = cb.in(pricePath);
                    String[] mdseIdArray = mdseIds.split(",");
                    for(String mdseId : mdseIdArray){
                        if(StringUtils.isNotBlank(mdseId)){
                            mdseIdsPre.value(Integer.parseInt(mdseId.trim()));
                        }
                    }
                    predicate = cb.and(predicate, mdseIdsPre);
                }

                query.where(predicate);
                return query.getRestriction();
            }
        }, pageRequest);
    }

    public MdseEntity findMdseById(Long id){
        return mdseRepository.findOne(id);
    }

}
