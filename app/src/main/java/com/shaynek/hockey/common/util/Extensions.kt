package com.shaynek.hockey.common.util

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

private const val timeFormat = "hh:mm aa"

fun Date.getLocalTime(): String {
    val formatter = SimpleDateFormat(timeFormat, Locale.getDefault())
    return formatter.format(this)
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> Flowable<out T>.scheduleForAndroid(): Flowable<out T> =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.forRepository(): Flowable<T> = this.onBackpressureLatest().replay(1).autoConnect()