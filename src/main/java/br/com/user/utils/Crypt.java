package br.com.user.utils;

import com.google.common.io.BaseEncoding;
import lombok.experimental.UtilityClass;

import java.security.MessageDigest;

@UtilityClass
public class Crypt {

    public static String md5(Object value) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.toString().getBytes());
            byte[] digest = md5.digest();
            return BaseEncoding.base16().encode(digest);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
