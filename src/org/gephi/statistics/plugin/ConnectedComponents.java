package org.gephi.statistics.plugin;

import org.gephi.graph.api.HierarchicalUndirectedGraph;
import org.gephi.data.attributes.api.AttributeModel;

public class ConnectedComponents {

	public static final String WEAKLY = "componentnumber";
	
	public void weaklyConnected(HierarchicalUndirectedGraph hgraph, AttributeModel attributeModel) {}
	
	public int getGiantComponent() {
		throw new RuntimeException("ConnectedComponents.getGiantComponent: dummy");
	}
	
}
