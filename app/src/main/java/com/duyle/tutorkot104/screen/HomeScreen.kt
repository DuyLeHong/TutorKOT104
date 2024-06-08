package com.duyle.tutorkot104.screen

import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.duyle.tutorkot104.R
import com.duyle.tutorkot104.entity.SanPham
import com.duyle.tutorkot104.entity.getListSanpham
import org.w3c.dom.Text


@Composable
fun HomeScreen() {

    var listSanphams by remember {
        mutableStateOf(getListSanpham())
    }

    val context = LocalContext.current

    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Text(text = "Quan ly san pham", style = MaterialTheme.typography.titleLarge)

        Button(onClick = {

            val temps = listSanphams.toMutableList() // tao ban sao list
            temps.add(SanPham(5, 9f, "SP 5", "mo ta 5", true)) // add du lieu

            listSanphams = temps

//            listSanphams = listSanphams.toMutableList().apply {
//                add(SanPham(5, 9f, "SP 5", "mo ta 5", true))
//            }


        }) {
            Text(text = "Them san pham")
        }

        LazyColumn {

            items(listSanphams) {
                Row (
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }, horizontalArrangement = Arrangement.SpaceBetween){

                    Column {
                        ItemText(content = it.name)
                        ItemText(content = it.price.toString())
                    }

                    Column {
                        ItemText(content = it.description.toString())
                        ItemText(content = it.status.toString())
                    }

                    Column {
                        Image(painter = painterResource(id = android.R.drawable.ic_menu_edit), contentDescription = "", Modifier.clickable {

                        })

                        Image(painter = painterResource(id = android.R.drawable.ic_menu_delete), contentDescription = "", Modifier.clickable {
                            val temps = listSanphams.toMutableList() // tao ban sao list
                            temps.remove(it)

                            listSanphams = temps
                        })
                    }
                }

                Divider()
            }
        }
    }


}

@Composable
private fun ItemText (content : String) {
    Text(text = content, style = MaterialTheme.typography.bodyLarge)
}