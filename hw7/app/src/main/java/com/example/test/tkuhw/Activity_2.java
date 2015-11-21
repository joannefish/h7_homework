package com.example.test.tkuhw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.tkuhw.beans.MyDataResult;

public class Activity_2 extends AppCompatActivity {

    // UI Components
    private ListView contentListView;
    private Adapter resultItemBaseListAdapter;

    // Receive Data From Activity
    private MyDataResult.Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_2);
        this.execute();
    }

    /*
     * Helper Methods
     */
    private void execute() {
        this.receiveDataFromActivity();
        this.prepareUI();
        this.loadDataForListView();
        this.prepareEvents();
    }

    private void receiveDataFromActivity() {
        this.result = (MyDataResult.Result) this.getIntent().getExtras().get("DATA");
    }

    private void prepareUI() {
        this.contentListView = (ListView) findViewById(R.id.list_content);
        this.resultItemBaseListAdapter = new Adapter(this);
        this.contentListView.setAdapter(this.resultItemBaseListAdapter);
    }

    private void loadDataForListView() {
        this.resultItemBaseListAdapter.clear();
        this.resultItemBaseListAdapter.addAll(result.getResults());
        this.resultItemBaseListAdapter.notifyDataSetChanged();
    }

    private void prepareEvents() {
        this.contentListView.setOnItemClickListener(this.dataOnItemClickListener);
    }

    private AdapterView.OnItemClickListener dataOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            this.launchActivity(view);
        }

        private void launchActivity(View view) {
            MyDataResult.ResultItem resultItem = (MyDataResult.ResultItem) view.getTag();
            Intent intent = new Intent(Activity_2.this, Activity_3.class);
            intent.putExtra("DATA", resultItem);
            startActivity(intent);
        }
    };
}
