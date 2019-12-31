package com.braisedpanda.my.blog.commons.utils;


import org.springframework.util.StringUtils;


/**
 * @ClassName: ParamValidator
 * @Description: 参数验证
 * @author JiC
 * @date 2018年9月20日
 */
public final class ParamValidator {

	
	/**
	 * @Description: Object 参数非空验证
	 * @param obj
	 * @return
	 * @return boolean 
	 * @throws
	 * @author JiC 
	 * @date 2018年9月20日
	 */
	public static boolean objsParamVerify(Object ...obj) {
		int len;
		if(obj == null || (len = obj.length) <= 0) {
			return false;
		}
		Object str;
		for(int i = 0; i < len; i++) {
			str = obj[i];
			if (str == null) {
				return false;
			}else if (str instanceof String && StringUtils.isEmpty(str)) {
				return false;
			}
		}
		return true;
	}
}
