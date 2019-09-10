package com.example.roomdb_person

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    private lateinit var roomdb:AppDatabase
    var aperson:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        roomdb = AppDatabase.getInstance(applicationContext)

        val person = PersonEntity(uid =  0,
            firstName = "Kiran",
            secondName = "Nepali")


        val viewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        viewModel.getpersonInfo()
        val personInfo: MutableLiveData<List<PersonEntity>>? = viewModel.passToMainActivity()

        personInfo?.observe(this,object: Observer<List<PersonEntity>> {
            override fun onChanged(t: List<PersonEntity>?) {
                Log.d("firsname", t!![0].firstName)
            }
        })
    }
}


//         class MyAsync:AsyncTask<Void,Void,List<PersonEntity>>(){
//            override fun doInBackground(vararg p0: Void?): List<PersonEntity> {
//                roomdb.personDAO().insertAll(person)
//                return listOf(person)
//
//            }
//
//             override fun onPostExecute(result: List<PersonEntity>?) {
//                 super.onPostExecute(result)
////                 tv_firstName.text = roomdb.personDAO().getAllByIDs(1).get(0).firstName
//             }
//        }
//
//        MyAsync().execute()



