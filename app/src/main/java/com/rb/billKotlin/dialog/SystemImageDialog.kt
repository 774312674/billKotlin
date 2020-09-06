package com.rb.billKotlin.dialog

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.rb.billKotlin.R

/**
 * @author: changZePeng
 * @date: 2020/9/1
 */
class SystemImageDialog: DialogFragment(),View.OnClickListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_system_image,container)
        val tvPhoto= view.findViewById<TextView>(R.id.tvPhoto)
        val tvCamera= view.findViewById<TextView>(R.id.tvCamera)

        tvPhoto.setOnClickListener(this)
        tvCamera.setOnClickListener(this)

        return view
    }

    override fun onStart() {
        super.onStart()

        val window=dialog?.window
        window?.setBackgroundDrawableResource(R.color.transparent)
        window?.setLayout(-1, -2);

    }



    override fun onClick(v: View?) {
        if (v!=null){
            when(v.id){
                R.id.tvPhoto->
                    clickListener?.clickPhoto()
                R.id.tvCamera->
                    clickListener?.clickCamera()
            }
        }
    }

    var clickListener:ClickListener?=null


    interface ClickListener{
        fun clickPhoto()
        fun clickCamera()
    }


}