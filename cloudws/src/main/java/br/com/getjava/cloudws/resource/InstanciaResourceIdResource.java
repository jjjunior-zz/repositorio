package br.com.getjava.cloudws.resource;

import java.io.StringWriter;

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
import br.com.getjava.cloudws.domain.Usuario;
import br.com.getjava.cloudws.enumeration.Bits;
import br.com.getjava.cloudws.enumeration.SistemaOperacional;
import br.com.getjava.cloudws.enumeration.TipoMaquina;
import br.com.getjava.cloudws.enumeration.TipoUsuario;

public class InstanciaResourceIdResource extends ServerResource {
	
	
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
		Usuario usuario = Usuario.newInstance("jjjunior@gmail.com", "1234546", TipoUsuario.ROOT);
		Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance("Teste1455e",2, 20, 200, TipoMaquina.LARGE,t,usuario );

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
		Usuario usuario = Usuario.newInstance("jjjunior@gmail.com", "1234546", TipoUsuario.ROOT);
		Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance("Teste441455e",2, 20, 200,TipoMaquina.XLARGE,t,usuario);
		c.setId(1);		
		
		Gson gson = new GsonBuilder().create();		

		return gson.toJson(c);
		
	}	
	
	public String representText() {
		Usuario usuario = Usuario.newInstance("jjjunior@gmail.com", "1234546", TipoUsuario.ROOT);
		Template t = Template.newInstance("Teste1e", "wildfly 10", SistemaOperacional.LINUX, Bits.bits64);
		Instancia c = Instancia.newInstance("Teste44145485e",2, 20, 200, TipoMaquina.LARGE,t,usuario);
		return c.toString();
	}

}
