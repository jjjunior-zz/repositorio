package br.com.getjava.cloudws.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import br.com.getjava.cloudws.resource.InstanceResource;
import br.com.getjava.cloudws.resource.InstanceResourceById;

public class RouterApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
	
		Router router = new Router(getContext());		
		router.attach("/instances", InstanceResource.class);
		router.attach("/instances/{instances_id}", InstanceResourceById.class);
		return router;
	}
}
