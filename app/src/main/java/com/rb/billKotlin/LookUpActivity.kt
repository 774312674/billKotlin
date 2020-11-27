package com.rb.billKotlin

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rb.billKotlin.database.CheckDao
import com.rb.billKotlin.database.CheckTabBean
import kotlinx.android.synthetic.main.activity_look_up.*

/**
 * @author: changZePeng
 * @date: 2020/9/2
 */
class LookUpActivity :BaseActivity(){

    private lateinit var adapter: LookUpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter=LookUpAdapter(CheckDao(this).selectAll())
        recyclerView.adapter = adapter

        adapter.itemClick = object :LookUpAdapter.OnItemClickListener{
            override fun click(bean: CheckTabBean, position: Int) {
                var  intent = Intent(this@LookUpActivity,AddActivity::class.java)
                intent.putExtra("id",bean.id)
                startActivity(intent)
            }
        }
    }
}