package com.nwar.dsm.deanomoo_dsm.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nwar.dsm.deanomoo_dsm.DataModule.ReplyComment
import com.nwar.dsm.deanomoo_dsm.R

class ReplyCommentAdapter(val context: Context, val items : ArrayList<ReplyComment>?) : RecyclerView.Adapter<ReplyCommentAdapter.ViewHolder>(){

    private lateinit var view : ViewHolder

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.reply_comment_item,p0,false))
        return view
    }

    override fun getItemCount(): Int {
        if(items==null) return 0
        else return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if(items!=null) p0.bind(items[p1], context)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.reply_name_tv)
        private val content = view.findViewById<TextView>(R.id.reply_content_tv)
        fun bind(reCommentInfo : ReplyComment, context: Context){
            name.text = reCommentInfo.name
            content.text = reCommentInfo.content
        }
    }
}