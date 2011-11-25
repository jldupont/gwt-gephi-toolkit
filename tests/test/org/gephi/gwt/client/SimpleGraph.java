package test.org.gephi.gwt.client;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphFactory;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.dhns.core.Dhns;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SimpleGraph implements EntryPoint {
	
	DirectedGraph dg=null;
	GraphModel gm=null;
	GraphFactory gf=null;
	ForceAtlasLayout layout=null;
	
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		createGraph();
		doLayout();
		
	}
	
	public void createGraph() {
		
		gm = new Dhns();		
		dg = gm.getDirectedGraph();

		gf=gm.factory();

		
		Node node1a=gf.newNode("V1a");
		Node node1b=gf.newNode("V1b");
		Node node1c=gf.newNode("V1c");
		
		dg.addNode(node1a);
		dg.addNode(node1b);
		dg.addNode(node1c);
		
		Edge e1 = gm.factory().newEdge(node1a, node1b, 1f, true);
		Edge e2 = gm.factory().newEdge(node1a, node1c, 1f, true);
		
		dg.addEdge(e1);
		dg.addEdge(e2);
	}
	
	public void doLayout() {
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
