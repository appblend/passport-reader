package com.tananaev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tananaev.passportreader.MainActivity;
import com.tananaev.passportreader.R;
import com.tananaev.scanner.CaptureActivity;

import org.jmrtd.lds.icao.MRZInfo;

import static com.tananaev.scanner.CaptureActivity.MRZ_RESULT;

public class LaunchActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int MRZ_SCANNING = 1001;
    private static final String TAG = "LaunchActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        requestPermission();
        findViewById(R.id.bt_start_mrz_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LaunchActivity.this, CaptureActivity.class), MRZ_SCANNING);
            }
        });
        findViewById(R.id.bt_passport_reader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }
        });
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MRZ_SCANNING) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    MRZInfo mrzInfo = (MRZInfo) bundle.getSerializable(MRZ_RESULT);
                    Log.i(TAG, "Text read: " + mrzInfo.toString());

                    Intent nfcReaderIntent = new Intent(LaunchActivity.this, MainActivity.class);
                    Bundle nfcBundle = new Bundle();
                    nfcBundle.putSerializable(MRZ_RESULT, mrzInfo);
                    nfcReaderIntent.putExtras(bundle);


                    nfcReaderIntent.putExtra("dateOfBirth", mrzInfo.getDateOfBirth());
                    nfcReaderIntent.putExtra("dateOfExpiry", mrzInfo.getDateOfExpiry());
                    nfcReaderIntent.putExtra("passportNumber", mrzInfo.getDocumentNumber());
                    startActivity(nfcReaderIntent);
                } else {
                    Log.e(TAG, "No Text captured, intent data is null");
                }
            } else {
                Log.e(TAG, "No Text captured, intent data is null");
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}