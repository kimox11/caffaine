package com.example.caffaine

import java.io.Serializable

class user(FirstName: String,LastName: String,Email: String,Password: String,PhoneNumber: String) : Serializable {

    var FirstName:String = FirstName
    var LastName:String = LastName
    var Email:String = Email
    var Password:String = Password
    var PhoneNumber:String = PhoneNumber

}