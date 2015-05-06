/**
 * 
 */
package com.seshenghuo.util.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 *
 */
public class IPRange {
	private String subnetMask = ""; //掩码
	private String broadcastAddress = ""; //广播地址
	private String networkAddress = ""; //网络地址
	private String firstIPAddress = ""; //可用IP启始地址
	private String lastIPAddress = ""; //结束IP
	private String startIPAddress = ""; //启始IP
	private long maxAddressNumber = 0; //最大地址数
	private boolean inRange = false; // 是否在范围内
	private boolean inAllowedRange = false; // 是否在允许范围内
	
	/**
	 * 
	 */
	public IPRange() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the subnetMask
	 */
	public String getSubnetMask() {
		return subnetMask;
	}

	/**
	 * @param subnetMask the subnetMask to set
	 */
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	/**
	 * @return the broadcastAddress
	 */
	public String getBroadcastAddress() {
		return broadcastAddress;
	}

	/**
	 * @param broadcastAddress the broadcastAddress to set
	 */
	public void setBroadcastAddress(String broadcastAddress) {
		this.broadcastAddress = broadcastAddress;
	}

	/**
	 * @return the networkAddress
	 */
	public String getNetworkAddress() {
		return networkAddress;
	}

	/**
	 * @param networkAddress the networkAddress to set
	 */
	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}

	/**
	 * @return the firstIPAddress
	 */
	public String getFirstIPAddress() {
		return firstIPAddress;
	}

	/**
	 * @param firstIPAddress the firstIPAddress to set
	 */
	public void setFirstIPAddress(String firstIPAddress) {
		this.firstIPAddress = firstIPAddress;
	}

	/**
	 * @return the lastIPAddress
	 */
	public String getLastIPAddress() {
		return lastIPAddress;
	}

	/**
	 * @param lastIPAddress the lastIPAddress to set
	 */
	public void setLastIPAddress(String lastIPAddress) {
		this.lastIPAddress = lastIPAddress;
	}

	/**
	 * @return the startIPAddress
	 */
	public String getStartIPAddress() {
		return startIPAddress;
	}

	/**
	 * @param startIPAddress the startIPAddress to set
	 */
	public void setStartIPAddress(String startIPAddress) {
		this.startIPAddress = startIPAddress;
	}

	/**
	 * @return the maxAddressNumber
	 */
	public long getMaxAddressNumber() {
		return maxAddressNumber;
	}

	/**
	 * @param maxAddressNumber the maxAddressNumber to set
	 */
	public void setMaxAddressNumber(long maxAddressNumber) {
		this.maxAddressNumber = maxAddressNumber;
	}

	/**
	 * @return the inRange
	 */
	public boolean isInRange() {
		return inRange;
	}

	/**
	 * @param inRange the inRange to set
	 */
	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}

	/**
	 * @return the inAllowedRange
	 */
	public boolean isInAllowedRange() {
		return inAllowedRange;
	}

	/**
	 * @param inAllowedRange the inAllowedRange to set
	 */
	public void setInAllowedRange(boolean inAllowedRange) {
		this.inAllowedRange = inAllowedRange;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Gson g = new Gson();
		String json = g.toJson(this);

		g = null;

		return json;	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((broadcastAddress == null) ? 0 : broadcastAddress.hashCode());
		result = prime * result
				+ ((firstIPAddress == null) ? 0 : firstIPAddress.hashCode());
		result = prime * result + (inAllowedRange ? 1231 : 1237);
		result = prime * result + (inRange ? 1231 : 1237);
		result = prime * result
				+ ((lastIPAddress == null) ? 0 : lastIPAddress.hashCode());
		result = prime * result
				+ (int) (maxAddressNumber ^ (maxAddressNumber >>> 32));
		result = prime * result
				+ ((networkAddress == null) ? 0 : networkAddress.hashCode());
		result = prime * result
				+ ((startIPAddress == null) ? 0 : startIPAddress.hashCode());
		result = prime * result
				+ ((subnetMask == null) ? 0 : subnetMask.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPRange other = (IPRange) obj;
		if (broadcastAddress == null) {
			if (other.broadcastAddress != null)
				return false;
		} else if (!broadcastAddress.equals(other.broadcastAddress))
			return false;
		if (firstIPAddress == null) {
			if (other.firstIPAddress != null)
				return false;
		} else if (!firstIPAddress.equals(other.firstIPAddress))
			return false;
		if (inAllowedRange != other.inAllowedRange)
			return false;
		if (inRange != other.inRange)
			return false;
		if (lastIPAddress == null) {
			if (other.lastIPAddress != null)
				return false;
		} else if (!lastIPAddress.equals(other.lastIPAddress))
			return false;
		if (maxAddressNumber != other.maxAddressNumber)
			return false;
		if (networkAddress == null) {
			if (other.networkAddress != null)
				return false;
		} else if (!networkAddress.equals(other.networkAddress))
			return false;
		if (startIPAddress == null) {
			if (other.startIPAddress != null)
				return false;
		} else if (!startIPAddress.equals(other.startIPAddress))
			return false;
		if (subnetMask == null) {
			if (other.subnetMask != null)
				return false;
		} else if (!subnetMask.equals(other.subnetMask))
			return false;
		return true;
	}
}
