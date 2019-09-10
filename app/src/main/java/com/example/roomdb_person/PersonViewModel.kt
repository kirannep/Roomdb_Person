package com.example.roomdb_person

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonViewModel(application: Application): AndroidViewModel(application) {

    val personObserver = PersonObserver()
    val compositeDisposable = CompositeDisposable()
    private var personlist: MutableLiveData<List<PersonEntity>>? = MutableLiveData()
    private var insertlist: MutableLiveData<List<PersonEntity>>? = MutableLiveData()
    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()


    val personRequest = AppDatabase.getInstance(application).personDAO()

    fun insertpersonInfo(person:PersonEntity){
        personRequest.insertAll(person)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({showSuccess.value = true},{
                Log.i("ViewModel error",it.message)
                showSuccess.value=false})

    }





    fun getpersonInfo(){
        val getrequest : Observable<List<PersonEntity>> = personRequest.getAll()
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