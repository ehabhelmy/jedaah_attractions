package com.spade.ja.ui.Home.attractioninner.calendar;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionExceptionalDate;
import com.spade.ja.datalayer.pojo.response.attractioninner.AttractionWeekDay;
import com.spade.ja.datalayer.pojo.response.attractioninner.Day;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.attractioninner.attractionpayment.pojo.AttractionPaymentModel;
import com.spade.ja.ui.Home.attractioninner.calendar.adapter.TimePickAdapter;
import com.spade.ja.ui.Home.attractioninner.calendar.pojo.TimeModel;
import com.spade.ja.ui.Home.attractioninner.pojo.AttractionPaymentData;
import com.spade.ja.ui.widget.JACalendarView;
import com.spade.ja.ui.widget.StaticGridView;
import com.spade.ja.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 3/14/18.
 */

public class CalendarFragment extends BaseFragment implements CalendarContract.View, JACalendarView.DaySelectListener {

    @Inject
    CalendarPresenter presenter;

    private AttractionPaymentData attractionPaymentData;

    @BindView(R.id.calendarContainer)
    LinearLayout calendarContainer;

    @BindView(R.id.timeContainer)
    RelativeLayout timeContainer;

    @BindView(R.id.timeGrid)
    StaticGridView timegridView;

    @BindView(R.id.calendar_1)
    JACalendarView calendarView;

    private TimeModel timeModel;
    private Map<Integer, AttractionWeekDay> weekDayMap;

