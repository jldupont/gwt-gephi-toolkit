/**
 * @author jldupont
 */
package _org.gephi.gwt;

import java.util.HashMap;

import org.gephi.graph.api.GraphController;
import org.gephi.project.api.ProjectController;
import org.gephi.project.impl.ProjectControllerImpl;
import org.gephi.graph.dhns.*;

import com.google.gwt.core.client.GWT;

public class Factory {

	static boolean initDone=false;
	
	static enum Classe {
		PROJECT_CONTROLLER,
		GRAPH_CONTROLLER,
	}
	
	@SuppressWarnings("unchecked")
	static HashMap<Class, Object> map=new HashMap<Class, Object>();
	
	@SuppressWarnings("unchecked")
	static HashMap<Class, Classe> cmap=new HashMap<Class, Classe>();
	
	static void init() {
		
		map.put(ProjectController.class, null);
		cmap.put(ProjectController.class, Classe.PROJECT_CONTROLLER);

		map.put(GraphController.class, null);
		cmap.put(GraphController.class, Classe.GRAPH_CONTROLLER);
		
		initDone=true;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> klass){
		
		System.out.println("Factory: creating: "+klass.getName());
		
		if (!initDone)
			init();
		
		if (map.containsKey(klass)) {
			Object k=map.get(klass);
			if (k!=null) {
				return (T) k;
			}
		} else {
			throw new RuntimeException("Unknown class: "+klass.toString());
		}
		// instance doesn't exist yet... create it!
		Classe c=cmap.get(klass);
		
		Object o=null;
		
		switch(c) {
		case PROJECT_CONTROLLER:	o=GWT.create(ProjectControllerImpl.class); break;
		case GRAPH_CONTROLLER:      o=GWT.create(DhnsGraphController.class); break;
		}
		
		return (T) o;
	}//
	
}//
