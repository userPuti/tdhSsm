package com.tdh.utils.response;

/**
 * The type Res result.
 *
 * @author yef
 * @date 2018 /8/20
 */
public class ResResult {


	/**
	 * Success response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO success() {
        return new ResponseNoData(ResResultEnum.SUCCESS.getCode(), ResResultEnum.SUCCESS.getMsg());
    }

	/**
	 * Success with data response vo.
	 *
	 * @param data the data
	 * @return the response vo
	 */
	public static ResponseVO successWithData(Object data) {
        return new ResponseWithData(ResResultEnum.SUCCESS.getCode(), ResResultEnum.SUCCESS.getMsg(), data);
    }

	/**
	 * Success in msg response vo.
	 *
	 * @param msg the msg
	 * @return the response vo
	 */
	public static ResponseVO successInMsg(String msg) {
        return new ResponseNoData(ResResultEnum.SUCCESS.getCode(), msg);
    }

	/**
	 * No data response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO noData() {
        return new ResponseNoData(ResResultEnum.NO_DATA.getCode(), ResResultEnum.NO_DATA.getMsg());
    }

	/**
	 * No data in msg response vo.
	 *
	 * @param msg the msg
	 * @return the response vo
	 */
	public static ResponseVO noDataInMsg(String msg) {
        return new ResponseNoData(ResResultEnum.NO_DATA.getCode(), msg);
    }

	/**
	 * Fail response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO fail() {
        return new ResponseNoData(ResResultEnum.ERROR.getCode(), ResResultEnum.ERROR.getMsg());
    }

	/**
	 * Fail response vo.
	 *
	 * @param data the data
	 * @return the response vo
	 */
	public static ResponseVO fail(Object data) {
        return new ResponseWithData(ResResultEnum.ERROR.getCode(), ResResultEnum.ERROR.getMsg(), data);
    }

	/**
	 * Service fail response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO serviceFail() {
        return new ResponseNoData(ResResultEnum.SERVICE_ERR.getCode(), ResResultEnum.SERVICE_ERR.getMsg());
    }

	/**
	 * Fail response vo.
	 *
	 * @param msg the msg
	 * @return the response vo
	 */
	public static ResponseVO fail(String msg) {
        return new ResponseNoData(ResResultEnum.ERROR.getCode(), msg);
    }

	/**
	 * Fail response vo.
	 *
	 * @param code the code
	 * @param msg  the msg
	 * @return the response vo
	 */
	public static ResponseVO fail(int code, String msg) {
        return new ResponseNoData(code, msg);
    }

	/**
	 * Invalid response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO invalid() {
        return new ResponseNoData(ResResultEnum.PARMS_ERR.getCode(), ResResultEnum.PARMS_ERR.getMsg());
    }

	/**
	 * Invalid response vo.
	 *
	 * @param data the data
	 * @return the response vo
	 */
	public static ResponseVO invalid(Object data) {
        return new ResponseWithData(ResResultEnum.PARMS_ERR.getCode(), ResResultEnum.PARMS_ERR.getMsg(), data);
    }

	/**
	 * User not found response vo.
	 *
	 * @return the response vo
	 */
	public static ResponseVO userNotFound() {
        return new ResponseNoData(ResResultEnum.NO_USER.getCode(), ResResultEnum.NO_USER.getMsg());
    }

}
