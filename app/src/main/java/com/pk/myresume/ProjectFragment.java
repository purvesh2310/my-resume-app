package com.pk.myresume;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pk.myresume.adapter.ProjectsRecycleViewAdapter;
import com.pk.myresume.model.Project;
import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Purvesh on 3/9/2017.
 */

public class ProjectFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Project> projects = null;

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("projects.json",getActivity());

        try {
            projects = new ArrayList<>();
            JSONArray projectArray = new JSONArray(jsonString);
            for (int i=0; i<projectArray.length(); i++) {
                JSONObject obj = (JSONObject) projectArray.get(i);

                String projectName = (String) obj.get("name");
                String projectType = (String) obj.get("type");
                String imageName = (String) obj.get("image");

                int imageId = getContext().getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());

                projects.add(new Project(projectName, projectType, imageId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        ProjectsRecycleViewAdapter adapter = new ProjectsRecycleViewAdapter(projects,getContext());
        rv.setAdapter(adapter);

        getActivity().setTitle("Projects");
    }
}
