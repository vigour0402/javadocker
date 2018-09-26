package com.ctl.javadocker;

import java.util.Date;

import javax.enterprise.context.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class TimeService {
	private static final Logger LOG = LoggerFactory.getLogger(TimeService.class);
	public String getText() {
		LOG.debug("get Time");
		return String.format("Time %d", new Date().getTime());
	}

}
