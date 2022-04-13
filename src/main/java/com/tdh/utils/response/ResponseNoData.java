package com.tdh.utils.response;


/**
 * The type Response no data.
 *
 * @author yef
 * @date 2018 /8/30
 */
public class ResponseNoData extends  ResponseVO{

    private int code;

    private String msg;


	/**
	 * Instantiates a new Response no data.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public ResponseNoData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
