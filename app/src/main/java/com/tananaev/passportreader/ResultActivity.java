/*
 * Copyright 2016 - 2017 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tananaev.passportreader;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_STATE = "state";
    public static final String KEY_NATIONALITY = "nationality";
    public static final String KEY_PHOTO = "photo";
    public static final String KEY_PHOTO_BASE64 = "photoBase64";
    public static final String KEY_PASSIVE_AUTH = "passiveAuth";
    public static final String KEY_CHIP_AUTH = "chipAuth";
    public static final String KEY_DOB = "date of birth";
    public static final String KEY_DATE_OF_EXPIRE = "expired date";
    public static final String KEY_PLACE_OF_BIRTH = "place_of_birth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ((TextView) findViewById(R.id.output_first_name)).setText(getIntent().getStringExtra(KEY_FIRST_NAME));
        ((TextView) findViewById(R.id.output_last_name)).setText(getIntent().getStringExtra(KEY_LAST_NAME));
        ((TextView) findViewById(R.id.output_gender)).setText(getIntent().getStringExtra(KEY_GENDER));
        ((TextView) findViewById(R.id.output_state)).setText(getIntent().getStringExtra(KEY_STATE));
        ((TextView) findViewById(R.id.output_nationality)).setText(getIntent().getStringExtra(KEY_NATIONALITY));
        ((TextView) findViewById(R.id.output_passive_auth)).setText(getIntent().getStringExtra(KEY_PASSIVE_AUTH));
        ((TextView) findViewById(R.id.output_chip_auth)).setText(getIntent().getStringExtra(KEY_CHIP_AUTH));
        ((TextView) findViewById(R.id.output_dob)).setText(getIntent().getStringExtra(KEY_DOB));
        ((TextView) findViewById(R.id.output_expired_date)).setText(getIntent().getStringExtra(KEY_DATE_OF_EXPIRE));
        ((TextView) findViewById(R.id.output_place_of_birth)).setText(getIntent().getStringExtra(KEY_PLACE_OF_BIRTH));

        if (getIntent().hasExtra(KEY_PHOTO)) {
            ((ImageView) findViewById(R.id.view_photo)).setImageBitmap((Bitmap) getIntent().getParcelableExtra(KEY_PHOTO));
        }
    }

}
