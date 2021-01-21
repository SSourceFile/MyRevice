package com.fire.myreivces.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.fire.myreivces.R

class CustomerDialog<T>(dsl: CustomDialogDSL<T>.() -> Unit) : DialogFragment() {
  var dl = CustomDialogDSL<T>().apply(dsl);
  var rootViews: View? = null
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    rootViews = inflater.inflate(setResId(), container, false)
    return rootViews
  }

  private fun setResId(): Int {
    return R.layout.dialog_my
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    var content: TextView? = rootViews?.findViewById<TextView>(R.id.tv_content)
    var close: TextView? = rootViews?.findViewById<TextView>(R.id.tv_close)
    var confirm: TextView? = rootViews?.findViewById<TextView>(R.id.tv_confirm)
    content?.also {
      it.text = "尼玛的我草打算复读生发剂"
    }
    close?.setOnClickListener {
      dl.dialogFailer?.invoke()
      dismiss()
    }
    confirm?.setOnClickListener {
      dl.dialogSuccess?.invoke()
      dismiss()
    }
  }

}