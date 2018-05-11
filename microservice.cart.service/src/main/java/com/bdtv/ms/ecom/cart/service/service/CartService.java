package com.bdtv.ms.ecom.cart.service.service;

import com.bdtv.ms.ecom.cart.service.entity.Cart;
import com.bdtv.ms.ecom.cart.service.entity.CartEntry;

import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
public interface CartService
{
	Cart createCart();

	Cart getCartById(final Long id);

	List<Cart> getCartsByCustomerId(final Long id);

	CartEntry createCartEntry(final Long cartId, final CartEntry cartEntry);

	Cart updataCart(final Cart cart);

	CartEntry updateCartEntry(final Long cartId, final CartEntry cartEntry);

	void deleteCart(final Long id);

	void deleteCartEntry(final Long id);
}