    @OnClick(R.id.next)
    void gotToPaymentView() {
        if (timeModel == null) {
            showPopUp("You must select a date.");
        } else {
            presenter.openPaymentView(new AttractionPaymentModel(attractionPaymentData.isCredit(), attractionPaymentData.isCash(), attractionPaymentData.isPayLater(), attractionPaymentData.getMaxPayLaterTickets(), attractionPaymentData.getMaxCashTickets(), false, timeModel, attractionPaymentData.getAttractionTitle(), attractionPaymentData.getAttractionId()));
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializeDagger() {
        JaApplication jaApplication = (JaApplication) getActivity().getApplicationContext();
        jaApplication.getMainComponent().inject(this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calendar;
    }

    @Override
    protected String getScreenTrackingName() {
        return "calendar screen";
    }

    @Override
    public void setupCalendar(AttractionPaymentData attractionPaymentData) {
        this.attractionPaymentData = attractionPaymentData;
        List<Day> weekDays = attractionPaymentData.getWeekDays();
        ArrayList<Day> dayArrayList = new ArrayList<>();
        List<AttractionExceptionalDate> exceptionalDates = attractionPaymentData.getExceptionalDates();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        ArrayList<Integer> openDays = new ArrayList<>();
        ArrayList<Integer> closedDays = new ArrayList<>();
        for (Day day : weekDays) {
            if (day.getAttractionWeekDays().get(0).getIsClosed() == 0) {
                dayArrayList.add(day);
                openDays.add(DateTimeUtils.parseDayOfMonth(day.getDay()));
            } else {
                closedDays.add(DateTimeUtils.parseDayOfMonth(day.getDay()));
            }
        }
        for (AttractionExceptionalDate attractionExceptionalDate : exceptionalDates) {
            if (attractionExceptionalDate.getDate() != null) {
                if (DateTimeUtils.isDateInCurrentMonth(attractionExceptionalDate.getDate())) {
                    Day day = new Day();
                    day.setId(attractionExceptionalDate.getId());
                    day.setType("Excep");
                    day.setDate(attractionExceptionalDate.getDate());
                    List<AttractionWeekDay> attractionWeekDayList = new ArrayList<>();
                    AttractionWeekDay attractionWeekDay = new AttractionWeekDay();
                    attractionWeekDay.setAddons(attractionExceptionalDate.getAddons());
                    attractionWeekDay.setIsClosed(0);
                    attractionWeekDay.setTypes(attractionExceptionalDate.getTypes());
                    attractionWeekDay.setStartTime(attractionExceptionalDate.getStartTime());
                    attractionWeekDay.setEndTime(attractionExceptionalDate.getEndTime());
                    attractionWeekDayList.add(attractionWeekDay);
                    day.setAttractionWeekDays(attractionWeekDayList);
                    dayArrayList.add(day);
                    openDays.add(DateTimeUtils.parseDayOfMonthExceptional(attractionExceptionalDate.getDate()));
                    //TODO : map expectional date to week day and add to array
                } else {
                    JACalendarView jaCalendarView = new JACalendarView(getActivity());
                    jaCalendarView.setMonth(DateTimeUtils.parseMonthExceptional(attractionExceptionalDate.getDate()));
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, convertFromDpToPixel(), 0, 0);
                    jaCalendarView.setLayoutParams(params);
                    jaCalendarView.setListener(this);
                    ArrayList<Integer> expDay = new ArrayList<>();
                    expDay.add(DateTimeUtils.parseDayOfMonthExceptional(attractionExceptionalDate.getDate()));
                    jaCalendarView.setupCalendar(DateTimeUtils.getMonth(DateTimeUtils.convertJSONDateToDate(attractionExceptionalDate.getDate())).toUpperCase() + " " + DateTimeUtils.getYear(DateTimeUtils.convertJSONDateToDate(attractionExceptionalDate.getDate())).toUpperCase()
                            , expDay.toArray(new Integer[]{}), null, dayArrayList);
                    calendarContainer.addView(jaCalendarView);
                }
            }
        }
        calendarView.setListener(this);
        Integer[] openDaysArray;
        Integer[] closedDaysArray;
        openDaysArray = openDays.toArray(new Integer[]{});
        closedDaysArray = closedDays.toArray(new Integer[]{});
        calendarView.setupCalendar(
                DateTimeUtils.getMonth(new Date()).toUpperCase() + " " + DateTimeUtils.getYear(new Date()).toUpperCase()
                , openDaysArray, closedDaysArray, dayArrayList);
    }

    private int convertFromDpToPixel() {
        Resources r = getActivity().getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                r.getDisplayMetrics()
        );
        return px;
    }

    @Override
    public void onDaySelectedListener(Day day) {
        List<TimeModel> timeModels = new ArrayList<>();
        for (AttractionWeekDay attractionWeekDay : day.getAttractionWeekDays()) {
            if (day.getType() != null) {
                timeModels.add(new TimeModel(day.getId(),day.getDate(), attractionWeekDay.getStartTime(), attractionWeekDay.getEndTime(), day.getType() != null ? "Excep" : null));
            } else {
                timeModels.add(new TimeModel(day.getId(), DateTimeUtils.getDateFromDay(day.getDay()), attractionWeekDay.getStartTime(), attractionWeekDay.getEndTime(), day.getType() != null ? "Excep" : null));
            }
        }
        setupTimes(timeModels);
        timeContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDayUnSelectedListener(Day day) {
        timeContainer.setVisibility(View.GONE);
        timeModel = null;
    }

    public void setupTimes(List<TimeModel> timeModel2) {
        TimePickAdapter timePickAdapter = new TimePickAdapter(getActivity());
        timePickAdapter.setTimes(timeModel2);
        timegridView.setAdapter(timePickAdapter);
        timegridView.setOnItemClickListener((adapterView, view, i, l) -> {
            TimeModel timeModel1 = (TimeModel) adapterView.getItemAtPosition(i);
            if (timeModel == null) {
                timeModel = timeModel1;
                TextView textView = view.findViewById(R.id.tagType);
                textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.tag_rect_black));
            } else if (timeModel == timeModel1) {
                timeModel = null;
                TextView textView = view.findViewById(R.id.tagType);
                textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.lightBlack));
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.tag_rect_white));
            }
        });
    }
}
