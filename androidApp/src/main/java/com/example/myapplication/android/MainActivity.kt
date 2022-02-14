package com.example.myapplication.android

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Greeting

import java.util.*

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateLout: LinearLayout = findViewById(R.id.dateLout)
        val txtDate: TextView  = findViewById(R.id.txtDate)

        val edtName: EditText  = findViewById(R.id.edtName)
        val spnProject: Spinner  = findViewById(R.id.spnProject)
        val btnAddData: Button  = findViewById(R.id.btnAddData)
        val rvList: RecyclerView  = findViewById(R.id.rvList)

        var selectProject : String = "Select Project"

        var cal = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd MMM, yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                txtDate.text = sdf.format(cal.time)
            }
        }

        dateLout.setOnClickListener (object: View.OnClickListener{
            override fun onClick(p0: View?) {
                DatePickerDialog(this@MainActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        spnProject.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectProject = parent.getItemAtPosition(position).toString()
            }

        }

        btnAddData.setOnClickListener (object: View.OnClickListener{
            override fun onClick(p0: View?) {
                if(txtDate.text.toString() == ""){
                    Toast.makeText(applicationContext, "Please select date", Toast.LENGTH_SHORT).show()
                } else if(edtName.text.toString().trim() == ""){
                    Toast.makeText(applicationContext, "Please enter name", Toast.LENGTH_SHORT).show()
                } else if(selectProject == "Select Project"){
                    Toast.makeText(applicationContext, "Please select project", Toast.LENGTH_SHORT).show()
                } else {

                }
            }
        })

        listAdapter = ListAdapter()
        val layoutManager = LinearLayoutManager(applicationContext)
        rvList.layoutManager = layoutManager
        rvList.itemAnimator = DefaultItemAnimator()
        rvList.adapter = listAdapter

    }
}
