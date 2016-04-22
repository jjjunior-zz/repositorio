package br.com.getjava.cloudws.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import br.com.getjava.cloudws.resource.HelloResource;
import br.com.getjava.cloudws.resource.InstanciaResource;

public class RotasApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
	
		Router router = new Router(getContext());

		router.attach("/hello", HelloResource.class);
		router.attach("/instancias", InstanciaResource.class);

		return router;
	}
}
