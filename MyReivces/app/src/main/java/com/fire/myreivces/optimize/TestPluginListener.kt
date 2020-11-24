package com.fire.myreivces.optimize

import android.content.Context
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import com.tencent.matrix.util.MatrixLog

class TestPluginListener(context: Context?) : DefaultPluginListener(context) {
  override fun onReportIssue(issue: Issue) {
    super.onReportIssue(issue)
    MatrixLog.e(TAG, issue.toString())

    //add your code to process data
  }

  companion object {
    const val TAG = "Matrix.TestPluginListener"
  }
}