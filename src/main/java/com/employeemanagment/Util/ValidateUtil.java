package com.employeemanagment.Util;

import java.util.Collection;

public class ValidateUtil {
	
	public static boolean checkEmptyList(Collection<?> list) {
		if(list == null || list.isEmpty() || list.size() <= 0 )
			return true;
		return false;
	}
	
	public static boolean checkNullObject(Object obj) {
		return obj == null?true:false;
	}
}
