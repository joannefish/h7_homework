package com.example.test.tkuhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.tkuhw.beans.MyDataResult;

import it.sephiroth.android.library.picasso.Picasso;

public class Activity_3 extends AppCompatActivity {

    private MyDataResult.ResultItem resultItem;

    private ImageView ImageView;
    private TextView parkNameText;
    private TextView locationText;
    private TextView administrationText;
    private TextView introText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_3);
        this.execute();
    }

    private void receiveDateFromActivity() {
        this.resultItem = (MyDataResult.ResultItem) this.getIntent().getExtras().get("DATA");
    }

    private void execute() {
        this.receiveDateFromActivity();
        this.prepareUI();
        this.showUI();
    }

    private void prepareUI() {
        this.ImageView = (ImageView) findViewById(R.id.img);
        this.parkNameText = (TextView) findViewById(R.id.text_park_name);
        this.locationText = (TextView) findViewById(R.id.text_location_name);
        this.administrationText = (TextView) findViewById(R.id.text_administration);
        this.introText = (TextView) findViewById(R.id.text_Introduction);
    }

    private void showUI() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams params = this.ImageView.getLayoutParams();
        params.height = width;
        this.ImageView.setLayoutParams(params);
        Picasso.with(this).load(this.resultItem.getImage()).placeholder(R.drawable.ic_loading).into(this.ImageView);

        this.parkNameText.setText("公園名稱: "+ resultItem.getParkName());
        this.locationText.setText("地址: "+ resultItem.getLocation());
        this.introText.setText("詳細介紹: " + resultItem.getIntroduction());
    }
}
