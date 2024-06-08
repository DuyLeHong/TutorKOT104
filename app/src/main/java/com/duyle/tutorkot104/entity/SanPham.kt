package com.duyle.tutorkot104.entity

data class SanPham (var id : Int, var price : Float, var name : String, var description : String?, var status: Boolean?){

    override fun toString(): String {
        return "SanPham(price=$price, name='$name', description=$description, status=$status)"
    }
}

fun getListSanpham () : List<SanPham> {
    val lists = mutableListOf<SanPham>()

    lists.add(SanPham(1, 10f, "San pham 1", "", true))
    lists.add(SanPham(2, 15f, "San pham 2", "", false))
    lists.add(SanPham(3, 12f, "San pham 3", "", false))
    lists.add(SanPham(4, 14f, "San pham 4", "", true))

    return lists
}