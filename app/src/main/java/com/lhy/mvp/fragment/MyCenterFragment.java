package com.lhy.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhy.mvp.R;
import com.lhy.mvp.bean.MyItemInfo;
import com.lhy.mvp.bean.MyItemOneInfo;
import com.lhy.mvp.module.base.BaseFragment;
import com.lhy.mvp.module.base.IRxBusPresenter;
import com.lhy.mvp.module.manage.download.DownloadActivity;
import com.lhy.mvp.module.manage.love.LoveActivity;
import com.lhy.mvp.module.manage.setting.SettingsActivity;
import com.lhy.mvp.utils.DefIconFactory;
import com.lhy.mvp.utils.ImageLoader;
import com.dl7.recycler.adapter.BaseMultiItemQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.helper.RecyclerViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCenterFragment extends BaseFragment<IRxBusPresenter> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MyCenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCenterFragment newInstance(String param1, String param2) {
        MyCenterFragment fragment = new MyCenterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {


        List<MyItemInfo> data = new ArrayList<>();

        MyItemInfo mMyItemInfo = new MyItemInfo(MyItemInfo.ITEM_TYPE_NORMAL);
        mMyItemInfo.setMyItemOneInfo(new MyItemOneInfo("设置", 1, "setting", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645609215&di=20bf9aa72a9deea7f31636d50529d904&imgtype=0&src=http%3A%2F%2Fcdns2.freepik.com%2Ffree-photo%2Fsettings-general_318-28232.jpg"));
        MyItemInfo mMyItemInfo1 = new MyItemInfo(MyItemInfo.ITEM_TYPE_EMPTY);
        mMyItemInfo1.setMyItemOneInfo(new MyItemOneInfo("空", 1, "1", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645609215&di=20bf9aa72a9deea7f31636d50529d904&imgtype=0&src=http%3A%2F%2Fcdns2.freepik.com%2Ffree-photo%2Fsettings-general_318-28232.jpg"));

        MyItemInfo mMyItemInfo2 = new MyItemInfo(MyItemInfo.ITEM_TYPE_NORMAL);
        mMyItemInfo2.setMyItemOneInfo(new MyItemOneInfo("我的收藏", 1, "collect", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645609215&di=20bf9aa72a9deea7f31636d50529d904&imgtype=0&src=http%3A%2F%2Fcdns2.freepik.com%2Ffree-photo%2Fsettings-general_318-28232.jpg"));

        MyItemInfo mMyItemInfo3 = new MyItemInfo(MyItemInfo.ITEM_TYPE_EMPTY);
        mMyItemInfo3.setMyItemOneInfo(new MyItemOneInfo("空", 1, "collect", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645609215&di=20bf9aa72a9deea7f31636d50529d904&imgtype=0&src=http%3A%2F%2Fcdns2.freepik.com%2Ffree-photo%2Fsettings-general_318-28232.jpg"));

        MyItemInfo mMyItemInfo4 = new MyItemInfo(MyItemInfo.ITEM_TYPE_NORMAL);
        mMyItemInfo4.setMyItemOneInfo(new MyItemOneInfo("我的下载", 1, "downloag", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512645609215&di=20bf9aa72a9deea7f31636d50529d904&imgtype=0&src=http%3A%2F%2Fcdns2.freepik.com%2Ffree-photo%2Fsettings-general_318-28232.jpg"));

        data.add(mMyItemInfo);
        data.add(mMyItemInfo1);
        data.add(mMyItemInfo2);
        data.add(mMyItemInfo3);
        data.add(mMyItemInfo4);
        MyMultiListAdapter mAdapter = new MyMultiListAdapter(mContext, data);
        SlideInRightAnimationAdapter animAdapter = new SlideInRightAnimationAdapter(mAdapter);
        RecyclerViewHelper.initRecyclerViewV(mContext, rvList, true, new AlphaInAnimationAdapter(animAdapter));

        View view = LayoutInflater.from(mContext).inflate(R.layout.my_center_head, null);
        mAdapter.addHeaderView(view);

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public class MyMultiListAdapter extends BaseMultiItemQuickAdapter<MyItemInfo> {

        public MyMultiListAdapter(Context context, List<MyItemInfo> data) {
            super(context, data);
        }

        public MyMultiListAdapter(Context context) {
            super(context);
        }

        @Override
        protected void attachItemType() {
            addItemType(MyItemInfo.ITEM_TYPE_EMPTY, R.layout.item_common_empty);
            addItemType(MyItemInfo.ITEM_TYPE_NORMAL, R.layout.item_common_goto);
        }

        @Override
        protected void convert(BaseViewHolder holder, MyItemInfo item) {
            switch (item.getType()) {
                case MyItemInfo.ITEM_TYPE_NORMAL:
                    _handleNewsNormal(holder, item.getMyItemOneInfo());
                    break;
                case MyItemInfo.ITEM_TYPE_EMPTY:
                    _handleNewsPhotoSet(holder, item.getMyItemOneInfo());
                    break;
            }
        }

        /**
         * 处理设置中的跳转
         *
         * @param holder
         * @param item
         */

        private void _handleNewsNormal(final BaseViewHolder holder, final MyItemOneInfo item) {
            if (item != null) {
                TextView tv_titleinfo = holder.getView(R.id.tv_titleinfo);
                tv_titleinfo.setText(item.getTitle());
                ImageView newsIcon = holder.getView(R.id.iv_icon);
                ImageLoader.loadCenterCrop(mContext, item.getImgsrc(), newsIcon, DefIconFactory.provideIcon());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(item.getScheme().equals("setting")){
                            Intent it=new Intent(mContext, SettingsActivity.class);
                            startActivity(it);
                        }else    if(item.getScheme().equals("collect")){
                            Intent it=new Intent(mContext, LoveActivity.class);
                            startActivity(it);
                        }else    if(item.getScheme().equals("downloag")){
                            Intent it=new Intent(mContext, DownloadActivity.class);
                            startActivity(it);
                        }
                    }
                });
            }
        }

        /**
         * 处理空位置
         *
         * @param holder
         * @param item
         */

        private void _handleNewsPhotoSet(BaseViewHolder holder, final MyItemOneInfo item) {
            if (item != null) {
                TextView tv_titleinfo = holder.getView(R.id.tv_titleinfo);
               // tv_titleinfo.setText(item.getTitle());
            }
        }
    }


}
