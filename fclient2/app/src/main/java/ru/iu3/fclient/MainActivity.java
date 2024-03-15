package ru.iu3.fclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

import ru.iu3.fclient.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'fclient' library on application startup.
    static {
        System.loadLibrary("fclient");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        int num = initRng();
        byte[] key = randomBytes(10);

        String s = "super test text";
        byte[] testData = s.getBytes(StandardCharsets.UTF_8);

        byte[] encrypted = encrypt(key, testData);
        byte[] decrypted = encrypt(key, encrypted);

        String res = new String(decrypted, StandardCharsets.UTF_8);
        TextView tv = binding.sampleText;
        tv.setText(s);
    }

    /**
     * A native method that is implemented by the 'fclient' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static native byte[] encrypt(byte[] key,byte[] data);
    public static native byte[] decrypt(byte[] key, byte[] data);
}