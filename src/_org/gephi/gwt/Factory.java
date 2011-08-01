package _org.gephi.gwt;

import java.util.HashMap;

public class Factory {

	@SuppressWarnings("unchecked")
	HashMap<Class, Object> map=new HashMap<Class, Object>();
	
	void init() {
		
		//map.put(arg0, arg1);
	}
	
	public static <T> T get(Class<T> klass) {
		
		return null;
	}//
	
}//
