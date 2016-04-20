package br.com.getjava.cloudws;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import br.com.getjava.cloudws.resource.HelloResource;

public class RotasApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
	
		Router router = new Router(getContext());

		router.attach("/hello", HelloResource.class);

		return router;
	}
}
