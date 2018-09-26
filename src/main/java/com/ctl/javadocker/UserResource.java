package com.ctl.javadocker;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ctl.javadocker.data.User;
import com.ctl.javadocker.data.UserRepository;


@Path("/users")
public class UserResource {
	
	@Inject
	UserRepository userDao;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getUsers(){
//    public List<User> getUsers(){
        List<User> r = userDao.findAll();
//        return r;
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(r) {};
        Response response = Response.ok(entity).build();
        return response;
    }
}