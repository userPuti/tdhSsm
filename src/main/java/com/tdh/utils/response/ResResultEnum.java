/**
 * 
 */
package com.tdh.utils.response;

/**
 * The enum Res result enum.
 *
 * @author qiugq
 */
public enum ResResultEnum {
	/**
	 * Success res result enum.
	 */
	SUCCESS(0, "成功"),
	/**
	 * Error res result enum.
	 */
	ERROR(1, "失败"),
	/**
	 * Parms err res result enum.
	 */
	PARMS_ERR(101, "参数异常"),
	/**
	 * No data res result enum.
	 */
	NO_DATA(102, "查无记录"),
	/**
	 * No user res result enum.
	 */
	NO_USER(103, "用户不存在"),
	/**
	 * 等待上一次运行结束
	 */
	WAIT_LAST(104, "等待上一次结束"),
	/**
	 * Ftp err res result enum.
	 */
	FTP_ERR(201, "FTP上传错误"),
	/**
	 * Interface err res result enum.
	 */
	INTERFACE_ERR(202, "接口返回失败"),
	/**
	 * Service err res result enum.
	 */
	SERVICE_ERR(500, "服务异常");

    ResResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

	/**
	 * Gets msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
        return msg;
    }

	/**
	 * Sets msg.
	 *
	 * @param msg the msg
	 */
	public void setMsg(String msg) {
        this.msg = msg;
    }

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
        return code;
    }

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(int code) {
        this.code = code;
    }

}
