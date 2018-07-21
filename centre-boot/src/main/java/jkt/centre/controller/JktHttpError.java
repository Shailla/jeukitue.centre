package jkt.centre.controller;

public class JktHttpError {

	/** Internationalization code of the error message */
	private final String code;
	
	/** Readable debug error message */
	private final String debugMessage;

	/**
	 * Constructor
	 * @param code internationalization code of the error message
	 * @param message readable debug error message
	 */
	public JktHttpError(final String debugMessage) {
		this.code = Messages.ERROR_METHOD_UNKNWON;
		this.debugMessage = debugMessage;
	}
	
	/**
	 * Constructor
	 * @param code internationalization code of the error message
	 * @param message readable debug error message
	 */
	public JktHttpError(final String code, final String debugMessage) {
		this.code = code;
		this.debugMessage = debugMessage;
	}
	
	/** Internationalization code of the error message */
	public String getCode() {
		return code;
	}

	/** Readable debug error message */
	public String getDebugMessage() {
		return debugMessage;
	}
}
