package test.org.gephi.project;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphFactory;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.impl.ProjectControllerImpl;
import org.openide.util.Lookup;

import com.google.gwt.junit.client.GWTTestCase;

public class Project extends GWTTestCase {

	public String getModuleName() {
		return "test.org.gephi.project.Project";
	}

	DirectedGraph dg=null;
	GraphModel gm=null;
	GraphFactory gf=null;
	ForceAtlasLayout layout=null;
	
	public void testCreate() {
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		
		assertTrue(pc.getClass()==ProjectControllerImpl.class);
	}
	
	public void testNewProject() {
		
		Lookup.getDefault().clear();
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		
		
		
		GraphController gc=null;
		
		System.out.println("testNewProject: before GraphController creation");
		gc = Lookup.getDefault().lookup(GraphController.class);
		System.out.println("testNewProject: after GraphController creation");
		assertTrue(gc!=null);
		
		System.out.println("testNewProject: before .getModel creation");
		gm = gc.getModel();
		System.out.println("testNewProject: after .getModel creation");
		assertTrue(gm!=null);
		
		
		dg = gm.getDirectedGraph();
		System.out.println("testNewProject: after .getDirectedGraph creation");
		
		assertTrue(dg!=null);
		
		gf=gm.factory();
		assertTrue(gf!=null);
		
		Node node1a=gf.newNode("V1a");
		Node node1b=gf.newNode("V1b");
		Node node1c=gf.newNode("V1c");
		
		assertTrue(node1a!=null);
		assertTrue(node1b!=null);
		
		dg.addNode(node1a);
		dg.addNode(node1b);
		dg.addNode(node1c);
		
		Edge e1 = gm.factory().newEdge(node1a, node1b, 1f, true);
		Edge e2 = gm.factory().newEdge(node1a, node1c, 1f, true);
		
		dg.addEdge(e1);
		dg.addEdge(e2);
		
		layout=new ForceAtlasLayout();
		layout.setGraphModel(gm);
		layout.setAttractionStrength(2.0);
		layout.setGravity(0.5);
		layout.setCooling(2.2);
		layout.initAlgo();
		layout.setAdjustSizes(Boolean.TRUE);
		
		for (int i=0;i<10;i++) {
			if (layout.canAlgo())
				layout.goAlgo();
		}
		
	}
	
}///
