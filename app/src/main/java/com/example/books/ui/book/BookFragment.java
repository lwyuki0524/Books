package com.example.books.ui.book;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import com.example.books.R;
import java.util.ArrayList;
import java.util.List;

import HomeList.HomeList_card;
import sqlite.MyDBHelper;

public class BookFragment extends ListFragment {

    private View root;//定义view用来設置fragment的layout
    private Cursor result,result2;
    private String dateStr,actTypestr,money,typestr,imagestr;
    private List<HomeList_card> homelist = new ArrayList<HomeList_card>();
    private MyDBHelper mydbHelper;
    private List<String> Total = new ArrayList<String>();
    private List<String> ActType = new ArrayList<String>();
    private List<String> TypeIcon = new ArrayList<String>();
    private List<String> Type = new ArrayList<String>();
    private boolean state = true;//預設介面為總支出

    private int balanceInt=0,month_incomeInt=0,month_expencesInt=0;

    private BookListAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new BookListAdapter(Total,ActType,TypeIcon,Type);
        setListAdapter(adapter);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.book, container, false);
        initData();
        return root;
    }

    private void initData() {

        final TextView balance = (TextView)root.findViewById(R.id.balance);
        final TextView month = (TextView)root.findViewById(R.id.month);

        final Button income=(Button)root.findViewById(R.id.income);
        final Button expenses=(Button)root.findViewById(R.id.expenses);

        mydbHelper = new MyDBHelper(getActivity());
        SQLiteDatabase db = mydbHelper.getReadableDatabase();//讀取資料庫

        //SQL查詢語法
        String sql="SELECT *,SUM(money) FROM accounting_TABLE GROUP BY activity_type ORDER BY date||time DESC";
        result=db.rawQuery(sql,null);//執行SQL查詢
        int resultCounts=result.getCount();//取得資料總數
        if(resultCounts==0||!result.moveToFirst()){
            Toast.makeText(getActivity(),"資料庫無資料", Toast.LENGTH_SHORT).show();
            Log.v("DB", "讀取記錄失敗");

            balance.setText(" 目前無資料 ");
            expenses.setVisibility(View.INVISIBLE);
            income.setVisibility(View.INVISIBLE);
        }
        else {
            Log.v("DB", "讀取中...");

            while (!result.isAfterLast()) {  //相當於rs.next()

                Log.v("DB", "讀取記錄成功");
                dateStr = result.getString(result.getColumnIndex("date"));
                Log.v("date", dateStr);
                money = result.getString(result.getColumnIndex("SUM(money)"));
                actTypestr = result.getString(result.getColumnIndex("activity_type"));
                typestr = result.getString(result.getColumnIndex("type"));
                Log.v("type", typestr);
                imagestr = result.getString(result.getColumnIndex("image"));
                Log.v("image", imagestr);

                if(typestr.equals("支出")){
                    month_expencesInt += Integer.parseInt(money);

                }
                else {
                    month_incomeInt += Integer.parseInt(money);
                }

                Total.add(money);
                ActType.add(actTypestr);
                TypeIcon.add(imagestr);
                Type.add(typestr);
                Log.v("money", money);

                result.moveToNext(); //移至下一筆
            }

            final String monthstr = dateStr.substring(5,7);
            balance.setText(String.valueOf(month_expencesInt));
            month.setText("總支出");
            expenses.setBackground(getResources().getDrawable(R.drawable.book_type_btn_press));
            income.setBackground(getResources().getDrawable(R.drawable.book_type_btn));

            income.setOnClickListener(new View.OnClickListener() {//收入被按下
                @Override
                public void onClick(View view) {
                    balanceInt = month_incomeInt;
                    balance.setText(String.valueOf(balanceInt));
                    month.setText("總收入");
                    expenses.setBackground(getResources().getDrawable(R.drawable.book_type_btn));
                    income.setBackground(getResources().getDrawable(R.drawable.book_type_btn_press));
                }
            });

            expenses.setOnClickListener(new View.OnClickListener() {//支出被按下
                @Override
                public void onClick(View view) {
                    balanceInt = month_expencesInt;
                    balance.setText(String.valueOf(balanceInt));
                    month.setText("總支出");
                    expenses.setBackground(getResources().getDrawable(R.drawable.book_type_btn_press));
                    income.setBackground(getResources().getDrawable(R.drawable.book_type_btn));
                }
            });
            result.close();
            db.close();
        }
    }

    public static BookFragment newInstance(Boolean state) {
        Bundle args = new Bundle();
        BookFragment fragment = new BookFragment();
        args.putBoolean("state",state);
        fragment.setArguments(args);
        return fragment;
    }

}