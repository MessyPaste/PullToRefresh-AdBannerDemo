package com.liutaw.pulltorefreshadbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.liutaw.navigationbarlib.NaviBarBuilder;
import com.liutaw.pulltorefreshadbanner.banner.NetworkImageHolderView;
import com.liutaw.pulltorefreshadbanner.banner.RefreshCompleteCallBack;
import com.liutaw.pulltorefreshadbanner.data.DataProvider;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MainActivity extends AppCompatActivity implements RefreshCompleteCallBack {

    // use butterknife lib
    @Bind(R.id.navi_container)
    FrameLayout navi_container;
    @Bind(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.banner_ad_store)
    CusConvenientBanner convenientBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // use navigationbarlib
        NaviBarBuilder builder = new NaviBarBuilder(getApplicationContext());
        builder.buildCenterView().buildTitleText("PullToRefresh&AdBanner Demo");
        navi_container.addView(builder.build());

        //init pull to refresh
        initPullToRefresh();

        //init ad banner

    }

    private void initPullToRefresh() {
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }

    private void updateData() {
        refreshBannerImages();

    }

    private void refreshBannerImages() {
        //网络加载例子
        final List<String> networkImages = Arrays.asList(DataProvider.images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView(networkImages.size(), MainActivity.this);
            }
        }, networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused});
        convenientBanner.setParentView(mPtrFrame);
    }

    @Override
    public void refreshComplete() {
        //images loading complete
        mPtrFrame.refreshComplete();
    }
}
