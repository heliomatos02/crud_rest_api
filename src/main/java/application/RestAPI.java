package application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import resource.FornecedorResource;

/**
 * 
 */
@ApplicationPath("/rest")
public class RestAPI extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	
	public RestAPI() {
		this.singletons.add(new FornecedorResource());
	}
	
	@Override
	public Set<Object> getSingletons(){
		return this.singletons;
	}
}
