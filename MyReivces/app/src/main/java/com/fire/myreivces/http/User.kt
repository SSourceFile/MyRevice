package com.fire.myreivces.http

class User {

  var data: List<UserItem>? = null
  var errorCode: Int? = 0
  var errorMsg: String? = ""
}

class UserItem {
  var desc: String? = ""
  var id: Int? = 0
  var imagePath: String? = ""
  var isVisible: Int? = 0
  var order: Int? = 0
  var title: String? = ""
  var type: Int? = 0
  var url: String? = ""

}
