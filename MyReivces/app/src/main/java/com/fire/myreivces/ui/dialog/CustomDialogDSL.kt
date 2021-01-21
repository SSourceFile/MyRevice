package com.fire.myreivces.ui.dialog

import android.view.View

class CustomDialogDSL<T> {

  var dialog: CustomerDialog<T>? = null

  internal var dialogTitle: ((title: String) -> String)? = null

  internal var dialogMsg: ((T) -> Unit)? = null
  internal var layoutId: ((resId: Int) ->Int)? = null
  internal var dialogCallback: ((success: View.OnClickListener, cancel: View.OnClickListener) ->Unit)? = null
  internal var dialogSuccess: (() ->Unit)? = null
  internal var dialogFailer: (() ->Unit)? = null


  fun setTitle(black: (title: String) ->String){
    this.dialogTitle = black
  }

  fun setDialogSuccess(block: () ->Unit){
    this.dialogSuccess = block
  }
  fun setDialogErr(black: () ->Unit){
      this.dialogFailer = black
  }

  fun setLayout(black: (resId: Int) ->Int){
    this.layoutId = black
  }


  fun setDialogMsg(black: (title: T) -> Unit){
    this.dialogMsg = black
  }

  fun setDialogCallBack(black: (success: View.OnClickListener, cancel: View.OnClickListener) -> Unit){
    this.dialogCallback = black
  }
}