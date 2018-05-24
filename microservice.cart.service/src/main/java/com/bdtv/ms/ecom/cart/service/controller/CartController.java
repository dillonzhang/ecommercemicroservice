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

import javax.xml.ws.Response;
import java.util.List;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */
@RestController
@RequestMapping("/cartapi/cart")
public class CartController
{
	@Autowired
	private CartService cartService;

	@ApiOperation(value = "Create a empty Cart", nickname = "createCart")
	@ApiImplicitParam(name = "customerId", value = "Customer Id", dataType = "Long", required = true)
	@PostMapping
	public ResponseEntity<Cart> createCart(@RequestParam final Long customerId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.createCart(customerId));
	}

	@ApiOperation(value = "Get cart data by cart id", nickname = "getCartById")
	@ApiImplicitParam(name = "cartId", value = "Cart Id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{cartId}")
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
	@PostMapping("/{cartId}/entry")
	public ResponseEntity<CartEntry> createCartEntry(@PathVariable final Long cartId, @RequestBody final CartEntry cartEntry)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.createCartEntry(cartId, cartEntry));
	}

	@ApiOperation(value = "Update Cart Data", nickname = "updateCartData")
	@ApiImplicitParam(name = "cart", value = "Cart Data", required = true, dataType = "Cart")
	@PutMapping
	public ResponseEntity<Cart> updateCartData(@RequestBody final Cart cart)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.updataCart(cart));
	}

	@ApiOperation(value = "Update Cart Entry Data", nickname = "updateCartEntryData")
	@ApiImplicitParam(name = "cartEntry", value = "Cart Entry Data", required = true, dataType = "CartEntry")
	@PutMapping("/{cartId}/entry")
	public ResponseEntity<CartEntry> updateCartEntryData(@PathVariable final Long cartId, @RequestBody final CartEntry cartEntry)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.cartService.updateCartEntry(cartId, cartEntry));
	}

	@ApiOperation(value = "Delete cart by id", nickname = "deleteCartById")
	@ApiImplicitParam(name = "cartId", value = "Cart Id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/{cartId}")
	public ResponseEntity deleteCartById(@PathVariable final Long cartId)
	{
		this.cartService.deleteCart(cartId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Delete cart entry by id", nickname = "deleteCartEntryById")
	@ApiImplicitParam(name = "entryId", value = "Entry Id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/entry/{entryId}")
	public ResponseEntity deleteEntryById(@PathVariable final Long entryId)
	{
		this.cartService.deleteCartEntry(entryId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Add product to cart by product id", nickname = "addProductToCartById")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", value = "Customer Id", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "productId", value = "Product Id", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "quantity", value = "Product Quantity", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "cartId", value = "Cart Id", required = false, dataType = "Long", paramType = "query") })
	@PostMapping("/addToCart")
	public ResponseEntity<Cart> addProductToCartById(@RequestParam(name = "customerId") final Long customerId,
			@RequestParam(name = "productId") final Long productId, @RequestParam(name = "quantity") final Long quantity,
			@RequestParam(name = "cartId", required = false) final Long cartId)
	{
		Cart cart = this.cartService.addToCart(productId, quantity, customerId, cartId);
		return ResponseEntity.status(HttpStatus.OK).body(cart);
	}
}
