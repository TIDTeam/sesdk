/**
 * 
 */
package com.seshenghuo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seshenghuo.util.bean.IPRange;

/**
 * @author carlli
 *
 */
public class IPTools {
	public static final int NOT_CONFING = -1;
	public static final int IN_RANGE = 1;
	public static final int OVER_RANGE = 0;
	
	/**
	 * 
	 */
	public IPTools() {
		// TODO Auto-generated constructor stub
	}
	
	private static final long[] IPV4_MASK_CIDR = {
      //subnet mask   CIDR
		0xffffffff, //32	255.255.255.255
		0xfffffffe, //31	255.255.255.254
		0xfffffffc, //30	255.255.255.252
		0xfffffff8, //29	255.255.255.248
		0xfffffff0, //28	255.255.255.240
		0xffffffe0,	//27	255.255.255.224
		0xffffffc0,	//26	255.255.255.192
		0xffffff80,	//25	255.255.255.128
		0xffffff00,	//24	255.255.255.0
		0xfffffe00,	//23	255.255.254.0
		0xfffffc00,	//22	255.255.252.0
		0xfffff800,	//21	255.255.248.0
		0xfffff000,	//20	255.255.240.0
		0xffffe000,	//19	255.255.224.0
		0xffffc000,	//18	255.255.192.0
		0xffff8000,	//17	255.255.128.0
		0xffff0000,	//16	255.255.0.0
		0xfffe0000,	//15	255.254.0.0
		0xfffc0000,	//14	255.252.0.0
		0xfff80000,	//13	255.248.0.0
		0xfff00000,	//12	255.240.0.0
		0xffe00000,	//11	255.224.0.0
		0xffc00000,	//10	255.192.0.0
		0xff800000,	//9		255.128.0.0
		0xff000000,	//8		255.0.0.0
		0xfe000000,	//7		254.0.0.0
		0xfc000000,	//6		252.0.0.0
		0xf8000000,	//5		248.0.0.0
		0xf0000000,	//4		240.0.0.0
		0xe0000000,	//3		224.0.0.0
		0xc0000000,	//2		192.0.0.0
		0x80000000,	//1		128.0.0.0
		0x00000000	//0		0.0.0.0
	};
	
	private static boolean valid4(String ip){
		boolean v = false;
		Pattern p = Pattern.compile("^([0-9]{1,3})(\\.[0-9]{1,3}){3}$");
		Matcher m = p.matcher(ip);
		
		v = m.matches();
		
		return v;
	}
	
	public static long encodeIP4(String ip){
		long result = 0;
		
		if(!valid4(ip)){
			return result;
		}else{
			String[] arr = ip.split("\\.");			
			long tmp = 0;
			int size = arr.length;
			
			for(int i = size - 1; i >= 0; i--){
				tmp = Long.parseLong(arr[size - 1 - i]);
				
				result |= tmp << (i * 8);
			}
			
		}
		
		return result;
	}
	
	public static String decodeIP4(long ip){
		StringBuilder sb = new StringBuilder(15);
		String sip = null;
		int length = 4;
		
		for(int i = 0; i < length; i++){
			sb.insert(0, Long.toString(ip & 0xff));
			
			if(i < 3){
				sb.insert(0, '.');
			}
			
			ip = ip >> 8;
		}
		
		sip = sb.toString();
		
		if(!valid4(sip)){
			return null;
		}
		
		return sip;
	}
	
	private static long calcBroadcastAddress(long ip, long mask){
		byte[] b0 = Convert.long2bytes(ip);
		byte[] b1 = Convert.long2bytes(mask);
		
		long bca = 0;
		
		for(int i = 7; i >= 0; i--){
			bca |= ((b0[i] | (~b1[i]) & 0xff)) << ((7 - i) * 8);
		}

		return bca;
	}
	
	private static long calcNetworkAddress(long ip, long mask){
		byte[] b0 = Convert.long2bytes(ip);
		byte[] b1 = Convert.long2bytes(mask);
		
		long nwa = 0;
		
		for(int i = 7; i >= 0; i--){
			nwa |= (b0[i] & b1[i]) << ((7 - i) * 8);
		}
		
		return nwa;
	}
	
