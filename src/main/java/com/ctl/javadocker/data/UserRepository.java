package com.ctl.javadocker.data;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.entitymanager.EntityManagerConfig;
import javax.persistence.FlushModeType;

@Repository
@EntityManagerConfig(entityManagerResolver = SimpleDbResolver.class, flushMode = FlushModeType.COMMIT)
public interface UserRepository extends EntityRepository<User, Integer>
{

    

}
