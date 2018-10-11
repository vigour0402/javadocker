package com.ctl.javadocker;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ctl.javadocker.data.User;
import com.ctl.javadocker.data.UserRepository;

@Path("/users")
@ApplicationScoped
public class UserResource {

	@Inject
	UserRepository userDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> r = userDao.findAll();
		return r;

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(User user_) {
		userDao.save(user_);
	}
}