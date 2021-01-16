package com.fire.myreivces.ui.dialog

import android.app.Dialog
import android.widget.TextView
import com.fire.myreivces.R
import com.fire.myreivces.ui.home.MainActivity

fun <T> MainActivity.showCustomerDialog(dsl: CustomerDialog<T>.() -> Unit){
  var dl = CustomerDialog<T>().apply(dsl);
  dl.dialog?.let {
    Dialog(applicationContext)
  }
  dl.dialog?.setContentView(R.layout.dialog_customer)
  var title: TextView? = dl.dialog?.findViewById<TextView>(R.id.dia_txt)
//  title?.setText(dl.dialogTitle?.invoke(""))
//  dl.dialog?.show()
}