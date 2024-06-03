package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> {

    private Context context ;
    private List<TripeTypes> items ;
    public MainPageAdapter(Context context ,List<TripeTypes> items){
        this.context=context;
        this.items=items;
    }
    @NonNull
    @Override
    public MainPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_main_page,
                parent,
                false);
        return new MainPageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageViewHolder holder, int position) {
    final TripeTypes tripeTypes =items.get(position);
    CardView cardView = holder.cardView ;
    holder.imgLogoMainPage.setImageResource(items.get(position).getLogoImage());
    holder.imgMainPage.setImageResource(items.get(position).getMainimage());
    holder.txtType.setText(items.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MainPageViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView ;
        private ImageView imgLogoMainPage ;
        private ImageView imgMainPage ;
        TextView txtType ;
        public MainPageViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView=  cardView;
            imgLogoMainPage=cardView.findViewById(R.id.imgLogoMainPage);
            imgMainPage=cardView.findViewById(R.id.imgMainPage);
            txtType=cardView.findViewById(R.id.txtType);
        }
    }
}
