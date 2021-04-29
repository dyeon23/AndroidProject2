package com.example.androidproject2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

//public class MainActivity extends Activity implements MonthCalendarFragment.OnMyListner{
//
//    @Override
//    public void onReceivedData(Object data) {
//
//    }
//}

public class MainActivity extends AppCompatActivity implements MonthCalendarFragment.OnMyListner {

    //    // 데이터 원본 준비
    Calendar today;
    ArrayList<String> list = new ArrayList<>();
    int year;
    int month;
//    Intent getIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today = Calendar.getInstance();   //현재 날짜를 가진 캘린더 객체 생성

        getSupportActionBar().setTitle(today.get(Calendar.YEAR) + "년" + (today.get(Calendar.MONTH) + 1) + "월");

//       // TextView todayDate=(TextView)findViewById(R.id.date);   // id를 바탕으로 화면 레이아웃에 정의된 TextView 객체 로딩
//
//        //Button pre = findViewById(R.id.button);
//        //Button next = findViewById(R.id.button2);
//
//        getIn = getIntent();                      //인텐트 받기
//        if (getIn.getIntExtra("year",0)==0){     // 전달받는 인텐트 여부 확인
//            init();               //초기달력정보 받아오기
//        }
//
//        else{    //인텐트가 있을 때, 즉 새로운 액티비티가 생성되었을 때
//            getCalendar();   //캘린더 정보 받아오기
//        }

        //앱바 타이틀 현재 년 월로 설정


//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.main_container, new MonthViewFragment());
//        fragmentTransaction.commit();

//        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//
//        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리소스 사용
//        ArrayAdapter<String> adapt
//                = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                list);
//
//        // 어댑터를 GridView 객체에 연결
//        gridview.setAdapter(adapt);
    }

    public void titleset(int year, int month, int day) {

        if (day >= 1)        //날짜가 있을때만 토스트 메세지 띄우기
            //캘린더 클래스의 월은 0~11, +1을 해주어서 1~12로 설정
            Toast.makeText(getApplicationContext(), +year + "." + (month + 1) + "." + day, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.month_view:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new MonthViewFragment());
                fragmentTransaction.commit();

                Toast.makeText(getApplicationContext(), "month_view", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.week_view:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new WeekViewFragment());
                fragmentTransaction.commit();

                Toast.makeText(getApplicationContext(), "week_view", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //현재 날짜의 달력 정보를 가져오는 함수. 초기 액티비티 시작시
    private void init() {
        today.set(Calendar.DAY_OF_MONTH, 1);     //현재 달의 첫번째날로 날짜 설정
        int lastDate = today.getActualMaximum(Calendar.DATE);  //이번달의 마지막 날 얻어서 저장
        int startDate = today.get(Calendar.DAY_OF_WEEK);   //이번달의 시작요일 얻어서 저장
        for (int i = 0; i < startDate - 1; i++) {
            list.add("");                //시작 요일 이전 요일 공백으로 채우기
        }
        for (int i = 1; i <= lastDate; i++) {
            list.add(Integer.toString(i));     //일 채우기
        }

    }

//
//    //달력 정보를 가져오는 함수,인텐트로 새로운 달력 정보를 가져올 경우
//    private void getCalendar(){
//        int year=getIn.getIntExtra("year",0);    //인텐트로 year 받아서 저장
//        int month=getIn.getIntExtra("month",0);  //인텐트로 month 받아서 저장
//        today.set(year,month,1);                             //현재 년도 및 월 설정.
//        int lastDate = today.getActualMaximum(Calendar.DATE);  //이번달의 마지막 날 얻어서 저장
//        int startDate = today.get(Calendar.DAY_OF_WEEK);   //이번달의 시작요일 얻어서 저장
//        for(int i=0;i<startDate-1;i++){
//            list.add("");                //공백으로 채우기
//        }
//        for(int i=1;i<=lastDate;i++){
//            list.add(Integer.toString(i));     //일 채우기
//        }
//    }

}
