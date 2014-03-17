package com.example.mitsloan2;

import com.example.mitsloan2.adapters.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SpeakerDetailBioActivity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogue_speaker_detail);
 
        // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int image_id = i.getExtras().getInt("image_id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
 
        ImageView imageView = (ImageView) findViewById(R.id.list_image2);
        imageView.setImageResource(imageAdapter.mThumbIds[image_id]);
    }

}
