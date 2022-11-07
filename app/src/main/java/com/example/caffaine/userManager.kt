package com.example.caffaine

import java.io.File

class userManager {
     companion object {
          var users: ArrayList<user> = ArrayList()
          var currentUser: user? = null
          var PHOTO_BROADCAST:String = "PHOTO-BROADCAST"
          var directory: String = ""
     }

}