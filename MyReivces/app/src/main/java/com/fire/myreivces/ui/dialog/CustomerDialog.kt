package com.fire.myreivces.ui.dialog

import android.app.Dialog
import android.view.View

class CustomerDialog<T> {

  var dialog: (Dialog)? = null

  internal var dialogTitle: ((title: String) -> Unit)? = null

  internal var dialogMsg: ((T) -> Unit)? = null

  internal var dialogCallback: ((success: View.OnClickListener, cancel: View.OnClickListener) ->Unit)? = null


  fun setTitle(black: (title: String) ->Unit){
    this.dialogTitle = black
  }

  fun setDialogMsg(black: (title: T) -> Unit){
    this.dialogMsg = black
  }

  fun setDialogCallBack(black: (success: View.OnClickListener, cancel: View.OnClickListener) -> Unit){
    this.dialogCallback = black
  }
}