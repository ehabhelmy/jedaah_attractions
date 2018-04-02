package com.spade.ja.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.attractioninner.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ehab on 3/13/18.
 */

public class JACalendarView extends RelativeLayout {

    private View view;
    private Map<Integer,DayHolder> calenderDays;
    private int month;
    private List<ClickSelection> clickSelections = new ArrayList<>();

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    @BindView(R.id.calendarViewMonth)
    TextView calendarMonth;

    @BindView(R.id.cal_1)
    LinearLayout cal_1;

    @BindView(R.id.num_1)
    TextView num_1;

    @BindView(R.id.dot_1)
    ImageView dot_1;

    @BindView(R.id.cal_2)
    LinearLayout cal_2;

    @BindView(R.id.num_2)
    TextView num_2;

    @BindView(R.id.dot_2)
    ImageView dot_2;

    @BindView(R.id.cal_3)
    LinearLayout cal_3;

    @BindView(R.id.num_3)
    TextView num_3;

    @BindView(R.id.dot_3)
    ImageView dot_3;

    @BindView(R.id.cal_4)
    LinearLayout cal_4;

    @BindView(R.id.num_4)
    TextView num_4;

    @BindView(R.id.dot_4)
    ImageView dot_4;

    @BindView(R.id.cal_5)
    LinearLayout cal_5;

    @BindView(R.id.num_5)
    TextView num_5;

    @BindView(R.id.dot_5)
    ImageView dot_5;

    @BindView(R.id.cal_6)
    LinearLayout cal_6;

    @BindView(R.id.num_6)
    TextView num_6;

    @BindView(R.id.dot_6)
    ImageView dot_6;

    @BindView(R.id.cal_7)
    LinearLayout cal_7;

    @BindView(R.id.num_7)
    TextView num_7;

    @BindView(R.id.dot_7)
    ImageView dot_7;

    @BindView(R.id.cal_8)
    LinearLayout cal_8;

    @BindView(R.id.num_8)
    TextView num_8;

    @BindView(R.id.dot_8)
    ImageView dot_8;

    @BindView(R.id.cal_9)
    LinearLayout cal_9;

    @BindView(R.id.num_9)
    TextView num_9;

    @BindView(R.id.dot_9)
    ImageView dot_9;

    @BindView(R.id.cal_10)
    LinearLayout cal_10;

    @BindView(R.id.num_10)
    TextView num_10;

    @BindView(R.id.dot_10)
    ImageView dot_10;

    @BindView(R.id.cal_11)
    LinearLayout cal_11;

    @BindView(R.id.num_11)
    TextView num_11;

    @BindView(R.id.dot_11)
    ImageView dot_11;

    @BindView(R.id.cal_12)
    LinearLayout cal_12;

    @BindView(R.id.num_12)
    TextView num_12;

    @BindView(R.id.dot_12)
    ImageView dot_12;

    @BindView(R.id.cal_13)
    LinearLayout cal_13;

    @BindView(R.id.num_13)
    TextView num_13;

    @BindView(R.id.dot_13)
    ImageView dot_13;

    @BindView(R.id.cal_14)
    LinearLayout cal_14;

    @BindView(R.id.num_14)
    TextView num_14;

    @BindView(R.id.dot_14)
    ImageView dot_14;

    @BindView(R.id.cal_15)
    LinearLayout cal_15;

    @BindView(R.id.num_15)
    TextView num_15;

    @BindView(R.id.dot_15)
    ImageView dot_15;

    @BindView(R.id.cal_16)
    LinearLayout cal_16;

    @BindView(R.id.num_16)
    TextView num_16;

    @BindView(R.id.dot_16)
    ImageView dot_16;

    @BindView(R.id.cal_17)
    LinearLayout cal_17;

    @BindView(R.id.num_17)
    TextView num_17;

    @BindView(R.id.dot_17)
    ImageView dot_17;

    @BindView(R.id.cal_18)
    LinearLayout cal_18;

    @BindView(R.id.num_18)
    TextView num_18;

    @BindView(R.id.dot_18)
    ImageView dot_18;

    @BindView(R.id.cal_19)
    LinearLayout cal_19;

