package com.example.books.ui.add;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.example.books.R;
import java.text.SimpleDateFormat;
import java.util.Date;

import sqlite.MyDBHelper;

public class AddFragment extends Fragment {
//    private AddViewModel addViewModel;
    private String img="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        addViewModel =ViewModelProviders.of(this).get(AddViewModelAddViewModel.class);
        final View root = inflater.inflate(R.layout.add, container, false);

        Button complete = (Button)root.findViewById(R.id.complete); //完成的按鈕
        Button income = (Button)root.findViewById(R.id.income); //收入的按鈕

        final ImageView category_img=(ImageView)root.findViewById(R.id.select_category);   //取得上方顯示的收入類別圖示
        category_img.setImageDrawable(getResources().getDrawable(R.drawable.eat));   //預設是吃喝圖示

        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.eat)).getBitmap();

        img ="eat";

        income.setOnClickListener(new View.OnClickListener() {  //去收入頁面
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_navigation_add_to_navigation_income);
            }
        });

        Button diet=(Button)root.findViewById(R.id.btn_diet);
        Button dress=(Button)root.findViewById(R.id.btn_dress);
        Button souvenir=(Button)root.findViewById(R.id.btn_souvenir);
        Button online_shopping=(Button)root.findViewById(R.id.btn_online_shopping);
        Button airticket=(Button)root.findViewById(R.id.btn_airticket);
        Button entertainment=(Button)root.findViewById(R.id.btn_entertainment);
        Button telephone_bill=(Button)root.findViewById(R.id.btn_telephone_bill);
        Button medical=(Button)root.findViewById(R.id.btn_medical);
        Button fare=(Button)root.findViewById(R.id.btn_fare);
        Button water_electricity_bill=(Button)root.findViewById(R.id.btn_water_electricity_bill);
        Button gift=(Button)root.findViewById(R.id.btn_gift);
        Button book=(Button)root.findViewById(R.id.btn_book);

        TextView tv_diet=(TextView)root.findViewById(R.id.tv_diet);
        TextView tv_dress=(TextView)root.findViewById(R.id.tv_dress);
        TextView tv_souvenir=(TextView)root.findViewById(R.id.tv_souvenir);
        TextView tv_online_shopping=(TextView)root.findViewById(R.id.tv_online_shopping);
        TextView tv_airticket=(TextView)root.findViewById(R.id.tv_airticket);
        TextView tv_entertainment=(TextView)root.findViewById(R.id.tv_entertainment);
        TextView tv_telephone_bill=(TextView)root.findViewById(R.id.tv_telephone_bill);
        TextView tv_medical=(TextView)root.findViewById(R.id.tv_medical);
        TextView tv_fare=(TextView)root.findViewById(R.id.tv_fare);
        TextView tv_water_electricity_bill=(TextView)root.findViewById(R.id.tv_water_electricity_bill);
        TextView tv_gift=(TextView)root.findViewById(R.id.tv_gift);
        TextView tv_book=(TextView)root.findViewById(R.id.tv_book);


        diet.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("diet");

            }
        });

        dress.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("dress");

            }
        });

        souvenir.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("souvenir");
            }
        });

        online_shopping.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("online_shopping");
            }
        });

        airticket.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("airticket");
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("entertainment");
            }
        });

        telephone_bill.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("telephone_bill");
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("medical");
            }
        });

        fare.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("fare");
            }
        });

        water_electricity_bill.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("water_electricity_bill");
            }
        });

        gift.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("gift");
            }
        });

        book.setOnClickListener(new View.OnClickListener() {//按下收入類別按鈕
            @Override
            public void onClick(View v) {
                show_category("book");
            }
        });

        tv_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("diet");
            }
        });

        tv_dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("dress");
            }
        });

        tv_souvenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("souvenir");
            }
        });

        tv_online_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("online_shopping");
            }
        });

        tv_airticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("airticket");
            }
        });

        tv_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("entertainment");
            }
        });

        tv_telephone_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("telephone_bill");
            }
        });

        tv_medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("medical");
            }
        });

        tv_fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("fare");
            }
        });

        tv_water_electricity_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("water_electricity_bill");
            }
        });

        tv_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("gift");
            }
        });

        tv_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_category("book");
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {//按下完成按鈕，存資料
            MyDBHelper mydbHelper = new MyDBHelper(getActivity());
            String activity_type="",date = "",time="";
            int money=0;

            SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date today = new Date();
            String dateStr = simpleDateFormat_date.format(today); //format()方法将Date转换成指定格式的String

            String s_date=dateStr.substring(0,10);
            String s_now=dateStr.substring(11,19);

            @Override
            public void onClick(View view) {
                TextView category_name=(TextView)root.findViewById(R.id.select_category_name);   //取得上方顯示的收入類別
                EditText input_meney=(EditText)root.findViewById(R.id.input);    //取得輸入的金額


                //若使用者未輸入金額 或輸入0
                if(input_meney.getText().toString().equals("")||Integer.valueOf(String.valueOf(input_meney.getText()))==0){
                    // Create a Toast notification/message
                    Toast toast = Toast.makeText(
                            //getActivity(),"Custom Toast From Fragment",Toast.LENGTH_LONG
                            getActivity().getApplicationContext(), "請輸入金額", Toast.LENGTH_SHORT
                    );
                    // Set the Toast display position layout center
                    toast.setGravity(Gravity.CENTER,0,0);
                    // Finally, show the toast
                    toast.show();
                }
                else{
                    int earn_money=Integer.valueOf(String.valueOf(input_meney.getText())); //將金額轉為整數

                    //改這邊資料可以存到資料庫
                    activity_type=category_name.getText().toString();
                    date = s_date;
                    time=s_now;
                    money=earn_money;

                    mydbHelper.insertData(activity_type,"支出",money,date,time,img);//插入資料到資料庫

                    // Create a Toast notification/message
                    Toast toast = Toast.makeText(
                            //getActivity(),"Custom Toast From Fragment",Toast.LENGTH_LONG
                            getActivity().getApplicationContext(), "新增成功", Toast.LENGTH_SHORT
                    );
                    // Set the Toast display position layout center
                    toast.setGravity(Gravity.CENTER,0,0);
                    // Finally, show the toast
                    toast.show();

                    Navigation.findNavController(view).navigate(R.id.action_navigation_add_to_navigation_home);  //回主頁
                }

            }
        });



