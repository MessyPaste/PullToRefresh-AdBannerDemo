package com.liutaw.pulltorefreshadbanner.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.liutaw.pulltorefreshadbanner.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class NetworkImageHolderView implements CBPageAdapter.Holder<String>{

    private ImageView imageView;
    private int mCount;
    private RefreshCompleteCallBack mCallBack;
    public NetworkImageHolderView(int count,RefreshCompleteCallBack callBack) {
        this.mCount = count;
        this.mCallBack = callBack;
    }
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, String data) {
        imageView.setImageResource(R.drawable.ic_default_adimage);
        ImageLoader.getInstance().displayImage(data,imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
                Toast.makeText(view.getContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
            }
        });
        if(position == mCount -1)
            mCallBack.refreshComplete();
    }
}