    @BindView(R.id.num_19)
    TextView num_19;

    @BindView(R.id.dot_19)
    ImageView dot_19;

    @BindView(R.id.cal_20)
    LinearLayout cal_20;

    @BindView(R.id.num_20)
    TextView num_20;

    @BindView(R.id.dot_20)
    ImageView dot_20;

    @BindView(R.id.cal_21)
    LinearLayout cal_21;

    @BindView(R.id.num_21)
    TextView num_21;

    @BindView(R.id.dot_21)
    ImageView dot_21;

    @BindView(R.id.cal_22)
    LinearLayout cal_22;

    @BindView(R.id.num_22)
    TextView num_22;

    @BindView(R.id.dot_22)
    ImageView dot_22;

    @BindView(R.id.cal_23)
    LinearLayout cal_23;

    @BindView(R.id.num_23)
    TextView num_23;

    @BindView(R.id.dot_23)
    ImageView dot_23;

    @BindView(R.id.cal_24)
    LinearLayout cal_24;

    @BindView(R.id.num_24)
    TextView num_24;

    @BindView(R.id.dot_24)
    ImageView dot_24;

    @BindView(R.id.cal_25)
    LinearLayout cal_25;

    @BindView(R.id.num_25)
    TextView num_25;

    @BindView(R.id.dot_25)
    ImageView dot_25;

    @BindView(R.id.cal_26)
    LinearLayout cal_26;

    @BindView(R.id.num_26)
    TextView num_26;

    @BindView(R.id.dot_26)
    ImageView dot_26;

    @BindView(R.id.cal_27)
    LinearLayout cal_27;

    @BindView(R.id.num_27)
    TextView num_27;

    @BindView(R.id.dot_27)
    ImageView dot_27;

    @BindView(R.id.cal_28)
    LinearLayout cal_28;

    @BindView(R.id.num_28)
    TextView num_28;

    @BindView(R.id.dot_28)
    ImageView dot_28;

    @BindView(R.id.cal_29)
    LinearLayout cal_29;

    @BindView(R.id.num_29)
    TextView num_29;

    @BindView(R.id.dot_29)
    ImageView dot_29;

    @BindView(R.id.cal_30)
    LinearLayout cal_30;

    @BindView(R.id.num_30)
    TextView num_30;

    @BindView(R.id.dot_30)
    ImageView dot_30;

    @BindView(R.id.cal_31)
    LinearLayout cal_31;

    @BindView(R.id.num_31)
    TextView num_31;

    @BindView(R.id.dot_31)
    ImageView dot_31;

    private Set<Integer> selectedDays;
    private DaySelectListener listener;
    List<Day> weekDays;
    private int pos = -1;
    private boolean isSelected = false;

    public interface DaySelectListener {

        void onDaySelectedListener(Day day);

        void onDayUnSelectedListener(Day day);
    }

    public void setListener(DaySelectListener listener) {
        this.listener = listener;
    }

