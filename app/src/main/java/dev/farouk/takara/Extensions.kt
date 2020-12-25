package dev.farouk.takara

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
* Created by Farouk on 25/12/2020.
*/
fun AppCompatActivity.goToActivity(context: Context, clss: Class<*>) {
    this.startActivity(Intent(context, clss))
}