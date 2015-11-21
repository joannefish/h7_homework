package com.example.test.tkuhw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.tkuhw.beans.MyDataResult;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by wenlin on 2015/11/21.
 */
public class Adapter extends BaseAdapter {

    private final List<MyDataResult.ResultItem> list;
    private Context context;
    private final LayoutInflater layoutInflater;

    public Adapter(Context context) {
        super();
        this.list = new ArrayList<>();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (this.list == null) ? 0 : this.list.size();
    }

    @Override
    public MyDataResult.ResultItem getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.selectdata, null);
        }

        final MyDataResult.ResultItem resultItem = this.getItem(position);

        ImageView landscapeImageView = (ImageView) convertView.findViewById(R.id.imgv_landscape);
        TextView parkNameText = (TextView) convertView.findViewById(R.id.text_park_name);
        TextView locationText = (TextView) convertView.findViewById(R.id.text_location_name);
        TextView areaText = (TextView) convertView.findViewById(R.id.text_administration);

        Picasso.with(convertView.getContext()).load(resultItem.getImage()).placeholder(R.drawable.ic_loading).into(landscapeImageView);

        parkNameText.setText( "公園名稱: "+resultItem.getParkName());
        areaText.setText("公園地區: "+resultItem.getAdministrativeArea());
        locationText.setText("公園地址: "+resultItem.getLocation());

        convertView.setTag(resultItem);

        return convertView;
    }

    public void clear() {
        this.list.clear();
    }

    public boolean addAll(List<MyDataResult.ResultItem> data) {
        return this.list.addAll(data);
    }
}
