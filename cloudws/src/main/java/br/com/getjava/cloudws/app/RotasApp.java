package br.com.getjava.cloudws.app;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import br.com.getjava.cloudws.resource.InstanciaResource;
import br.com.getjava.cloudws.resource.InstanciaResourceIdResource;

public class RotasApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
	
		Router router = new Router(getContext());		
		router.attach("/instancia", InstanciaResource.class);
		router.attach("/instancia/{instancia_id}", InstanciaResourceIdResource.class);
		return router;
	}
}
