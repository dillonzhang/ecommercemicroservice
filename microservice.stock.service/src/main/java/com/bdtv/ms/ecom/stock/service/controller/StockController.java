package com.bdtv.ms.ecom.stock.service.controller;


import com.bdtv.ms.ecom.stock.service.entity.Stock;
import com.bdtv.ms.ecom.stock.service.service.StockService;
import io.swagger.annotations.ApiImplicitParam;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author dangao on 5/24/2018.
 * @version 1.0
 */
@RestController
@RequestMapping("/stockapi/stock")
public class StockController
{
	@Autowired
	private StockService stockService;

	@ApiOperation(value = "Get stock data by product id", nickname = "getStockByProductId")
	@ApiImplicitParam(name = "productId", value = "Product Id", dataType = "Long", required = true, paramType = "query")
	@GetMapping
	public ResponseEntity<Stock> getStockByProductId(@RequestParam final Long productId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.stockService.getStockByProductId(productId));
	}

	@ApiOperation(value = "Create a stock data", nickname = "createStock")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productId", value = "Product Id", dataType = "Long", required = true, paramType = "query"),
			@ApiImplicitParam(name = "stockLevel", value = "Stock Level", dataType = "Long", required = true, paramType = "query") })
	@PostMapping
	public ResponseEntity<Stock> createStock(@RequestParam(value = "productId") final Long productId,
			@RequestParam(value = "stockLevel") final Long stockLevel)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.stockService.createStock(productId, stockLevel));
	}

	@ApiOperation(value = "Update product stock", nickname = "updateProductStock")
	@ApiImplicitParams({ @ApiImplicitParam(name = "productId", value = "Product Id", dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "stockLevel", value = "Stock Level", dataType = "Long", paramType = "query") })
	@PutMapping
	public ResponseEntity<Stock> updateProductStock(@RequestParam final Long productId, @RequestParam final Long stockLevel)
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.stockService.updateStock(productId, stockLevel));
	}

	@ApiOperation(value = "Delete stock by Product id", nickname = "deleteStockByProductId")
	@ApiImplicitParam(name = "productId", value = "Product Id", dataType = "Long", required = true, paramType = "query" )
	@DeleteMapping
	public ResponseEntity<Stock> deleteStock(@RequestParam final Long productId)
	{
		this.stockService.deleteStock(productId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
