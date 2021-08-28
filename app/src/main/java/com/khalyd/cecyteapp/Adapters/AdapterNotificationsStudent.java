package com.khalyd.cecyteapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalyd.cecyteapp.Models.Posts;
import com.khalyd.cecyteapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class AdapterNotificationsStudent extends RecyclerView.Adapter<AdapterNotificationsStudent.ViewHolder> {

    List<Posts> post;




    public AdapterNotificationsStudent(List<Posts> post) {
        this.post = post;
    }

    //Aqui puedo pedir el layout

    private Context context;


    //Puedo buscar por medio de Perfil =  Estudiante


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        int layout = 0;

        layout = R.layout.model_notifications_recycler_student;

        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

        ViewHolder viewHolder  = new ViewHolder(v);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String ip = context.getString(R.string.ip);
        //TODO checar el diseño y poner los datos restantes

        holder.title.setText(post.get(position).getTitle());
        holder.txtDescription.setText(post.get(position).getDescription());
        holder.nameDirectivo.setText(post.get(position).getNameDirectivo());
        holder.positionDirectivo.setText(post.get(position).getPosition());

        /*
        String profil =  post.get(position).getProfile();
        switch (profil){
            case "10":
                holder.info.setImageResource(R.drawable.ic_all_profiles);
                break;
        }*/

        if (post.get(position).getProfile().equals("10")){
            holder.info.setImageResource(R.drawable.ic_all_profiles);
        }else {

            holder.info.setImageDrawable(null);
        }


        if(!post.get(position).getUrlDirectivo().equals("")){


            String urlDirectivo = ip + "/AcitivitiesMain/AllImages/" + post.get(position).getUrlDirectivo();
            Glide.with(context).load(urlDirectivo).into(holder.profileImage);

        }else {

            String url = context.getString(R.string.img_noImageProfile);
            Glide.with(context).load(url).into(holder.profileImage);

        }

        String url = ip + "/AcitivitiesMain/IntoAppDirectivo/" + post.get(position).getUrl(); //Pic about post
        Glide.with(context).load(url).into(holder.imgReference);

        //(TODO IMPORTANTE) Al parecer si le pongo condicionales no se cargan bien los datos si pongo alguna condicional debo
        //verificar TODO que funcione correctamente AL PARECER SI EMPIEZO CON UN IF DEBE USAR EL ELSE Y NO USAR SWITCH O EN SU CASO USAR EL DEFAULT:
        holder.datepost.setText(post.get(position).getDatepots());




    }

    @Override
    public int getItemCount() {
        return post.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription,nameDirectivo,positionDirectivo,title;
        ImageView imgReference,info;
        CircleImageView profileImage;
        //Add after
        TextView datepost;

        public ViewHolder(View itemView) {
            super(itemView);

                txtDescription = itemView.findViewById(R.id.description);
                profileImage = itemView.findViewById(R.id.headProfile);
                nameDirectivo = itemView.findViewById(R.id.nameDirectivoPost);
                positionDirectivo = itemView.findViewById(R.id.position);
                title = itemView.findViewById(R.id.titleDirectivo3);
                imgReference = itemView.findViewById(R.id.imageReference);
                info = itemView.findViewById(R.id.infoaboutPost);
                datepost = itemView.findViewById(R.id.datePOstFromDB);
        }
    }
}