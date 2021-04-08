package com.example.myapplication

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityReminderBinding
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class Reminder : AppCompatActivity() {

    private lateinit var binding: ActivityReminderBinding

    private var cal = Calendar.getInstance()
    private var cal1 = Calendar.getInstance()

    private fun setMyTimeFormat() :String {
        val myFormat = "HH:mm"
        val sdf = SimpleDateFormat(myFormat)
        return sdf.format(cal.time)
    }

    private fun setMyDateFormat() :String {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        return sdf.format(cal1.time)
    }

    private fun myTimePicker() : TimePickerDialog.OnTimeSetListener{
        val timeSetListener = object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                binding.timeReminder.text = setMyTimeFormat()
            }
        }
        return timeSetListener
    }

    private fun myDatePicker() : DatePickerDialog.OnDateSetListener{
        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
                cal1.set(Calendar.YEAR,year)
                cal1.set(Calendar.MONTH,month)
                cal1.set(Calendar.DAY_OF_MONTH,day)
                binding.dateReminder.text = setMyDateFormat()
            }
        }
        return dateSetListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder)

        var mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val requiestCode = 101
        var mPendingIntent : PendingIntent? = null
        var sendIntent : Intent? = null

        binding.setAlarm.setOnClickListener {
            if (mPendingIntent!=null) {
                mAlarmManager.cancel(mPendingIntent)
                mPendingIntent?.cancel()
            }
            var setAlarmTime = Calendar.getInstance()
            var time = binding.timeReminder.text.split(":")
            var date = binding.dateReminder.text.split("/")
            setAlarmTime.set(Calendar.YEAR,date[0].toInt())
            setAlarmTime.set(Calendar.MONTH,date[1].toInt())
            setAlarmTime.set(Calendar.DAY_OF_MONTH,date[2].toInt())
            setAlarmTime.set(Calendar.HOUR_OF_DAY,time[0].toInt())
            setAlarmTime.set(Calendar.MINUTE,time[1].toInt())
            setAlarmTime.set(Calendar.SECOND,0)




            sendIntent = Intent(this, AlarmReceiver::class.java)
            sendIntent?.putExtra(EXTRA_PESAN, binding.title.text.toString())
            mPendingIntent = PendingIntent.getBroadcast(this, requiestCode, sendIntent, 0)
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, setAlarmTime.timeInMillis,mPendingIntent)
            Toast.makeText(this,"Reminder has been set", Toast.LENGTH_SHORT).show()
            binding.title.text.clear()
        }
        binding.showTimePicker.setOnClickListener { TimePickerDialog(
            this, myTimePicker(),
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()}

        binding.showDatePicker.setOnClickListener { DatePickerDialog(
                this, myDatePicker(),
                cal1.get(Calendar.YEAR),
                cal1.get(Calendar.MONTH),
                cal1.get(Calendar.DAY_OF_MONTH)
        ).show()}
    }
}