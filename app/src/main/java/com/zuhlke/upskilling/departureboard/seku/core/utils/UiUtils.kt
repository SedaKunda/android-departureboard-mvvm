package com.zuhlke.upskilling.departureboard.seku.core.utils

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
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

fun View.getView(@IdRes idRes:Int): String {
    return this.findViewById<TextView>(idRes).text.toString()
}

fun RecyclerView.setDivider(@DrawableRes drawableRes: Int) {
    val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
    val drawable = ContextCompat.getDrawable(this.context, drawableRes)
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}