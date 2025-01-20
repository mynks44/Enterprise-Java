package ca.sheridancollege.suranim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.suranim.beans.Product;
import ch.qos.logback.core.model.Model;



public class ProductController {

	public String index()
	{
		return "index";
	}
	
	@GetMapping("working")
	public String working()
	{
		return "working";
		
	}
	
	@GetMapping("/formAction")
	public String formAction(Model model, 
			@RequestParam int productId,
			@RequestParam String name,
			@RequestParam double cost)
	
	{
		Product product= new Product();
		Product.setProductId(productId);
		Product.setName(name);
		Product.setCost(cost);
		System.out.println("ID:"+product.getProductId()+"Name:" + product.getName()+ "Cost:" + product.getCost());
		return "working";
	}
	
}
