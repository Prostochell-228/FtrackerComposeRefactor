@file:Suppress("DEPRECATION")

package com.examle.ftrackercomposerefactor.FallWorkingDirectory

import android.telephony.SmsManager
import com.examle.ftrackercomposerefactor.FallHelpDirectory.AllConstants.phoneNumbers

class SendMassage {
    var countContacts = 0
        //По запросу спам рассылка всем в ВаЦаПе (нет пока в смс)
    fun sendSms(phoneNumber: MutableList<Double>, message: String) {

        phoneNumbers.forEach {
            val phoneNumber = phoneNumbers[this.countContacts]
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber.toString(), null, message+"Моя геолокация:", null, null)
            countContacts = countContacts+1
        }
    }
}