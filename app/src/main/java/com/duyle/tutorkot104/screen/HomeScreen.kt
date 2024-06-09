package com.duyle.tutorkot104.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog
import com.duyle.tutorkot104.entity.SanPham
import com.duyle.tutorkot104.entity.getListSanpham


@Composable
fun HomeScreen() {

    var listSanphams by remember {
        mutableStateOf(getListSanpham())
    }

    val context = LocalContext.current

    var showDialogItemInfor by remember {
        mutableStateOf(false)
    }

    var sp: SanPham? by remember {
        mutableStateOf(null)
    }

    if (showDialogItemInfor) {
        ShowDialog(
            title = "Thong tin SP",
            content = sp.toString(),
            onDismiss = { showDialogItemInfor = false },
            onConfirm = { showDialogItemInfor = false })
    }

    var showDialogXoaSp by remember {
        mutableStateOf(false)
    }

    if (showDialogXoaSp) {
        ShowDialog(
            title = "Xoa SP",
            content = "Ban co chac chan xoa san pham?",
            onDismiss = { showDialogXoaSp = false },
            onConfirm = {
                val temps = listSanphams.toMutableList() // tao ban sao list
                temps.remove(sp)

                listSanphams = temps
                showDialogXoaSp = false
            })
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable {
//                            Toast
//                                .makeText(context, it.toString(), Toast.LENGTH_SHORT)
//                                .show()
                            sp = it
                            showDialogItemInfor = true
                        }, horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        ItemText(content = it.name)
                        ItemText(content = it.price.toString())
                    }

                    Column {
                        ItemText(content = it.description.toString())
                        ItemText(content = it.status.toString())
                    }

                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(painter = painterResource(id = android.R.drawable.ic_menu_edit),
                            contentDescription = "",
                            Modifier
                                .width(15.dp)
                                .clickable {

                                })

                        Image(painter = painterResource(id = android.R.drawable.ic_menu_delete),
                            contentDescription = "",
                            Modifier
                                .width(15.dp)
                                .clickable {
                                    sp = it
                                    showDialogXoaSp = true
                                })
                    }
                }

                Divider()
            }
        }
    }
}

@Composable
private fun ShowDialog(
    title: String,
    content: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        },
        title = { Text(text = title) },
        text = { ItemText(content = content) }
    )
}

@Composable
private fun ItemText(content: String) {
    Text(text = content, style = MaterialTheme.typography.bodyLarge)
}