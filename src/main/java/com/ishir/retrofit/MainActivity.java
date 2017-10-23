package com.ishir.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText_name;
    private EditText editText_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_pwd = (EditText) findViewById(R.id.editText_pwd);
    }


    public void login(View view) {
        final String name = editText_name.getText().toString();
        final String pwd = editText_pwd.getText().toString();
        //Retrofit的终极使用方式这样的：
        RetrofitHelper.create()
                .getNetService()
                .login(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<LoginBean>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super LoginBean> observer) {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        String data = loginBean.getData();
                        String name = loginBean.getName();
                        String password = loginBean.getPassword();
                        //设置为用户名+密码
                        textView.setText(name + password + data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

