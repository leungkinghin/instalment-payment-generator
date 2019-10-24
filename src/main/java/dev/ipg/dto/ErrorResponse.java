package dev.ipg.dto;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	private String errMsg = "";
}
