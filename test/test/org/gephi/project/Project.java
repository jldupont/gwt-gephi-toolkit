package test.org.gephi.project;

import org.gephi.project.api.ProjectController;
import org.openide.util.Lookup;

import com.google.gwt.junit.client.GWTTestCase;

public class Project extends GWTTestCase {

	public String getModuleName() {
		return "test.org.gephi.project.Project";
	}

	public void testCreate() {
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
		
		assertTrue(pc.getClass()==ProjectController.class);
	}
	
	
}///
