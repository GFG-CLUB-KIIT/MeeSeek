package co.kit.gfg.chatapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DevelopersFragment extends Fragment implements DevClickHandler {
    RecyclerView developerRecyclerView;

    public DevelopersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View developerFragment = inflater.inflate(R.layout.fragment_developers, container, false);
        developerRecyclerView = developerFragment.findViewById(R.id.developers_recyclerView);

        ArrayList<DeveloperModel> developerModelArrayList = new ArrayList<>();
        developerModelArrayList.add(new DeveloperModel("Abhishek Dutt", "Position", R.drawable.ic_baseline_adb_24));
        developerModelArrayList.add(new DeveloperModel("Abhishek Samantaray", "Position", R.drawable.ic_baseline_adb_24));
        developerModelArrayList.add(new DeveloperModel("Nitish Kumar Sonthalia", "Position", R.drawable.ic_baseline_adb_24));
        developerModelArrayList.add(new DeveloperModel("Shishir Sharma", "Position", R.drawable.ic_baseline_adb_24));
        developerModelArrayList.add(new DeveloperModel("Shivam Srivastava", "Android Developer", R.drawable.developer_shivam));
        developerModelArrayList.add(new DeveloperModel("Sivnarayan Pal", "Position", R.drawable.ic_baseline_adb_24));

        DevelopersAdapter developersAdapter = new DevelopersAdapter(developerModelArrayList, getContext(), this);
        developerRecyclerView.setAdapter(developersAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        developerRecyclerView.setLayoutManager(linearLayoutManager);

        return developerFragment;
    }

    @Override
    public void OnClick(int position) {
        switch (position) {
                        case 0:
                            String link0 = "https://kiit.ac.in/";
                            Intent intent0 = new Intent(Intent.ACTION_VIEW);
                            intent0.setData(Uri.parse(link0));
                            startActivity(intent0);
                            break;

                        case 1:
                            String link1 = "https://google.co.in/";
                            Intent intent1 = new Intent(Intent.ACTION_VIEW);
                            intent1.setData(Uri.parse(link1));
                            startActivity(intent1);
                            break;

                        case 2:
                            String link2 = "https://facebook.com/";
                            Intent intent2 = new Intent(Intent.ACTION_VIEW);
                            intent2.setData(Uri.parse(link2));
                            startActivity(intent2);
                            break;

                        case 3:
                            String link3 = "https://youtube.com/";
                            Intent intent3 = new Intent(Intent.ACTION_VIEW);
                            intent3.setData(Uri.parse(link3));
                            startActivity(intent3);
                            break;

                        case 4:
                            String link4 = "https://www.linkedin.com/in/shivam-0/";
                            Intent intent4 = new Intent(Intent.ACTION_VIEW);
                            intent4.setData(Uri.parse(link4));
                            startActivity(intent4);
                            break;

                        case 5:
                            String link5 = "https://linkedin.com/";
                            Intent intent5 = new Intent(Intent.ACTION_VIEW);
                            intent5.setData(Uri.parse(link5));
                            startActivity(intent5);
                            break;

                        default:
                            // default
                    }
    }
}