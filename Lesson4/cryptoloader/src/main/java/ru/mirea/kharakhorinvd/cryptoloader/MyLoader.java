package ru.mirea.kharakhorinvd.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {

    public static final String ARG_WORD = "word";

    private final byte[] encryptedMessage;
    private final byte[] keyBytes;
    public static final String ARG_ENCRYPTED_MESSAGE = "encrypted_message";
    public static final String ARG_KEY_BYTES = "key_bytes";

    public MyLoader(@NonNull Context context, byte[] encryptedMessage, byte[] keyBytes) {
        super(context);
        this.encryptedMessage = encryptedMessage;
        this.keyBytes = keyBytes;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Log.d("MyLoader", "loadInBackground() called");
        if (encryptedMessage != null && keyBytes != null) {
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            return decryptMsg(encryptedMessage, key);
        }
        return null;
    }

    private String decryptMsg(byte[] cipherText, SecretKey secret) {
        /* Decrypt the message */
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
