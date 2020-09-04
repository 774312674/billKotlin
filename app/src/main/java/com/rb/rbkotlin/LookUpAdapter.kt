package com.rb.rbkotlin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rb.billKotlin.R
import com.rb.rbkotlin.database.CheckTabBean
import java.text.SimpleDateFormat
import java.util.*

class  LookUpAdapter(arrayList: ArrayList<CheckTabBean>):RecyclerView.Adapter<LookUpAdapter.ViewHolder>(){

    var checkTabBeans = arrayList

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var id: TextView =itemView.findViewById(R.id.tvId)
        var content: TextView =itemView.findViewById(R.id.tvContent)
        var price: TextView =itemView.findViewById(R.id.tvPrice)
        var time: TextView =itemView.findViewById(R.id.tvTime)
    }

    override fun getItemCount(): Int {
        return checkTabBeans.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = checkTabBeans[position].id.toString()
        holder.content.text = checkTabBeans[position].content
        var price = checkTabBeans[position].price
        holder.price.text = "$price ￥"

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val date = Date(checkTabBeans[position].createTime)
        val createTime = dateFormat.format(date)
        holder.time.text = "$createTime"

        holder.itemView.setOnClickListener{
            itemClick?.click(checkTabBeans[position],position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_look_up,parent,false))
    }

    var itemClick:OnItemClickListener?=null

    interface OnItemClickListener{
        fun click(bean:CheckTabBean,position: Int)
    }

}
