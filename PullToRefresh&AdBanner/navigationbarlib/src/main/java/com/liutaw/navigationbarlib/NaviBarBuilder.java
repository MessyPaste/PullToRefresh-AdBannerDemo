package com.liutaw.navigationbarlib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class NaviBarBuilder {
    private Context mContext;

    FrameLayout mLeftView;
    FrameLayout mCenterView;
    FrameLayout mRightView;

    private View wholeView;
    private boolean mIsCenterViewNeedCunstomed = false;
    private ViewItemBuilder mViewItemBuilder = new ViewItemBuilder();

    public NaviBarBuilder(Context context) {
        init(context, R.layout.navi_bar);
    }

    public NaviBarBuilder(Context context, boolean isCenterViewNeedCunstomed) {
        if (isCenterViewNeedCunstomed) {
            init(context, R.layout.navi_bar_cumstomview);
            return;
        }
        init(context, R.layout.navi_bar);
    }

    private void init(Context context, int layoutResId) {
        this.mContext = context;
        wholeView = LayoutInflater.from(this.mContext).inflate(layoutResId, null);
        mLeftView = (FrameLayout) wholeView.findViewById(R.id.framelayout_left);
        mCenterView = (FrameLayout) wholeView.findViewById(R.id.framelayout_center);
        mRightView = (FrameLayout) wholeView.findViewById(R.id.framelayout_right);

        mLeftView.removeAllViews();
        mCenterView.removeAllViews();
        mRightView.removeAllViews();
    }

    private FrameLayout currentBuildView = null;

    public ViewItemBuilder buildLeftView() {
        currentBuildView = mLeftView;
        return mViewItemBuilder;
    }

    public ViewItemBuilder buildCenterView() {
        currentBuildView = mCenterView;
        return mViewItemBuilder;
    }

    public ViewItemBuilder buildRightView() {
        currentBuildView = mRightView;
        return mViewItemBuilder;
    }

    public View build() {
        return wholeView;
    }

    public View getLeftView() {
        return mLeftView;
    }

    public View getCenterView() {
        return mCenterView;
    }

    public View getRightView() {
        return mRightView;
    }

    public View setOnLeftViewOnClickListener(View.OnClickListener onClickListener) {
        getLeftView().setOnClickListener(onClickListener);
        return getLeftView();
    }

    public View setOnCenterViewOnClickListener(View.OnClickListener onClickListener) {
        getCenterView().setOnClickListener(onClickListener);
        return getCenterView();
    }

    public View setOnRightViewOnClickListener(View.OnClickListener onClickListener) {
        getRightView().setOnClickListener(onClickListener);
        return getRightView();
    }

    private View getInflaterView(int layoutResId) {
        return LayoutInflater.from(mContext).inflate(layoutResId, null);
    }

    public class ViewItemBuilder {
        public NaviBarBuilder buildImg(int imgDrawableId) {
            View view = getInflaterView(R.layout.navi_sideimg_item);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_side_navi);
            imageView.setImageDrawable(mContext.getResources().getDrawable(imgDrawableId));

            currentBuildView.addView(view);
            currentBuildView = null;
            return NaviBarBuilder.this;
        }

        public NaviBarBuilder buildSideText(String text) {
            View view = getInflaterView(R.layout.navi_sidetext_item);
            TextView textView = (TextView) view.findViewById(R.id.text_titleside_navi);
            textView.setText(text);

            currentBuildView.addView(view);
            currentBuildView = null;
            return NaviBarBuilder.this;
        }

        public NaviBarBuilder buildTitleText(String text) {
            View titleView = getInflaterView(R.layout.navi_title_item);
            TextView textView = (TextView) titleView.findViewById(R.id.text_title_navi);
            textView.setText(text);

            currentBuildView.addView(titleView);
            currentBuildView = null;
            return NaviBarBuilder.this;
        }

        public NaviBarBuilder buildCustomView(View view) {
            currentBuildView.addView(view);
            currentBuildView = null;
            return NaviBarBuilder.this;
        }
    }

}
