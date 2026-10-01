package vn.feylix.util;
import java.security.SecureRandom;
public final class OtpUtils_24162099 {
    public static final long VALID_MILLIS=5*60*1000L;
    private OtpUtils_24162099(){}
    public static String generate(){return String.format("%06d",new SecureRandom().nextInt(1_000_000));}
}
