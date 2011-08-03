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
		pc.newProject();
		
		assertTrue(pc.getClass()==ProjectControllerImpl.class);
	}
	
	public void testNewProject() {
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		//pc.newProject();
		
		GraphModel gm=null;
		DirectedGraph dg=null;
		GraphController gc=null;
		
		System.out.println("testNewProject: before GraphController creation");
		gc = Lookup.getDefault().lookup(GraphController.class);
		System.out.println("testNewProject: after GraphController creation");
		assertTrue(gc!=null);
		
		System.out.println("testNewProject: before .getModel creation");
		gm = gc.getModel();
		System.out.println("testNewProject: after .getModel creation");
		assertTrue(gm!=null);
		//dg = gm.getDirectedGraph();
		
		//assertTrue();
	}
	
	
}///
