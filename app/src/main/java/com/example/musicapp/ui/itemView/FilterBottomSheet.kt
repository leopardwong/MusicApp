package com.example.musicapp.ui.itemView

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.R
import com.example.musicapp.ui.model.CountryCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterBottomSheet(
   coroutineScope: CoroutineScope,
   sheetState: ModalBottomSheetState,
   countryList:List<CountryCode>,
   countryName: (String)->Unit,
   context: Context
) {
   var selectedCountry: String? by remember { mutableStateOf(null) }

   Column() {
      Text(
         text = context.getString(R.string.filter),
         style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
         overflow = TextOverflow.Ellipsis,
         color = Color.Black,
         modifier = Modifier
            .padding(start = 16.dp,top= 16.dp),
         fontWeight = FontWeight.Bold
      )
      LazyVerticalGrid(
         columns = GridCells.Adaptive(minSize = 128.dp)
      ) {
         items(countryList.size) { index ->
            val country = countryList[index]
            FilterCheckbox(
               name = country.countryName,
               checked = country.code == selectedCountry,
               onCheckedChange = {
                  selectedCountry = if (it) country.code else null
               }
            )
         }
      }
      Row(modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)){
         Spacer(Modifier.weight(1f))

         Button(onClick = {
            selectedCountry?.let { countryName(it) }
            coroutineScope.launch {
               sheetState.hide()
            }
         }) {
            Text(text = context.getString(R.string.search))
         }

      }

   }
}

@Composable
fun FilterCheckbox(
   name: String,
   checked: Boolean,
   onCheckedChange: (Boolean) -> Unit
) {
   Row(
      verticalAlignment = Alignment.CenterVertically
   ) {
      Checkbox(
         checked = checked,
         onCheckedChange = { isChecked ->
            onCheckedChange(isChecked)
         }
      )
      Text(
         text = name,
         fontWeight = FontWeight.Bold)
   }
}