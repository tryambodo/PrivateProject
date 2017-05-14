package id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.DetailActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.Fragment3;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.model.Result;

/**
 * Created by ASUS TP 450 LDV on 5/13/2017.
 */
public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Result> mlist;
    Fragment3 fragment3;
    Context context;

    public UpcomingAdapter(Fragment3 fragment3, ArrayList<Result> mlist, Context context) {
        this.mlist = mlist;
        this.fragment3 = fragment3;
        this.context = context;
    }

    @Override
    public UpcomingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UpcomingAdapter.ViewHolder holder, int position) {
        final Result result = mlist.get(position);
        holder.tvName.setText(result.title);
        holder.tvDesc.setText(result.overview);
        image = url + result.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_error)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = result.id;
                Intent intent = new Intent(context, DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", result.title);
                intent.putExtra("poster_path", result.backdrop_path);
                intent.putExtra("description", result.overview);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
            cardView = (CardView) itemView.findViewById(R.id.CardView);
        }
    }
}
