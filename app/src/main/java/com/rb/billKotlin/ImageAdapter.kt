package com.rb.billKotlin

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class  ImageAdapter(arrayList: ArrayList<String>):RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    var images = arrayList

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var imageView: ImageView =itemView.findViewById(R.id.ivImage)
        var ivDelete: ImageView =itemView.findViewById(R.id.ivDelete)
    }

    override fun getItemCount(): Int {
        return images.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageURI(Uri.parse(images[position]));
        holder.ivDelete.setOnClickListener{v->
            images.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image,null))
    }

}
