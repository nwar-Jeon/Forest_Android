package com.nwar.dsm.deanomoo_dsm.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.DataModule.ReplyComment
import com.nwar.dsm.deanomoo_dsm.R

class CommentAdapter (val context: Context, val items : ArrayList<Comment>?) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        Log.e("comment", "onCreateViewHolder")
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item,p0,false))
        return view
    }

    override fun getItemCount(): Int {
        if(items!=null)return items.size
        else return 0
    }

    override fun onBindViewHolder(p0: CommentAdapter.ViewHolder, p1: Int) {
        if(items!=null){
            p0.setRecyclerView(items[p1].replyCommentList)
            p0.bind(items[p1],context)
        }
        Log.e("comment", "onBindViewHolder")
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.comment_name_tv)
        private val content = view.findViewById<TextView>(R.id.comment_content_tv)
        private val recyclerView = view.findViewById<RecyclerView>(R.id.list_reply_list)
        fun bind(posterInfo : Comment, context: Context){
            Log.e("comment", "bind()")
            name.text = posterInfo.name
            content.text = posterInfo.content
        }
        fun setRecyclerView(replyCommentList: ArrayList<ReplyComment>?){
            Log.e("setRecyclerView", "진입..")
            val adapter = ReplyCommentAdapter(context, replyCommentList)
            val lm = CustomLinearLayoutManager(context)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = lm
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.setHasFixedSize(false)
            Log.e("SetRecyclerView","끝")
        }
    }
}