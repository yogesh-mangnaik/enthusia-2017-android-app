package org.enthusia.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.enthusia.Constants;
import org.enthusia.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    Context context;

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Constants.partners.length/2;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sponsors_item, viewGroup, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder imageViewHolder, final int i) {
        //title layout
        System.out.println((i + " : " + Constants.imageIds[2 * i] + " | link : "
                + Constants.links[2 * i] + " | Partner : " + Constants.partners[2*i]));
        imageViewHolder.title_layout.setVisibility(View.GONE);
        imageViewHolder.single_sponsor_layout.setVisibility(View.GONE);
        imageViewHolder.double_sponsor_layout.setVisibility(View.GONE);
        imageViewHolder.partner1.setVisibility(View.VISIBLE);
        imageViewHolder.partner2.setVisibility(View.VISIBLE);
        imageViewHolder.imageView1.setVisibility(View.VISIBLE);
        imageViewHolder.imageView2.setVisibility(View.VISIBLE);
        if(Constants.imageIds[2*i] == 0){
            imageViewHolder.title_layout.setVisibility(View.VISIBLE);
            imageViewHolder.title.setText(Constants.partners[2*i]);
        }
        else if( i == 0 || i == 1){
            imageViewHolder.single_sponsor_layout.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Constants.imageIds[2*i])
                    .fitCenter()
                    .crossFade()
                    .into(imageViewHolder.imageView);
            imageViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Constants.links[2*i].equals("")){
                        String url = Constants.links[2*i];
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    }
                }
            });
            imageViewHolder.partner.setText(Constants.partners[2*i]);
        }
        else{
            //double sponsor layout
            imageViewHolder.double_sponsor_layout.setVisibility(View.VISIBLE);
            if(Constants.imageIds[2*i] == 1){
                imageViewHolder.imageView1.setVisibility(View.GONE);
            }
            Glide.with(context)
                    .load(Constants.imageIds[2*i])
                    .fitCenter()
                    .crossFade()
                    .into(imageViewHolder.imageView1);
            if(Constants.imageIds[2*i+1] == 1){
                imageViewHolder.imageView2.setVisibility(View.GONE);
            }
            Glide.with(context)
                    .load(Constants.imageIds[2*i+1])
                    .fitCenter()
                    .crossFade()
                    .into(imageViewHolder.imageView2);
            imageViewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Constants.links[2*i].equals("")){
                        String url = Constants.links[2*i];
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    }

                }
            });
            imageViewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Constants.links[2*i+1].equals("")){
                        String url = Constants.links[2*i+1];
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    }
                }
            });
            if(Constants.partners[2*i].equals("")){
                imageViewHolder.partner1.setVisibility(View.GONE);
            }
            if(Constants.partners[2*i+1].equals("")){
                imageViewHolder.partner2.setVisibility(View.GONE);
            }
            imageViewHolder.partner1.setText(Constants.partners[2*i]);
            imageViewHolder.partner2.setText(Constants.partners[2*i+1]);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1, imageView2, imageView;
        TextView partner1, partner2, partner, title;
        LinearLayout double_sponsor_layout, single_sponsor_layout, title_layout;
        CardView cardView;
        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            imageView1 = (ImageView)itemView.findViewById(R.id.imageView1);
            imageView2 = (ImageView)itemView.findViewById(R.id.imageView2);
            partner = (TextView) itemView.findViewById(R.id.partner);
            partner1 = (TextView) itemView.findViewById(R.id.partner1);
            partner2 = (TextView) itemView.findViewById(R.id.partner2);
            title = (TextView) itemView.findViewById(R.id.title);
            double_sponsor_layout = (LinearLayout) itemView.findViewById(R.id.double_sponsor_layout);
            single_sponsor_layout = (LinearLayout) itemView.findViewById(R.id.single_sponsor_layout);
            title_layout = (LinearLayout) itemView.findViewById(R.id.title_layout);
            cardView = (CardView) itemView.findViewById(R.id.sponsor_card);
        }
    }
}