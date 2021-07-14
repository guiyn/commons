package org.cmcc.ecip.common.utils;

import com.alibaba.fastjson.serializer.SerializeFilter;

public class JsonSerializeFilter {

	
	public final static SerializeFilter KEY_DownLine2Line  =new com.alibaba.fastjson.serializer.NameFilter() {

		@Override
		public String process(Object object, String name, Object value) {
			return name.replaceAll("_", "-");
		}
		
	};
	
	
	public final static SerializeFilter KEY_UpFirstChar =new com.alibaba.fastjson.serializer.PascalNameFilter();

	
	public final static SerializeFilter VAL_NONULL= new com.alibaba.fastjson.serializer.ValueFilter() {

		@Override
		public Object process(Object object, String name, Object value) {
			if (value == null) {
				return "";
			}
			return value;
		}
		
	};
	 
}
