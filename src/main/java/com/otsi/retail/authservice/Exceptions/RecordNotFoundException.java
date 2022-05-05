/**
 * 
 */
package com.otsi.retail.authservice.Exceptions;

/**
 * @author vasavi
 *
 */
public class RecordNotFoundException extends BusinessException {

	
	private static final long serialVersionUID = 1L;


	/**
	 * @param msg
	 */
	public RecordNotFoundException(String msg,int statusCode) {
		super(msg, statusCode);
	}

	
	

}
