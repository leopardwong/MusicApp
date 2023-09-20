package com.example.musicapp.ui.itemView

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.musicapp.MainActivity
import com.example.musicapp.helper.Utils.saveLocale
import com.example.musicapp.ui.model.LanguageItem

@Composable
fun LanguageDropdownMenu(context: Context,languageTextList:List<LanguageItem>){
   var dropDownMenuExpanded by remember {
      mutableStateOf(false)
   }
   Box(modifier = Modifier){
      IconButton(onClick = { dropDownMenuExpanded = true }) {
         Icon(
            imageVector = Icons.Default.Language,
            contentDescription = null,
            tint = Color.White
         )
      }
      DropdownMenu(
         expanded = dropDownMenuExpanded,
         onDismissRequest = {
            dropDownMenuExpanded = false
         },
      ) {
         languageTextList.forEach {
            DropdownMenuItem(onClick = {
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