package test.org.gephi.project;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.project.api.ProjectController;
import org.gephi.project.impl.ProjectControllerImpl;
import org.openide.util.Lookup;

import com.google.gwt.junit.client.GWTTestCase;

public class Project extends GWTTestCase {

	public String getModuleName() {
		return "test.org.gephi.project.Project";
	}

	public void testCreate() {
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		
		assertTrue(pc.getClass()==ProjectControllerImpl.class);
	}
	
	public void testNewProject() {
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		
		GraphModel gm=null;
		DirectedGraph dg=null;
		
		gm = Lookup.getDefault().lookup(GraphController.class).getModel();
		
		assertTrue(gm!=null);
		//dg = gm.getDirectedGraph();
		
		//assertTrue();
	}
	
	
}///
