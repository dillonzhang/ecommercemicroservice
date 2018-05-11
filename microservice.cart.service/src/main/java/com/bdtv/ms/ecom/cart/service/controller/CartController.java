package com.bdtv.ms.ecom.cart.service.controller;

import com.bdtv.ms.ecom.cart.service.entity.Cart;
import com.bdtv.ms.ecom.cart.service.entity.CartEntry;
import com.bdtv.ms.ecom.cart.service.service.CartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController
{
	@Autowired
	private CartService cartService;

	@ApiOperation(value = "Create a empty Cart", nickname = "createCart")
	@GetMapping("/create")
	public ResponseEntity<Cart> createCart()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.createCart());
	}

	@ApiOperation(value = "Get cart data by cart id", nickname = "getCartById")
	@ApiImplicitParam(name = "cartId", value = "Cart Id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/id/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable final Long cartId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getCartById(cartId));
	}

	@ApiOperation(value = "Get carts by customer id", nickname = "getCartsByCustomerId")
	@ApiImplicitParam(name = "customerId", value = "Customer Id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/customerId/{customerId}")
	public ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable final Long customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.getCartsByCustomerId(customerId));
	}

	@ApiOperation(value = "Create a cart entry", nickname = "createCartEntry")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cartId", value = "Cart Id", required = true, dataType = "Long", paramType = "Path"),
			@ApiImplicitParam(name = "cartEntry", value = "Cart entry data", required = true, dataType = "CartEntry")

	})
	@PostMapping("/{cartId}/create/entry")
	public ResponseEntity<CartEntry> createCartEntry(@PathVariable final Long cartId, @RequestBody final CartEntry cartEntry)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.createCartEntry(cartId, cartEntry));
	}

	@ApiOperation(value = "Update Cart Data", nickname = "updateCartData")
	@ApiImplicitParam(name = "cart", value = "Cart Data", required = true, dataType = "Cart")
	@PutMapping("/update/cart")
	public ResponseEntity<Cart> updateCartData(@RequestBody final Cart cart)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.updataCart(cart));
	}

	@ApiOperation(value = "Update Cart Entry Data", nickname = "updateCartEntryData")
	@ApiImplicitParam(name = "cartEntry", value = "Cart Entry Data", required = true, dataType = "CartEntry")
	@PutMapping("/{cartId}/update/entry")
	public ResponseEntity<CartEntry> updateCartEntryData(@PathVariable final Long cartId, @RequestBody final CartEntry cartEntry)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.updateCartEntry(cartId, cartEntry));
	}

	@ApiOperation(value = "Delete cart by id", nickname = "deleteCartById")
	@ApiImplicitParam(name = "cartId", value = "Cart Id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity deleteCartById(@PathVariable final Long cartId)
	{
		this.cartService.deleteCart(cartId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Delete cart entry by id", nickname = "deleteCartEntryById")
	@ApiImplicitParam(name = "entryId", value = "Entry Id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/delete/entry/{entryId}")
	public ResponseEntity deleteEntryById(@PathVariable final Long entryId)
	{
		this.cartService.deleteCartEntry(entryId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
