package co.kit.gfg.chatapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.viewHolder> {

    ArrayList<DeveloperModel> developerList;
    Context context;
    DevClickHandler devClickHandler;

    public DevelopersAdapter(ArrayList<DeveloperModel> developerList, Context context, DevClickHandler devClickHandler) {
        this.developerList = developerList;
        this.context = context;
        this.devClickHandler = devClickHandler;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.developers_cardview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        DeveloperModel model = developerList.get(position);
        holder.image.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.position.setText(model.getPosition());
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, position;
        ImageView image;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.DeveloperName);
            position = itemView.findViewById(R.id.DeveloperEmail);
            image = itemView.findViewById(R.id.DeveloperImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                devClickHandler.OnClick(getAdapterPosition());
                                            }
                                        }
            );
        }

    }
}

