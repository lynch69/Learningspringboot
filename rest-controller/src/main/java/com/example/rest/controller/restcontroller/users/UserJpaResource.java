package com.example.rest.controller.restcontroller.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.rest.controller.restcontroller.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	
	private UserRepository repository;
	public UserJpaResource(UserRepository repository) {
		
		
		this.repository = repository;
	}

	
	@GetMapping("/jpa/users") 
	public List<users> retriveAllUsers()
	{
		return repository.findAll();
	}

	@GetMapping("/jpa/users")
	public EntityModel<List<users>> retriveAllUsersWithLink() {
	    List<users> userList = repository.findAll();

	    EntityModel<List<users>> entityModel = EntityModel.of(userList);
	    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsersWithLink());
	    entityModel.add(link.withRel("all-users"));

	    return entityModel;
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<users> retriveUser(@PathVariable int id) {
	    Optional<users> user = repository.findById(id);
	    if (user.isEmpty()) {
	        throw new UserNotFoundException("id:" + id);
	    }

	    EntityModel<users> entityModel = EntityModel.of(user.get());
	    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsersWithLink());
	    entityModel.add(link.withRel("all-users"));

	    return entityModel;
	}
	
	
	@DeleteMapping("jpa/users/{id}")
	public void User(@PathVariable int id)
	{
	    repository.deleteById(id);
			
	}
	
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id)
	{
		Optional<users> user = repository.findById(id);
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().getPosts();
			
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<users>  createUser(@Valid @RequestBody users user) {
		users savedUser = repository.save(user);
		
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	  
    
	

	

}
