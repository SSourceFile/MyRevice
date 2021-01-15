package com.fire.myreivces.ui.dialog

import android.app.Dialog
import com.fire.myreivces.ui.home.MainActivity

fun <T> MainActivity.showCustomerDialog(dsl: CustomerDialog<T>.() -> Unit){
  var dl = CustomerDialog<T>().apply(dsl);
  dl.dialog?.let {
    Dialog(applicationContext)
  }
  dl.dialogTitle?.invoke("")

  dl.dialog?.show()
}