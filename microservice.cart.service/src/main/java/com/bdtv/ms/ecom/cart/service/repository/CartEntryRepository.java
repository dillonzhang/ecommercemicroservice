package com.bdtv.ms.ecom.cart.service.repository;

import com.bdtv.ms.ecom.cart.service.entity.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long>
{
	void deleteCartEntryById(final Long id);
}
