package co.kit.gfg.chatapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedbackFragment extends Fragment {
    // Declaring variables
    Slider ui, performance, build, connectivity, overall_experience;
    EditText name, email, suggestions;
    Button submit_button;
    FirebaseDatabase feedback_data;
    DatabaseReference reference;

    // Declaring variables to store data for slider and EditText
    float value_ui, value_performance, value_build, value_connectivity, value_overall_experience;
    String value_name, value_email, value_suggestions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment =  inflater.inflate(R.layout.fragment_feedback, container, false);

        // Assigning variables to respected views
        ui = myFragment.findViewById(R.id.slider_ui);
        performance = myFragment.findViewById(R.id.slider_performance);
        build = myFragment.findViewById(R.id.slider_build);
        connectivity = myFragment.findViewById(R.id.slider_connectivity);
        overall_experience = myFragment.findViewById(R.id.slider_overall_experience);
        name = myFragment.findViewById(R.id.editText_PersonName);
        email = myFragment.findViewById(R.id.editText_EmailAddress);
        suggestions = myFragment.findViewById(R.id.editText_Suggestions);
        submit_button = myFragment.findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Getting and storing data of slider and EditText
                    value_name = name.getText().toString();
                    value_email = email.getText().toString();
                    value_suggestions = suggestions.getText().toString();
                    value_ui = ui.getValue();
                    value_performance = performance.getValue();
                    value_build = build.getValue();
                    value_connectivity = connectivity.getValue();
                    value_overall_experience = overall_experience.getValue();

                    // Validating the text fields if empty or not
                    if (TextUtils.isEmpty(value_name)) {
                        name.setError("Please enter your name");
                    }
                    else if (TextUtils.isEmpty(value_email)) {
                        email.setError("Please enter your email");
                    }
                    else {
                        // Calling method to add data to Firebase
                        add_to_firebase(value_name, value_email, value_suggestions, value_ui, value_performance,
                                value_build, value_connectivity, value_overall_experience);
                    }
            }
        });

        return myFragment;
    }

    // Method for adding data to Firebase
    private void add_to_firebase(String value_name, String value_email, String value_suggestions,
                                 float value_ui, float value_performance, float value_build,
                                 float value_connectivity, float value_overall_experience) {

        // Storing data in an object
        FeedbackDataHolder obj = new FeedbackDataHolder(value_name, value_email, value_suggestions,
                value_ui, value_performance, value_build, value_connectivity, value_overall_experience);

        // Getting instance from Firebase (Root and Reference)
        feedback_data = FirebaseDatabase.getInstance();
        reference = feedback_data.getReference("Feedback");

        // Adding data to database
        reference.push().setValue(obj);

        // Clearing all the filled details in the views
        name.setText("");
        email.setText("");
        suggestions.setText("");
        ui.setValue(0f);
        performance.setValue(0f);
        build.setValue(0f);
        connectivity.setValue(0f);
        overall_experience.setValue(0f);

        // Toast message for data added to firebase
        Toast.makeText(getContext(), "Feedback submitted successfully", Toast.LENGTH_LONG).show();
    }
}