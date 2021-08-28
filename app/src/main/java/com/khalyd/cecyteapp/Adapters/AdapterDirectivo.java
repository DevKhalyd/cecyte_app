package com.khalyd.cecyteapp.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterDirectivo extends RecyclerView.Adapter<AdapterDirectivo.ViewHolder>{

    List<Posts> post;

    public AdapterDirectivo(List<Posts> post) {
        this.post = post;
    }

    private Context context;

    @Override
    public AdapterDirectivo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Aqui se infla el layout

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_layout_directivo,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        context = parent.getContext();

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(AdapterDirectivo.ViewHolder holder, int position) {

        if (!post.get(position).getTitle().equals("")){ //Todos los pots deben de traer titulo

            holder.title.setText(post.get(position).getTitle());
            holder.txtDescription.setText(post.get(position).getDescription());
            holder.nameDirectivo.setText(post.get(position).getNameDirectivo());
            holder.positionDirectivo.setText(post.get(position).getPosition());

            String recive = post.get(position).getProfile();
            String giveme;

            switch (recive){

                case "10":
                    giveme = "Todos";
                    break;
                case "20":
                    giveme = "Profesores";
                    break;
                case "30":
                    giveme = "Estudiantes";
                    break;
                default:
                    giveme = "el grupo " + recive +" (Tutores)";
                    break;

            }
            holder.profileSeach.setText("Publicado para: " + giveme);


            //Aqui llegaran los perfiles



            holder.dateFromDB.setText(post.get(position).getDatepots());


            String ip = context.getString(R.string.ip);

            if (!post.get(position).getUrlDirectivo().equals("")){

                String urlDirectivo = ip + "/AcitivitiesMain/AllImages/" + post.get(position).getUrlDirectivo();
                Glide.with(context).load(urlDirectivo).into(holder.profileImage);

            }else{

                String url = context.getString(R.string.img_noImageProfile);
                Glide.with(context).load(url).into(holder.profileImage);
            }

            //  if (!post.get(position).getUrl().isEmpty()){
            String url = ip + "/AcitivitiesMain/IntoAppDirectivo/" + post.get(position).getUrl(); //Pic about post
            Glide.with(context).load(url).into(holder.imgReference);

        }else{



        }






    }

    @Override
    public int getItemCount() {
        return post.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDescription,nameDirectivo,positionDirectivo,title,profileSeach;
        ImageView imgReference;
        CircleImageView profileImage;
        // 18/03/19
        TextView dateFromDB;



        public ViewHolder(View itemView) {
            super(itemView);

            txtDescription = itemView.findViewById(R.id.descriptionD);
            profileImage = itemView.findViewById(R.id.headProfileD);
            nameDirectivo = itemView.findViewById(R.id.nameDirectivoPostD);
            positionDirectivo = itemView.findViewById(R.id.positionD);
            title = itemView.findViewById(R.id.titleDirectivo3D);
            imgReference = itemView.findViewById(R.id.imageReferenceD);
            profileSeach = itemView.findViewById(R.id.profileSelecteD);
            //18/03/19
            dateFromDB = itemView.findViewById(R.id.setDateFromDB);

        }
    }
}
