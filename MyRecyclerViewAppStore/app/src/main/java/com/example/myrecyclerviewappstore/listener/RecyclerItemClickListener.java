package com.example.myrecyclerviewappstore.listener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener mListener;
    GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        public void OnItemClick(View view, int position);
        public void OnLongItemClick(View view, int position);
    }

    /**Constructor Class**/
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){

        this.mListener = listener;

        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if(child != null && mListener != null){
                    mListener.OnLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }

                super.onLongPress(e);
            }

        });

    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        if(childView != null && mListener != null && mGestureDetector.onTouchEvent(motionEvent)){

            mListener.OnItemClick(childView, recyclerView.getChildAdapterPosition(childView));
            return true;

        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
