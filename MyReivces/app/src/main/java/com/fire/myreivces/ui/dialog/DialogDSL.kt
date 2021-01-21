package com.fire.myreivces.ui.dialog

import androidx.fragment.app.FragmentActivity

object DialogDSL{
  fun <T> showCustomerDialog(context: FragmentActivity,dsl: CustomDialogDSL<T>.() -> Unit){

    var dl = CustomDialogDSL<T>().apply(dsl);
    dl.dialog = CustomerDialog(dsl)
    dl.dialog?.showNow(context.supportFragmentManager, "heihei")
  }
}

