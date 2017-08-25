package com.pk.myresume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.R;

import java.util.ArrayList;

import com.pk.myresume.model.University;

/**
 * Created by Purvesh on 3/8/2017.
 */

public class UniversityListViewAdapter extends ArrayAdapter<University> {

    private ArrayList<University> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView universityName;
        TextView duration;
        TextView specialization;
        TextView grade;
        ImageView logo;
    }

    public UniversityListViewAdapter(ArrayList<University> data, Context context) {
        super(context, R.layout.item_university_list, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        University dataModel = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_university_list, parent, false);

            viewHolder.universityName = (TextView) convertView.findViewById(R.id.uni_name);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.uni_duration);
            viewHolder.specialization = (TextView) convertView.findViewById(R.id.uni_major);
            viewHolder.grade = (TextView) convertView.findViewById(R.id.uni_grade);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.uni_logo);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.universityName.setText(dataModel.getName());
        viewHolder.duration.setText(dataModel.getStartYear() + " - " + dataModel.getEndYear());
        viewHolder.specialization.setText(dataModel.getSpecialization());
        viewHolder.grade.setText(dataModel.getGrade());
        viewHolder.logo.setImageResource(dataModel.getLogoId());

        return convertView;
    }
}
