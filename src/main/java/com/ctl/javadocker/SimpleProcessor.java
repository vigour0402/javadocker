package com.ctl.javadocker;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctl.javadocker.data.User;
import com.ctl.javadocker.data.UserRepository;


//@RequestScoped
@Named("SimpleProcessor")
public class SimpleProcessor {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleProcessor.class);
	
	@Inject
	UserRepository userDao;

	/**
	 * Process Simple Request
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> process(Map<String, Object> map) throws Exception {
		LOG.info("process :"+map);
		LOG.info("process :"+map.get("id") + map.get("name"));
		
		HashMap<String, Object> out = new HashMap<>();
		out.put("id", map.get("id"));
		out.put("status", "complete");
		User u = new User();
		u.setID((int)map.get("id"));
		u.setName((String)map.get("name"));
		try {
			userDao.save(u);
		} catch (Exception ex) {
			LOG.error("Error", ex);
		}
		return out;
	}


}
