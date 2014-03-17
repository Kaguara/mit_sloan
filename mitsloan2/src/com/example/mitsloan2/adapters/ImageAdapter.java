package com.example.mitsloan2.adapters;

import com.example.mitsloan2.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context mContext;

	// Keep all Images in array
    public Integer[] mThumbIds = { R.drawable.access_bank_logo, 
    		R.drawable.agco_logo, 
    		R.drawable.chevron_logo,
    		R.drawable.coca_cola_logo,
    		R.drawable.corporate_council_on_africa,
    		R.drawable.africadotcom_logo_medium,
    		R.drawable.afrimind_logo,
    		R.drawable.aib_logo,
    		R.drawable.allafrica_logo,
    		R.drawable.applause_africa,
    		R.drawable.eft_logo,
    		R.drawable.mui_pr,
    		R.drawable.panatv_description,
    		R.drawable.rockmeafrica,
    		R.drawable.tech_moran,
    		R.drawable.ventures_africa_logo};
    
	// Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }
    
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumbIds[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
        return imageView;
	}

}
