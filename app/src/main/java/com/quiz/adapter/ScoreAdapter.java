package com.quiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quiz.R;
import com.quiz.model.Quiz;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class ScoreAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> data;

    public ScoreAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_question, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            viewHolder.textQuestion.setText("Score: "+data.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.textQuestion)
        TextView textQuestion;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
