package com.pk.myresume;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class CompanyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.company_detail_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);

        setInformationInCompanyDetailView(position);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,NavigationDrawerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("companyFragment","1");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Parses the company information from JSON file and displays in Company Detail View
     * @param selectedCompanyPosition
     */
    public void setInformationInCompanyDetailView(int selectedCompanyPosition){

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("experience.json",this);

        try{
            JSONArray companyArray = new JSONArray(jsonString);
            JSONObject companyObject = (JSONObject) companyArray.get(selectedCompanyPosition);

            String companyName = (String) companyObject.get("companyName");
            String location = (String) companyObject.get("location");
            String startYear = (String) companyObject.get("startYear");
            String endYear = (String) companyObject.get("endYear");
            String companyPosition = (String) companyObject.get("position");
            String description = (String) companyObject.get("description");
            String companyLogo = (String) companyObject.get("companyLogo");

            TextView companyLocation = (TextView) findViewById(R.id.company_location);
            TextView companyRole = (TextView) findViewById(R.id.company_role);
            TextView companyDuration = (TextView) findViewById(R.id.company_duration);
            TextView companyDescription = (TextView) findViewById(R.id.company_description);
            ImageView companyLogoImage = (ImageView) findViewById(R.id.company_logo);

            companyLocation.setText(location);
            companyDuration.setText(startYear + " - " + endYear);
            companyRole.setText(companyPosition);
            companyDescription.setText(description);

            int imageId = this.getResources().getIdentifier(companyLogo, "drawable", this.getPackageName());

            companyLogoImage.setImageResource(imageId);

            setTitle(companyName);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
