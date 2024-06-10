package com.duyle.tutorkot104.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.duyle.tutorkot104.entity.SanPham
import com.duyle.tutorkot104.entity.getListSanpham

//var listSanphams : MutableList<SanPham> = mutableListOf()

var sanPhamTemp: SanPham = SanPham(name = "", price = 0f, description = "", status = false)

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

    var showDialogThemSanPham by remember {
        mutableStateOf(false)
    }

    if (showDialogThemSanPham) {
        ShowDialogThemSuaSP(
            title = "Them SP",
            onDismiss = { showDialogThemSanPham = false },
            onConfirm = {
                listSanphams = listSanphams.toMutableList().apply {
                    add(sanPhamTemp.copy())

                    showDialogThemSanPham = false
                }
            })
    }

    var showDialogSuaSanPham by remember {
        mutableStateOf(false)
    }

    if (showDialogSuaSanPham) {
        ShowDialogThemSuaSP(
            title = "Sua SP",
            sanPham = sp,
            onDismiss = { showDialogSuaSanPham = false },
            onConfirm = {
                listSanphams = listSanphams.toMutableList().apply {
                    val index = listSanphams.indexOf(sp)

                    if (index != -1) {

                        set(index, sanPhamTemp.copy())
                    }
                }

                showDialogSuaSanPham = false
            })
    }



    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Quan ly san pham", style = MaterialTheme.typography.titleLarge)

        Button(onClick = {

            showDialogThemSanPham = true

//            val temps = listSanphams.toMutableList() // tao ban sao list
//            temps.add(SanPham(5, 9f, "SP 5", "mo ta 5", true)) // add du lieu
//
//            listSanphams = temps

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
                                    sp = it.copy()
                                    sanPhamTemp = it.copy()
                                    showDialogSuaSanPham = true
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
fun ShowDialogThemSuaSP(
    title: String,
    sanPham: SanPham? = null,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {

    var tenSanPham by remember {
        mutableStateOf("")
    }

    var giaSanPham by remember {
        mutableStateOf("")
    }

    var motaSanPham by remember {
        mutableStateOf("")
    }

    var statusSanPham by remember {
        mutableStateOf("")
    }

    if (sanPham != null) {
        tenSanPham = sanPham.name
        giaSanPham = sanPham.price.toString()
        motaSanPham = sanPham.description.toString()
        statusSanPham = sanPham.status.toString()
    }

    Dialog(onDismissRequest = { }) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    OutlinedTextField(
                        value = tenSanPham,
                        onValueChange = { tenSanPham = it },
                        label = { Text(text = "Ten SP") },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedTextField(
                        value = giaSanPham,
                        onValueChange = { giaSanPham = it },
                        label = { Text(text = "Gia SP") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    OutlinedTextField(
                        value = motaSanPham,
                        onValueChange = { motaSanPham = it },
                        label = { Text(text = "Mo ta") },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedTextField(
                        value = statusSanPham,
                        onValueChange = { statusSanPham = it },
                        label = { Text(text = "Trang thai") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Button(onClick = {

                        sanPhamTemp.apply {
                            name = tenSanPham
                            price = giaSanPham.toFloat()
                            description = motaSanPham
                            status = statusSanPham.toBoolean()
                        }

                        onConfirm()

//                        if (sanPham == null) { // them sp
//                            sanPhamTemp = SanPham(name = tenSanPham, price = giaSanPham.toFloat(), description = motaSanPham, status = statusSanPham.toBoolean())
//
////                            listSanphams = listSanphams.toMutableList().apply {
////                                add(newSP)
////                            }
//
//                        } else { // sua sp
//
//                            sanPhamTemp?.apply {
//                                name = tenSanPham
//                                price = giaSanPham.toFloat()
//                                description = motaSanPham
//                                status = statusSanPham.toBoolean()
//                            }
////                            listSanphams = listSanphams.toMutableList().apply {
////                                val index = this.indexOf(sanPham)
////                                this.get(index).apply {
////                                    name = tenSanPham
////                                    price = giaSanPham.toFloat()
////                                    description = motaSanPham
////                                    status = statusSanPham.toBoolean()
////                                }
////                            }
//
//                        }
                    }) {
                        Text(text = "Luu")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(onClick = onDismiss) {
                        Text(text = "Cancel")
                    }
                }

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