package com.pk.myresume;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.util.JSONUtil;

import org.json.JSONObject;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            SkillsFragment.OnFragmentInteractionListener, SummaryFragment.OnFragmentInteractionListener{

    public Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUserInfoInNavigationDrawer();

        Intent intent = getIntent();

        String isBackPressedOnProjectDetail = intent.getStringExtra("projectFragment");
        String isBackPressedOnCompanyDetail = intent.getStringExtra("companyFragment");

        intent.removeExtra("projectFragment");
        intent.removeExtra("companyFragment");

        // Opens the project list view fragment if back button is pressed from the project detail view
        if(isBackPressedOnProjectDetail != null && isBackPressedOnProjectDetail.equals("1")) {
            fragment = new ProjectFragment();
        }

        // Opens the company list view fragment if back button is pressed from the company detail view
        else if (isBackPressedOnCompanyDetail != null && isBackPressedOnCompanyDetail.equals("1")){
            fragment = new ExperienceFragment();
        }

        // Opens the Summary Fragment as a first fragment when activity is presented
        else if(savedInstanceState == null) {
            fragment = new SummaryFragment();
            navigationView.getMenu().getItem(0).setChecked(true);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        fragment = null;
        if (id == R.id.nav_summary) {
            fragment = new SummaryFragment();
        } else if (id == R.id.nav_education) {
            fragment = new EducationFragment();
        } else if (id == R.id.nav_skills) {
            fragment = new SkillsFragment();
        } else if (id == R.id.nav_experience) {
            fragment = new ExperienceFragment();
        } else if (id == R.id.nav_project) {
            fragment = new ProjectFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Sets user information in the navigation drawer header
     */
    public void setUserInfoInNavigationDrawer(){

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("summary.json",this);

        try{
            JSONObject json = new JSONObject(jsonString);
            String name = (String) json.get("name");
            String email = (String) json.get("email");
            String profilePicName = (String) json.get("profilePic");

            NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
            View header = navView.getHeaderView(0);

            TextView nameTextView = (TextView) header.findViewById(R.id.menuUsername);
            TextView emailTextView = (TextView) header.findViewById(R.id.menuUserEmail);
            ImageView profilePic = (ImageView) header.findViewById(R.id.menuProfilePhoto);

            Resources res = this.getResources();

            int imageId = res.getIdentifier(profilePicName, "drawable", this.getPackageName());

            // For making the user display picture circular
            Bitmap src = BitmapFactory.decodeResource(res, imageId);
            RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
            dr.setCircular(true);
            profilePic.setImageDrawable(dr);

            nameTextView.setText(name);
            emailTextView.setText(email);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