//        addViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//            }
//        });
        return root;

    }

    //變換上方的圖形與文字
    private void show_category(String name) {
        TextView category_name=(TextView)getView().findViewById(R.id.select_category_name);   //取得上方顯示的收入類別
        ImageView category_img=(ImageView)getView().findViewById(R.id.select_category);   //取得上方顯示的收入類別圖示

        switch (name){
            case "diet":
                category_name.setText("吃喝");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.eat));   //顯示收入類型圖示
                img = "eat";
                break;

            case "dress":
                category_name.setText("服飾");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.clothes));   //顯示收入類型圖示
                img = "clothes";
                break;

            case "souvenir":
                category_name.setText("手信");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.bag));   //顯示收入類型圖示
                img = "bag";
                break;

            case "online_shopping":
                category_name.setText("網購");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.shoppingcart));   //顯示收入類型圖示
                img = "shoppingcart";
                break;

            case "airticket":
                category_name.setText("機票");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.airticket));   //顯示收入類型圖示
                img = "airticket";
                break;

            case "entertainment":
                category_name.setText("娛樂");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.entertainment));   //顯示收入類型圖示
                img = "entertainment";
                break;

            case "telephone_bill":
                category_name.setText("話費");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.callphone));   //顯示收入類型圖示
                img = "callphone";
                break;

            case "medical":
                category_name.setText("醫療");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.medical));   //顯示收入類型圖示
                img = "medical";
                break;

            case "fare":
                category_name.setText("車費");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.car));   //顯示收入類型圖示
                img = "car";
                break;

            case "water_electricity_bill":
                category_name.setText("水電");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.water));   //顯示收入類型圖示
                img = "water";
                break;

            case "gift":
                category_name.setText("禮物");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.gift));   //顯示收入類型圖示
                img = "gift";
                break;

            case "book":
                category_name.setText("書費");    //顯示收入類型
                category_img.setImageDrawable(getResources().getDrawable(R.drawable.paybook));   //顯示收入類型圖示
                img = "paybook";
                break;

        }

    }

}