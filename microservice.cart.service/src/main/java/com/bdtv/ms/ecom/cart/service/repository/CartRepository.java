package com.bdtv.ms.ecom.cart.service.repository;

import com.bdtv.ms.ecom.cart.service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
	List<Cart> getCartByCustomerId(final Long id);

	void deleteCartById(final Long id);

	Cart getCartById(final Long id);
}
