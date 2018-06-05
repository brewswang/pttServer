package com.dventus.ptt.ftpException;

import com.dventus.common.exceptions.DventusException;
import com.dventus.common.exceptions.Severity;

public class PttFtpException extends DventusException {

	public PttFtpException(int errorCatParam, int errorCodeParam,  Severity severityParam,  String messageParam,
		String applicationParam) {
		super(errorCatParam, errorCodeParam, severityParam, messageParam, applicationParam);
	}

	private static final long serialVersionUID = 1L;


}
