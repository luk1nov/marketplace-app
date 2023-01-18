package com.lukyanov.itemservice.spec;

import com.lukyanov.itemservice.entity.Product;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class ProductSpecs {

    public static Specification<Product> getProductsByName(String searchQuery){
        return (root, query, cb) -> cb.like(root.get("name"), searchQuery);
    }

    public static Specification<Product> getProductsByDescription(String searchQuery){
        return (root, query, cb) -> cb.like(root.get("description"), searchQuery);
    }


    public static Specification<Product> getProductsByMinPrice(BigDecimal minPrice){
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> getProductsByMaxPrice(BigDecimal maxPrice){
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> getProductsByCategory(Long categoryId){
        return (root, query, cb) -> cb.equal(root.get("category.id"), categoryId);
    }

    public static Specification<Product> getProductsByCondition(Long conditionId){
        return (root, query, cb) -> cb.equal(root.get("condition.id"), conditionId);
    }

    public static Specification<Product> getProductsBySellerId(Long sellerId){
        return (root, query, cb) -> cb.equal(root.get("condition.id"), sellerId);
    }

    public static Optional<Specification<Product>> getProductsByCriteria(ProductSearchCriteria criteria){
        if(Objects.isNull(criteria)){
            return Optional.empty();
        }
        List<Specification<Product>> specificationList = new ArrayList<>();
        if (StringUtils.isNotBlank(criteria.getSearchQuery())){
            String searchQuery = criteria.getSearchQuery();
            specificationList.add(Specification.where(getProductsByName(searchQuery))
                    .or(getProductsByDescription(searchQuery)));
        }
        if (Objects.nonNull(criteria.getMinPrice())){
            specificationList.add(getProductsByMinPrice(criteria.getMinPrice()));
        }
        if (Objects.nonNull(criteria.getMaxPrice())){
            specificationList.add(getProductsByMaxPrice(criteria.getMaxPrice()));
        }
        if (Objects.nonNull(criteria.getConditionId())){
            specificationList.add(getProductsByCondition(criteria.getConditionId()));
        }
        if (Objects.nonNull(criteria.getCategoryId())){
            specificationList.add(getProductsByCategory(criteria.getCategoryId()));
        }
        if (Objects.nonNull(criteria.getSellerId())){
            specificationList.add(getProductsBySellerId(criteria.getSellerId()));
        }
        return specificationList.stream().reduce(Specification::and);
    }
}
