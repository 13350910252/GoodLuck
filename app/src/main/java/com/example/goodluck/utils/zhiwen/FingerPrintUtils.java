package com.example.goodluck.utils.zhiwen;

import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;

import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;

public class FingerPrintUtils {
    private static FingerPrintUtils instance;
    private final FingerprintManagerCompat compat;
    private static CancellationSignal cancelSignal;//取消
    private BiometricPrompt biometricPrompt;
    public FingerPrintUtils(Context context) {
        compat = FingerprintManagerCompat.from(context.getApplicationContext());
    }

    public static synchronized FingerPrintUtils instance(Context context) {
        if (instance == null) {
            synchronized (FingerPrintUtils.class) {
                if (instance == null) {
                    instance = new FingerPrintUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * @return 是否有指纹硬件
     * 1.检查手机硬件（有没有指纹感应区）
     * 2.系统版本是否大于Android6.0
     */
    public boolean hasFingerHard() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        if (compat == null) {
            return false;
        }
        return compat.isHardwareDetected();
    }

    /**
     * @return 是否有录入指纹
     */
    public boolean hasFingerInput() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return false;
        }
        if (compat == null) {
            return false;
        }
        return compat.hasEnrolledFingerprints();
    }

    /**
     * 创建指纹验证
     */
    public void authenticate(final FingerListener listener) {
        //必须重新实例化。cancel过，资源会被释放，
        cancelSignal = new CancellationSignal();
        compat.authenticate(null,//用于通过指纹验证取出AndroidKeyStore中key的值
                0,//系统建议为0,其他值，谷狗占位用于其他生物验证
                cancelSignal,//强制取消指纹验证
                new FingerprintManagerCompat.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errMsgId, CharSequence errString) {
                        super.onAuthenticationError(errMsgId, errString);
                        if (listener != null) {
                            listener.onResult(false, errString,true);
                        }

                    }

                    @Override
                    public void onAuthenticationFailed() {
                        //多次指纹验证错误后，回调此方法；
                        //并且，（第一次错误）由系统锁定30s
                        if (listener != null) {
                            listener.onResult(false, "指纹错误，请再试一次",false);
                        }
                    }

                    @Override
                    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        if (listener != null) {
                            listener.onResult(true, "验证成功",false);
                        }
                    }
                }, null);
    }

    public static void cancel() {
        if (cancelSignal != null) {
            cancelSignal.cancel();
        }
    }

    public interface FingerListener {
        void onResult(boolean success, CharSequence msg,boolean isLock);
    }
}
