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

import com.bumptech.glide.Glide;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MainActivityTypeHolder> {

    private Context context ;
    private List<Trips> items ;
    SelectType listener ;

    public TypeAdapter(Context context , List<Trips> items){
        this.context=context;
        this.items=items;
    //    this.listener=listener;
    }
    @NonNull
    @Override
    public TypeAdapter.MainActivityTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_type_page,
                parent,
                false);
        return new TypeAdapter.MainActivityTypeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeAdapter.MainActivityTypeHolder holder, int position) {
        final Trips trips =items.get(position);
        CardView cardView = holder.cardView ;
        ImageView imgType = (ImageView) cardView.findViewById(R.id.imgType);
        Glide.with(context).load(trips.getImage()).into(imgType);
        TextView txtTripName =(TextView) cardView.findViewById(R.id.txtTripName);
        TextView txtLocationTypes=(TextView) cardView.findViewById(R.id.txtLocationTypes);
        txtTripName.setText(trips.getTitle());
        txtLocationTypes.setText(trips.getDestination());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onTypeClick(items.get(position));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MainActivityTypeHolder extends RecyclerView.ViewHolder {

        private CardView cardView ;

        public MainActivityTypeHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView=  cardView;

        }
    }
}


