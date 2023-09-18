package com.example.musicapp.ui.itemView

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.musicapp.MainActivity
import com.example.musicapp.helper.Utils.saveLocale
import com.example.musicapp.ui.model.LanguageItem


val languageTextList = listOf(
   LanguageItem("English","Change to English","en"),
   LanguageItem("Traditional Chinese","Change to Traditional Chinese","zh"),
)

@Composable
fun LanguageDropdownMenu(context: Context){
   var dropDownMenuExpanded by remember {
      mutableStateOf(false)
   }
   Box(modifier = Modifier){
      Button(onClick = {
         dropDownMenuExpanded = true
      }){
         Text(text = "Click me")
      }
      DropdownMenu(
         expanded = dropDownMenuExpanded,
         onDismissRequest = {
            dropDownMenuExpanded = false
         },
         offset = DpOffset(x = 10.dp, y = (-60).dp)
      ) {
         languageTextList.forEach {
            DropdownMenuItem(onClick = {
               Toast.makeText(context, it.showText, Toast.LENGTH_SHORT)
                  .show()
               saveLocale(context,it.langCode)
               val intent = Intent(context, MainActivity::class.java)
                  .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
               context.startActivity(intent)
               dropDownMenuExpanded = false
            }) {
               Text(it.title)
            }
         }
      }
   }

}