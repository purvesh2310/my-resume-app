package com.pk.myresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectDetailActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private TextView durationTextView;
    private TextView roleTextView;
    private ImageView projectImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.project_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String projectName = getIntent().getStringExtra("projectName");
        setTitle(projectName);

        int position = getIntent().getIntExtra("position",0);
        setInformationInProjectDetailView(position);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,NavigationDrawerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("projectFragment","1");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setInformationInProjectDetailView(int selectedProject){

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("projects.json",this);

        JSONObject projectObj = null;
        try {
            JSONArray projectArray = new JSONArray(jsonString);
            projectObj =(JSONObject) projectArray.get(selectedProject);

            String imageName = (String) projectObj.get("image");
            String description = (String) projectObj.get("description");
            String duration = (String) projectObj.get("duration");
            String role = (String) projectObj.get("role");

            int imageId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

            projectImageView = (ImageView) findViewById(R.id.project_image);
            descriptionTextView = (TextView) findViewById(R.id.project_description);
            durationTextView = (TextView) findViewById(R.id.duration_value);
            roleTextView = (TextView) findViewById(R.id.projectRole_value);

            projectImageView.setImageResource(imageId);
            descriptionTextView.setText(description);
            durationTextView.setText(duration);
            roleTextView.setText(role);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
