package com.bdtv.ms.ecom.cart.service.service.impl;

import com.bdtv.ms.ecom.cart.service.entity.Cart;
import com.bdtv.ms.ecom.cart.service.entity.CartEntry;
import com.bdtv.ms.ecom.cart.service.repository.CartEntryRepository;
import com.bdtv.ms.ecom.cart.service.repository.CartRepository;
import com.bdtv.ms.ecom.cart.service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@Service
@Transactional
public class DefaultCartService implements CartService
{
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartEntryRepository cartEntryRepository;

	@Override
	public Cart createCart()
	{
		Cart cart = new Cart();
		cart.setName("00000");
		cart.setTotalPrice(BigDecimal.ZERO);
		cart.setCustomerId(Long.valueOf(1111111111));
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartById(final Long id)
	{
		return cartRepository.getCartById(id);
	}

	@Override
	public List<Cart> getCartsByCustomerId(final Long id)
	{
		return cartRepository.getCartByCustomerId(id);
	}

	@Override
	public CartEntry createCartEntry(final Long cartId, final CartEntry cartEntry)
	{
		cartEntry.setCart(this.getCartById(cartId));
		return cartEntryRepository.save(cartEntry);
	}

	@Override
	public Cart updataCart(final Cart cart)
	{
		return cartRepository.saveAndFlush(cart);
	}

	@Override
	public CartEntry updateCartEntry(final Long cartId, final CartEntry cartEntry)
	{
		cartEntry.setCart(this.cartRepository.getCartById(cartId));
		return cartEntryRepository.saveAndFlush(cartEntry);
	}

	@Override
	public void deleteCart(final Long id)
	{
		cartRepository.deleteCartById(id);
	}

	@Override
	public void deleteCartEntry(final Long id)
	{
		cartEntryRepository.deleteCartEntryById(id);
	}
}
