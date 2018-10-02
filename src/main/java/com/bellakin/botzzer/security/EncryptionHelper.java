package com.bellakin.botzzer.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Service
public class EncryptionHelper {

  private EncryptionHelper() {};

  public static String encryptPassword(String password) {
    String SALT = "12910HN!)I0(H!H=0904901ui1idfg193fvhb=1197$&I*%=af=10f+)H!£nc)0*HFD+)!N%%&D£!+)";
    return DigestUtils.sha256Hex(SALT + password);
  }
}
