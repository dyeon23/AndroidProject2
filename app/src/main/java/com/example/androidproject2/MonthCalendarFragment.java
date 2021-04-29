package com.example.androidproject2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthCalendarFragment extends Fragment {

    private int mParam1;
    private int mParam2;

    // 데이터 원본 준비
    Calendar today;
    ArrayList<String> list = new ArrayList<>();

    public interface OnMyListner{
        public void titleset(int mparam1,int mparam2,int day);
    }






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters


    public MonthCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthCalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        today = Calendar.getInstance();

        ActionBar actionBar =((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(mParam1+"년"+(mParam2+1)+"월");




        if (getArguments().getInt(ARG_PARAM2)== today.get(Calendar.MONTH+1)){     // 전달받는 인텐트 여부 확인
            init();               //초기달력정보 받아오기
        }

        else{    //인텐트가 있을 때, 즉 새로운 액티비티가 생성되었을 때
            getCalendar();   //캘린더 정보 받아오기
        }
        View myFragmentView = inflater.inflate(R.layout.fragment_month_calendar, container, false);

        // id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = (GridView) myFragmentView.findViewById(R.id.gridview);

        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리소스 사용
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);



        // 어댑터를 GridView 객체에 연결
        gridview.setAdapter(adapt);

        //현재 날짜 토스트 메세지 띄우기
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int day=position-today.get(Calendar.DAY_OF_WEEK)+2;      //현재 일 구하기
                if(day>=1)        //날짜가 있을 때만 토스트 메세지 띄우기
                    //캘린더 클래스의 월은 0~11, +1을 해주어서 1~12로 설정
                    Toast.makeText(getActivity(),""+today.get(Calendar.YEAR)+"."+(today.get(Calendar.MONTH)+1)+"."+day,Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return myFragmentView;
    }

    //현재 날짜의 달력 정보를 가져오는 함수. 초기 액티비티 시작시
    private void init(){
        today.set(Calendar.DAY_OF_MONTH,1);     //현재 달의 첫번째날로 날짜 설정
        int lastDate = today.getActualMaximum(Calendar.DATE);  //이번달의 마지막 날 얻어서 저장
        int startDate = today.get(Calendar.DAY_OF_WEEK);   //이번달의 시작요일 얻어서 저장
        for(int i=0;i<startDate-1;i++){
            list.add("");                //시작 요일 이전 요일 공백으로 채우기
        }
        for(int i=1;i<=lastDate;i++){
            list.add(Integer.toString(i));     //일 채우기
        }

    }

    //달력 정보를 가져오는 함수,인텐트로 새로운 달력 정보를 가져올 경우
    private void getCalendar(){
        int year=getArguments().getInt(ARG_PARAM1);    //인텐트로 year 받아서 저장
        int month=getArguments().getInt(ARG_PARAM2);  //인텐트로 month 받아서 저장
        today.set(year,month,1);                             //현재 년도 및 월 설정.
        int lastDate = today.getActualMaximum(Calendar.DATE);  //이번달의 마지막 날 얻어서 저장
        int startDate = today.get(Calendar.DAY_OF_WEEK);   //이번달의 시작요일 얻어서 저장
        for(int i=0;i<startDate-1;i++){
            list.add("");                //공백으로 채우기
        }
        for(int i=1;i<=lastDate;i++){
            list.add(Integer.toString(i));     //일 채우기
        }
    }

}