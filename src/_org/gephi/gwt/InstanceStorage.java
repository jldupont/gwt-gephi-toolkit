/**
 * @author: Jean-Lou Dupont
 */
package _org.gephi.gwt;

import java.util.HashMap;

public class InstanceStorage {

	HashMap<String, Object> map=new HashMap<String, Object>();
	
	public InstanceStorage() {
	}
	
	@SuppressWarnings("unchecked")
	public <T> T lookup(Class<T> klass) {
		
		if (map.containsKey(klass.getClass().toString()))
			return (T) map.get(klass.getClass().toString());
		
		return null;
	}
	
	public void add(Object o) {
		if (map.containsKey(o.getClass().toString())) {
			throw new RuntimeException("InstanceStorage.add: already instance for class: "+o.getClass().toString());
		}
		map.put(o.getClass().toString(), o);
	}
	
	public void remove(Object o) {
		if (!map.containsKey(o.getClass().toString()))
			throw new RuntimeException("InstanceStorage.remove: no instance for class: "+o.getClass().toString());
		
		map.remove(o.getClass().toString());
	}
	
}
