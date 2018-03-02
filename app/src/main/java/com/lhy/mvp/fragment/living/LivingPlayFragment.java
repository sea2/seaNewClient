package com.lhy.mvp.fragment.living;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.lhy.mvp.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by admin on 2017/2/16.
 */

public class LivingPlayFragment extends BaseLivingPlayFragment implements SurfaceHolder.Callback, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnPreparedListener {
    boolean isLoadData;

    Unbinder unbinder;
    private SurfaceView sufaceView;
    private IjkMediaPlayer ijkMediaPlayer;
    private ImageView markIv;
    private LivingItems.DataBean.ItemsBean item;
    SpinKitView loadingView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        // 使用container的布局参数
        item = (LivingItems.DataBean.ItemsBean) getArguments().getSerializable("item");
        root = inflater.inflate(R.layout.fragment_living_play, container, false);
        sufaceView = (SurfaceView) root.findViewById(R.id.sufaceView);
        markIv = (ImageView) root.findViewById(R.id.markIv);
        CircleImageView headIv = (CircleImageView) root.findViewById(R.id.headIv);
        TextView niknameTv = (TextView) root.findViewById(R.id.nikeName1Tv);
        loadingView = (SpinKitView) root.findViewById(R.id.loading_view);
        Glide.with(a).load(item.getAvatar()).into(headIv);
        niknameTv.setText(item.getNickName());
        Glide.with(a).load(item.getPhoto()).placeholder(R.color.color_theme).into(markIv);
        log(item.getNickName());
        return root;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setOnCompletionListener(this);
        ijkMediaPlayer.setOnErrorListener(this);
        ijkMediaPlayer.setOnPreparedListener(this);
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.release();
            ijkMediaPlayer = null;
        }
        unbinder.unbind();
    }

    @Override
    public void initData() {
        super.initData();
        if (getUserVisibleHint() == true && !isLoadData) {
            // 加载数据。 第一页加载没有问题
            LivingItems.DataBean.ItemsBean item = (LivingItems.DataBean.ItemsBean) getArguments().getSerializable("item");
            startPlay(item.getStreamurl());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (root != null) {
            if (isVisibleToUser) {
                // 是开播放
                startPlay(item.getStreamurl());
            } else {
                // 停止播放
                showMark();
                stopPlay();

            }
        }
    }

    private void stopPlay() {
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.pause();
            ijkMediaPlayer.stop();
            ijkMediaPlayer.reset();
            sufaceView.getHolder().removeCallback(this);
        }
    }

    private void startPlay(String path) {
        showMark();
        sufaceView.getHolder().addCallback(this);

        ijkMediaPlayer.reset();
        try {
            ijkMediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.prepareAsync();

        onToDo(1);
    }

    private void showMark() {
        markIv.setVisibility(View.VISIBLE);

    }

    @Override
    public void initAdapter() {
        super.initAdapter();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (null != ijkMediaPlayer && !TextUtils.isEmpty(ijkMediaPlayer.getDataSource())) {
            ijkMediaPlayer.start();
        }
        Log.e("tag", item.getNickName() + "----->onCeate");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if (null != ijkMediaPlayer && ijkMediaPlayer.isPlaying()) {
            ijkMediaPlayer.pause();
            ijkMediaPlayer.stop();
        }
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        //完成
        toast("完成了");
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        // 完成了
        toast("失败了");
        return true;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        SurfaceHolder holder = sufaceView.getHolder();
        iMediaPlayer.setDisplay(holder);
        iMediaPlayer.start();
        hideMark();
    }


    private void hideMark() {

        ObjectAnimator animator = ObjectAnimator.ofFloat(markIv, "alpha", 1, 0f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(500);
        animator.start();

    }


    private OnFragmentInteractionListener mListener;


    public void onToDo(int type) {
        if (mListener != null) {
            mListener.onFragmentInteraction(type);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int type);
    }

}
