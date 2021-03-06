/*
Copyright 2008-2010 Gephi
Authors : Martin Škurla <bujacik@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.gephi.data.attributes.type;

/**
 * Complex type that define a list of Byte items. Can be created from a byte
 * array, from a Byte array or from single string using either given or default separators.
 * 
 * @author Martin Škurla
 */
public final class ByteList extends AbstractList<Byte> {
    
	
    public ByteList(byte[] primitiveByteArray) {
        create(primitiveByteArray);
    }

    public ByteList(Byte[] wrapperByteArray) {
        create(wrapperByteArray);
    }

    public ByteList(String input) {
        this(input, AbstractList.DEFAULT_SEPARATOR);
    }

    public ByteList(String input, String separator) {
        String[] values=input.split(separator);
        this.list=new Byte[values.length];
    	for (int p=0;p<list.length;p++) {
    		this.list[p]=Byte.parseByte(values[p]);
    	}        
    }
    
    public void create(byte[] arr) {
    	this.list=new Byte[arr.length];
    	for (int p=0;p<list.length;p++) {
    		this.list[p]=arr[p];
    	}
    }
    public void create(Byte[] arr) {
    	this.list=new Byte[arr.length];
    	for (int p=0;p<list.length;p++) {
    		this.list[p]=arr[p];
    	}
    }    
}
