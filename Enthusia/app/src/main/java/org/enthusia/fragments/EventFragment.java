package org.enthusia.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;

import org.enthusia.R;

public class EventFragment extends android.support.v4.app.Fragment  {

    Handler handler = new Handler();

    public EventFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_events, container, false);
        int images[] = new int[]{R.drawable.football, R.drawable.basketball, R.drawable.cricket
                , R.drawable.badminton, R.drawable.chess, R.drawable.table_tennis, R.drawable.kabaddi,
                R.drawable.tennis, R.drawable.aquatics, R.drawable.carrom, R.drawable.throwball, R.drawable.rink_football,
                R.drawable.athletics, R.drawable.volleyball, R.drawable.kho_kho, R.drawable.mnc};
        ImageView main_image = (ImageView) view.findViewById(R.id.main_image);
        Glide.with(getActivity())
                .load(R.drawable.stadium12)
                .crossFade()
                .fitCenter()
                .into(main_image);
        final int id[] = new int[]{R.id.events_football_foldingcell,
                R.id.events_basketball_foldingcell,
                R.id.events_cricket_foldingcell,
                R.id.events_badminton_foldingcell,
                R.id.events_chess_foldingcell,
                R.id.events_tabletennis_foldingcell,
                R.id.events_kabaddi_foldingcell,
                R.id.events_tennis_foldingcell,
                R.id.events_aquatics_foldingcell,
                R.id.events_carrom_foldingcell,
                R.id.events_throwball_foldingcell,
                R.id.events_rinkfootball_foldingcell,
                R.id.events_athletics_foldingcell,
                R.id.events_volleyball_foldingcell,
                R.id.events_khokho_foldingcell,
                R.id.events_mnc_foldingcell};
        int[] imageId = new int[]{R.id.events_football_image,
                R.id.events_basketball_image,
                R.id.events_cricket_image,
                R.id.events_badminton_image,
                R.id.events_chess_image,
                R.id.events_tabletennis_image,
                R.id.events_kabaddi_image,
                R.id.events_tennis_image,
                R.id.events_aquatics_image,
                R.id.events_carrom_image,
                R.id.events_throwball_image,
                R.id.events_rinkfootball_image,
                R.id.events_athletics_image,
                R.id.events_volleyball_image,
                R.id.events_khokho_image,
                R.id.events_mnc_image};
        final View[] views = new View[16];
        for(int i=0; i<id.length; i++){
            views[i] = view.findViewById(id[i]);
            final int finalI = i;
            ImageView imageView = (ImageView) view.findViewById(imageId[i]);
            Glide.with(getActivity())
                    .load(images[i])
                    .crossFade()
                    .fitCenter()
                    .into(imageView);
            views[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j=0; j<id.length; j++){
                        try{
                            if(((FoldingCell)views[j]).isUnfolded()){
                                ((FoldingCell)views[j]).toggle(false);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }try{
                        ((FoldingCell)views[finalI]).toggle(false);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
//        for(int i=0; i<5; i++){
//            views[i].setTranslationY(2000f);
//            views[i].setVisibility(View.GONE);
//        }
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0; i<5; i++){
//                    final int finalI = i;
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            views[finalI].animate()
//                                    .translationY(0f)
//                                    .setInterpolator(new DecelerateInterpolator())
//                                    .setDuration(500)
//                                    .start();
//                            views[finalI].setVisibility(View.VISIBLE);
//                        }
//                    }, i*200);
//                }
//            }
//        });
//        t.start();
        return view;
    }
}
