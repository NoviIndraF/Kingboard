package com.nifcompany.kingboard.adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nifcompany.kingboard.R;

public class AdapterRvTheme extends RecyclerView.Adapter<AdapterRvTheme.ListViewHolder> {


    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_theme, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //editor = sharedPreferences.edit();
        holder.imgItemTheme.getResources();

        Integer integer = (Integer) holder.imgItemTheme.getTag();


        //editor.putInt(THEME_KEY, integer).apply();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemTheme;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemTheme = itemView.findViewById(R.id.ImgItemTheme);
        }
    }
}
