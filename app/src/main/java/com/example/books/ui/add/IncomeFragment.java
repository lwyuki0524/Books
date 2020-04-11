package com.example.books.ui.add;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.books.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import sqlite.MyDBHelper;

public class IncomeFragment extends Fragment {
    private IncomeViewModel incomeViewModel;
    private String img="salary";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        incomeViewModel = ViewModelProviders.of(this).get(IncomeViewModel.class);
        final View root = inflater.inflate(R.layout.income, container, false);

        Button complete2 = (Button)root.findViewById(R.id.complete2);
        Button expenses = (Button)root.findViewById(R.id.expenses);

        final ImageView category_img=(ImageView)root.findViewById(R.id.select_category);   //取得上方顯示的收入類別圖示
        category_img.setImageDrawable(getResources().getDrawable(R.drawable.salary));   //預設是工資圖示

        Button salary=(Button)root.findViewById(R.id.btn_salary);
        Button red_envelopes=(Button)root.findViewById(R.id.btn_red_envelopes);
        Button part_time=(Button)root.findViewById(R.id.btn_part_time);
        Button investment=(Button)root.findViewById(R.id.btn_investment);

        Button bonus=(Button)root.findViewById(R.id.btn_bonus);
        ImageView withdrawal=(ImageView)root.findViewById(R.id.btn_withdrawal);
        ImageView stocks=(ImageView)root.findViewById(R.id.btn_stocks);
        ImageView others=(ImageView)root.findViewById(R.id.btn_others);

        TextView tv_salary=(TextView)root.findViewById(R.id.tv_salary);
        TextView tv_red_envelopes=(TextView)root.findViewById(R.id.tv_red_envelopes);
        TextView tv_part_time=(TextView)root.findViewById(R.id.tv_part_time);
        TextView tv_investment=(TextView)root.findViewById(R.id.tv_investment);
        TextView tv_bonus=(TextView)root.findViewById(R.id.tv_bonus);
        TextView tv_withdrawal=(TextView)root.findViewById(R.id.tv_withdrawal);
        TextView tv_stocks=(TextView)root.findViewById(R.id.tv_stocks);
        TextView tv_others=(TextView)root.findViewById(R.id.tv_others);


        salary.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("salary");
            }
        });

        red_envelopes.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("red_envelopes");
            }
        });

        part_time.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("part_time");
            }
        });

        investment.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("investment");
            }
        });

        bonus.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("bonus");
            }
        });

        withdrawal.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("withdrawal");
            }
        });

        stocks.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("stocks");
            }
        });

        others.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("others");
            }
        });


        tv_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("salary");

            }
        });

        tv_red_envelopes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("red_envelopes");
            }
        });

        tv_part_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("part_time");
            }
        });

        tv_investment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("investment");
            }
        });

        tv_bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("bonus");
            }
        });

        tv_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("withdrawal");
            }
        });

        tv_stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("stocks");
            }
        });

        tv_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("others");
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {//按下支出按鈕
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_income_to_navigation_add);
            }
        });

        //按下完成按鈕，存資料
        complete2.setOnClickListener(new View.OnClickListener() {
            MyDBHelper mydbHelper = new MyDBHelper(getActivity());
            String activity_type="",date = "",time="";
            int money=0;

            SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date today = new Date();
            //format()方法将Date转换成指定格式的String
            String dateStr = simpleDateFormat_date.format(today);

            String s_date=dateStr.substring(0,10);
            String s_now=dateStr.substring(11,19);

            @Override
            public void onClick(View view) {
                //取得上方顯示的收入類別
                TextView category_name=(TextView)root.findViewById(R.id.select_category_name);
                //取得輸入的金額
                EditText input_meney=(EditText)root.findViewById(R.id.input);

                //若使用者未輸入金額 或輸入0 或不是整數
                if(input_meney.getText().toString().equals("")||Integer.valueOf(String.valueOf(input_meney.getText()))==0){
                    Toast toast = Toast.makeText(
                            getActivity().getApplicationContext(), "請輸入金額", Toast.LENGTH_SHORT
                    );
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else{
                    //將金額轉為整數
                    int earn_money=Integer.valueOf(String.valueOf(input_meney.getText()));

                    //改這邊資料可以存到資料庫
                    activity_type= category_name.getText().toString();
                    date = s_date;
                    time=s_now;
                    money=earn_money;

                    //插入資料到資料庫
                    mydbHelper.insertData(activity_type,"收入",money,date,time,img);

                    Toast toast = Toast.makeText(
                            getActivity().getApplicationContext(), "新增成功", Toast.LENGTH_SHORT
                    );
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                    //回主頁
                    Navigation.findNavController(view).navigate(R.id.action_navigation_income_to_navigation_home);
                }

            }
        });



        incomeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;

    }

    //變換上方的圖形與文字
    private void show_category(String name) {
        //取得上方顯示的收入類別
        TextView category_name=(TextView)getView().findViewById(R.id.select_category_name);
        //取得上方顯示的收入類別圖示
        ImageView category_img=(ImageView)getView().findViewById(R.id.select_category);

        switch (name){
            case "salary":
                category_name.setText("工資");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.salary));
                img = "salary";
                break;

            case "red_envelopes":
                category_name.setText("紅包");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.envelope));
                img = "envelope";
                break;

            case "part_time":
                category_name.setText("兼職");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.parttme));
                img = "parttme";
                break;

            case "investment":
                category_name.setText("投資");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.investment));
                img = "investment";
                break;

            case "bonus":
                category_name.setText("獎金");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.bonus));
                img = "bonus";
                break;

            case "withdrawal":
                category_name.setText("提款");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.withdraw));
                img = "withdraw";
                break;

            case "stocks":
                category_name.setText("股票");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.stock));
                img = "stock";
                break;

            case "others":
                category_name.setText("其他");    //顯示收入類型
                //顯示收入類型圖示
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.coins));
                img = "coins";
                break;

        }

    }
}
