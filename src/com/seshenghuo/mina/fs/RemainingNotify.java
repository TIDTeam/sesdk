/**
 * 
 */
package com.seshenghuo.mina.fs;

/**
 * @author Administrator
 * 
 */
public interface RemainingNotify {
	public void message(FileClient client, int cmd, int remain, int pckSize);
}
