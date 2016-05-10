package com.czly.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TelnetUtil {
	
	/**
	 * telnet 是否能连通
	 * @param address
	 * @param port
	 * @return
	 */
	public static boolean telent(String address, int port){
		Socket server = null;
		try {
			server = new Socket();
			InetSocketAddress iaddress= new InetSocketAddress(address,port);
			server.connect(iaddress, 2000);
			return server.isConnected();
		}catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}finally{
			if (server != null){
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
