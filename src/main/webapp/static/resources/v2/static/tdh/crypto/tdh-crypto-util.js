/**********************************************************
 ******************* tdh 前端 AES 加密工具 *****************
 ******* 必须提前引入： crypto-js.min.js *********
 **********************************************************/

(function () {
    /**
     * 通达海AES公钥
     * @type {string}
     */
    var TDHAesPublicKey = "0123456701234567"; // 0a1b2c3d4e5f6987

    window.TDHCryptoUtil = {};



    /**
     * 对传输的参数进行编码处理
     */
     TDHCryptoUtil.encodeURLParam= function(word) {
        var paramStr = TDHCryptoUtil.aesEncrypt(word);
        return "tdh-params="+ encodeURIComponent(encodeURIComponent(paramStr));
    }


    /**
     * 对传输的参数编码进行解码
     * @param {*} word 从tdhparam读取的数据
     */
     TDHCryptoUtil.decodeURLParam= function(word) {
        return TDHCryptoUtil.aesDecrypt(word);
    }


    /**
     * AES 通达海加密
     * @param word
     * @returns {string}
     */
     TDHCryptoUtil.aesEncrypt = function (word) {
        //密钥--应和后台java解密或是前台js解密的密钥保持一致（16进制）
        var key = CryptoJS.enc.Utf8.parse(TDHAesPublicKey);
        //偏移量
        var srcs = CryptoJS.enc.Utf8.parse(word);
        //算法
        var encrypted = CryptoJS.AES.encrypt(srcs, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });

        //替换/为#--防止值为“1”的情况
        var reg = new RegExp('/', "g");
        return encrypted.toString().replace(reg, "#");
    }

    /**
 * AES 通达海解密
 * @param word 加密内容
 * @returns {any}
 */
     TDHCryptoUtil.aesDecrypt = function (word) {
        var reg = new RegExp('#', "g");
        word = word.toString().replace(reg, "/")
        var key = CryptoJS.enc.Utf8.parse(TDHAesPublicKey);
        var decrypt = CryptoJS.AES.decrypt(word, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }

})();



