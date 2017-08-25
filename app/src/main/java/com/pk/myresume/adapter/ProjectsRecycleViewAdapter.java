package com.pk.myresume.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.ProjectDetailActivity;
import com.pk.myresume.R;
import com.pk.myresume.model.Project;

import java.util.List;

/**
 * Created by Purvesh on 3/9/2017.
 */

public class ProjectsRecycleViewAdapter extends RecyclerView.Adapter<ProjectsRecycleViewAdapter.ProjectViewHolder> {

    List<Project> projects;
    Context context;

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView projectName;
        TextView projectType;
        ImageView projectPhoto;

        ProjectViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view);
            projectName = (TextView)itemView.findViewById(R.id.project_title);
            projectType = (TextView)itemView.findViewById(R.id.project_type);
            projectPhoto = (ImageView)itemView.findViewById(R.id.project_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Intent intent = new Intent(view.getContext(),ProjectDetailActivity.class);

                    intent.putExtra("projectName", projectName.getText());
                    intent.putExtra("position",position);

                    view.getContext().startActivity(intent);
                }

            });
        }
    }

   public ProjectsRecycleViewAdapter(List<Project> projects,Context context){
        this.projects = projects;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_project_list, viewGroup, false);
        ProjectViewHolder pvh = new ProjectViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder projectViewHolder, final int i) {

        projectViewHolder.projectName.setText(projects.get(i).getProjectName());
        projectViewHolder.projectType.setText(projects.get(i).getProjectType());
        projectViewHolder.projectPhoto.setImageResource(projects.get(i).getProjectPhoto());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
