package org.openide.util;

import _org.gephi.gwt.InstanceStorage;

public class _InstanceLookup extends Lookup {

	static _InstanceLookup i=null;
	
	public _InstanceLookup create() {
		if (i==null)
			i=new _InstanceLookup();
		return i;
	}
	
	public static Lookup getSingleton() {
		return i;
	}
	
	public <T> T lookup(Class<T> clazz) { 
		return InstanceStorage.lookup(clazz);
	}
	
	public void add(Object o) {
		InstanceStorage.add(o);
	}
	public void remove(Object o) {
		InstanceStorage.remove(o);
	}
	
}
