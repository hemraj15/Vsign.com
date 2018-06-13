package com.vsign.tech.data.exception;


public class InstanceNotFoundException extends InstanceException {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6047462894998472443L;

    public InstanceNotFoundException(Object key, String className) {
        super("Instance not found!", key, className);
    }
}
