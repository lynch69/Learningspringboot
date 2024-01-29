package com.example.rest.controller.restcontroller.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<users> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new users(1,"Ritik",LocalDate.now().minusYears(30)));
		users.add(new users(1,"Ritik",LocalDate.now().minusYears(30)));
		users.add(new users(1,"Ritik",LocalDate.now().minusYears(30)));
	}
	
	public List<users> findall(){
		
		return users;
	}
	
	public users save(users user)
	{
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	
	public users findOne(int id)
	{
		Predicate<? super com.example.rest.controller.restcontroller.users.users> predicate= user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
		}
	 


    public void deletebyId(int id)
    {
	Predicate<? super com.example.rest.controller.restcontroller.users.users> predicate= user -> user.getId().equals(id);
	users.removeIf(predicate);
	}
 
}