	public static IPRange calcIP4Range(String cidr, long locIpAddress){
		IPRange info = new IPRange();
		String[] group = cidr.split("/");
		
		String ip = group[0]; 
		int block = Integer.parseInt(group[1]);
		
		if(block < 0 || block > 32){
			throw new IllegalArgumentException("The CIDR Range is 0~32");
		}
		
		if(!valid4(ip)){
			throw new IllegalArgumentException("Illegal IP address");
		}
		
		long lIP = encodeIP4(ip);
		long mask = IPV4_MASK_CIDR[32 - block];
		String subnetMask = decodeIP4(mask);
		boolean inRange = false;
		boolean inAllowedRange = false;
		long maxAddressNumber = 0;
		long bca = calcBroadcastAddress(lIP, mask);
		long nwa = calcNetworkAddress(lIP, mask);
		long fa = 0;
		long la = 0;
		String broadcastAddress = "";
		String networkAddress = "";
		String firstIPAddress = "";
		String lastIPAddress = "";
		
		if(block == 32){			
			firstIPAddress = ip;
			lastIPAddress = ip;
			
			maxAddressNumber = 1;
		}else if(block == 31){
			firstIPAddress = decodeIP4(nwa);
			lastIPAddress = decodeIP4(bca);
			
			maxAddressNumber = 2;			
		}else{			
			maxAddressNumber = (long)Math.pow(2, 32 - block) - 2;
			
			broadcastAddress = decodeIP4(bca);
			networkAddress = decodeIP4(nwa);
			firstIPAddress = decodeIP4(nwa + 1);
			lastIPAddress = decodeIP4(bca - 1);
		}
		
		fa = encodeIP4(firstIPAddress);
		la = encodeIP4(lastIPAddress);
		
		if(locIpAddress > 0){
			if(locIpAddress >= fa && locIpAddress <= la){
				inRange = true;
			}
			if(locIpAddress >= lIP && locIpAddress <= la){
				inAllowedRange = true;
			}
		}
	    
		info.setInRange(inRange);
		info.setInAllowedRange(inAllowedRange);
		info.setBroadcastAddress(broadcastAddress);
		info.setNetworkAddress(networkAddress);
		info.setFirstIPAddress(firstIPAddress);
		info.setLastIPAddress(lastIPAddress);
		info.setMaxAddressNumber(maxAddressNumber);
		info.setStartIPAddress(ip);
		info.setSubnetMask(subnetMask);
		
		return info;
		
	}
	
	public static IPRange calcIP4Range(String cidr, String locIpAddress){
		return calcIP4Range(cidr, encodeIP4(locIpAddress));
	}
	
	public static int checkIPList(String iplist, long loc){
		
		if(null == iplist || "".equals(iplist)){
			return NOT_CONFING;
		}
		
		if("*".equals(iplist)){
			return IN_RANGE;
		}
		
		String[] arr = iplist.split(";");
		String tmp = null;
		String[] ipGroup = null;
		IPRange cidr = null;
		int size = arr.length;
		long sip = 0;
		long eip = 0;
		
		for(int  i = 0; i < size; i++){
			tmp = arr[i];
			
			if(tmp.indexOf('-') != -1){
				ipGroup = tmp.split("-");
				sip = encodeIP4(ipGroup[0]);
				eip = encodeIP4(ipGroup[1]);
				
				if(loc >= sip && loc <= eip){
					return IN_RANGE;
				}
			}else if(tmp.indexOf('/') != -1){
				cidr = calcIP4Range(tmp, loc);
				
				if(cidr.isInAllowedRange()){
					return IN_RANGE;
				}
			}else{
				sip = eip = encodeIP4(tmp);
				
				if(loc >= sip && loc <= eip){
					return IN_RANGE;
				}
			}
		}
		
		return OVER_RANGE;
	}
	
	public static int checkIPList(String iplist, String loc){
		return checkIPList(iplist, encodeIP4(loc));
	}

}
