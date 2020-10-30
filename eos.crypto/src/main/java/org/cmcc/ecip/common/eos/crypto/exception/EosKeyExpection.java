package org.cmcc.ecip.common.eos.crypto.exception;

public class EosKeyExpection extends Exception {

    public EosKeyExpection(String s) {
        super(s);
    }

    public EosKeyExpection(String s, Throwable throwable) {
        super(s, throwable);
    }
}