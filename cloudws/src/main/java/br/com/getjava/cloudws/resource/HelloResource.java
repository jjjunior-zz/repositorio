package br.com.getjava.cloudws.resource;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Preference;
import org.restlet.representation.Variant;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.getjava.cloudws.domain.Instancia;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;
import br.com.getjava.cloudws.enumeration.Status;

public class HelloResource extends ServerResource {

	
	@Get("xml|json")
	public String representation(Variant v){
		
		System.out.println(v);
		
		String n = super.getRequest().getHeaders().getFirstValue("ContentType", true);
		System.out.println(n);
		
		
		if(getVariants(getRequest().getMethod()).equals(MediaType.APPLICATION_JSON)){
			return representJson();
		}else if(getVariants(getMethod()).equals(MediaType.APPLICATION_JSON)){
			return representXml();
		}		
		return null;
		
	}
	
	
	
	//@Get("xml")
	public String representXml() {		
		
		 //if ( MediaType.APPLICATION_JSON.equals( variant.getMediaType(), true )

		//Cerveja c = new Cerveja("Skol", "assaASsa", "asdasads", Tipo.BOCK);
		Template t = Template.newInstance("Teste1e", "wildfly 10",SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance(2, 20, 200, Status.INICIADO, br.com.getjava.cloudws.enumeration.Tipo.LARGE, t);
		
		StringWriter sw = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(c.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(c, sw);
		} catch (PropertyException e1) {
			e1.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	//@Get("json")
	public String representJson() {	

		//Cerveja c = new Cerveja("Skol", "assaASsa", "asdasads", Tipo.BOCK);
		Template t = Template.newInstance("Teste1e", "wildfly 10",SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance(2, 20, 200, Status.INICIADO, br.com.getjava.cloudws.enumeration.Tipo.LARGE, t);		
		Gson gson = new GsonBuilder().create();
        
		return gson.toJson(c);
	}
	
	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		return super.getResponse();
	}
}
