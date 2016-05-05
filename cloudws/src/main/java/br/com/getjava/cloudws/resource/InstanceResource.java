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

import br.com.getjava.cloudws.domain.Instance;
import br.com.getjava.cloudws.domain.Template;
import br.com.getjava.cloudws.domain.User;
import br.com.getjava.cloudws.enumeration.CpuType;
import br.com.getjava.cloudws.enumeration.OperationalSystem;
import br.com.getjava.cloudws.enumeration.ProcessorArchitecture;
import br.com.getjava.cloudws.enumeration.Status;
import br.com.getjava.cloudws.enumeration.UserType;

public class InstanceResource extends ServerResource {

	@Get("json|xml")
	public String representation(Variant variant) {
		
		if (MediaType.APPLICATION_JSON.equals(variant.getMediaType())) {
			return representJson();
		} else if (MediaType.APPLICATION_XML.equals(variant.getMediaType())) {
			return representXml();
		}
		return null;
	}

	public String representXml() {

		User usuario = User.newInstance("jjjunior@gmail.com", "1234546", UserType.ROOT);
		usuario.setId(1);
		
		Template t = Template.newInstance("Teste1e", "wildfly 10", OperationalSystem.LINUX, ProcessorArchitecture.bit64);
		t.setId(1);
		
		Instance c = Instance.newInstance("Servidor Wildfly", 2, 20, 200, CpuType.LARGE, Status.STOP, t, usuario);
		c.setId(1);

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

		User user = User.newInstance("jjjunior@gmail.com", "1234546", UserType.ROOT);
		user.setId(1);
		
		Template t = Template.newInstance("Teste1e", "wildfly 10", OperationalSystem.LINUX, ProcessorArchitecture.bit64);
		t.setId(1);

		Instance c = Instance.newInstance("Teste1e144", 2, 20, 200, CpuType.XLARGE, Status.STOP, t, user);
		c.setId(1);

		Instance c1 = Instance.newInstance("Teste1e1445", 20, 30, 300, CpuType.MICRO, Status.STOP, t, user);
		c1.setId(2);

		Gson gson = new GsonBuilder().create();

		List<Instance> lista = new ArrayList<>();
		lista.add(c);
		lista.add(c1);

		return gson.toJson(lista);
	}

	public String representText() {
		User usuario = User.newInstance("jjjunior@gmail.com", "1234546", UserType.ROOT);
		Template t = Template.newInstance("Teste1e", "wildfly 10", OperationalSystem.LINUX, ProcessorArchitecture.bit64);
		Instance c = Instance.newInstance("Teste1e14445", 2, 20, 200, CpuType.LARGE, Status.STOP, t, usuario);
		return c.toString();
	}

}
