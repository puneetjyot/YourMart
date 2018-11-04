
package com.nagarro.yourmartapi.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.service.SellerService;

@RestController
@CrossOrigin

public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@PostMapping("/register/seller")
	private ResponsesDto RegisterSeller(@RequestBody SellerRegistrationDto sellerRegistrationDto) {
		
		return sellerService.registerSeller(sellerRegistrationDto);
	}
	
	@PostMapping("/login/seller")
	private ResponsesDto Loginseller(@RequestBody LoginDto loginDto) {
		return sellerService.loginSeller(loginDto);
	}
	@PutMapping("/seller/status")
	public Response updateSellerStatus(@RequestHeader(value="token") String token,@RequestBody List<SellerStatusDto> sellerStatusDto) 
	{
		
		System.out.println(sellerStatusDto.get(0).getId()+"hans");
		System.out.println(token+"token");
		sellerStatusDto.get(0).setToken(token);
		
		return sellerService.updateSellerStatus(sellerStatusDto);
	}
	
	
	
//	@GetMapping("/list/seller")
//	private Response<List<SellerDetailsDto>> getSellerList()
//	{
//		
//		return sellerService.getSellerList();
//	}
	
	
	@GetMapping("/list/seller/{id}")
	private Response<SellerDetailsDto> getSeller(@PathVariable String id)
	{
		
		return sellerService.getSeller(id);
	}
	
	@GetMapping("/currentseller")
	private Response<SellerDetailsDto> getCurrentSeller(@RequestHeader(value="token")String authtoken)
	{			Response response=new Response<>();

		try {
			Integer.parseInt(authtoken.substring(6));
		}
		catch(Exception e) {
			response.setData(null);
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			response.setMessage(e.getMessage());
		}
		if(Objects.isNull(authtoken)) {
			response.setData(null);
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			response.setMessage(QueriesConstant.UNAUTHORISED_MESSAGE);
			return response;
		}
		
		else {
			String id=authtoken.substring(6);
			return sellerService.getSeller(id);

		}
	
	}
	
	@PutMapping("/seller/{id}")
	private Response<SellerDetailsDto> updateUser(@PathVariable String id,@RequestBody SellerDetailsDto sellerDetailsDto) {
		
		
		
		return sellerService.updateUser(id,sellerDetailsDto);
		
	}
	
	@GetMapping("/list/seller")
	private Response<List<SellerDetailsDto>> getUserByStatus(@RequestParam(value="status",required=false) List<String> status,@RequestParam(value="sortBy", required=false)String sortBy){
		
		System.out.println("here we");
		return sellerService.filterSeller(status,sortBy);
	}
	
	@GetMapping("/list/seller/search")
	private Response<List<SellerDetailsDto>> getUserBySearch(@RequestParam(value="ownername",required=false) String ownersearch,@RequestParam(value="companyname",required=false) String companysearch,@RequestParam(value="telephone",required=false) String mobilesearch){
		
		return sellerService.searchSeller(ownersearch,companysearch,mobilesearch);
	}

	
}
