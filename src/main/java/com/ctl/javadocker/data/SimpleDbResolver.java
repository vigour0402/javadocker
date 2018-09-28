package com.ctl.javadocker.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerResolver;

@ApplicationScoped
public class SimpleDbResolver implements EntityManagerResolver {

	@Inject
	@SimpleDB // Qualifier - assumes a producer is around...
	private EntityManager em;

	@Override
	public EntityManager resolveEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
