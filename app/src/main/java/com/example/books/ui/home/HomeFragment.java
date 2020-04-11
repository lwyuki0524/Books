package com.example.books.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.books.MainActivity;
import com.example.books.R;

import java.util.ArrayList;
import java.util.List;

import HomeList.HomeListAdapter;
import HomeList.HomeListAdapterOld;
import HomeList.HomeList_card;
import sqlite.MyDBHelper;

public class HomeFragment extends ListFragment  {

    private HomeViewModel homeViewModel;
    private View root;//定義view用来設置fragment的layout
    private Cursor result;
    private String id_str,dateStr,actTypestr,money,typestr,imagestr;
    private List<HomeList_card> homelist = new ArrayList<HomeList_card>();
    private MyDBHelper mydbHelper;
    private List<String> ID = new ArrayList<String>();
    private List<String> Date = new ArrayList<String>();
    private List<String> Total = new ArrayList<String>();
    private List<String> ActType = new ArrayList<String>();
    private List<String> Type = new ArrayList<String>();
    private List<String> Money = new ArrayList<String>();
    private List<String> Icons = new ArrayList<String>();
    private ListView homeList;

    private int balanceInt=0,month_incomeInt=0,month_expencesInt=0;

    private HomeListAdapter adapter;
    String msg="";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new HomeListAdapter(ID,Date,Total,ActType,Money,Type,Icons);
        setListAdapter(adapter);
        homeList = getListView();
        homeList.setOnItemLongClickListener(homeListListerner);
    }

    private AdapterView.OnItemLongClickListener homeListListerner = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long l) {
            AlertDialog.Builder adBuild = new AlertDialog.Builder(getContext()){};
            adBuild.setTitle("刪除");
            adBuild.setMessage("是否要刪除此項目?");
            adBuild.setPositiveButton("刪除",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mydbHelper = new MyDBHelper(getActivity());
                    SQLiteDatabase db = mydbHelper.getReadableDatabase();//讀取資料庫
                    db.delete("accounting_TABLE", "id=?", new String[]{adapter.getId(position)});
                    Toast.makeText(getContext(),"已刪除",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    Navigation.findNavController(getView()).navigate(R.id.action_navigation_home_self);
                }
            });
            adBuild.setNegativeButton("否",null);
            adBuild.show();
            return false;
        }
    };
////    private AdapterView.OnItemClickListener homeListListerner = new AdapterView.OnItemClickListener() {
////        @Override
////        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////            msg="測試List"+i;
////            Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
////            AlertDialog.Builder adBuild = new AlertDialog.Builder(getContext()){};
////            adBuild.setTitle("刪除");
////            adBuild.setMessage("是否要刪除此項目?");
////            adBuild.setPositiveButton("刪除",aldBtListener);
////            adBuild.setNegativeButton("否",aldBtListener);
////            adBuild.show();
////        }
////    };
//
//    private DialogInterface.OnClickListener aldBtListener=new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            switch (i){
//                case -2:
//                    Toast.makeText(getContext(),"取消",Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home, container, false);
        initData();
        return root;
    }


    private void initData() {

        mydbHelper = new MyDBHelper(getActivity());
        SQLiteDatabase db = mydbHelper.getReadableDatabase();//讀取資料庫

        //SQL查詢語法
        String sql="SELECT id,date,time,money,activity_type,type,image FROM accounting_TABLE ORDER BY date||time DESC";

        result=db.rawQuery(sql,null);//執行SQL查詢
        int resultCounts=result.getCount();//取得資料總數
        if(resultCounts==0||!result.moveToFirst()){
            Toast.makeText(getActivity(),"資料庫無資料", Toast.LENGTH_SHORT).show();
            Log.v("DB", "讀取記錄失敗");

            LinearLayout list_outer = (LinearLayout) root.findViewById(R.id.list_outer);
            TextView nodata = new TextView(list_outer.getContext());
            nodata.setText("目前無資料");
            list_outer.addView(nodata);

        }
        else {
            Log.v("DB", "讀取中...");

            while (!result.isAfterLast()) {  //相當於rs.next()

                HomeList_card homelist_card=new HomeList_card();

                Log.v("DB", "讀取記錄成功");
                id_str = result.getString(result.getColumnIndex("id"));
                dateStr = result.getString(result.getColumnIndex("date"));
                dateStr += "  "+result.getString(result.getColumnIndex("time"));
                Log.v("date", dateStr);
                money = result.getString(result.getColumnIndex("money"));
                actTypestr = result.getString(result.getColumnIndex("activity_type"));
                typestr = result.getString(result.getColumnIndex("type"));
                imagestr = result.getString(result.getColumnIndex("image"));
                if(typestr.equals("支出")){
                    month_expencesInt += Integer.parseInt(money);

                }
                else {
                    month_incomeInt += Integer.parseInt(money);
                }

                ID.add(id_str);
                Date.add(dateStr);
                Total.add(money);
                ActType.add(actTypestr);
                Money.add(money);
                Type.add(typestr);
                Icons.add(imagestr);
                Log.v("money", money);

                result.moveToNext(); //移至下一筆
            }

            TextView month_income = (TextView)root.findViewById(R.id.month_income);
            TextView month_expences = (TextView)root.findViewById(R.id.month_expences);
            TextView balance = (TextView)root.findViewById(R.id.balance);

            if(month_incomeInt>month_expencesInt){
                balanceInt = month_incomeInt - month_expencesInt;
                balance.setText(String.valueOf(balanceInt));
            }
            else {
                balanceInt = month_expencesInt - month_incomeInt;
                balance.setText("-"+String.valueOf(balanceInt));
            }

            month_income.setText(String.valueOf(month_incomeInt));
            month_expences.setText(String.valueOf(month_expencesInt));

            result.close();
            db.close();
        }

    }

}