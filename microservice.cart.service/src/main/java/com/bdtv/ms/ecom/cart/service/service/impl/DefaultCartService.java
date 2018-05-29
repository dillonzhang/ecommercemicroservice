package com.bdtv.ms.ecom.cart.service.service.impl;

import com.bdtv.ms.ecom.cart.service.data.Product;
import com.bdtv.ms.ecom.cart.service.entity.Cart;
import com.bdtv.ms.ecom.cart.service.entity.CartEntry;
import com.bdtv.ms.ecom.cart.service.feign.ProductFeignClient;
import com.bdtv.ms.ecom.cart.service.repository.CartEntryRepository;
import com.bdtv.ms.ecom.cart.service.repository.CartRepository;
import com.bdtv.ms.ecom.cart.service.service.CartService;
import com.bdtv.ms.ecom.cart.service.service.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Comparator;
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

	@Autowired
	private ProductFeignClient productFeignClient;

	@Override
	public Cart createCart(final Long customerId)
	{
		Cart cart = new Cart();
		cart.setName("00000");
		cart.setTotalPrice(BigDecimal.ZERO);
		cart.setCustomerId(customerId);
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

	@Override
	public Cart addToCart(final Long productId, final Long quantity, final Long customerId, final Long cartId)
	{
		Cart cart;
		if (cartId == null)
		{
			cart = this.getLatestCartByCustomerId(customerId);
		}
		else
		{
			cart = this.getCartById(cartId);
			if (cart == null)
			{
				throw new CartNotFoundException("Cart with id[" + cartId + "] is not found.");
			}
		}

		CartEntry cartEntry = null;
		if (!CollectionUtils.isEmpty(cart.getEntries()))
		{
			cartEntry = cart.getEntries().stream().filter(e -> e.getProductId().equals(productId)).findFirst().orElse(null);
		}

		if (cartEntry != null)
		{
			cartEntry.setCount(cartEntry.getCount() + quantity);
		}
		else
		{
			cartEntry = new CartEntry();
			cartEntry.setCount(quantity);
			cartEntry.setCart(cart);
			cartEntry.setProductId(productId);
			//TODO calculate price
			cartEntry.setSubTotal(BigDecimal.ZERO);
			cartEntry.setTax(BigDecimal.ZERO);
			cartEntry.setTotalPrice(BigDecimal.ZERO);
		}

		this.cartEntryRepository.saveAndFlush(cartEntry);
		return this.getCartById(cart.getId());
	}

	@Override
	public Cart getLatestCartByCustomerId(final Long customerId)
	{
		List<Cart> carts = this.getCartsByCustomerId(customerId);
		if (CollectionUtils.isEmpty(carts))
		{
			return this.createCart(customerId);
		}
		else
		{
			return carts.stream().max(Comparator.comparing(Cart::getModifiedTime)).get();
		}
	}
}
