package com.nwar.dsm.deanomoo_dsm.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    public CustomLinearLayoutManager(Context context){
        super(context);
    }
    private int[] array = new int[2];
    @Override
    public boolean canScrollVertically(){
        return false;
    }
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec){
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);

        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;
        for(int i=0; i<getItemCount(); i++){
            if(getOrientation()==HORIZONTAL){
                measureScrapChild(recycler,i,View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),heightSpec,array);
                width = width + array[0];
                if(i==0) height = array[1];
                else measureScrapChild(recycler,i,widthSpec,View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),array);
                height = height+array[1];
                if(i==0)width = array[0];
            }
        }
        switch (widthMode){
            case View.MeasureSpec.EXACTLY : width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        switch (heightMode){
            case View.MeasureSpec.EXACTLY:
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        setMeasuredDimension(width, height);
    }
    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[]measuredDimension){
        View view = recycler.getViewForPosition(position);
        if(view.getVisibility() == View.GONE){
            measuredDimension[0] = 0;
            measuredDimension[1] = 0;
            return;
        }
        super.measureChildWithMargins(view,0,0);
        RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
        int childWidthSpec = ViewGroup.getChildMeasureSpec(
                widthSpec,
                getPaddingLeft()+getPaddingRight()+getDecoratedLeft(view)+getDecoratedRight(view),
                p.width);
        int childHeightSpec = ViewGroup.getChildMeasureSpec(
                heightSpec,
                getPaddingTop()+getPaddingBottom()+getDecoratedTop(view)+getDecoratedBottom(view),
                p.height);
        view.measure(childWidthSpec,childHeightSpec);
        measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin+p.rightMargin;
        measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin+p.topMargin;
        recycler.recycleView(view);
    }
}
