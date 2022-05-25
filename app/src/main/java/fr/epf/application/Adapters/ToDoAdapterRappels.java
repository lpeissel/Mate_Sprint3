package fr.epf.application.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.epf.application.AddNewRappel;
import fr.epf.application.R;
import fr.epf.application.RappelsActivity;
import fr.epf.application.Utils.DatabaseHandler2;
import fr.epf.application.models.ToDoModel2;

public class ToDoAdapterRappels extends RecyclerView.Adapter<ToDoAdapterRappels.ViewHolder> {

    private List<ToDoModel2> rappelList;
    private DatabaseHandler2 db;
    private RappelsActivity activity;

    public ToDoAdapterRappels(DatabaseHandler2 db, RappelsActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rappel_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        db.openDatabase();

        final ToDoModel2 item = rappelList.get(position);
        holder.rappelText.setText(item.getRappelText());
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return rappelList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setRappels(List<ToDoModel2> rappelList) {
        this.rappelList = rappelList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ToDoModel2 item = rappelList.get(position);
        db.deleteRappel(item.getId());
        rappelList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel2 item = rappelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("rappelTitre", item.getRappelTitre());
        bundle.putString("rappelText", item.getRappelText());
        AddNewRappel fragment = new AddNewRappel();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewRappel.TAG);
    }

   public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rappelText;
        TextView rappelTitre;

        ViewHolder(View view) {
            super(view);
            rappelTitre = view.findViewById(R.id.RappelTitre);
            rappelText = view.findViewById(R.id.RappelText);
        }
    }
}
