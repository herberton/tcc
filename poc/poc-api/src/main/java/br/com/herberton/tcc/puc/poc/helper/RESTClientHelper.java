package br.com.herberton.tcc.puc.poc.helper;

import static br.com.herberton.tcc.puc.poc.contract.IConstant.BACKEND_ADDRESS;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class RESTClientHelper {

	public static String url(String path, Object... paths) {
		
		StringBuilder url = new StringBuilder(BACKEND_ADDRESS);
		
		url.append(path);
		
		stream(defaultIfNull(paths, new String[] {}))
			.forEach(p -> { url.append("/").append(p); });
		
		return url.toString();
	
	}	

}
