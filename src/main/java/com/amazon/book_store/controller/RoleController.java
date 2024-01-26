package com.amazon.book_store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.book_store.entity.Role;
import com.amazon.book_store.service.RoleService;


@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService ;
	
	@GetMapping("/roles")
	public List<Role> findAllRoles() {
		
		return roleService.findAllRoles();
	}
	
	@GetMapping("/getRole")
	public Role finRoleByName(@RequestParam(name="roleName") String name) {
				
		
		Role theROle = roleService.findByName(name); 
		return theROle ;
	}
	
	@PostMapping("/roles")
	public Role Add(@Valid @RequestBody Role theRole) {
		
		roleService.save(theRole) ;
		
		return theRole ;
	}
	
	@DeleteMapping("/roles/{id}")
	public Map<String,String>deleteRoleById(@PathVariable int id){
		
		roleService.findById(id);

		roleService.delete(id);
		
		Map<String,String> resp = new HashMap<>();
		
		resp.put("message", "Role deleted successfully - " + id);
		
		return resp ;
	}
}
