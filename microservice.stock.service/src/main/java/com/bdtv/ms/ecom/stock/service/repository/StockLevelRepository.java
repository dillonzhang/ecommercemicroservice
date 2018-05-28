package com.bdtv.ms.ecom.stock.service.repository;

import com.bdtv.ms.ecom.stock.service.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author dangao on 5/24/2018.
 * @version 1.0
 */

@Repository
public interface StockLevelRepository extends JpaRepository<Stock, Long>
{
	Stock getStockByProductId(final Long productId);

	void deleteStockByProductId(final Long productId);
}