    private void setupCalendarDays() {

        calenderDays = new HashMap<>();
        calenderDays.put(1,new DayHolder(cal_1,num_1,dot_1));
        calenderDays.put(2,new DayHolder(cal_2,num_2,dot_2));
        calenderDays.put(3,new DayHolder(cal_3,num_3,dot_3));
        calenderDays.put(4,new DayHolder(cal_4,num_4,dot_4));
        calenderDays.put(5,new DayHolder(cal_5,num_5,dot_5));
        calenderDays.put(6,new DayHolder(cal_6,num_6,dot_6));
        calenderDays.put(7,new DayHolder(cal_7,num_7,dot_7));
        calenderDays.put(8,new DayHolder(cal_8,num_8,dot_8));
        calenderDays.put(9,new DayHolder(cal_9,num_9,dot_9));
        calenderDays.put(10,new DayHolder(cal_10,num_10,dot_10));
        calenderDays.put(11,new DayHolder(cal_11,num_11,dot_11));
        calenderDays.put(12,new DayHolder(cal_12,num_12,dot_12));
        calenderDays.put(13,new DayHolder(cal_13,num_13,dot_13));
        calenderDays.put(14,new DayHolder(cal_14,num_14,dot_14));
        calenderDays.put(15,new DayHolder(cal_15,num_15,dot_15));
        calenderDays.put(16,new DayHolder(cal_16,num_16,dot_16));
        calenderDays.put(17,new DayHolder(cal_17,num_17,dot_17));
        calenderDays.put(18,new DayHolder(cal_18,num_18,dot_18));
        calenderDays.put(19,new DayHolder(cal_19,num_19,dot_19));
        calenderDays.put(20,new DayHolder(cal_20,num_20,dot_20));
        calenderDays.put(21,new DayHolder(cal_21,num_21,dot_21));
        calenderDays.put(22,new DayHolder(cal_22,num_22,dot_22));
        calenderDays.put(23,new DayHolder(cal_23,num_23,dot_23));
        calenderDays.put(24,new DayHolder(cal_24,num_24,dot_24));
        calenderDays.put(25,new DayHolder(cal_25,num_25,dot_25));
        calenderDays.put(26,new DayHolder(cal_26,num_26,dot_26));
        calenderDays.put(27,new DayHolder(cal_27,num_27,dot_27));
        calenderDays.put(28,new DayHolder(cal_28,num_28,dot_28));
        calenderDays.put(29,new DayHolder(cal_29,num_29,dot_29));
        calenderDays.put(30,new DayHolder(cal_30,num_30,dot_30));
        calenderDays.put(31,new DayHolder(cal_31,num_31,dot_31));
    }


    public JACalendarView(Context context) {
        super(context);
        init();
    }

    public JACalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JACalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ButterKnife.bind(inflate(getContext(), R.layout.calendar, this));
        this.setLayoutParams(new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        setupCalendarDays();
        selectedDays = new HashSet<>();
    }

    public void setupCalendar(String monthText, Integer[] days, Integer[] closedDays, List<Day> weekDays) {

        calendarMonth.setText(monthText);
        this.weekDays = weekDays;
        for (int i = 0 ; i < days.length ; i++) {
            final int index = i;
            DayHolder dayHolder = calenderDays.get(days[i]);
            dayHolder.getDay().setTextColor(ContextCompat.getColor(getContext(),R.color.black));
            ClickSelection clickSelection = new ClickSelection(index,dayHolder);
            clickSelections.add(clickSelection);
            dayHolder.getCell().setOnClickListener(clickSelection);
        }

        if (closedDays != null) {
            for (int j = 0; j < closedDays.length; j++) {
                DayHolder dayHolder = calenderDays.get(closedDays[j]);
                dayHolder.getDot().setVisibility(VISIBLE);
            }
        }
    }

    private class ClickSelection implements OnClickListener {

        int index;
        DayHolder dayHolder;

        public ClickSelection(int index,DayHolder dayHolder) {
            this.index = index;
            this.dayHolder = dayHolder;
        }

        public void onDayUnselected() {
            dayHolder.getDay().setTextColor(ContextCompat.getColor(getContext(),R.color.black));
            dayHolder.getDay().setBackgroundColor(ContextCompat.getColor(getContext(),R.color.white));
            isSelected = false;
            pos = -1;
            listener.onDayUnSelectedListener(weekDays.get(index));
        }

        public void onDaySelected(){
            pos = index;
            isSelected = true;
            dayHolder.getDay().setTextColor(ContextCompat.getColor(getContext(),R.color.white));
            dayHolder.getDay().setBackground(ContextCompat.getDrawable(getContext(),R.drawable.green_circle));
            listener.onDaySelectedListener(weekDays.get(index));
        }

        @Override
        public void onClick(View view) {
            if (isSelected && pos == index){
                onDayUnselected();
            }else{
                for (ClickSelection clickSelection:clickSelections) {
                    clickSelection.onDayUnselected();
                }
                onDaySelected();
            }
        }
    }

    private class DayHolder {

        private LinearLayout cell;
        private TextView day;
        private ImageView dot;


        public DayHolder(LinearLayout cell, TextView day, ImageView dot) {
            this.cell = cell;
            this.day = day;
            this.dot = dot;
        }

        public LinearLayout getCell() {
            return cell;
        }

        public TextView getDay() {
            return day;
        }

        public ImageView getDot() {
            return dot;
        }
    }
}
