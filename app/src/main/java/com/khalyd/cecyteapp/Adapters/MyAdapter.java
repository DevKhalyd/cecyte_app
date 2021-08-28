package com.khalyd.cecyteapp.Adapters;


import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.khalyd.cecyteapp.Models.Materias;
import com.khalyd.cecyteapp.R;



import java.util.List;

//Para hacer un adapter se extiende de RecyclerView.Adapter<MyAdapter.ViewHolder>
//Una vez hecho eso nos ponememos sobre el error q nos salga y lo solucionamos con las soluciones de Android
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    //Nombramos lo q necesitemos

    private List <Materias> materias;
    private int layout;
    private OnItemClickListener itemClickListener;
    //Sobre el problema de Onitemclicklistener debo de solucionar el problema luego para que asi no me presente
    //Adelante como lo es La R. (Resources)
    private Context context;


    //Luego el constructos lleva el mismo nombre de la clalse

    public MyAdapter(List<Materias> materias, int layout, OnItemClickListener listener) {
        this.materias = materias;
        this.layout = layout;
        this.itemClickListener = listener;

    }
//Si hay errores en onCreate debe de ser porque debe decir MyAdapter.ViewHolder
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(materias.get(position), itemClickListener);

    /*    holder.imgSubject.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v ) {
             switch (position){
                  case position == 0:


            }
          }
       });*/
//Tambien este metodo tiene acceso a todos los views
    }




    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateCircula(viewHolder.itemView);
        }

    }


    public void animateCircula (View view){
        int centerX=  0;
        int centerY=  0;
        int Startadius= 0;
        int endRadius= Math.max(view.getWidth(),view.getHeight());
        Animator animator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(view,centerX,centerY,Startadius,endRadius);
        }
        view.setVisibility(View.VISIBLE);
        animator.setDuration(650);
        animator.start();
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSubject,txtteach;
        private ImageView imgSubject;
        //En esta clase de declara los q vamos a poner en los cardviews

        public ViewHolder(View itemView) {
            super(itemView);
            txtSubject =  itemView.findViewById(R.id.title1);
            txtteach =  itemView.findViewById(R.id.subtitle1);
            imgSubject =  itemView.findViewById(R.id.imgSubject);



        }
        public void bind (final Materias materias, final OnItemClickListener listener){
            txtSubject.setText(materias.getSubject());
            txtteach.setText(materias.getTeach());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(materias,getAdapterPosition());
                }
            });
        }
    }
    public  interface OnItemClickListener{
        void onItemClick(Materias materias,int position); //Esto es un metodo que sepuede llamar de caulquier dorma
        //LO DE AQUI ARRIBA ES EL METODO CON EL CUAL SE CONSIGUE ONITEMCLIK
        //Ahora entiendo al parecer con este metodo se consigue el nobre y la posicion tal como lo indica al inicio
        //del metodo wow :D
    }

}
