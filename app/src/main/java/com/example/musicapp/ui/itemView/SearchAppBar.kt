package com.example.musicapp.ui.itemView

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.ui.viewModel.SearchScreenViewModel
import com.example.musicapp.api.model.Results

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
   onSearchClicked: (String) -> Unit,
   onFilterClicked: ()-> Unit,
   context: Context
) {
   var textValue by remember { mutableStateOf("") }
   val keyboardController = LocalSoftwareKeyboardController.current
   Surface(
      modifier = Modifier
         .fillMaxWidth()
         .height(56.dp),
      elevation = AppBarDefaults.TopAppBarElevation,
      color = Color.Black
   ) {
      TextField(modifier = Modifier
         .fillMaxWidth(),
         value = textValue,
         onValueChange = {
            textValue = it
         },
         placeholder = {
            Text(
               modifier = Modifier
                  .alpha(ContentAlpha.medium),
               text = context.getString(R.string.searchBar),
               color = Color.White
            )
         },
         textStyle = TextStyle(
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            color = Color.White
         ),
         singleLine = true,
         leadingIcon = {
            IconButton(
               modifier = Modifier
                  .alpha(ContentAlpha.medium),
               onClick = {
                  keyboardController?.hide()
                  onSearchClicked(textValue)
               }
            ) {
               Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "Search Icon",
                  tint = Color.White
               )
            }
         },
         trailingIcon = {
            Row {
               IconButton(
                  onClick = {
                     onFilterClicked()
                  }
               ) {
                  Icon(
                     imageVector = Icons.Default.FilterAlt,
                     contentDescription = "Close Icon",
                     tint = Color.White
                  )
               }
            }
         },
         keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
         ),
         keyboardActions = KeyboardActions(
            onSearch = {
               keyboardController?.hide()
               onSearchClicked(textValue)
            }
         ),
         colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
         )
      )
   }
}