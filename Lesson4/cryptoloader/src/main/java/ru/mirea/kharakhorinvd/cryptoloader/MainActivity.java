package ru.mirea.kharakhorinvd.cryptoloader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import ru.mirea.kharakhorinvd.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private ActivityMainBinding binding;
    private final int LoaderID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.editTextText.getText().toString();
                SecretKey key = generateKey();
                byte[] encryptedMessage = encryptMsg(text, key);
                Bundle bundle = new Bundle();
                bundle.putByteArray(MyLoader.ARG_ENCRYPTED_MESSAGE, encryptedMessage);
                bundle.putByteArray(MyLoader.ARG_KEY_BYTES, key.getEncoded());
                LoaderManager.getInstance(MainActivity.this).initLoader(LoaderID, bundle, MainActivity.this);
            }
        });
    }

//    public void onClickButton(View view) {
//        String text = binding.editTextText.getText().toString();
//        SecretKey key = generateKey();
//        byte[] encryptedMessage = encryptMsg(text, key);
//        Bundle bundle = new Bundle();
//        bundle.putByteArray(MyLoader.ARG_ENCRYPTED_MESSAGE, encryptedMessage);
//        bundle.putByteArray(MyLoader.ARG_KEY_BYTES, key.getEncoded());
//        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this);
//    }

    public static SecretKey generateKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(256);
            return kg.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encryptMsg(String message, SecretKey secret) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return cipher.doFinal(message.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (id == LoaderID) {
            return new MyLoader(this, args.getByteArray(MyLoader.ARG_ENCRYPTED_MESSAGE), args.getByteArray(MyLoader.ARG_KEY_BYTES));
        } else {
            throw new InvalidParameterException("Invalid loader id");
        }
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (loader.getId() == LoaderID) {
            Toast.makeText(this, "Decrypted message: " + data, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}