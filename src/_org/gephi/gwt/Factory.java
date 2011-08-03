/**
 * @author jldupont
 */
package _org.gephi.gwt;

import java.util.HashMap;

import org.gephi.data.attributes.AttributeControllerImpl;
import org.gephi.data.attributes.api.AttributeController;
import org.gephi.graph.api.GraphController;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.WorkspaceProvider;
import org.gephi.project.impl.ProjectControllerImpl;
import org.gephi.project.impl.ProjectInformationImpl;
import org.gephi.project.impl.WorkspaceProviderImpl;
import org.gephi.workspace.impl.WorkspaceInformationImpl;
import org.gephi.graph.dhns.*;
//import org.gephi.graph.dhns.core.Dhns;

import com.google.gwt.core.client.GWT;

public class Factory {

	static boolean initDone=false;
	
	static enum Classe {
		//DHNS,
		ATTRIBUTE_CONTROLLER,
		PROJECT_CONTROLLER,
		PROJECT_INFORMATION_IMPL,
		GRAPH_CONTROLLER,
		WORKSPACE_PROVIDER,
		WORKSPACE_PROVIDER_IMPL,
		WORKSPACE_INFORMATION_IMPL,
	}
	
	@SuppressWarnings("unchecked")
	static HashMap<Class, Object> map=new HashMap<Class, Object>();
	
	@SuppressWarnings("unchecked")
	static HashMap<Class, Classe> cmap=new HashMap<Class, Classe>();
	
	// Singleton flag map
	//@SuppressWarnings("unchecked")
	//static HashMap<Class, Boolean> smap=new HashMap<Class, Boolean>();
	
	static void init() {

		//map.put(Dhns.class, null);
		//cmap.put(Dhns.class, Classe.DHNS);

		map.put(AttributeController.class, null);
		cmap.put(AttributeController.class, Classe.ATTRIBUTE_CONTROLLER);
		
		map.put(ProjectController.class, null);
		cmap.put(ProjectController.class, Classe.PROJECT_CONTROLLER);


		map.put(ProjectInformationImpl.class, null);
		cmap.put(ProjectInformationImpl.class, Classe.PROJECT_INFORMATION_IMPL);
		
		map.put(GraphController.class, null);
		cmap.put(GraphController.class, Classe.GRAPH_CONTROLLER);

		map.put(WorkspaceProvider.class, null);
		cmap.put(WorkspaceProvider.class, Classe.WORKSPACE_PROVIDER_IMPL);
		
		map.put(WorkspaceProviderImpl.class, null);
		cmap.put(WorkspaceProviderImpl.class, Classe.WORKSPACE_PROVIDER_IMPL);

		map.put(WorkspaceInformationImpl.class, null);
		cmap.put(WorkspaceInformationImpl.class, Classe.WORKSPACE_INFORMATION_IMPL);
		
		initDone=true;
	}
	
	public static void clear() {
		System.out.println("Factory.clear");
		map.clear();
		init();
	}
	
	public static void add(Object o) {
		if (map.containsKey(o.getClass())) {
			//throw new RuntimeException("Factory: class '"+o.getClass()+"' already exists...");
			Object r=map.get(o.getClass());
			if (r!=null) {
				System.out.println("Factory.add: class '"+o.getClass()+"' already exists...");
				return;				
			}
		}
		map.put(o.getClass(), o);
	}
	public static void remove(Object o) {
		if (map.containsKey(o.getClass())) {
			map.remove(o.getClass());
		} else {
			System.out.println("Factory.remove: class '"+o.getClass()+"' doesn't exist...");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> klass){
		
		if (!initDone)
			init();
		
		if (map.containsKey(klass)) {
			Object k=map.get(klass);
			if (k!=null) {
				System.out.println("Factory: retrieving: "+klass.getName());
				return (T) k;
			} 
			//else {
			//	throw new RuntimeException("Factory: Map with null: "+klass.toString());
			//}
		} else {
			//throw new RuntimeException("Factory: Unknown class: "+klass.toString());
			System.out.println("Factory.get: couldn't find instance for class: "+klass.toString());
			return null;
		}
		
		System.out.println("Factory: creating: "+klass.getName());
		
		// instance doesn't exist yet... create it!
		Classe c=cmap.get(klass);
		
		Object o=null;
		
		switch(c) {
		//case DHNS:							o=GWT.create(Dhns.class); break;
		case ATTRIBUTE_CONTROLLER:			o=GWT.create(AttributeControllerImpl.class); break;
		case PROJECT_CONTROLLER:			o=GWT.create(ProjectControllerImpl.class); break;
		case PROJECT_INFORMATION_IMPL:		o=GWT.create(ProjectInformationImpl.class); break;
		case GRAPH_CONTROLLER:      		o=GWT.create(DhnsGraphController.class); break;
		case WORKSPACE_PROVIDER_IMPL:      	o=GWT.create(WorkspaceProviderImpl.class); break;
		case WORKSPACE_INFORMATION_IMPL:    o=GWT.create(WorkspaceInformationImpl.class); break;
		}
		
		if (o==null) {
			System.out.println("Factory.get: couldn't create instance for class: "+klass.toString());
		} else {
			map.put(klass, o);	
		}
		
		return (T) o;
	}//
	
}//
