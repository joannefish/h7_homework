package com.example.test.tkuhw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.tkuhw.beans.MyDataResult;
import com.google.gson.Gson;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {
    // UI Components
    private ProgressBar progressLoading;

    private Button jsonContentButton;
    private Button parsedContentButton;
    private Button listViewButton;

    private TextView jsonContentText;
    private TextView parsedContentText;

    // The working queue of Volley
    private RequestQueue requestQueue;

    // Other Variables
    private MyDataResult myDataResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.execute();
    }

    /*
     * Helper Methods
     */
    private void execute() {
        this.initApiRequestQueue();
        this.prepareUI();
        this.prepareEvents();
        this.invokeStringRequest();
    }

    private void initApiRequestQueue() {
        this.requestQueue = Volley.newRequestQueue(this);
    }

    private void prepareUI() {
        this.progressLoading = (ProgressBar) findViewById(R.id.progress_loading);
        this.jsonContentButton = (Button) findViewById(R.id.btn_json_content);
        this.jsonContentText = (TextView) findViewById(R.id.text_json_content);
        this.parsedContentButton = (Button) findViewById(R.id.btn_parsed_content);
        this.parsedContentText = (TextView) findViewById(R.id.text_parsed_content);
        this.listViewButton = (Button) findViewById(R.id.btn_list_view);
    }

    private void prepareEvents() {
        this.jsonContentButton.setOnClickListener(this.buttonOnClickListener);
        this.parsedContentButton.setOnClickListener(this.buttonOnClickListener);
        this.listViewButton.setOnClickListener(this.buttonOnClickListener);
    }

    /*
     * API Request Methods
     */
    private void invokeStringRequest() {
        String requestURL = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=8f6fcb24-290b-461d-9d34-72ed1b3f51f0&limit=10";

        final StringRequest getDataRequest = new StringRequest(
                Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Gson gson = new Gson();
                        myDataResult = gson.fromJson(response, MyDataResult.class);

                        String tmpResult = "";
                        for (MyDataResult.ResultItem resultItem : myDataResult.getResult().getResults()) {
                            tmpResult += "id: "+resultItem.get_id() + "\n" +"公園名稱: " +resultItem.getParkName() + "\n" +"公園地址: "+ resultItem.getLocation() + "\n\n";
                        }

                        jsonContentText.setText(response);
                        parsedContentText.setText(tmpResult);

                        progressLoading.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressLoading.setVisibility(View.GONE);
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressLoading.setVisibility(View.VISIBLE);
            }
        }).start();
        requestQueue.add(getDataRequest);
    }

    /*
     * Events
     */
    private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            this.determineButton(v.getId());
        }

        private void determineButton(int viewId) {
            switch (viewId) {
                case R.id.btn_json_content:
                    jsonContentText.setVisibility(View.VISIBLE);
                    parsedContentText.setVisibility(View.GONE);
                    break;

                case R.id.btn_parsed_content:
                    jsonContentText.setVisibility(View.GONE);
                    parsedContentText.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_list_view:
                    if (myDataResult != null) {
                        Intent intent = new Intent(MainActivity.this, Activity_2.class);
                        intent.putExtra("DATA", myDataResult.getResult());
                        startActivity(intent);
                    }
                    break;
            }
        }
    };
}
