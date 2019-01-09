package com.spade.ja.ui.Home.eventsinner.eventphoneverification;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.spade.ja.JaApplication;
import com.spade.ja.R;
import com.spade.ja.ui.Base.BaseFragment;
import com.spade.ja.ui.Home.eventsinner.smsreciever.SMSReciever;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ehab on 1/25/18.
 */

public class EventPhoneVerificationFragment extends BaseFragment implements EventPhoneVerificationContract.View {

    @Inject
    EventPhoneVerificationPresenter presenter;

    private SMSReciever smsReciever;
    private String sms;

    @BindView(R.id.pin_box1)
    EditText mPinBox1;

    @BindView(R.id.pin_box2)
    EditText mPinBox2;

    @BindView(R.id.pin_box3)
    EditText mPinBox3;

    @BindView(R.id.pin_box4)
    EditText mPinBox4;

    @BindView(R.id.pin_continue_btn)
    Button mContinueBtn;

    private String[] mPinDigits;
    private boolean mResetPinBoxes = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        smsReciever = new SMSReciever(this);
    }

    @Override
    public void onStart() {
        initializeSmsReciever();
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initializeSmsReciever() {
        IntentFilter intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        intentFilter.setPriority(999);
        getActivity().registerReceiver(smsReciever,intentFilter,"android.permission.BROADCAST_SMS",null);
    }

    private void unRegisterSmsReciever(){
        getActivity().unregisterReceiver(smsReciever);
    }

    @Override
    public void onStop() {
        unRegisterSmsReciever();
        super.onStop();
    }

    private void init() {
        mContinueBtn.setEnabled(false);
        mContinueBtn.setBackground(getResources().getDrawable(R.drawable.button_dimmed));
        mContinueBtn.setTextColor(getResources().getColor(R.color.grey_scale_6));
        mPinDigits = new String[4];
        defineContinueBtn();
        setupFocusListeners();
        setupOnKeyListeners();
        setupTextChangeListeners();
        mPinBox1.requestFocus();
    }

    private void setupFocusListeners() {
        mPinBox1.setOnFocusChangeListener(this);
        mPinBox2.setOnFocusChangeListener(this);
        mPinBox3.setOnFocusChangeListener(this);
        mPinBox4.setOnFocusChangeListener(this);
    }

    private void setupTextChangeListeners() {
        mPinBox1.addTextChangedListener(new GenericTextWatcher(mPinBox1));
        mPinBox2.addTextChangedListener(new GenericTextWatcher(mPinBox2));
        mPinBox3.addTextChangedListener(new GenericTextWatcher(mPinBox3));
        mPinBox4.addTextChangedListener(new GenericTextWatcher(mPinBox4));
    }

    private void setupOnKeyListeners() {
        mPinBox1.setOnKeyListener(this);
        mPinBox2.setOnKeyListener(this);
        mPinBox3.setOnKeyListener(this);
        mPinBox4.setOnKeyListener(this);
    }

    private void defineContinueBtn() {
        mContinueBtn.setOnClickListener(v -> {
            disablePinBoxes();
            setupPinDigits();
            final String pin = getPin();
            //TODO L to be removed
            if (true || pin.equals(sms)){
                presenter.showOrderView();
            }else {
                mResetPinBoxes = true;
                changePinBoxesBackgroundToDefault();
                changeContinueButtonToDisabled();
                enablePinBoxes();
                mPinBox1.requestFocus();
            }
        });
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
        return R.layout.fragment_event_phone_verification;
    }

    @Override
    protected String getScreenTrackingName() {
        return "phone verification";
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        EditText box = (EditText) view;
        if (b) {
            changePinBoxBackgroundToDefault(box);
            box.setText("");
        }
        changeContinueBtnAppearance();
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_DEL) {
            switch (view.getId()) {
                case R.id.pin_box2:
                    requestFocusOnNextView(mPinBox1);
                    break;
                case R.id.pin_box3:
                    requestFocusOnNextView(mPinBox2);
                    break;
                case R.id.pin_box4:
                    requestFocusOnNextView(mPinBox3);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @OnClick(R.id.resend)
    void resendMessage(){
        presenter.sendMessage();
    }

    @Override
    public void onSMSRecieved(String sms) {
       //TODO : Success
        this.sms = sms;
        autoFillPinBoxes(sms);
    }

    private void autoFillPinBoxes(String sms) {
        mPinBox1.setText(sms.substring(0,1));
        mPinBox2.setText(sms.substring(1,2));
        mPinBox3.setText(sms.substring(2,3));
        mPinBox4.setText(sms.substring(3,4));
    }

    private void changeAllBoxesToBlue() {
        mPinBox1.setBackground(getActivity().getResources().getDrawable(R.drawable.blue_circle));
        mPinBox2.setBackground(getActivity().getResources().getDrawable(R.drawable.blue_circle));
        mPinBox3.setBackground(getActivity().getResources().getDrawable(R.drawable.blue_circle));
        mPinBox4.setBackground(getActivity().getResources().getDrawable(R.drawable.blue_circle));
    }

    private class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            final String text = editable.toString();
            switch (view.getId()) {
                case R.id.pin_box1:
                    changeViewFocus(text,mPinBox1,mPinBox2);
                    break;
                case R.id.pin_box2:
                    changeViewFocus(text,mPinBox2,mPinBox3);
                    break;
                case R.id.pin_box3:
                    changeViewFocus(text,mPinBox3,mPinBox4);
                    break;
                case R.id.pin_box4:
                    if (!text.equals("") && areAllBoxesFilled()) {
                        changePinBoxBackgroundToFull(mPinBox4);
                        changeContinueButtonToEnabled();
                        hideKeyboard();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mPinBox1.getWindowToken(), 0);
    }

    private void changeViewFocus(final String text,View lastPinBox, View nextPinBox) {
        if (!text.equals("")) {
            changePinBoxBackgroundToFull(lastPinBox);
            requestFocusOnNextView(nextPinBox);
        }
    }

    private void changePinBoxBackgroundToFull(View nextPinBox) {
        nextPinBox.setBackground(getActivity().getResources().getDrawable(R.drawable.blue_circle));
    }

    private void changePinBoxBackgroundToDefault(View nextPinBox) {
        nextPinBox.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
    }

    private void requestFocusOnNextView(final View nextPinBox) {
        nextPinBox.requestFocus();
    }

    private void changeContinueBtnAppearance() {
        if (areAllBoxesFilled()) {
            changeContinueButtonToEnabled();
        } else {
            changeContinueButtonToDisabled();
        }
    }

    public boolean areAllBoxesFilled() {
        return !mPinBox1.getText().toString().equals("") && !mPinBox2.getText().toString().equals("")
                && !mPinBox3.getText().toString().equals("") && !mPinBox4.getText().toString().equals("");
    }

    private void changeContinueButtonToDisabled() {
        mContinueBtn.setEnabled(false);
        mContinueBtn.setBackground(getResources().getDrawable(R.drawable.button_dimmed));
        mContinueBtn.setTextColor(getResources().getColor(R.color.grey_scale_6));
    }

    private void changeContinueButtonToEnabled() {
        mContinueBtn.setEnabled(true);
        mContinueBtn.setBackground(getResources().getDrawable(R.drawable.register_button));
        mContinueBtn.setTextColor(getResources().getColor(R.color.white));
    }

    public void setupPinDigits() {
        mPinDigits[0] = mPinBox1.getText().toString();
        mPinDigits[1] = mPinBox2.getText().toString();
        mPinDigits[2] = mPinBox3.getText().toString();
        mPinDigits[3] = mPinBox4.getText().toString();
    }

    public String getPin() {
        StringBuilder pin = new StringBuilder();
        for (String pinDigit : mPinDigits) {
            if (!TextUtils.isEmpty(pinDigit)) {
                pin.append(pinDigit);
            }
        }

        return pin.toString();
    }

    public void clearInputValues() {
        mPinBox1.setText("");
        mPinBox2.setText("");
        mPinBox3.setText("");
        mPinBox4.setText("");

        mResetPinBoxes = false;
    }

    public void disablePinBoxes() {
        mPinBox1.setEnabled(false);
        mPinBox2.setEnabled(false);
        mPinBox3.setEnabled(false);
        mPinBox4.setEnabled(false);
    }

    private void enablePinBoxes() {
        mPinBox1.setEnabled(true);
        mPinBox2.setEnabled(true);
        mPinBox3.setEnabled(true);
        mPinBox4.setEnabled(true);
    }

    private void changeBoxesColorWhenError() {
        mPinBox1.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox2.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox3.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox4.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
    }

    private void changePinBoxesBackgroundToDefault() {
        mPinBox1.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox2.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox3.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));
        mPinBox4.setBackground(getActivity().getResources().getDrawable(R.drawable.circle));

        if (mResetPinBoxes) {
            clearInputValues();
        }
    }

}
