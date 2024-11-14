package com.portfolio.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

	List<ProductPrice> findByProductid(Integer productid);
	
	void deleteByProductid(Integer productId);
	
	Optional<ProductPrice> findByProductidAndDate(Integer productid, Date date);
	
	Optional<ProductPrice> findTopByProductidAndDateBeforeOrderByDateDesc(Integer productid, Date date);

}

