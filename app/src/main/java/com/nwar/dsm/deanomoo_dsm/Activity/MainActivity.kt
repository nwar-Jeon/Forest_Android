package com.nwar.dsm.deanomoo_dsm.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.nwar.dsm.deanomoo_dsm.Adapter.PosterAdapter
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.DataModule.ReplyComment
import com.nwar.dsm.deanomoo_dsm.R
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection

class MainActivity : AppCompatActivity(), SwipyRefreshLayout.OnRefreshListener{

    private lateinit var commentList : ArrayList<Comment>        // 테스트용(댓글리스트)
    private lateinit var reCommentList : ArrayList<ReplyComment> // 테스트용(답글리스트)
    private var posterCount : Int = 30                           // 테스트용(게시물 개수)
    private val URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwkE2N1K1_lRv0G31xORCzPCU-MaCQa_QTL4IN06yMypcjRr_i"
    private var posterList = arrayListOf<Poster>()
    private lateinit var posterAdapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reCommentList = ArrayList()
        commentList = ArrayList()
        commentList.add(Comment("XXX","abcdefg",reCommentList))
        commentList.add(Comment("OOO", "1234567",reCommentList))
        reCommentList.add(ReplyComment("ㅇㅇㅇ","ㄱㄴㄷㄹㅁ"))

        setContentView(R.layout.activity_main)
        setEvent()
        setPosterList()
        posterAdapter = setRecyclerView()
    }

    private fun setEvent(){
        val swipeRefresh = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        swipeRefresh.setOnRefreshListener(this)

        val logo = findViewById<ImageView>(R.id.mainlogo)
        logo.setOnClickListener {
            val recyclerView = findViewById<RecyclerView>(R.id.list_poster_list)
            recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun setPosterList(){
        for (i in 1..10){
            if(posterCount<=0) break
            posterList.add(Poster(posterCount, "대마숲 $posterCount"+"번째", URL, commentList))
            posterCount--
        }
        posterList.add(Poster(0,"","",ArrayList()))
    }

    private fun setRecyclerView() : PosterAdapter {
        val adapter = PosterAdapter(this, posterList)
        Log.e("poster", "createAdapter")
        val lm = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.list_poster_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        Log.e("poster", "hasFixedSize")
        recyclerView.setHasFixedSize(true)
        return adapter
    }

    override fun onRefresh(direction : SwipyRefreshLayoutDirection) { // 스와이프 새로고침
        if(posterCount>0) {
            for(i in 0..4) {
                if(posterCount<=0) break
                posterList.removeAt(posterList.lastIndex)
                posterAdapter.addItem(Poster(posterCount, "대마 ${posterCount}번째 대마", URL, commentList))
                posterCount--
                posterList.add(Poster(0, "", "", ArrayList<Comment>()))
            }
        }
        else{
            Toast.makeText(this, "마지막 게시물입니다.",Toast.LENGTH_SHORT).show()
        }
        val swipe = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        swipe.isRefreshing = false
    }
}