package com.example.roomdb_person

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonViewModel:ViewModel() {

    val personObserver = PersonObserver()
    val compositeDisposable = CompositeDisposable()
    private var personlist: MutableLiveData<List<PersonEntity>>? = MutableLiveData()

    val personRequest = AppDatabase.getInstance()

    fun getpersonInfo(){
        val getrequest : Observable<List<PersonEntity>> = personRequest!!.getAll()
        getrequest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(personObserver)
    }


    private fun PersonObserver():io.reactivex.Observer<List<PersonEntity>>{
        return object : io.reactivex.Observer<List<PersonEntity>>{
            override fun onComplete() {
                Log.d("emitted","all items emitted")

            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<PersonEntity>) {
                personlist?.value = t
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

    fun passToMainActivity(): MutableLiveData<List<PersonEntity>>?{
        return personlist
    }
}