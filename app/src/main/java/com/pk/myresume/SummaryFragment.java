package com.pk.myresume;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pk.myresume.util.JSONUtil;

import org.json.JSONObject;

public class SummaryFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView userProfileImageView = (ImageView) view.findViewById(R.id.summary_user_dp);

        // Code for making image circular
        Resources res = getResources();
        Bitmap src = BitmapFactory.decodeResource(res, R.drawable.user_profile_pic);
        RoundedBitmapDrawable dr = RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
        dr.setCircular(true);
        userProfileImageView.setImageDrawable(dr);

        setInformationInSummaryFragment(view);

        getActivity().setTitle("Summary");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void setInformationInSummaryFragment(View view){

        JSONUtil utilObj = new JSONUtil();
        String jsonString = utilObj.loadJSONFromAsset("summary.json",getActivity());
        try {
            JSONObject json = new JSONObject(jsonString);
            String name = (String) json.get("name");
            String currentPosition = (String) json.get("current_position");
            String email = (String) json.get("email");
            String skype = (String) json.get("skype");
            String aboutMe = (String) json.get("summary");
            String mobileNumber = (String) json.get("mobile");

            TextView nameTextView = (TextView) view.findViewById(R.id.summary_user_name);
            TextView currentPositionTextView = (TextView) view.findViewById(R.id.summary_current_position);
            TextView aboutMeTextView = (TextView) view.findViewById(R.id.summary_aboutme);
            TextView emailTextView = (TextView) view.findViewById(R.id.summary_email);
            TextView skypeTextView = (TextView) view.findViewById(R.id.summary_im);
            TextView mobileTextView = (TextView) view.findViewById(R.id.summary_contact);

            nameTextView.setText(name);
            currentPositionTextView.setText(currentPosition);
            aboutMeTextView.setText(aboutMe);
            emailTextView.setText(email);
            skypeTextView.setText("Skype: " + skype);
            mobileTextView.setText(mobileNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
