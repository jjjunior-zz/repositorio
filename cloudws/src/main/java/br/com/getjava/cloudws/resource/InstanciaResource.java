package br.com.getjava.cloudws.resource;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.restlet.data.MediaType;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.getjava.cloudws.domain.Instancia;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;
import br.com.getjava.cloudws.enumeration.Status;
import br.com.getjava.cloudws.enumeration.Tipo;

public class InstanciaResource extends ServerResource {
	
	
	@Get("json")
	public String representation(Variant variant) {

		if (MediaType.APPLICATION_JSON.equals(variant.getMediaType())) {
			return representJson();
		} else if (MediaType.APPLICATION_XML.equals(variant.getMediaType())) {
			return representXml();
		}
		return null;
	}

	public String representXml() {

		//Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance(2, 20, 200, Status.INICIADO, Tipo.LARGE );

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

	public String representJson() {
		Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		
		Instancia c = Instancia.newInstance(2, 20, 200, Status.INICIADO, Tipo.XLARGE);
		c.setId(1);		
		
		Instancia c1 = Instancia.newInstance(20, 30, 300, Status.INICIADO, Tipo.MICRO);
		c1.setId(2);

		Gson gson = new GsonBuilder().create();
		
		List<Instancia> lista = new ArrayList<>(); 
		lista.add(c);
		lista.add(c1);

		return gson.toJson(lista);
	}	
	
	public String representText() {
		Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance(2, 20, 200, Status.INICIADO, br.com.getjava.cloudws.enumeration.Tipo.LARGE);
		return c.toString();
	}

}
