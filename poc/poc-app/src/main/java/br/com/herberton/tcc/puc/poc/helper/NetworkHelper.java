package br.com.herberton.tcc.puc.poc.helper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.stereotype.Component;

import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Component
public class NetworkHelper implements INetworkHelper {

	@Override
	public String getNetworkAddress() {

		Enumeration<NetworkInterface> e = null;
		
		try {
			e = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException se) {
			throw new RuntimeException(se);
		};
		
		while (e.hasMoreElements()) {
			NetworkInterface networkInterface =  e.nextElement();
			Enumeration<InetAddress> ee = networkInterface.getInetAddresses();
			
			while (ee.hasMoreElements()) {
				InetAddress inetAddress = ee.nextElement();
				if(inetAddress.isSiteLocalAddress()) {
					return inetAddress.getHostAddress();
				}
			}
			
		}
		
		return "NOT FOUND";
		
	}
	
}
