package com.example.sans.myapplication1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sans on 13/7/16.
 */

public class MovieAdapter extends BaseAdapter {

    Context context;
    public MovieAdapter(Context context, List<Movie> objects) {
        super(context,R.layout.list_row, objects);
        this.context=context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_row, parent,false);
        }
        TextView title=(TextView)convertView.findViewById(R.id.titleview);
        title.setText(movie.getTitle());

        return  convertView;


    }
}
