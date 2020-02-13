package com.zuhlke.upskilling.departureboard.seku.utils

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun Activity.showToastLong(@StringRes stringRes:Int) {
    Toast.makeText(this,stringRes,Toast.LENGTH_LONG).show()
}

fun View.hide() { this.visibility = View.GONE}
fun View.show() { this.visibility = View.VISIBLE}

fun checkRequiredField(editText: EditText) = TextUtils.isEmpty(editText.text)

fun <T> Single<T>.toIO() =
    this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())



